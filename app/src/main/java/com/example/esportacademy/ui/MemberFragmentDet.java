package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVMemberDetAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragmentDet extends Fragment {

    private RecyclerView recyclerView;
    private RVMemberDetAdapter rvMemberDetAdapter;

    public MemberFragmentDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member_det, container, false);
        recyclerView = v.findViewById(R.id.rvmemberdet);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        populateMember();
        return v;
    }

    private void populateMember() {
        rvMemberDetAdapter = new RVMemberDetAdapter(getContext());
        recyclerView.setAdapter(rvMemberDetAdapter);
    }
}
