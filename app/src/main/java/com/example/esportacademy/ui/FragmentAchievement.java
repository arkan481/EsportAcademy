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
public class FragmentAchievement extends Fragment {

    LinearLayout lrach;

    public FragmentAchievement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);
        lrach = v.findViewById(R.id.LRachid);
        addfirstview(container);
        return v;
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.achitem,container,false);
        lrach.addView(k);
    }
}
