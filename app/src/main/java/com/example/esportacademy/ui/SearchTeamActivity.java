package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewTeamFoundAdapter;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.models.TeamModel;
import com.example.esportacademy.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchTeamActivity extends AppCompatActivity {

    private ArrayList<TeamModel>teamModels= new ArrayList<>();
    private GridView gvteamfound;
    private GridViewTeamFoundAdapter gridViewTeamFoundAdapter;
    private Button btnmaketeam;
    private ImageView ivbackbutton;
    private TextView etsearch;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_team);
        Intent intent = getIntent();
        progressDialog = new ProgressDialog(SearchTeamActivity.this);
        etsearch = findViewById(R.id.etsearchgamess);
        gvteamfound = findViewById(R.id.gvteamfoundid);
        btnmaketeam = findViewById(R.id.btnmaketeamid);
        ivbackbutton = findViewById(R.id.ivbackbuttonsearch);
        gridViewTeamFoundAdapter = new GridViewTeamFoundAdapter(teamModels,SearchTeamActivity.this);
        gvteamfound.setAdapter(gridViewTeamFoundAdapter);

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

        refreshData();

    }

    private void refreshData() {
        getAllTeamData();
        gridViewTeamFoundAdapter.notifyDataSetChanged();
        gvteamfound.invalidateViews();
    }

    private void getAllTeamData() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                Server.URL_GET_ALLTEAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                teamModels.add(new TeamModel(data.getString("id"),data.getString("bglink"),data.getString("logolink"),data.getString("teamname"),data.getString("teamdesc")));
                                gridViewTeamFoundAdapter.notifyDataSetChanged();
                            }
                        }catch (JSONException e) {
                            System.out.println("exception e"+e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        System.out.println("error "+error.getMessage());
                    }
                }
        );
        RequestHandler.getInstance(SearchTeamActivity.this).addToRequestQueue(stringRequest);

    }


}
