package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esportacademy.R;

public class TournamentActivity extends AppCompatActivity {

    private TextView tvOverView,tvParticipants,tvBracket,tvNewsTour;
    private View vOverView,vParticipants,vBracket,vNewsTour;
    private ImageView ivBackTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        ivBackTour = findViewById(R.id.ivBackTour);
        tvOverView = findViewById(R.id.tvOverView);
        tvParticipants = findViewById(R.id.tvParticipants);
        tvBracket = findViewById(R.id.tvBrackets);
        tvNewsTour = findViewById(R.id.tvNewsTour);
        vOverView = findViewById(R.id.vOverView);
        vParticipants = findViewById(R.id.vParticipants);
        vBracket = findViewById(R.id.vBracket);
        vNewsTour = findViewById(R.id.vNewsTour);
        turnGreenLight(1);

        tvOverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnGreenLight(1);
            }
        });

        tvParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnGreenLight(2);
            }
        });

        tvBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnGreenLight(3);
            }
        });

        tvNewsTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnGreenLight(4);
            }
        });

        ivBackTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void turnGreenLight(int i) {
        switch (i) {
            case 1:
                vOverView.setVisibility(View.VISIBLE);
                vParticipants.setVisibility(View.INVISIBLE);
                vBracket.setVisibility(View.INVISIBLE);
                vNewsTour.setVisibility(View.INVISIBLE);
                break;
            case 2:
                vOverView.setVisibility(View.INVISIBLE);
                vParticipants.setVisibility(View.VISIBLE);
                vBracket.setVisibility(View.INVISIBLE);
                vNewsTour.setVisibility(View.INVISIBLE);
                break;
            case 3:
                vOverView.setVisibility(View.INVISIBLE);
                vParticipants.setVisibility(View.INVISIBLE);
                vBracket.setVisibility(View.VISIBLE);
                vNewsTour.setVisibility(View.INVISIBLE);
                break;
            case 4:
                vOverView.setVisibility(View.INVISIBLE);
                vParticipants.setVisibility(View.INVISIBLE);
                vBracket.setVisibility(View.INVISIBLE);
                vNewsTour.setVisibility(View.VISIBLE);
                break;
            default:
                vOverView.setVisibility(View.VISIBLE);
                vParticipants.setVisibility(View.INVISIBLE);
                vBracket.setVisibility(View.INVISIBLE);
                vNewsTour.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
