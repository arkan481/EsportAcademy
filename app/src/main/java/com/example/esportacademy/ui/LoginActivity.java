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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.app.UserSessionManager;
import com.example.esportacademy.utils.Server;

import org.json.JSONArray;
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
    private ProgressDialog progressDialog,progressDialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog3 = new ProgressDialog(this);
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
                                getUserInformation();
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
                        Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
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

    private void getUserInformation() {
        progressDialog3.setMessage("Getting Your Information");
        progressDialog3.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_USER_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog3.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                if (data.getString("name")=="null"&&data.getString("email")=="null"&&data.getString("userphoto")=="null") {
                                    System.out.println("no details");
                                }else {
                                    UserSessionManager.getInstance(LoginActivity.this).setDetails(data.getString("name"),data.getString("email"),data.getString("userphoto"));
                                }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog3.dismiss();
                        Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userid",String.valueOf(UserSessionManager.getInstance(LoginActivity.this).getUserID()));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
    }
}
