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

public class RVMemberDetAdapter extends RecyclerView.Adapter<RVMemberDetAdapter.ViewHolder> {
    private Context context;
    int image[] = {R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall};

    public RVMemberDetAdapter (Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.memberitemdet,parent,false);
        return new RVMemberDetAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(context.getResources().getDrawable(image[position]));
        holder.textView.setText("name "+position);
    }

    @Override
    public int getItemCount() {
        return image.length;
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
