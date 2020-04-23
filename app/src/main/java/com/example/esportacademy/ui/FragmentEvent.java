package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVEventTourAdapter;
import com.example.esportacademy.models.EventTourModel;
import com.example.esportacademy.models.GameModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEvent extends Fragment {

    private RecyclerView rvEvent;
    private RVEventTourAdapter rvEventTourAdapter;
    private ArrayList<EventTourModel> eventTourModels;
    private GameModel gameModel;

    public FragmentEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        Bundle bundle = getArguments();
        gameModel = bundle.getParcelable("gamemodel");
        rvEvent = v.findViewById(R.id.rveventid);
        populateEvent();
        return v;
    }

    private void populateEvent() {
        if (gameModel.getEventTourModels()!=null) {
            eventTourModels = gameModel.getEventTourModels();
        }else {
            eventTourModels = new ArrayList<>();
            eventTourModels.add(new EventTourModel(R.drawable.event1));
            eventTourModels.add(new EventTourModel(R.drawable.event2));
        }
        rvEventTourAdapter = new RVEventTourAdapter(getContext(),eventTourModels);
        rvEvent.setAdapter(rvEventTourAdapter);
    }
}
