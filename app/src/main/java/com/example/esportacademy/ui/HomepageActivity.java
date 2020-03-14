package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.models.TeamModel;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Button btnsearch;
    private ArrayList<TeamModel>teamModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        frameLayout = findViewById(R.id.FRhomeid);
        btnsearch = findViewById(R.id.btnsearchid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRhomeid,new FragmentHome());
        fragmentTransaction.commit();

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this,SearchTeamActivity.class);
                startActivity(intent);
            }
        });
    }
}
