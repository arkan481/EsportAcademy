package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.app.UserSessionManager;
import com.example.esportacademy.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TeamDetailActivity extends AppCompatActivity {

    private ImageView ivwlgen,ivwlach,ivwlgall,ivwlmem,ivbackbtn,ivTeamBg,ivTeamLogo;
    private TextView tvTeamName;
    private LinearLayout lrgen,lrach,lrgall,lrmem;
    private String teamname,logolink,bglink,teamdesc,teamid;
    private Button btnJoin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        Intent intent = getIntent();
        this.teamname = intent.getStringExtra("teamname");
        this.teamdesc = intent.getStringExtra("teamdesc");
        this.teamid = intent.getStringExtra("teamid");
        this.logolink = intent.getStringExtra("teamlogo");
        this.bglink = intent.getStringExtra("teambg");
        progressDialog = new ProgressDialog(TeamDetailActivity.this);
        btnJoin = findViewById(R.id.btnjoin);
        ivTeamBg = findViewById(R.id.ivteambgdetail);
        tvTeamName = findViewById(R.id.tvteamnamedet);
        ivTeamLogo = findViewById(R.id.ivteamlogodet);
        ivwlgen = findViewById(R.id.ivwlgen);
        ivwlach = findViewById(R.id.ivwlach);
        ivwlgall = findViewById(R.id.ivwlgall);
        ivbackbtn = findViewById(R.id.IVbackbtndet);
        ivwlmem = findViewById(R.id.ivwlmem);
        lrgen = findViewById(R.id.LRgendet);
        lrach = findViewById(R.id.LRachdet);
        lrgall = findViewById(R.id.LRgalldet);
        lrmem = findViewById(R.id.LRmemdet);
        loadTeam();
        firstFragment();
        lrgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragment();
            }
        });
        lrach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragment();
            }
        });
        lrgall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdFragment();
            }
        });
        lrmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthFragment();
            }
        });
        ivbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinTeam();
            }
        });
    }

    private void loadTeam() {
        Picasso.get().load(bglink).into(ivTeamBg);
        Picasso.get().load(logolink).into(ivTeamLogo);
        tvTeamName.setText(teamname);
    }

    private void joinTeam() {
        progressDialog.setMessage("Joining Team");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_INSERT_JOIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(TeamDetailActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(TeamDetailActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userid", String.valueOf(UserSessionManager.getInstance(TeamDetailActivity.this).getUserID()));
                params.put("teamid",teamid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(TeamDetailActivity.this).addToRequestQueue(stringRequest);
    }


    private void firstFragment() {
        turnWhiteLineOn(1);
        Bundle bundle = new Bundle();
        bundle.putString("teamdesc",teamdesc);
        bundle.putString("teamid",teamid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment genFragment = new GeneralDetFragment();
        genFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frdetail,genFragment);
        fragmentTransaction.commit();
    }

    private void secondFragment() {
        turnWhiteLineOn(2);
        Bundle bundle = new Bundle();
        bundle.putString("teamid",teamid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment achFrag = new FragmentAchDet();
        achFrag.setArguments(bundle);
        fragmentTransaction.replace(R.id.frdetail,achFrag);
        fragmentTransaction.commit();
    }

    private void thirdFragment() {
        turnWhiteLineOn(3);
        Bundle bundle = new Bundle();
        bundle.putString("teamid",teamid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment gallFrag = new GallFragmentDet();
        gallFrag.setArguments(bundle);
        fragmentTransaction.replace(R.id.frdetail,gallFrag);
        fragmentTransaction.commit();
    }

    private void fourthFragment() {
        turnWhiteLineOn(4);
        Bundle bundle = new Bundle();
        bundle.putString("teamid",teamid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new MemberFragmentDet();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frdetail,fragment);
        fragmentTransaction.commit();
    }

    private void turnWhiteLineOn(int i) {
        switch (i) {
            case 1:
                ivwlgen.setVisibility(View.VISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.VISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 3:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.VISIBLE);
                ivwlmem.setVisibility(View.INVISIBLE);
                break;
            case 4:
                ivwlgen.setVisibility(View.INVISIBLE);
                ivwlach.setVisibility(View.INVISIBLE);
                ivwlgall.setVisibility(View.INVISIBLE);
                ivwlmem.setVisibility(View.VISIBLE);
                break;
        }
    }
}
