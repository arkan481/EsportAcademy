package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.models.GameModel;

import java.util.ArrayList;

public class ViewPagerGamesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GameModel> gameModels;

    public ViewPagerGamesAdapter(ArrayList<GameModel>gameModels,Context context) {
        this.context=context;
        this.gameModels=gameModels;
    }

    @Override
    public int getCount() {
        System.out.println("the size is "+gameModels.size());
        return gameModels.size();
    }

    @Override
    public GameModel getItem(int position) {

    return gameModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =LayoutInflater.from(context).inflate(R.layout.itemgamesvp,parent,false);
        ImageView gameimage = v.findViewById(R.id.IVgamesitem);
        TextView gametitle = v.findViewById(R.id.tvgametitleitemid);
        gameimage.setImageResource(gameModels.get(position).getImage());
        gametitle.setText(gameModels.get(position).getTitle());
        return v;
    }
}
