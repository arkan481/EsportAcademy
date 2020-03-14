package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVGamesAdapter;
import com.example.esportacademy.adapters.RVProplayerAdapter;
import com.example.esportacademy.adapters.VPHomeAutoAdapter;
import com.example.esportacademy.adapters.ViewPagerAutoAdapter;
import com.example.esportacademy.adapters.ViewPagerGamesAdapter;
import com.example.esportacademy.models.GameModel;
import com.example.esportacademy.models.NewsModel;
import com.example.esportacademy.models.ProPlayerModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private RecyclerView rvgameshome,rvproplayerhome;

    private ArrayList<GameModel> gameModels;
    private RecyclerView.Adapter rvadapter;
    private RVGamesAdapter rvGamesAdapter;
    private ArrayList<ProPlayerModel>proPlayerModels;
    private RVProplayerAdapter rvProplayerAdapter;
    private ViewPager autoviewpager;
    private ArrayList<NewsModel> newsModels;
    private VPHomeAutoAdapter vpHomeAutoAdapter;
    private int currentnewspos;
    private Timer timer;
    private ImageView circle1,circle2,circle3,circle4;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rvgameshome = v.findViewById(R.id.rvgameshomeid);
        rvproplayerhome = v.findViewById(R.id.rvproplayerid);
        autoviewpager = v.findViewById(R.id.vphome1id);
        circle1 = v.findViewById(R.id.circlewhite1);
        circle2 = v.findViewById(R.id.circlewhite2);
        circle3 = v.findViewById(R.id.circlewhite3);
        circle4 = v.findViewById(R.id.circlewhite4);
        populategamemodel();
        populateproplayermodel();
        populatenews();
        newsslideshow();
        return v;
    }
    private void populategamemodel() {
        gameModels = new ArrayList<>();
        gameModels.add(new GameModel("Call Of Duty Mobile",R.drawable.codmback));
        gameModels.add(new GameModel("Escape From Tarkov",R.drawable.estfmback));
        gameModels.add(new GameModel("Apex Legends",R.drawable.apexback));
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
