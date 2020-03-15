package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.esportacademy.R;

public class MakeTeamActivity extends AppCompatActivity {

    private LinearLayout lrgen,lrach,lrgall,lrmem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        lrgen = findViewById(R.id.LRgenmtid);
        lrach = findViewById(R.id.LRachhmtid);
        lrgall = findViewById(R.id.LRgallerymtid);
        lrmem = findViewById(R.id.LRmembemtid);
        firstfragment();
        lrgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstfragment();
            }
        });
        lrach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondfragment();
            }
        });
        lrgall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdfragment();
            }
        });
        lrmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthfragment();
            }
        });


    }
    private void firstfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentGeneral());
        fragmentTransaction.commit();
    }
    private void secondfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentAchievement());
        fragmentTransaction.commit();
    }
    private void thirdfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new GalleryFragment());
        fragmentTransaction.commit();
    }
    private void fourthfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new MemberFragment());
        fragmentTransaction.commit();
    }
}
