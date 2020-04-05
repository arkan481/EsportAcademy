package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVEventTourAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEvent extends Fragment {

    private RecyclerView rvEvent;
    private RVEventTourAdapter rvEventTourAdapter;

    public FragmentEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        rvEvent = v.findViewById(R.id.rveventid);
        populateEvent();
        return v;
    }

    private void populateEvent() {
        rvEventTourAdapter = new RVEventTourAdapter(getContext());
        rvEvent.setAdapter(rvEventTourAdapter);
    }
}
