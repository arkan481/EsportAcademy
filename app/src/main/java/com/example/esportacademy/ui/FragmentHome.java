package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVGamesAdapter;
import com.example.esportacademy.adapters.RVProplayerAdapter;
import com.example.esportacademy.adapters.ViewPagerAutoAdapter;
import com.example.esportacademy.adapters.ViewPagerGamesAdapter;
import com.example.esportacademy.models.GameModel;
import com.example.esportacademy.models.ProPlayerModel;

import java.util.ArrayList;

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
        populategamemodel();
        populateproplayermodel();
        return v;
    }
    private void populategamemodel() {
        gameModels = new ArrayList<>();
        gameModels.add(new GameModel("Call Of Duty Mobile",R.drawable.codmback));
        gameModels.add(new GameModel("Escape From Tarkov",R.drawable.estfmback));
        gameModels.add(new GameModel("Apex Legends",R.drawable.apexback));
        rvGamesAdapter = new RVGamesAdapter(getContext(),gameModels);
        rvgameshome.setAdapter(rvGamesAdapter);
        // TODO : CHANGE TO RECYCLER VIEW
    }
    private void populateproplayermodel() {
        proPlayerModels=new ArrayList<>();
        proPlayerModels.add(new ProPlayerModel(R.drawable.jessback,"jess No Limit"));
        proPlayerModels.add(new ProPlayerModel(R.drawable.lmback,"Lemon"));
        proPlayerModels.add(new ProPlayerModel(R.drawable.bkback,"Brandon Kent"));
        rvProplayerAdapter = new RVProplayerAdapter(getContext(),proPlayerModels);
        rvproplayerhome.setAdapter(rvProplayerAdapter);
    }
}
