package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVTourTour;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTournament extends Fragment {

    private RecyclerView rvtour;
    private RVTourTour rvTourTour;
    private ArrayAdapter<String> leftArray,rightArray;
    private Spinner rightSpinner,leftSpinner;

    public FragmentTournament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tournament, container, false);
        rvtour = v.findViewById(R.id.rvtour);
        leftArray = new ArrayAdapter<>(
                getContext(),
                R.layout.customspinner2,
                getResources().getStringArray(R.array.leftspinner)
        );
        rightArray = new ArrayAdapter<String>(
                getContext(),
                R.layout.customspinner2,
                getResources().getStringArray(R.array.rightspinner)
        );
        rightArray.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        leftArray.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        rightSpinner = v.findViewById(R.id.rightspinner);
        leftSpinner = v.findViewById(R.id.leftspinner);
        leftSpinner.setAdapter(leftArray);
        rightSpinner.setAdapter(rightArray);
        populateTournament();
        return v;
    }

    private void populateTournament() {
        rvTourTour = new RVTourTour(getContext());
        rvtour.setAdapter(rvTourTour);
    }
}
