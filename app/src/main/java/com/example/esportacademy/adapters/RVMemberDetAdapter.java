package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esportacademy.R;
import com.example.esportacademy.models.TeamMemberModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVMemberDetAdapter extends RecyclerView.Adapter<RVMemberDetAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TeamMemberModel> teamMemberModels;

    public RVMemberDetAdapter (Context context,ArrayList<TeamMemberModel> teamMemberModels) {
        this.context=context;
        this.teamMemberModels = teamMemberModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.memberitemdet,parent,false);
        return new RVMemberDetAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(teamMemberModels.get(position).getPhoto()=="null") {
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_person_black_24dp));
        }else {
            Picasso.get().load(teamMemberModels.get(position).getPhoto()).into(holder.imageView);
        }
        holder.textView.setText(teamMemberModels.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return teamMemberModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.ivmemphotodet);
            this.textView = itemView.findViewById(R.id.tvmemnamedet);
        }
    }
}
