package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Space;

import com.example.esportacademy.MainActivity;
import com.example.esportacademy.R;
import com.example.esportacademy.app.UserSessionManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserSessionManager.getInstance(SplashScreen.this).getUsername()!=null) {
                    Intent intent = new Intent(SplashScreen.this,HomepageActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }
}
