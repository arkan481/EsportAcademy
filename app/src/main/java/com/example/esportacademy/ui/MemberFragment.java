package com.example.esportacademy.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    private LinearLayout lrmemberauto;
    private ImageView ivbtnaddmember,ivmemphoto;
    private maketeaminterface maketeaminterface;
    private ArrayList<EditText>etmemnamearr = new ArrayList<>();
    private ArrayList<EditText>etmemdescarr = new ArrayList<>();
    private static final int PERMISSION_PICK_CODE=1001;
    private static final int IMAGE_PICK_CODE = 1000;

    public MemberFragment(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        lrmemberauto = v.findViewById(R.id.LRmemberauto);
        ivbtnaddmember = v.findViewById(R.id.btnaddmember);
        ivbtnaddmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        ivbtnaddmember.setVisibility(View.GONE); // here
        if (maketeaminterface.getMemberUpdating()==true) {
            for (int i=0;i<maketeaminterface.getMember().size();i++) {
                addfirstview(container);
                etmemnamearr.get(i).setText(maketeaminterface.getMember().get(i));
                etmemdescarr.get(i).setText(maketeaminterface.getMemberDesc().get(i));
            }
            ivmemphoto.setImageURI(maketeaminterface.getTempMemberPhoto());
        }else {
            addfirstview(container);
            maketeaminterface.setMemberUpdating(false);
        }
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        maketeaminterface.setMemberUpdating(true);
        maketeaminterface.setMember(getMemName());
        maketeaminterface.setMemberDesc(getMemDesc());
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.memberitem,container,false);
        EditText etmemname = k.findViewById(R.id.etmemnameid);
        EditText etmemdesc = k.findViewById(R.id.etmemdescid);
        ivmemphoto = k.findViewById(R.id.memphoto);
        ivmemphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery();
            }
        });
        etmemnamearr.add(etmemname);
        etmemdescarr.add(etmemdesc);
        lrmemberauto.addView(k);
    }

    private ArrayList<String> getMemName() {
        ArrayList<String>datas = new ArrayList<>();
        for (int i =0;i<etmemnamearr.size();i++) {
            datas.add(etmemnamearr.get(i).getText().toString());
        }
        return datas;
    }
    private ArrayList<String> getMemDesc() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<etmemdescarr.size();i++) {
            datas.add(etmemdescarr.get(i).getText().toString());
        }
        return datas;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_PICK_CODE:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    pickImage();
                }else {
                    Toast.makeText(getContext(),"Permission Denied",Toast.LENGTH_LONG);
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==getActivity().RESULT_OK&&requestCode==IMAGE_PICK_CODE) {
            Uri uri = data.getData();
            ivmemphoto.setImageURI(uri);
            maketeaminterface.setMemberPhoto(getImageBLOB(ivmemphoto)); // here the index of mem photo converted to be blob
            maketeaminterface.setTempMemberPhoto(uri);
        }else {
            Toast.makeText(getContext(),"You didn't set the image",Toast.LENGTH_LONG).show();
        }
    }

    private void prepareToGallery() {
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


    private byte[] getImageBLOB(ImageView iv) {
        Drawable drawable = iv.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP,50,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }


}
