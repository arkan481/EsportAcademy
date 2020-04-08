package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class SearchTeamActivity extends AppCompatActivity {

    private ArrayList<TeamModel>teamModels= new ArrayList<>();
    private GridView gvteamfound;
    private GridViewTeamFoundAdapter gridViewTeamFoundAdapter;
    private Button btnmaketeam;
    private ImageView ivbackbutton;
    private TextView etsearch;
    private ProgressDialog progressDialog;
    private String teamname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_team);
        Intent intent = getIntent();
        teamname = intent.getStringExtra("search");
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
            etsearch.setText(teamname);
        }
        etsearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                etsearch.setSingleLine();
                if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    System.out.println("haii");
                    refreshData(etsearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
        refreshData(this.teamname);
    }

    private void refreshData(String teamname) {
        getSeachedTeam(teamname);
        gridViewTeamFoundAdapter.notifyDataSetChanged();
        gvteamfound.invalidateViews();
    }

    private void getSeachedTeam(final String teamname) {
        teamModels.clear();
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_BY_NAME,
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
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teamname",teamname);
                return params;
            }
        };
        RequestHandler.getInstance(SearchTeamActivity.this).addToRequestQueue(stringRequest);
    }

//    private void getAllTeamData() {
//        progressDialog.setMessage("Please Wait...");
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
//                Server.URL_GET_ALLTEAM,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject object = new JSONObject(response);
//                            JSONArray jsonArray = object.getJSONArray("data");
//                            for (int i=0;i<jsonArray.length();i++) {
//                                JSONObject data = jsonArray.getJSONObject(i);
//                                teamModels.add(new TeamModel(data.getString("id"),data.getString("bglink"),data.getString("logolink"),data.getString("teamname"),data.getString("teamdesc")));
//                                gridViewTeamFoundAdapter.notifyDataSetChanged();
//                            }
//                        }catch (JSONException e) {
//                            System.out.println("exception e"+e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        System.out.println("error "+error.getMessage());
//                    }
//                }
//        );
//        RequestHandler.getInstance(SearchTeamActivity.this).addToRequestQueue(stringRequest);
//
//    }


}
