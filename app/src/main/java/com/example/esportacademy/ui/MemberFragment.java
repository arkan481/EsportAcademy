package com.example.esportacademy.ui;

import android.os.Bundle;

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

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {

    private LinearLayout lrmemberauto;
    private ImageView ivbtnaddmember,ivbtndone;
    private maketeaminterface maketeaminterface;
    private ArrayList<EditText>etmemnamearr = new ArrayList<>();
    private ArrayList<EditText>etmemdescarr = new ArrayList<>();

    public MemberFragment(maketeaminterface maketeaminterface) {
        // Required empty public constructor
        this.maketeaminterface=maketeaminterface;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member, container, false);
        lrmemberauto = v.findViewById(R.id.LRmemberauto);
        ivbtnaddmember = v.findViewById(R.id.btnaddmember);
        ivbtndone = v.findViewById(R.id.ivbtndonemem);
        ivbtndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivbtndone.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_24dp));
                maketeaminterface.setMember(getMemName());
                maketeaminterface.setMemberDesc(getMemDesc());
                maketeaminterface.setMemberReady(true);
            }
        });
        ivbtnaddmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateChanged();
                populatemember(container);
            }
        });
        populatemember(container);
        return v;
    }
    private void populatemember(ViewGroup container) {
        View k = LayoutInflater.from(getContext()).inflate(R.layout.memberitem,container,false);
        EditText etmemname = k.findViewById(R.id.etmemnameid);
        EditText etmemdesc = k.findViewById(R.id.etmemdescid);
        etmemnamearr.add(etmemname);
        etmemdescarr.add(etmemdesc);
        lrmemberauto.addView(k);
        etNameValidator();
        etDescValidator();
    }

    private ArrayList<String> getMemName() {
        ArrayList<String>datas = new ArrayList<>();
        for (int i =0;i<etmemnamearr.size();i++) {
            datas.add(etmemnamearr.get(i).getText().toString());
        }
        return datas;
    }
    private ArrayList<String> getMemDesc() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<etmemdescarr.size();i++) {
            datas.add(etmemdescarr.get(i).getText().toString());
        }
        return datas;
    }

    private void etNameValidator() {
        for (int i=0;i<etmemnamearr.size();i++) {
            etmemnamearr.get(i).addTextChangedListener(new TextWatcher() {
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
    private void etDescValidator() {
        for (int i=0;i<etmemdescarr.size();i++) {
            etmemdescarr.get(i).addTextChangedListener(new TextWatcher() {
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
        ivbtndone.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_red_24dp));
        maketeaminterface.setMemberReady(false);
    }
}
