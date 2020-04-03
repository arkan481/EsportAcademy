package com.example.esportacademy.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.models.TeamModel;
import com.example.esportacademy.ui.TeamDetailActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchteamitem,parent,false);
        ImageView teamlogo,teambg;
        TextView teamname;
        teambg = v.findViewById(R.id.ivteambgsearch);
        teamname = v.findViewById(R.id.tvteamnamesearchid);
        teamlogo = v.findViewById(R.id.ivteamlogoid);
        teamname.setText(teamModels.get(position).getTeamname());
        Picasso.get().load(teamModels.get(position).getBglink()).into(teambg);
        Picasso.get().load(teamModels.get(position).getLogolink()).into(teamlogo);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamDetailActivity.class);
                intent.putExtra("teambg",teamModels.get(position).getBglink());
                intent.putExtra("teamlogo",teamModels.get(position).getLogolink());
                intent.putExtra("teamname",teamModels.get(position).getTeamname());
                intent.putExtra("teamdesc",teamModels.get(position).getDesc());
                intent.putExtra("teamid",teamModels.get(position).getId());
                context.startActivity(intent);
            }
        });
        return v;
    }
}
