package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.esportacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAchDet extends Fragment {

    private LinearLayout lrAch;

    public FragmentAchDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ach_det, container, false);
        lrAch = v.findViewById(R.id.LRachlistdet);
        populateAch(container);
        return v;
    }

    private void populateAch(ViewGroup container) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.achitemdet,container,false);
        lrAch.addView(v);
    }
}
