package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.esportacademy.R;
import com.example.esportacademy.models.NewsModel;

import java.util.ArrayList;

public class VPHomeAutoAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<NewsModel> newsModels;

    public VPHomeAutoAdapter(Context context, ArrayList<NewsModel> newsModels) {
        this.context = context;
        this.newsModels = newsModels;
    }

    @Override
    public int getCount() {
        return newsModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.newsitem,container,false);
        ImageView newsimage = v.findViewById(R.id.ivnewsimage);
        TextView newstitle = v.findViewById(R.id.tvnewstitle);
        newsimage.setImageResource(newsModels.get(position).getImage());
        newstitle.setText(newsModels.get(position).getNewstitle());
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }
}
