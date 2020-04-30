package com.example.esportacademy.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esportacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {

    private TextView tvDetail,tvRules,tvPrize,tvSchedule,tvContact;

    public OverViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_over_view, container, false);
        tvDetail = v.findViewById(R.id.tvDetails);
        tvRules = v.findViewById(R.id.tvRules);
        tvPrize = v.findViewById(R.id.tvPrize);
        tvSchedule = v.findViewById(R.id.tvSchedule);
        tvContact = v.findViewById(R.id.tvContact);
        firstFragment();

        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstFragment();
            }
        });

        tvRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondFragment();
            }
        });

        tvPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdFragment();
            }
        });

        tvSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthFragment();
            }
        });

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fifthFragment();
            }
        });

        return v;
    }

    private void textColor(int i) {
        switch (i) {
            case 1:
                tvDetail.setTextColor(Color.parseColor("#72d119"));
                tvDetail.setTypeface(null,Typeface.BOLD_ITALIC);
                tvRules.setTextColor(Color.WHITE);
                tvRules.setTypeface(null,Typeface.BOLD);
                tvPrize.setTextColor(Color.WHITE);
                tvPrize.setTypeface(null,Typeface.BOLD);
                tvSchedule.setTextColor(Color.WHITE);
                tvSchedule.setTypeface(null,Typeface.BOLD);
                tvContact.setTextColor(Color.WHITE);
                tvContact.setTypeface(null,Typeface.BOLD);
                break;
            case 2:
                tvDetail.setTextColor(Color.WHITE);
                tvDetail.setTypeface(null,Typeface.BOLD);
                tvRules.setTextColor(Color.parseColor("#72d119"));
                tvRules.setTypeface(null,Typeface.BOLD_ITALIC);
                tvPrize.setTextColor(Color.WHITE);
                tvPrize.setTypeface(null,Typeface.BOLD);
                tvSchedule.setTextColor(Color.WHITE);
                tvSchedule.setTypeface(null,Typeface.BOLD);
                tvContact.setTextColor(Color.WHITE);
                tvContact.setTypeface(null,Typeface.BOLD);
                break;
            case 3:
                tvDetail.setTextColor(Color.WHITE);
                tvDetail.setTypeface(null,Typeface.BOLD);
                tvRules.setTextColor(Color.WHITE);
                tvRules.setTypeface(null,Typeface.BOLD);
                tvPrize.setTextColor(Color.parseColor("#72d119"));
                tvPrize.setTypeface(null,Typeface.BOLD_ITALIC);
                tvSchedule.setTextColor(Color.WHITE);
                tvSchedule.setTypeface(null,Typeface.BOLD);
                tvContact.setTextColor(Color.WHITE);
                tvContact.setTypeface(null,Typeface.BOLD);
                break;
            case 4:
                tvDetail.setTextColor(Color.WHITE);
                tvDetail.setTypeface(null,Typeface.BOLD);
                tvRules.setTextColor(Color.WHITE);
                tvRules.setTypeface(null,Typeface.BOLD);
                tvPrize.setTextColor(Color.WHITE);
                tvPrize.setTypeface(null,Typeface.BOLD);
                tvSchedule.setTextColor(Color.parseColor("#72d119"));
                tvSchedule.setTypeface(null,Typeface.BOLD_ITALIC);
                tvContact.setTextColor(Color.WHITE);
                tvContact.setTypeface(null,Typeface.BOLD);
                break;
            case 5:
                tvDetail.setTextColor(Color.WHITE);
                tvDetail.setTypeface(null,Typeface.BOLD);
                tvRules.setTextColor(Color.WHITE);
                tvRules.setTypeface(null,Typeface.BOLD);
                tvPrize.setTextColor(Color.WHITE);
                tvPrize.setTypeface(null,Typeface.BOLD);
                tvSchedule.setTextColor(Color.WHITE);
                tvSchedule.setTypeface(null,Typeface.BOLD);
                tvContact.setTextColor(Color.parseColor("#72d119"));
                tvContact.setTypeface(null,Typeface.BOLD_ITALIC);
                break;
        }
    }

    private void firstFragment() {
        textColor(1);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRoverView,new FragmentDetailOverView());
        fragmentTransaction.commit();
    }

    private void secondFragment() {
        textColor(2);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRoverView,new FragmentRules());
        fragmentTransaction.commit();
    }

    private void thirdFragment() {
        textColor(3);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRoverView,new FragmentPrize());
        fragmentTransaction.commit();
    }

    private void fourthFragment() {
        textColor(4);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRoverView,new FragmentSchedule());
        fragmentTransaction.commit();
    }

    private void fifthFragment() {
        textColor(5);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRoverView,new FragmentContact());
        fragmentTransaction.commit();
    }
}
