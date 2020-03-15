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
public class FragmentGeneral extends Fragment {

    private LinearLayout rlgames;

    public FragmentGeneral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        rlgames = v.findViewById(R.id.LRgamesitem);
        addfirstview(container);

        return v;
    }
    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.addgamesitem,container,false);
        rlgames.addView(k,rlgames.getChildCount()-1);
    }
}
