package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.esportacademy.interfaces.maketeaminterface;
import com.example.esportacademy.utils.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateTeamActivity extends AppCompatActivity {

    private TextView tvcancelcreate;
    private ImageView ivcmot;
    private Spinner spinner;
    private ArrayAdapter<String>arrayAdapter;
    private String description;
    private ArrayList<String> games,achievement,achievementdesc,member,memberDesc;
    private EditText etnamecreate;
    private Uri tempRecruitment,tempAch,tempGalleryImages,tempMemPhoto,tempBG,tempIcon;
    private ProgressDialog progressDialog,progressDialog2;
    private String teamiconstring,teambgstring,achImageString,gallImageString,memPhotoString,rtsImageString;

    public CreateTeamActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("called 2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        final Intent intent =getIntent();
        member = intent.getStringArrayListExtra("membername");
        memberDesc = intent.getStringArrayListExtra("memberdesc");
        achievement = intent.getStringArrayListExtra("achname");
        achievementdesc = intent.getStringArrayListExtra("achdesc");
        this.games = intent.getStringArrayListExtra("games");
        description = intent.getStringExtra("teamdesc");
        progressDialog = new ProgressDialog(CreateTeamActivity.this);
        progressDialog2 = new ProgressDialog(CreateTeamActivity.this);
        tvcancelcreate = findViewById(R.id.tvcancelcreate);
        ivcmot = findViewById(R.id.ivcmotid);
        etnamecreate = findViewById(R.id.etteamnamecrate);
        spinner = findViewById(R.id.spinnercreate);
        etnamecreate.setText(intent.getStringExtra("teamname"));
        tvcancelcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arrayAdapter = new ArrayAdapter<String>(
                CreateTeamActivity.this,
                R.layout.customspinner,
                getResources().getStringArray(R.array.country)
        );
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        // Get The Uri Image
        tempRecruitment = intent.getParcelableExtra("recimage");
        tempAch = intent.getParcelableExtra("achimage");
        tempGalleryImages = intent.getParcelableExtra("gallimage");
        tempMemPhoto = intent.getParcelableExtra("memphoto");
        tempBG = intent.getParcelableExtra("teambg");
        tempIcon = intent.getParcelableExtra("teamlogo");

        ivcmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTeam();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        insertRts();
                        insertGames();
                        insertAch();
                        insertGall();
                        insertMember();
                    }
                },3000);

            }
        });
    }

    private void insertTeam() {
        try {
            progressDialog.setMessage("Creating Your Team...");
            progressDialog.show();
            // Converting The Images
            teamiconstring = getImage(tempIcon);
            teambgstring = getImage(tempBG);
            achImageString = getImage(tempAch);
            gallImageString = getImage(tempGalleryImages);
            memPhotoString = getImage(tempMemPhoto);
            rtsImageString = getImage(tempRecruitment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_TEAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                                Toast.makeText(CreateTeamActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CreateTeamActivity.this,HomepageActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(CreateTeamActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                        Toast.makeText(CreateTeamActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teamname",etnamecreate.getText().toString());
                params.put("teamdesc",description);
                params.put("teamlogo",teamiconstring);
                params.put("teambg",teambgstring);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertMember() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_MEMBER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("mem success");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("mem error "+ error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("memname",member.get(0));
                params.put("memdesc",memberDesc.get(0));
                params.put("memphoto",memPhotoString);
                return params;
            }
        };
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertGames() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_GAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("game ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("game error");
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("gamename",games.get(0));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);

    }

    private void insertGall() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_GALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("gall ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("gall error");
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("gallimage",gallImageString);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertAch() {
        StringRequest stringRequest =new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_ACH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("ach ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("ach error");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("achname",achievement.get(0));
                params.put("achdesc",achievementdesc.get(0));
                params.put("achimage",achImageString);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertRts() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_RTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                            }else {
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateTeamActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("rtsimage",rtsImageString);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    public String getImage(Uri uri) throws IOException {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.WEBP,5,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return blobToString(bytes);
    }

    private String blobToString(byte[] bytes) {
        String image = Base64.encodeToString(bytes,Base64.DEFAULT);
        return image;
    }

}
