package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;

public class TeamDetailActivity extends AppCompatActivity {

    private ImageView ivwlgen,ivwlach,ivwlgall,ivwlmem,ivbackbtn;
    private LinearLayout lrgen,lrach,lrgall,lrmem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ivwlgen = findViewById(R.id.ivwlgen);
        ivwlach = findViewById(R.id.ivwlach);
        ivwlgall = findViewById(R.id.ivwlgall);
        ivbackbtn = findViewById(R.id.IVbackbtndet);
        ivwlmem = findViewById(R.id.ivwlmem);
        lrgen = findViewById(R.id.LRgendet);
        lrach = findViewById(R.id.LRachdet);
        lrgall = findViewById(R.id.LRgalldet);
        lrmem = findViewById(R.id.LRmemdet);
        firstFragment();

        lrgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragment();
            }
        });
        lrach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragment();
            }
        });
        lrgall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdFragment();
            }
        });
        lrmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthFragment();
            }
        });
        ivbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void firstFragment() {
        turnWhiteLineOn(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frdetail,new GeneralDetFragment());
        fragmentTransaction.commit();
    }

    private void secondFragment() {
        turnWhiteLineOn(2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frdetail,new FragmentAchDet());
        fragmentTransaction.commit();
    }

    private void thirdFragment() {
        turnWhiteLineOn(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frdetail,new GallFragmentDet());
        fragmentTransaction.commit();
    }

    private void fourthFragment() {
        turnWhiteLineOn(4);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frdetail,new MemberFragmentDet());
        fragmentTransaction.commit();
    }

    private void turnWhiteLineOn(int i) {
        switch (i) {
            case 1:
                ivwlgen.setVisibility(View.VISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.VISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 3:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.VISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 4:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.VISIBLE);
                break;
        }
    }
}
