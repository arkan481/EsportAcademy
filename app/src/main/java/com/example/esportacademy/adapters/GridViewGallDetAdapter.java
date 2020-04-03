package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.esportacademy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridViewGallDetAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> imagelink;

    public GridViewGallDetAdapter(Context context,ArrayList<String> imagelink) {
        this.context = context;
        this.imagelink = imagelink;
    }

    @Override
    public int getCount() {
        return imagelink.size();
    }

    @Override
    public Object getItem(int position) {
        return imagelink.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.gallitemdet,parent,false);
        ImageView imageView = v.findViewById(R.id.gallimagedet);
        Picasso.get().load(imagelink.get(position)).into(imageView);
        return v;
    }
}
