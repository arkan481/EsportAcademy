package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;

public class MakeTeamActivity extends AppCompatActivity {

    private LinearLayout lrgen,lrach,lrgall,lrmem;
    private ImageView ivbackbutton,ivline1,ivline2,ivline3,ivline4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        lrgen = findViewById(R.id.LRgenmtid);
        lrach = findViewById(R.id.LRachhmtid);
        lrgall = findViewById(R.id.LRgallerymtid);
        lrmem = findViewById(R.id.LRmembemtid);
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
        ivbackbutton = findViewById(R.id.ivbackbuttonmt);
        ivline1 = findViewById(R.id.ivline1);
        ivline2 = findViewById(R.id.ivline2);
        ivline3 = findViewById(R.id.ivline3);
        ivline4 = findViewById(R.id.ivline4);
        firstfragment();

        ivbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void turnlineon(int i) {
        switch (i) {
            case 1:
                ivline1.setVisibility(View.VISIBLE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
            case 2:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.VISIBLE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
            case 3:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.VISIBLE);
                ivline4.setVisibility(View.GONE);
                break;
            case 4:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.VISIBLE);
                break;
            default:
                ivline1.setVisibility(View.VISIBLE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
        }
    }
    private void firstfragment() {
        turnlineon(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentGeneral(),"general");
        fragmentTransaction.commit();
    }
    private void secondfragment() {
        turnlineon(2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentAchievement());
        fragmentTransaction.commit();
    }
    private void thirdfragment() {
        turnlineon(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new GalleryFragment());
        fragmentTransaction.commit();
    }
    private void fourthfragment() {
        turnlineon(4);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new MemberFragment());
        fragmentTransaction.commit();
    }

}
