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
public class FragmentAchievement extends Fragment {

    private LinearLayout lrach;
    private ImageView ivaddach,ivachimage;
    private maketeaminterface maketeaminterface;
    private ArrayList<EditText> achnameedittexts = new ArrayList<>();
    private ArrayList<EditText> achdescedittexts = new ArrayList<>();
    private static final int PERMISSION_PICK_CODE=1001;
    private static final int IMAGE_PICK_CODE = 1000;

    public FragmentAchievement(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);
        lrach = v.findViewById(R.id.LRachid);
        ivaddach = v.findViewById(R.id.ivaddach);
        ivaddach.setVisibility(View.GONE);
        ivaddach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        if (maketeaminterface.getAchUpdating()==true) {
            for (int i=0;i<maketeaminterface.getAchievement().size();i++) {
                addfirstview(container);
                achnameedittexts.get(i).setText(maketeaminterface.getAchievement().get(i));
                achdescedittexts.get(i).setText(maketeaminterface.getAchDesc().get(i));
                ivachimage.setImageURI(maketeaminterface.getTempAchImage());
                ivachimage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }else {
            maketeaminterface.setAchUpdating(false);
            addfirstview(container);
        }
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        maketeaminterface.setAchUpdating(true);
        maketeaminterface.setAchievement(getAchNameData());
        maketeaminterface.setaAhievementDesc(getAchDescData());
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.achitem,container,false);
        EditText etachname = k.findViewById(R.id.etachname);
        ivachimage = k.findViewById(R.id.ivachimage);
        EditText etachdesc = k.findViewById(R.id.etachdesc);
        achnameedittexts.add(etachname);
        achdescedittexts.add(etachdesc);
        ivachimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery();
            }
        });
        lrach.addView(k);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_PICK_CODE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
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
            ivachimage.setImageURI(uri);
            ivachimage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            maketeaminterface.setTempAchImage(uri);
            maketeaminterface.setAchImage(getImageBLOB(ivachimage));
        }else {
            Toast.makeText(getContext(),"You didn't set the image",Toast.LENGTH_LONG).show();
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
        bitmap.compress(Bitmap.CompressFormat.WEBP,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    private ArrayList<String> getAchNameData() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<achnameedittexts.size();i++) {
            datas.add(achnameedittexts.get(i).getText().toString());
        }
        return datas;
    }

    private ArrayList<String> getAchDescData() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<achdescedittexts.size();i++) {
            datas.add(achdescedittexts.get(i).getText().toString());
        }
        return datas;
    }

}
