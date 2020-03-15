package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.esportacademy.R;

public class GridViewGalleryAdapter extends BaseAdapter {

    private Context context;
    private int[] images ={R.layout.galleryitem,R.layout.galleryitem};

    public GridViewGalleryAdapter(Context context) {
        this.context=context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =LayoutInflater.from(context).inflate(R.layout.galleryitem,parent,false);
        return v;
    }
}
