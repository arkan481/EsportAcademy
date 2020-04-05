package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVNewsTourAdapter;
import com.example.esportacademy.models.NewsTourModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNews extends Fragment {

    private RecyclerView rvNews;
    private ArrayList<NewsTourModel> newsTourModels = new ArrayList<>();
    RVNewsTourAdapter rvNewsTourAdapter;

    public FragmentNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        rvNews = v.findViewById(R.id.RVnewstour);
        populateNews();
        return v;
    }

    private void populateNews() {
        newsTourModels.add(new NewsTourModel(R.drawable.newsimage1,"Game MOBA Pertama Indonesia Lokapala Rilis Januari 2020","Game MOBA Pertama Indonesia Lokapala Rilis Januari 2020"));
        newsTourModels.add(new NewsTourModel(R.drawable.ilya,"Lokapala Kenalkan Karakter Ilya, Anak Kecil Tapi Tanker!","By:Jefri Sibarani 30 November 2019"));
        rvNewsTourAdapter = new RVNewsTourAdapter(getContext(),newsTourModels);
        rvNews.setAdapter(rvNewsTourAdapter);

    }
}
