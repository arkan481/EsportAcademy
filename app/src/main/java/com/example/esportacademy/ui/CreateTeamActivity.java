package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.util.ArrayList;

public class CreateTeamActivity extends AppCompatActivity {

    private TextView tvcancelcreate;
    private Spinner spinner;
    private ArrayAdapter<String>arrayAdapter;
    private String description;
    private maketeaminterface maketeaminterface;
    private ArrayList<String> games,achievement,achievementdesc,member,memberDesc;

    public CreateTeamActivity() {

    }

    public CreateTeamActivity(maketeaminterface maketeaminterface) {
        this.maketeaminterface=maketeaminterface;
        this.description=this.maketeaminterface.getDesc();
        this.games=this.maketeaminterface.getGames();
        this.achievement=this.maketeaminterface.getAchievement();
        this.achievementdesc=this.maketeaminterface.getAchDesc();
        this.member=this.maketeaminterface.getMember();
        this.memberDesc=this.maketeaminterface.getMemberDesc();
        System.out.println("the desc "+this.description);
        for (int i=0;i<games.size();i++) {
            System.out.println("the games "+games.get(i));
        }
        for (int i=0;i<achievement.size();i++) {
            System.out.println("The ach "+achievement.get(i));
            System.out.println("The ach desc "+achievementdesc.get(i));
        }
        for (int i =0;i<member.size();i++) {
            System.out.println("The member name "+member.get(i));
            System.out.println("The member desc "+memberDesc.get(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        tvcancelcreate = findViewById(R.id.tvcancelcreate);
        spinner = findViewById(R.id.spinnercreate);
        tvcancelcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arrayAdapter = new ArrayAdapter<String>(
                CreateTeamActivity.this,
                R.layout.customspinner,
                getResources().getStringArray(R.array.country)
        );
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

}
