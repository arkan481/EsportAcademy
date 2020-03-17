package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

public class MakeTeamActivity extends AppCompatActivity implements maketeaminterface {

    private LinearLayout lrgen,lrach,lrgall,lrmem;
    private ImageView ivbackbutton,ivline1,ivline2,ivline3,ivline4,ivcreatebtn;
    private String description,games,achievement,achievementdesc,member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        lrgen = findViewById(R.id.LRgenmtid);
        lrach = findViewById(R.id.LRachhmtid);
        lrgall = findViewById(R.id.LRgallerymtid);
        lrmem = findViewById(R.id.LRmembemtid);
        ivcreatebtn = findViewById(R.id.ivcreatebtn);
        ivcreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeTeamActivity.this,CreateTeamActivity.class);
                new CreateTeamActivity(MakeTeamActivity.this);
                startActivity(intent);
            }
        });
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
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentGeneral(this),"general");
        fragmentTransaction.commit();
    }
    private void secondfragment() {
        turnlineon(2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentAchievement(this));
        fragmentTransaction.commit();
    }
    private void thirdfragment() {
        turnlineon(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new GalleryFragment(this));
        fragmentTransaction.commit();
    }
    private void fourthfragment() {
        turnlineon(4);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new MemberFragment(this));
        fragmentTransaction.commit();
    }

    @Override
    public void setDescription(String desc) {
        this.description=desc;
    }

    @Override
    public void setGames(String games) {
        this.games=games;
    }

    @Override
    public void setAchievement(String ach) {
        this.achievement=ach;
    }

    @Override
    public void setaAhievementDesc(String achDesc) {
        this.achievementdesc=achDesc;
    }

    @Override
    public void setMember(String member) {
        this.member=member;
    }

    @Override
    public String getDesc() {
        return this.description;
    }

    @Override
    public String getGames() {
        return this.games;
    }

    @Override
    public String getAchievement() {
        return this.achievement;
    }

    @Override
    public String getAchDesc() {
        return this.achievementdesc;
    }

    @Override
    public String getMember() {
        return this.member;
    }
}
