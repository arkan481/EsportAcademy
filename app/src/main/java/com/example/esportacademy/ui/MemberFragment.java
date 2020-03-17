package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    private LinearLayout lrmemberauto;
    private ImageView ivbtnaddmember;
    private maketeaminterface maketeaminterface;

    public MemberFragment(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        lrmemberauto = v.findViewById(R.id.LRmemberauto);
        ivbtnaddmember = v.findViewById(R.id.btnaddmember);
        ivbtnaddmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populatemember(container);
            }
        });
        populatemember(container);
        return v;
    }
    private void populatemember(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.memberitem,container,false);
        lrmemberauto.addView(k);
    }
}
