package com.example.esportacademy.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralDetFragment extends Fragment {

    private LinearLayout lrGameDet;
    private TextView tvDesc;
    private Bundle bundle;
    private String teamid;
    private ImageView ivrts;
    private ProgressDialog progressDialog;

    public GeneralDetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general_det, container, false);
        progressDialog = new ProgressDialog(getContext());
        bundle = getArguments();
        teamid = bundle.getString("teamid");
        tvDesc =  v.findViewById(R.id.tvDescDet);
        ivrts = v.findViewById(R.id.ivrtsdet);
        lrGameDet = v.findViewById(R.id.LRgamesdet);
        populateDesc();
        populateGame(container);
        populateRTS();
        return v;
    }

    private void populateDesc() {
        tvDesc.setText(bundle.getString("teamdesc"));
    }

    private void populateGame(final ViewGroup container) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_GAMES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("gamedata");
                            for (int i = 0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                View v = LayoutInflater.from(getContext()).inflate(R.layout.itemgamedetail,container,false);
                                TextView tv = v.findViewById(R.id.tvgamenamedet);
                                tv.setText(data.getString("gamename"));
                                lrGameDet.addView(v);
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
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teamid",teamid);
                return params;
            }
        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    private void populateRTS() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_RTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("rtsdata");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                Picasso.get().load(data.getString("rtslink")).into(ivrts);
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
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teamid",teamid);
                return params;
            }
        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
