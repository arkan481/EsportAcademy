package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewGallDetAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GallFragmentDet extends Fragment {

    private GridViewGallDetAdapter gridViewGallDetAdapter;
    private GridView gridView;

    public GallFragmentDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gall_det, container, false);
        gridView = v.findViewById(R.id.GVgalldet);
        populateGridView();
        return v;
    }

    private void populateGridView() {
        gridViewGallDetAdapter = new GridViewGallDetAdapter(getContext());
        gridView.setAdapter(gridViewGallDetAdapter);
    }
}
