package com.example.esportacademy.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.app.UserSessionManager;
import com.example.esportacademy.utils.Server;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private ImageView ivback,ivWhiteCam,profphoto;
    private ProgressDialog progressDialog,progressDialog2,progressDialog3;
    private EditText etname,etemail;
    private static final int PERMISSION_PICK_CODE=1001;
    private static final int IMAGE_PICK_CODE = 1000;
    private String photoString;
    private Button btnSave;
    private Uri uri;
    private TextView tvUn;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        tvUn = v.findViewById(R.id.tvunprof);
        profphoto = v.findViewById(R.id.profilephoto);
        btnSave = v.findViewById(R.id.btnsaveprof);
        progressDialog3 = new ProgressDialog(getContext());
        progressDialog2 = new ProgressDialog(getContext());
        etname = v.findViewById(R.id.etname);
        ivWhiteCam = v.findViewById(R.id.ivwhitecam);
        etemail = v.findViewById(R.id.etemail);
        progressDialog = new ProgressDialog(getContext());
        ivback = v.findViewById(R.id.ivbackprof);
        tvUn.setText(UserSessionManager.getInstance(getContext()).getUsername());
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
            }
        });
        ivWhiteCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().isEmpty()||etemail.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),"Please fill in all the requisition",Toast.LENGTH_SHORT).show();
                }else if(uri==null) {
                    Toast.makeText(getContext(),"please change your photo",Toast.LENGTH_SHORT).show();
                }
                else {
                    new ConvertImage(EditProfileFragment.this).execute();
                }
            }
        });
        checkDetails();
        return v;
    }

    private void checkDetails() {
        if (UserSessionManager.getInstance(getContext()).hasDetails()==true) {
            System.out.println("hoyy");
            etemail.setText(UserSessionManager.getInstance(getContext()).getUserEmail());
            etname.setText(UserSessionManager.getInstance(getContext()).getName());
            Picasso.get().invalidate(UserSessionManager.getInstance(getContext()).getUserPhoto());
            Picasso.get().load(UserSessionManager.getInstance(getContext()).getUserPhoto()).networkPolicy(NetworkPolicy.NO_CACHE).into(profphoto);
        }else {
            etemail.setText("");
            etname.setText("");
            profphoto.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_black_24dp));
        }
    }

    public String getImage(Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP,5,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return blobToString(bytes);
    }

    private String blobToString(byte[] bytes) {
        String image = Base64.encodeToString(bytes,Base64.DEFAULT);
        return image;
    }

    public void prepareToGallery() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }else {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_PICK_CODE);
            }
        }else {
            pickImage();
        }
    }

    private void pickImage() {
        Intent intentToGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_PICK_CODE:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    return;
                }else {
                    Toast.makeText(getContext(),"Permission Denied",Toast.LENGTH_LONG);
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==getActivity().RESULT_OK&&requestCode==IMAGE_PICK_CODE) {
            Uri uri = data.getData();
            profphoto.setImageURI(uri);
            this.uri = uri;
        }else {
            Toast.makeText(getContext(),"You didn't set the image",Toast.LENGTH_LONG).show();
        }
    }

    private void getUserInformation() {
        progressDialog3.setMessage("Getting Your Information");
        progressDialog3.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_USER_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog3.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                UserSessionManager.getInstance(getContext()).setDetails(data.getString("name"),data.getString("email"),data.getString("userphoto"));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog3.dismiss();
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userid",String.valueOf(UserSessionManager.getInstance(getContext()).getUserID()));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void insertUserInformation() {
        progressDialog.setMessage("Updating Your Information");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_INSERT_USER_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                                Toast.makeText(getContext(),"User Information Updated",Toast.LENGTH_SHORT).show();
                                getUserInformation();
                            }else {
                                Toast.makeText(getContext(),"Internal Server Error",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", String.valueOf(UserSessionManager.getInstance(getContext()).getUserID()));
                params.put("email",etemail.getText().toString());
                params.put("name",etname.getText().toString());
                params.put("photo",photoString);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private class ConvertImage extends AsyncTask<Void,Void,Void> {
        private EditProfileFragment editProfileFragment;
        public ConvertImage (EditProfileFragment editProfileFragment) {
            this.editProfileFragment = editProfileFragment;
        }

        @Override
        protected void onPreExecute() {
            editProfileFragment.progressDialog2.setMessage("Converting Your Photo");
            editProfileFragment.progressDialog2.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                photoString = editProfileFragment.getImage(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog2.dismiss();
            editProfileFragment.insertUserInformation();
        }
    }
}
