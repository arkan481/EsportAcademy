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
        ImageView image1,image2,image3,image4,teamlogo;
        TextView teamname;
        teamname = v.findViewById(R.id.tvteamnamesearchid);
        image1 = v.findViewById(R.id.ivteamsearch1);
        image2 = v.findViewById(R.id.ivteamsearch2);
        image3 = v.findViewById(R.id.ivteamsearch3);
        image4 = v.findViewById(R.id.ivteamsearch4);
        teamlogo = v.findViewById(R.id.ivteamlogoid);
        teamname.setText(teamModels.get(position).getTeamname());
        image1.setImageResource(teamModels.get(position).getImage1());
        image2.setImageResource(teamModels.get(position).getImage2());
        image3.setImageResource(teamModels.get(position).getImage3());
        image4.setImageResource(teamModels.get(position).getImage4());
        teamlogo.setImageResource(teamModels.get(position).getTeamlogo());
        return v;
    }
}
