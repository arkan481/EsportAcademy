package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.app.UserSessionManager;
import com.example.esportacademy.utils.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ImageView ivback,iveyelog;
    private TextView tvsignup;
    boolean eyeclicked = false;
    private EditText etpassword,etusername;
    private RelativeLayout rllogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivback = findViewById(R.id.IVbackloginid);
        tvsignup = findViewById(R.id.tvsignuplogid);
        iveyelog = findViewById(R.id.IVeyelogid);
        progressDialog = new ProgressDialog(LoginActivity.this);
        etusername = findViewById(R.id.etusernamelogid);
        etpassword = findViewById(R.id.etpasswordlogid);
        rllogin = findViewById(R.id.RLloginlogid);
        rllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
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
    private void loginCheck() {
        final String username = etusername.getText().toString().trim();
        final String password = etpassword.getText().toString().trim();
        progressDialog.setMessage("Logging You In...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_LOGIN_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                                Toast.makeText(LoginActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                UserSessionManager.getInstance(LoginActivity.this).setLogin(jsonObject.getInt("id"),jsonObject.getString("username"));
                                Intent intent = new Intent(LoginActivity.this,HomepageActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }
}
