package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.esportacademy.R;

public class GridViewGallDetAdapter extends BaseAdapter {

    private Context context;

    public GridViewGallDetAdapter(Context context) {
        this.context=context;
    }

    int image[] = {R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall,R.drawable.c9gall};

    // TODO : Dummy Image
    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.gallitemdet,parent,false);
        ImageView imageView = v.findViewById(R.id.gallimagedet);
        imageView.setImageDrawable(context.getResources().getDrawable(image[position]));
        return v;
    }
}
