package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.esportacademy.R;
import com.example.esportacademy.models.GameModel;

public class GameDetailActivity extends AppCompatActivity {

    private CardView cvNews,cvEvent,cvTour;
    private ImageView ivNews,ivEvent,ivTour,ivBack,ivGameHeader;
    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        Intent intent = getIntent();
        gameModel = intent.getParcelableExtra("gamemodel");
        cvNews = findViewById(R.id.cvNews);
        cvEvent = findViewById(R.id.cvEvent);
        ivBack = findViewById(R.id.ivbackbtngamedet);
        cvTour = findViewById(R.id.cvTour);
        ivNews = findViewById(R.id.ivNewsGame);
        ivEvent = findViewById(R.id.ivEventGame);
        ivTour = findViewById(R.id.ivTourGame);
        ivGameHeader = findViewById(R.id.ivGameHeader);
        ivGameHeader.setImageDrawable(getResources().getDrawable(gameModel.getGameHeader()));
        firstFragment();

        cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragment();
            }
        });
        cvEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragment();
            }
        });
        cvTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdFragment();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void firstFragment() {
        moveFragment(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentNews = new FragmentNews();
        Bundle bundle = new Bundle();
        bundle.putParcelable("gamemodel",gameModel);
        fragmentNews.setArguments(bundle);
        fragmentTransaction.replace(R.id.frnews,fragmentNews);
        fragmentTransaction.commit();
    }

    private void secondFragment() {
        moveFragment(2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentEvent = new FragmentEvent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("gamemodel",gameModel);
        fragmentEvent.setArguments(bundle);
        fragmentTransaction.replace(R.id.frnews,fragmentEvent);
        fragmentTransaction.commit();
    }

    private void thirdFragment() {
        moveFragment(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frnews,new FragmentTournament());
        fragmentTransaction.commit();
    }

    private void moveFragment(int i) {
        switch (i) {
            case 1:
                cvNews.setCardBackgroundColor(Color.BLACK);
                cvEvent.setCardBackgroundColor(Color.parseColor("#181818"));
                cvTour.setCardBackgroundColor(Color.parseColor("#181818"));
                ivNews.setVisibility(View.VISIBLE);
                ivEvent.setVisibility(View.INVISIBLE);
                ivTour.setVisibility(View.INVISIBLE);
                break;
            case 2:
                cvNews.setCardBackgroundColor(Color.parseColor("#181818"));
                cvEvent.setCardBackgroundColor(Color.BLACK);
                cvTour.setCardBackgroundColor(Color.parseColor("#181818"));
                ivNews.setVisibility(View.INVISIBLE);
                ivEvent.setVisibility(View.VISIBLE);
                ivTour.setVisibility(View.INVISIBLE);
                break;
            case 3:
                cvNews.setCardBackgroundColor(Color.parseColor("#181818"));
                cvEvent.setCardBackgroundColor(Color.parseColor("#181818"));
                cvTour.setCardBackgroundColor(Color.BLACK);
                ivNews.setVisibility(View.INVISIBLE);
                ivEvent.setVisibility(View.INVISIBLE);
                ivTour.setVisibility(View.VISIBLE);
                break;
        }
    }
}
