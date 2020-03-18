package com.example.esportacademy.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGeneral extends Fragment  {

    private LinearLayout rlgames;
    private ImageView ivaddgamegeneral;
    private maketeaminterface maketeaminterface;
    private EditText etdesc;
    private ArrayList<EditText>editTexts= new ArrayList<>();
    private ImageView ivbtndonegen;

    public FragmentGeneral(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        etdesc= v.findViewById(R.id.etdescgen);
        rlgames = v.findViewById(R.id.LRgamesitem);
        ivaddgamegeneral = v.findViewById(R.id.ivaddgamegeneral);
        ivbtndonegen = v.findViewById(R.id.btndonegeneralid);

        ivbtndonegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivbtndonegen.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_24dp));
                maketeaminterface.setDescription(etdesc.getText().toString());
                maketeaminterface.setGames(getgamesdata());
                maketeaminterface.setGenReady(true);
            }
        });
        ivaddgamegeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
                stateChanged();
            }
        });

        addfirstview(container);
        etdesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                stateChanged();
            }
        });

        return v;
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.addgamesitem,container,false);
        EditText editText = k.findViewById(R.id.tvgamename);
        editTexts.add(editText);
        rlgames.addView(k,rlgames.getChildCount()-1);
        etgamesvalidator();
    }
    private ArrayList<String> getgamesdata() {
        final ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<editTexts.size();i++) {
            datas.add(editTexts.get(i).getText().toString());
        }
        return datas;
    }
    private void etgamesvalidator() {
        for (int i=0;i<editTexts.size();i++) {
            editTexts.get(i).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    stateChanged();
                }
            });
        }
    }
    private void stateChanged() {
        ivbtndonegen.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_red_24dp));
        maketeaminterface.setGenReady(false);
    }
}
