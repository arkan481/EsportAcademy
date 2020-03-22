package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.interfaces.maketeaminterface;
import com.example.esportacademy.utils.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateTeamActivity extends AppCompatActivity {

    private TextView tvcancelcreate;
    private ImageView ivcmot;
    private Spinner spinner;
    private ArrayAdapter<String>arrayAdapter;
    private String description;
    private maketeaminterface maketeaminterface;
    private ArrayList<String> games,achievement,achievementdesc,member,memberDesc;
    private EditText etnamecreate;
    private byte[] recruitmentImage,teamicon,teambg;
    private ProgressDialog progressDialog;

    public CreateTeamActivity() {

    }

    public CreateTeamActivity(maketeaminterface maketeaminterface) {
        this.maketeaminterface=maketeaminterface;
        this.games=this.maketeaminterface.getGames();
        this.achievement=this.maketeaminterface.getAchievement();
        this.achievementdesc=this.maketeaminterface.getAchDesc();
        this.member=this.maketeaminterface.getMember();
        this.memberDesc=this.maketeaminterface.getMemberDesc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        final Intent intent =getIntent();
        teamicon = intent.getByteArrayExtra("teamicon");
        teambg = intent.getByteArrayExtra("teambg");
        recruitmentImage = intent.getByteArrayExtra("recimage");
        description = intent.getStringExtra("teamdesc");
        progressDialog = new ProgressDialog(CreateTeamActivity.this);
        tvcancelcreate = findViewById(R.id.tvcancelcreate);
        ivcmot = findViewById(R.id.ivcmotid);
        etnamecreate = findViewById(R.id.etteamnamecrate);
        spinner = findViewById(R.id.spinnercreate);
        etnamecreate.setText(intent.getStringExtra("teamname"));
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
        ivcmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTeam();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        insertRts();
                    }
                },1000);
            }
        });
    }

    private void insertTeam() {
        progressDialog.setMessage("Creating Your Team...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_TEAM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                                Toast.makeText(CreateTeamActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CreateTeamActivity.this,HomepageActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(CreateTeamActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateTeamActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teamname",etnamecreate.getText().toString());
                params.put("teamlogo",teamicon.toString());
                params.put("teambg",teambg.toString());
                params.put("teamdesc",description);
                return params;
            }
        };
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

    private void insertRts() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_RTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                            }else {
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateTeamActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("rtsimage",recruitmentImage.toString());
                return params;
            }
        };
        RequestHandler.getInstance(CreateTeamActivity.this).addToRequestQueue(stringRequest);
    }

}
