package com.example.esportacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.esportacademy.adapters.ViewPagerAutoAdapter;
import com.example.esportacademy.ui.*;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPagerauto;
    ViewPagerAutoAdapter viewPagerAutoAdapter;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPagerauto = findViewById(R.id.viewpagerautoid);
        fragmentvp1 fragmentvp1 =new fragmentvp1();
        fragmentvp2 fragmentvp2 = new fragmentvp2();
        fragmentvp3 fragmentvp3 = new fragmentvp3();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentvp1,"tag1");
        fragmentTransaction.add(fragmentvp2,"tag2");
        fragmentTransaction.add(fragmentvp3,"tag3");
        generatevp();


    }

    private void generatevp() {
        viewPagerAutoAdapter =new ViewPagerAutoAdapter(fragmentManager,3);
        viewPagerauto.setAdapter(viewPagerAutoAdapter);
    }
}
