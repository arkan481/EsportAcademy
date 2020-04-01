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
public class GeneralDetFragment extends Fragment {

    private LinearLayout lrGameDet;

    public GeneralDetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general_det, container, false);
        lrGameDet = v.findViewById(R.id.LRgamesdet);
        populateGame(container);
        return v;
    }

    private void populateGame(ViewGroup container) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.itemgamedetail,container,false);
        lrGameDet.addView(v);
    }

}
