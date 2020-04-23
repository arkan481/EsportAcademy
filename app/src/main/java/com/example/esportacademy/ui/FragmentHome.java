package com.example.esportacademy.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVGamesAdapter;
import com.example.esportacademy.adapters.RVProplayerAdapter;
import com.example.esportacademy.adapters.VPHomeAutoAdapter;
import com.example.esportacademy.adapters.ViewPagerAutoAdapter;
import com.example.esportacademy.adapters.ViewPagerGamesAdapter;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.models.GameModel;
import com.example.esportacademy.models.NewsModel;
import com.example.esportacademy.models.ProPlayerModel;
import com.example.esportacademy.models.TeamModel;
import com.example.esportacademy.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private RecyclerView rvgameshome,rvproplayerhome;

    private ArrayList<GameModel> gameModels;
    private CardView fabhome;
    private RecyclerView.Adapter rvadapter;
    private ArrayList<TeamModel> teamModels = new ArrayList<>();
    private RVGamesAdapter rvGamesAdapter;
    private Button btnsearch;
    private ArrayList<ProPlayerModel>proPlayerModels;
    private RVProplayerAdapter rvProplayerAdapter;
    private ViewPager autoviewpager;
    private ProgressDialog progressDialog;
    private ArrayList<NewsModel> newsModels;
    private VPHomeAutoAdapter vpHomeAutoAdapter;
    private int currentnewspos;
    private Timer timer;
    private TextView etsearchgames;
    private ImageView circle1,circle2,circle3,circle4,teamIcon1,teamIcon2,teamIcon3,teamIcon4,ivCross,ivadd,ivaddteam,ivaddTour;
    private View separator;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        fabhome = v.findViewById(R.id.fabhome);
        progressDialog = new ProgressDialog(getContext());
        separator = v.findViewById(R.id.viewsep);
        ivCross = v.findViewById(R.id.ivcross);
        ivaddteam = v.findViewById(R.id.ivaddteam);
        ivaddTour = v.findViewById(R.id.ivaddtour);
        ivadd = v.findViewById(R.id.ivaddwhtever);
        teamIcon1 = v.findViewById(R.id.TeamIcon1);
        teamIcon2 = v.findViewById(R.id.TeamIcon2);
        teamIcon3 = v.findViewById(R.id.TeamIcon3);
        teamIcon4 = v.findViewById(R.id.TeamIcon4);
        rvgameshome = v.findViewById(R.id.rvgameshomeid);
        rvproplayerhome = v.findViewById(R.id.rvproplayerid);
        autoviewpager = v.findViewById(R.id.vphome1id);
        circle1 = v.findViewById(R.id.circlewhite1);
        btnsearch = v.findViewById(R.id.btnsearchid);
        etsearchgames = v.findViewById(R.id.etsearchgames);
        circle2 = v.findViewById(R.id.circlewhite2);
        circle3 = v.findViewById(R.id.circlewhite3);
        circle4 = v.findViewById(R.id.circlewhite4);
        populategamemodel();
        populateproplayermodel();
        populatenews();
        newsslideshow();
        fabHide();
        ivaddteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MakeTeamActivity.class);
                startActivity(intent);
            }
        });

        ivaddTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"This feature is currently unavailable",Toast.LENGTH_SHORT).show();

            }
        });

        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHide();
            }
        });

        ivadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabShow();
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etsearchgames.getText().toString().isEmpty()||etsearchgames.getText().toString().equals(" ")) {
                    Toast.makeText(getContext(),"Please input the team name",Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(getContext(),SearchTeamActivity.class);
                    intent.putExtra("search",etsearchgames.getText().toString());
                    startActivity(intent);
                }

            }
        });
        etsearchgames.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.KEYCODE_ENTER||event.getAction()==KeyEvent.ACTION_DOWN) {
                    if (etsearchgames.getText().toString().isEmpty()||etsearchgames.getText().toString().equals(" ")) {
                        Toast.makeText(getContext(),"Please input the team name",Toast.LENGTH_SHORT).show();
                    }else {
                        etsearchgames.setSingleLine();
                        Intent intent = new Intent(getContext(),SearchTeamActivity.class);
                        intent.putExtra("search",etsearchgames.getText().toString());
                        startActivity(intent);
                        return true;
                    }

                }
                return false;
            }
        });
        populateTeam();
        teamIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTeam1();
            }
        });

        teamIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTeam2();
            }
        });

        teamIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTeam3();
            }
        });

        teamIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTeam4();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        etsearchgames.setText("");
    }

    private void intentTeam1() {
        Intent intent = new Intent(getContext(), TeamDetailActivity.class);
        intent.putExtra("teambg",teamModels.get(0).getBglink());
        intent.putExtra("teamlogo",teamModels.get(0).getLogolink());
        intent.putExtra("teamname",teamModels.get(0).getTeamname());
        intent.putExtra("teamdesc",teamModels.get(0).getDesc());
        intent.putExtra("teamid",teamModels.get(0).getId());
        getContext().startActivity(intent);
    }

    private void intentTeam2() {
        Intent intent = new Intent(getContext(), TeamDetailActivity.class);
        intent.putExtra("teambg",teamModels.get(1).getBglink());
        intent.putExtra("teamlogo",teamModels.get(1).getLogolink());
        intent.putExtra("teamname",teamModels.get(1).getTeamname());
        intent.putExtra("teamdesc",teamModels.get(1).getDesc());
        intent.putExtra("teamid",teamModels.get(1).getId());
        getContext().startActivity(intent);
    }

    private void intentTeam3() {
        Intent intent = new Intent(getContext(), TeamDetailActivity.class);
        intent.putExtra("teambg",teamModels.get(2).getBglink());
        intent.putExtra("teamlogo",teamModels.get(2).getLogolink());
        intent.putExtra("teamname",teamModels.get(2).getTeamname());
        intent.putExtra("teamdesc",teamModels.get(2).getDesc());
        intent.putExtra("teamid",teamModels.get(2).getId());
        getContext().startActivity(intent);
    }

    private void intentTeam4() {
        Intent intent = new Intent(getContext(), TeamDetailActivity.class);
        intent.putExtra("teambg",teamModels.get(3).getBglink());
        intent.putExtra("teamlogo",teamModels.get(3).getLogolink());
        intent.putExtra("teamname",teamModels.get(3).getTeamname());
        intent.putExtra("teamdesc",teamModels.get(3).getDesc());
        intent.putExtra("teamid",teamModels.get(3).getId());
        getContext().startActivity(intent);
    }

    private void fabShow() {
        ViewGroup.LayoutParams paramsforcv = (ViewGroup.LayoutParams) fabhome.getLayoutParams();
        paramsforcv.height= (int)getResources().getDimension(R.dimen.cvheightboo);
        fabhome.setLayoutParams(paramsforcv);
        ivadd.setVisibility(View.GONE);
        ivCross.setVisibility(View.VISIBLE);
        ivaddteam.setVisibility(View.VISIBLE);
        ivaddTour.setVisibility(View.VISIBLE);
        separator.setVisibility(View.VISIBLE);
    }

    private void fabHide() {
        ViewGroup.LayoutParams paramsforcv = (ViewGroup.LayoutParams) fabhome.getLayoutParams();
        paramsforcv.height= (int)getResources().getDimension(R.dimen.cvheight);
        fabhome.setLayoutParams(paramsforcv);
        ivadd.setVisibility(View.VISIBLE);
        ivCross.setVisibility(View.GONE);
        ivaddteam.setVisibility(View.GONE);
        ivaddTour.setVisibility(View.GONE);
        separator.setVisibility(View.GONE);
    }


    private void populateTeam() {
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
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

                            }
                            Picasso.get().load(teamModels.get(0).getLogolink()).into(teamIcon1);
                            Picasso.get().load(teamModels.get(1).getLogolink()).into(teamIcon2);
                            Picasso.get().load(teamModels.get(2).getLogolink()).into(teamIcon3);
                            Picasso.get().load(teamModels.get(3).getLogolink()).into(teamIcon4);
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
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    private void populategamemodel() {
        gameModels = new ArrayList<>();
        gameModels.add(new GameModel("Call Of Duty Mobile",R.drawable.codmback,R.drawable.lokapalagames));
        gameModels.add(new GameModel("Escape From Tarkov",R.drawable.estfmback,R.drawable.lokapalagames));
        gameModels.add(new GameModel("LokaPala",R.drawable.lokapalagames,R.drawable.lokapalagames));
        gameModels.add(new GameModel("Apex Legends",R.drawable.apexback,R.drawable.lokapalagames));
        gameModels.add(new GameModel("Mobile Legends",R.drawable.moblegendbg,R.drawable.mlheader));
        rvGamesAdapter = new RVGamesAdapter(getContext(),gameModels);
        rvgameshome.setAdapter(rvGamesAdapter);
    }
    private void populateproplayermodel() {
        proPlayerModels=new ArrayList<>();
        proPlayerModels.add(new ProPlayerModel(R.drawable.jessback,"jess No Limit"));
        proPlayerModels.add(new ProPlayerModel(R.drawable.lmback,"Lemon"));
        proPlayerModels.add(new ProPlayerModel(R.drawable.bkback,"Brandon Kent"));
        rvProplayerAdapter = new RVProplayerAdapter(getContext(),proPlayerModels);
        rvproplayerhome.setAdapter(rvProplayerAdapter);
    }

    private void populatenews() {
        newsModels = new ArrayList<>();
        newsModels.add(new NewsModel(R.drawable.gopayback,"Gandeng Pevita, GoPay Ajak Gamer Jadi Diri Sendiri!"));
        newsModels.add(new NewsModel(R.drawable.pbback,"PBSEA 2020, Turnamen Point Blank Pertama di Asia Tenggara"));
        newsModels.add(new NewsModel(R.drawable.ogplayerback,"Kenapa Gamer Kerap Kehilangan Motivasi Usai Jadi Juara?"));
        newsModels.add(new NewsModel(R.drawable.redplayerback,"Kalah Lagi, Bigetron Khawatir Alter Ego Tak Lolos Playoffs MPL"));
        vpHomeAutoAdapter = new VPHomeAutoAdapter(getContext(),newsModels);
        autoviewpager.setAdapter(vpHomeAutoAdapter);
        autoviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentnewspos=position;
                updatedots(currentnewspos);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void newsslideshow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentnewspos==newsModels.size()) {
                    currentnewspos =0;

                }
                updatedots(currentnewspos);
                autoviewpager.setCurrentItem(currentnewspos++,true);

            }
        };
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },250,2500);
    }
    private void updatedots(int pos) {

        switch (pos) {
            case 0:
                circle1.setImageResource(R.drawable.circlebluegrad);
                circle2.setImageResource(R.drawable.circlewhite);
                circle3.setImageResource(R.drawable.circlewhite);
                circle4.setImageResource(R.drawable.circlewhite);
                break;
            case 1:
                circle1.setImageResource(R.drawable.circlewhite);
                circle2.setImageResource(R.drawable.circlebluegrad);
                circle3.setImageResource(R.drawable.circlewhite);
                circle4.setImageResource(R.drawable.circlewhite);
                break;
            case 2:
                circle1.setImageResource(R.drawable.circlewhite);
                circle2.setImageResource(R.drawable.circlewhite);
                circle3.setImageResource(R.drawable.circlebluegrad);
                circle4.setImageResource(R.drawable.circlewhite);
                break;
            case 3:
                circle1.setImageResource(R.drawable.circlewhite);
                circle2.setImageResource(R.drawable.circlewhite);
                circle3.setImageResource(R.drawable.circlewhite);
                circle4.setImageResource(R.drawable.circlebluegrad);
                break;
        }
    }
}
