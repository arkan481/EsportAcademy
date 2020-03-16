package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.models.TeamModel;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Button btnsearch;
    private ArrayList<TeamModel>teamModels;
    private RelativeLayout rlhome,rlbookmark,rltrophy,rlprofile;
    private ImageView ivlamp1,ivlamp2,ivlamp3,ivlamp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        frameLayout = findViewById(R.id.FRhomeid);
        btnsearch = findViewById(R.id.btnsearchid);
        rlhome = findViewById(R.id.rlhomeicon);
        rlbookmark = findViewById(R.id.rlbookmarkicon);
        rltrophy = findViewById(R.id.rltrophyicon);
        rlprofile = findViewById(R.id.rlprofileicon);
        ivlamp1 = findViewById(R.id.ivlamp1);
        ivlamp2 = findViewById(R.id.ivlamp2);
        ivlamp3 = findViewById(R.id.ivlamp3);
        ivlamp4 = findViewById(R.id.ivlamp4);
        firstfragment();

        rlprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnlampon(4);
            }
        });
        rltrophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnlampon(3);
            }
        });
        rlbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to fragment
                turnlampon(2);
            }
        });
        rlhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstfragment();
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this,SearchTeamActivity.class);
                startActivity(intent);
            }
        });
    }

    private void firstfragment() {
        turnlampon(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRhomeid,new FragmentHome());
        fragmentTransaction.commit();
    }
    private void turnlampon(int i) {
        switch (i) {
            case 1:
                ivlamp1.setVisibility(View.VISIBLE);
                ivlamp2.setVisibility(View.GONE);
                ivlamp3.setVisibility(View.GONE);
                ivlamp4.setVisibility(View.GONE);
                break;
            case 2:
                ivlamp1.setVisibility(View.GONE);
                ivlamp2.setVisibility(View.VISIBLE);
                ivlamp3.setVisibility(View.GONE);
                ivlamp4.setVisibility(View.GONE);
                break;
            case 3:
                ivlamp1.setVisibility(View.GONE);
                ivlamp2.setVisibility(View.GONE);
                ivlamp3.setVisibility(View.VISIBLE);
                ivlamp4.setVisibility(View.GONE);
                break;
            case 4:
                ivlamp1.setVisibility(View.GONE);
                ivlamp2.setVisibility(View.GONE);
                ivlamp3.setVisibility(View.GONE);
                ivlamp4.setVisibility(View.VISIBLE);
                break;
            default:
                ivlamp1.setVisibility(View.VISIBLE);
                ivlamp2.setVisibility(View.GONE);
                ivlamp3.setVisibility(View.GONE);
                ivlamp4.setVisibility(View.GONE);
                break;
        }
    }
}
