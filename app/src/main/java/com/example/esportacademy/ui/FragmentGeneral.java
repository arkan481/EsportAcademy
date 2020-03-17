package com.example.esportacademy.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends Fragment  {

    private LinearLayout rlgames;
    private ImageView ivaddgamegeneral;
    private maketeaminterface maketeaminterface;
    private EditText etdesc;

    public FragmentGeneral(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        setRetainInstance(true);
        etdesc= v.findViewById(R.id.etdescgen);
        rlgames = v.findViewById(R.id.LRgamesitem);
        ivaddgamegeneral = v.findViewById(R.id.ivaddgamegeneral);
        ivaddgamegeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
                // Implement maketeaminterface
            }
        });

        addfirstview(container);

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        maketeaminterface.setDescription(etdesc.getText().toString());
        // TODO : implement destroy see bookmark
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.addgamesitem,container,false);
        EditText editText = k.findViewById(R.id.tvgamename);
        rlgames.addView(k,rlgames.getChildCount()-1);
        this.maketeaminterface.setGames(editText.getText().toString());
    }


}
