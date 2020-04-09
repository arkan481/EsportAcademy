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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewGalleryAdapter;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private maketeaminterface maketeaminterface;
    private static final int PERMISSION_PICK_CODE=1001;
    private static final int IMAGE_PICK_CODE = 1000;
    private GridViewGalleryAdapter gridViewGalleryAdapter;
    private ImageView btnok;
    private GridView gvGall;
    private ArrayList<Uri> images = new ArrayList<>();

    public GalleryFragment(maketeaminterface maketeaminterface) {
        this.maketeaminterface=maketeaminterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        gvGall = v.findViewById(R.id.gvGallery);
        btnok = v.findViewById(R.id.btnok);
        if (maketeaminterface.getGallUpdating()==true) {
            images = maketeaminterface.getTempGalleryImages();
            populateGallery();
        }else {
            populateGallery();
        }
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (images.size()>5) {
                    Toast.makeText(getContext(),"You have inputted the maximum number of photo",Toast.LENGTH_SHORT).show();
                }else {
                    prepareToGallery();
                }

            }
        });
        return v;
    }

    private void populateGallery() {
        gridViewGalleryAdapter = new GridViewGalleryAdapter(this.images,getContext());
        gvGall.setAdapter(gridViewGalleryAdapter);
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
    public void onPause() {
        super.onPause();
        maketeaminterface.setGallUpdating(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==getActivity().RESULT_OK&&requestCode==IMAGE_PICK_CODE) {
            Uri uri = data.getData();
            images.add(uri);
            gridViewGalleryAdapter.notifyDataSetChanged();
            maketeaminterface.setTempGalleryImages(images);
        }else {
            Toast.makeText(getContext(),"You didn't set the image",Toast.LENGTH_LONG).show();
        }
    }

}
