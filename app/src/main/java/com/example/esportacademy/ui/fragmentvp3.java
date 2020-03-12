package com.example.esportacademy.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.esportacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentvp3 extends Fragment {

    private Button buttonlogin,buttonsignup;

    public fragmentvp3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmentvp3, container, false);
        buttonlogin = v.findViewById(R.id.btnlgnvp3);
        buttonsignup = v.findViewById(R.id.btnsignupvp3);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
