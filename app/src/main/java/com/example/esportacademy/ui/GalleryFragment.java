package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewGalleryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private GridViewGalleryAdapter gridViewGalleryAdapter;
    private GridView gridView;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = v.findViewById(R.id.gvgallery);
        populategridview();
        return v;
    }

    private void populategridview() {
        gridViewGalleryAdapter = new GridViewGalleryAdapter(getContext());
        gridView.setAdapter(gridViewGalleryAdapter);
    }
}
