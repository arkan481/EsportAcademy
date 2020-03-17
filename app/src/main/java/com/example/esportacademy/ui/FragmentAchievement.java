package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAchievement extends Fragment {

    private LinearLayout lrach;
    private ImageView ivaddach;
    private maketeaminterface maketeaminterface;

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
        ivaddach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        addfirstview(container);
        return v;
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.achitem,container,false);
        lrach.addView(k);
    }
}
