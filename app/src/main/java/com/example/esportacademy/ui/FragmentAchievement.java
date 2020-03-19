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
    private ImageView ivaddach;
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
        ivaddach.setVisibility(View.GONE);
        ivaddach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfirstview(container);
            }
        });
        if (maketeaminterface.getAchUpdating()==true) {
            for (int i=0;i<maketeaminterface.getAchievement().size();i++) {
                addfirstview(container);
                achnameedittexts.get(i).setText(maketeaminterface.getAchievement().get(i));
                achdescedittexts.get(i).setText(maketeaminterface.getAchDesc().get(i));
            }
        }else {
            maketeaminterface.setAchUpdating(false);
            addfirstview(container);
        }
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        maketeaminterface.setAchUpdating(true);
        maketeaminterface.setAchievement(getAchNameData());
        maketeaminterface.setaAhievementDesc(getAchDescData());
    }

    private void addfirstview(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.achitem,container,false);
        EditText etachname = k.findViewById(R.id.etachname);
        EditText etachdesc = k.findViewById(R.id.etachdesc);
        achnameedittexts.add(etachname);
        achdescedittexts.add(etachdesc);
        lrach.addView(k);
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

}
