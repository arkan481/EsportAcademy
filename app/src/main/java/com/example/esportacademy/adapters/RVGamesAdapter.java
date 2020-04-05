package com.example.esportacademy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esportacademy.R;
import com.example.esportacademy.models.GameModel;
import com.example.esportacademy.ui.GameDetailActivity;

import java.util.ArrayList;

public class RVGamesAdapter extends RecyclerView.Adapter<RVGamesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GameModel>gameModels;

    public RVGamesAdapter(Context context,ArrayList<GameModel>gameModels) {
        this.context=context;
        this.gameModels=gameModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgamesvp,parent,false);
        return new RVGamesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.TVgametitle.setText(gameModels.get(position).getTitle());
        holder.IVgameimage.setImageResource(gameModels.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameDetailActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return gameModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView IVgameimage;
        private TextView TVgametitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IVgameimage = itemView.findViewById(R.id.IVgamesitem);
            TVgametitle = itemView.findViewById(R.id.tvgametitleitemid);
        }
    }


}
