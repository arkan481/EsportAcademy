package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esportacademy.R;

public class SignupActivity extends AppCompatActivity {

    private ImageView ivback,iveye1,iveye2;
    private TextView tvlogin;
    private EditText etpass,etconfpass;
    boolean isclicked1 = false;
    boolean isclicked2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ivback = findViewById(R.id.IVbacksignupid);
        tvlogin = findViewById(R.id.tvloginid);
        etpass = findViewById(R.id.etpasswordsignid);
        etconfpass = findViewById(R.id.etconfpasswordsignid);
        iveye1=findViewById(R.id.IVeyesign1id);
        iveye2=findViewById(R.id.IVeyesign2id);
        iveye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclicked1 = !isclicked1;
                if (isclicked1==false) {
                    iveye1.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_white_24dp));
                    etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    iveye1.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_white_24dp));
                    etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        iveye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclicked2 = !isclicked2;
                if (isclicked2==false) {
                    iveye2.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_white_24dp));
                    etconfpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    iveye2.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_white_24dp));
                    etconfpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
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
