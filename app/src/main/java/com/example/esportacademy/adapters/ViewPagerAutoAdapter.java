package com.example.esportacademy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.esportacademy.ui.*;

public class ViewPagerAutoAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private int tabno;

    public ViewPagerAutoAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabno=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentvp1 fragmentvp1 = new fragmentvp1();
                return fragmentvp1;
            case 1:
                fragmentvp2 fragmentvp2 = new fragmentvp2();
                return fragmentvp2;
            case 2:
                fragmentvp3 fragmentvp3 = new fragmentvp3();
                return fragmentvp3;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabno;
    }
}
