package com.example.esportacademy.ui;

import android.Manifest;
import android.content.Context;
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
import androidx.viewpager.widget.PagerAdapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends Fragment  {

    private LinearLayout rlgames;
    private ImageView ivaddgamegeneral,ivrecruitment;
    private maketeaminterface maketeaminterface;
    private EditText etdesc;
    private ArrayList<EditText>editTexts= new ArrayList<>();
    private static final int PERMISSION_PICK_CODE=1001;
    private static final int IMAGE_PICK_CODE = 1000;

    public FragmentGeneral(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        etdesc= v.findViewById(R.id.etdescgen);
        maketeaminterface.setGenUpdating(true);
        rlgames = v.findViewById(R.id.LRgamesitem);
        ivrecruitment = v.findViewById(R.id.ivrecruitment);
        ivaddgamegeneral = v.findViewById(R.id.ivaddgamegeneral);
        ivaddgamegeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        if (maketeaminterface.getGenUpdating()==true) {
            etdesc.setText(maketeaminterface.getDesc());
            for (int i=0;i<maketeaminterface.getGames().size();i++) {
                addfirstview(container);
                editTexts.get(i).setText(maketeaminterface.getGames().get(i));
            }
            ivrecruitment.setImageURI(maketeaminterface.getTempRecruitmentImage());
        }else {
            addfirstview(container);
        }
        ivrecruitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery();
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("the paused");
        maketeaminterface.setDescription(etdesc.getText().toString());
        maketeaminterface.setGames(getgamesdata());
        maketeaminterface.setGenUpdating(true);
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
            ivrecruitment.setImageURI(uri);
            maketeaminterface.setTempRecruitmentImage(uri);
            maketeaminterface.setRecruitmentImage(getImageBLOB(ivrecruitment));
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

    private byte[] getImageBLOB(ImageView iv) {
        Drawable drawable = iv.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    private void pickImage() {
        Intent intentToGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery,IMAGE_PICK_CODE);
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.addgamesitem,container,false);
        EditText editText = k.findViewById(R.id.tvgamename);
        editTexts.add(editText);
        rlgames.addView(k,rlgames.getChildCount()-1);
    }
    private ArrayList<String> getgamesdata() {
        final ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<editTexts.size();i++) {
            datas.add(editTexts.get(i).getText().toString());
        }
        return datas;
    }

}
