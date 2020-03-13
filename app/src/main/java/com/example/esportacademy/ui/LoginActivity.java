package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.esportacademy.R;

public class LoginActivity extends AppCompatActivity {

    private ImageView ivback,iveyelog;
    private TextView tvsignup;
    boolean eyeclicked = false;
    private EditText etpassword;
    private RelativeLayout rllogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivback = findViewById(R.id.IVbackloginid);
        tvsignup = findViewById(R.id.tvsignuplogid);
        iveyelog = findViewById(R.id.IVeyelogid);
        etpassword = findViewById(R.id.etpasswordlogid);
        rllogin = findViewById(R.id.RLloginlogid);
        rllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        iveyelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyeclicked = !eyeclicked;
                if (eyeclicked==false) {
                    iveyelog.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_white_24dp));
                    etpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    iveyelog.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_white_24dp));
                    etpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
