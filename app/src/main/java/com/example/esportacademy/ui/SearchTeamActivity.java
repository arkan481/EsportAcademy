package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewTeamFoundAdapter;
import com.example.esportacademy.models.TeamModel;

import java.util.ArrayList;

public class SearchTeamActivity extends AppCompatActivity {

    private ArrayList<TeamModel>teamModels;
    private GridView gvteamfound;
    private GridViewTeamFoundAdapter gridViewTeamFoundAdapter;
    private Button btnmaketeam;
    private ImageView ivbackbutton;
    private TextView etsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_team);
        Intent intent = getIntent();
       etsearch = findViewById(R.id.etsearchgamess);
        gvteamfound = findViewById(R.id.gvteamfoundid);
        btnmaketeam = findViewById(R.id.btnmaketeamid);
        ivbackbutton = findViewById(R.id.ivbackbuttonsearch);

        ivbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnmaketeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchTeamActivity.this,MakeTeamActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (intent.getStringExtra("search")!=null) {
            etsearch.setText(intent.getStringExtra("search"));
        }
        populateteam();

    }
    private void populateteam() {
        teamModels=new ArrayList<>();
        teamModels.add(new TeamModel(R.drawable.c9backplayer,R.drawable.c9backplayer2,R.drawable.c9backplayer3,R.drawable.c9backplayer4,R.drawable.cloud9logo,"Cloud 9"));
        gridViewTeamFoundAdapter = new GridViewTeamFoundAdapter(teamModels,SearchTeamActivity.this);
        gvteamfound.setAdapter(gridViewTeamFoundAdapter);
    }
}
