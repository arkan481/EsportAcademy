package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends Fragment {

    private LinearLayout rlgames;
    private ImageView ivaddgamegeneral;

    public FragmentGeneral() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        setRetainInstance(true);
        rlgames = v.findViewById(R.id.LRgamesitem);
        ivaddgamegeneral = v.findViewById(R.id.ivaddgamegeneral);
        ivaddgamegeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        addfirstview(container);

        return v;
    }
    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.addgamesitem,container,false);
        rlgames.addView(k,rlgames.getChildCount()-1);
    }
}
