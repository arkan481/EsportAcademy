package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAchievement extends Fragment {

    private LinearLayout lrach;
    private ImageView ivaddach,ivbtndone;
    private maketeaminterface maketeaminterface;
    private ArrayList<EditText> achnameedittexts = new ArrayList<>();
    private ArrayList<EditText> achdescedittexts = new ArrayList<>();

    public FragmentAchievement(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);
        lrach = v.findViewById(R.id.LRachid);
        ivaddach = v.findViewById(R.id.ivaddach);
        ivbtndone = v.findViewById(R.id.ivbtndoneach);
        ivaddach.setVisibility(View.GONE);
        ivbtndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivbtndone.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_24dp));
                maketeaminterface.setAchievement(getAchNameData());
                maketeaminterface.setaAhievementDesc(getAchDescData());
                maketeaminterface.setAchReady(true);
            }
        });
        ivaddach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
                stateChanged();
            }
        });
        addfirstview(container);
        return v;
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.achitem,container,false);
        EditText etachname = k.findViewById(R.id.etachname);
        EditText etachdesc = k.findViewById(R.id.etachdesc);
        achnameedittexts.add(etachname);
        achdescedittexts.add(etachdesc);
        lrach.addView(k);
        etachdescvalidator();
        etachnamevalidator();
    }

    private ArrayList<String> getAchNameData() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<achnameedittexts.size();i++) {
            datas.add(achnameedittexts.get(i).getText().toString());
        }
        return datas;
    }

    private ArrayList<String> getAchDescData() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<achdescedittexts.size();i++) {
            datas.add(achdescedittexts.get(i).getText().toString());
        }
        return datas;
    }

    private void stateChanged() {
        ivbtndone.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_red_24dp));
        maketeaminterface.setAchReady(false);
    }
    private void etachnamevalidator() {
        for (int i=0;i<achnameedittexts.size();i++) {
            achnameedittexts.get(i).addTextChangedListener(new TextWatcher() {
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
    private void etachdescvalidator() {
        for (int i=0;i<achdescedittexts.size();i++) {
            achdescedittexts.get(i).addTextChangedListener(new TextWatcher() {
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
}
