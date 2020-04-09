package com.example.esportacademy.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.esportacademy.R;

import java.util.ArrayList;

public class GridViewGalleryAdapter extends BaseAdapter {
    private ArrayList<Uri> images;
    private Context context;

    public GridViewGalleryAdapter(ArrayList<Uri> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.gallitem,parent,false);
        ImageView imageView = v.findViewById(R.id.ivgall);
        imageView.setImageURI(images.get(position));
        return v;
    }
}
