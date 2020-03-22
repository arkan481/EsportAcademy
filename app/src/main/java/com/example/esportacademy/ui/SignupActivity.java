package com.example.esportacademy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
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
import com.example.esportacademy.utils.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private ImageView ivback,iveye1,iveye2;
    private CheckBox checkBox;
    private TextView tvlogin;
    private EditText etpass,etconfpass,etusername;
    boolean isclicked1 = false;
    private RelativeLayout rlsignup;
    boolean isclicked2 = false;
    boolean aggrement = false;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ivback = findViewById(R.id.IVbacksignupid);
        tvlogin = findViewById(R.id.tvloginid);
        etusername = findViewById(R.id.etusernamesignid);
        checkBox = findViewById(R.id.checkboxsignupid);
        rlsignup = findViewById(R.id.RLSignupid);
        etpass = findViewById(R.id.etpasswordsignid);
        etconfpass = findViewById(R.id.etconfpasswordsignid);
        iveye1=findViewById(R.id.IVeyesign1id);
        progressDialog = new ProgressDialog(SignupActivity.this);
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
        rlsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etpass.getText().toString().isEmpty()||etusername.getText().toString().isEmpty()||etconfpass.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this,"Please fill all the requisition",Toast.LENGTH_SHORT).show();
                }else if (!etpass.getText().toString().equals(etconfpass.getText().toString())){
                    Toast.makeText(SignupActivity.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
                }else if (etpass.getText().toString().length()<=8||etpass.getText().toString().length()<=8) {
                    Toast.makeText(SignupActivity.this,"Password and username should have more then 8 characters!",Toast.LENGTH_SHORT).show();
                }else if(aggrement==false) {
                    Toast.makeText(SignupActivity.this,"You didn't agree on the terms and conditions",Toast.LENGTH_LONG).show();
                }else {
                    signUp();
                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggrement =!aggrement;
            }
        });
    }
    private void signUp() {
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        final String username = etusername.getText().toString().trim();
        final String password = etpass.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Server.URL_INSERT_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getInt("status")==1) {
                                Toast.makeText(SignupActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(SignupActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
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
        RequestHandler.getInstance(SignupActivity.this).addToRequestQueue(stringRequest);
    }
}
