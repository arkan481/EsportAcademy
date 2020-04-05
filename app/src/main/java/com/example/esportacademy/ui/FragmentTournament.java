package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVTourTour;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTournament extends Fragment {

    private RecyclerView rvtour;
    private RVTourTour rvTourTour;

    public FragmentTournament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tournament, container, false);
        rvtour = v.findViewById(R.id.rvtour);
        populateTournament();
        return v;
    }

    private void populateTournament() {
        rvTourTour = new RVTourTour(getContext());
        rvtour.setAdapter(rvTourTour);
    }
}
