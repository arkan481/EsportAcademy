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
public class MemberFragment extends Fragment {

    LinearLayout lrmemberauto;

    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        lrmemberauto = v.findViewById(R.id.LRmemberauto);
        populatemember(container);
        return v;
    }
    private void populatemember(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.memberitem,container,false);
        lrmemberauto.addView(k);
    }
}
