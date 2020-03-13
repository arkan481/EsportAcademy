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
import com.example.esportacademy.models.ProPlayerModel;

import java.util.ArrayList;

public class RVProplayerAdapter extends RecyclerView.Adapter<RVProplayerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProPlayerModel>proPlayerModels;

    public RVProplayerAdapter(Context context,ArrayList<ProPlayerModel>proPlayerModels) {
        this.context=context;
        this.proPlayerModels=proPlayerModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproplayer,parent,false);
        return new RVProplayerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvproname.setText(proPlayerModels.get(position).getName());
        holder.ivprophoto.setImageResource(proPlayerModels.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return proPlayerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivprophoto;
        private TextView tvproname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivprophoto=itemView.findViewById(R.id.IVproplayerimage);
            tvproname = itemView.findViewById(R.id.TVproplayername);
        }
    }


}
