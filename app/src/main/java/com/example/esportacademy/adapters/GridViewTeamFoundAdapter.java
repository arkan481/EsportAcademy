package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.models.TeamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridViewTeamFoundAdapter extends BaseAdapter {

    private ArrayList<TeamModel>teamModels;
    private Context context;

    public GridViewTeamFoundAdapter(ArrayList<TeamModel> teamModels, Context context) {
        this.teamModels = teamModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return teamModels.size();
    }

    @Override
    public TeamModel getItem(int position) {
        return teamModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchteamitem,parent,false);
        ImageView teamlogo,teambg;
        TextView teamname;
        teambg = v.findViewById(R.id.ivteambgsearch);
        teamname = v.findViewById(R.id.tvteamnamesearchid);
        teamlogo = v.findViewById(R.id.ivteamlogoid);
        teamname.setText(teamModels.get(position).getTeamname());
        Picasso.get().load(teamModels.get(position).getBglink()).into(teambg);
        Picasso.get().load(teamModels.get(position).getLogolink()).into(teamlogo);
        return v;
    }
}
