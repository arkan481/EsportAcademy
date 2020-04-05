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
import com.example.esportacademy.models.NewsTourModel;

import java.util.ArrayList;

public class RVNewsTourAdapter extends RecyclerView.Adapter<RVNewsTourAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewsTourModel> newsTourModels;
    public RVNewsTourAdapter(Context context,ArrayList<NewsTourModel> newsTourModels) {
        this.context = context;
        this.newsTourModels = newsTourModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.newsitemtour,parent,false);
        return new RVNewsTourAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(context.getResources().getDrawable(newsTourModels.get(position).getImage()));
        holder.tvHead.setText(newsTourModels.get(position).getNewsHead());
        holder.tvFoot.setText(newsTourModels.get(position).getNewsFooter());
    }

    @Override
    public int getItemCount() {
        return newsTourModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView tvHead,tvFoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivnewsimagetour);
            tvHead = itemView.findViewById(R.id.tvnewshead);
            tvFoot = itemView.findViewById(R.id.tvNewsFoot);
        }
    }
}
