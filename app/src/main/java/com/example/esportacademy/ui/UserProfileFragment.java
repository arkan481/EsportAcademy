package com.example.esportacademy.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.app.UserSessionManager;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private TextView tvName;
    private RelativeLayout rlprofile;
    private ImageView profPhoto;


    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        profPhoto = v.findViewById(R.id.ivphotoproff);
        rlprofile = v.findViewById(R.id.RLeditprofile);
        tvName = v.findViewById(R.id.tvnameprofile);
        tvName.setText(UserSessionManager.getInstance(getContext()).getUsername());
        rlprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FRhomeid,new EditProfileFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        checkDetails();
        return v;
    }
    private void checkDetails() {
        if(UserSessionManager.getInstance(getContext()).hasDetails()) {
            Picasso.get().invalidate(UserSessionManager.getInstance(getContext()).getUserPhoto());
            Picasso.get().load(UserSessionManager.getInstance(getContext()).getUserPhoto()).networkPolicy(NetworkPolicy.NO_CACHE).into(profPhoto);
        }else {
            profPhoto.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_black_24dp));
        }
    }
}
