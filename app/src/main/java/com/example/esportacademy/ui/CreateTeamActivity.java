package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

public class CreateTeamActivity extends AppCompatActivity {

    private TextView tvcancelcreate;
    private Spinner spinner;
    private ArrayAdapter<String>arrayAdapter;
    private String description,games,achievement,achievementdesc,member;
    private maketeaminterface maketeaminterface;

    public CreateTeamActivity() {

    }

    public CreateTeamActivity(maketeaminterface maketeaminterface) {
        this.maketeaminterface=maketeaminterface;
        this.description=this.maketeaminterface.getDesc();
        this.games=this.maketeaminterface.getGames();
        this.achievement=this.maketeaminterface.getAchievement();
        this.achievementdesc=this.maketeaminterface.getAchDesc();
        this.member=this.maketeaminterface.getMember();
        System.out.println("testa"+this.description);
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
