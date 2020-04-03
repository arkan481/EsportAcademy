package com.example.esportacademy.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.adapters.GridViewGallDetAdapter;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class GallFragmentDet extends Fragment {

    private GridViewGallDetAdapter gridViewGallDetAdapter;
    private GridView gridView;
    private String teamid;
    private ArrayList<String> imagelink = new ArrayList<>();
    private ProgressDialog progressDialog;

    public GallFragmentDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gall_det, container, false);
        Bundle bundle = getArguments();
        progressDialog = new ProgressDialog(getContext());
        this.teamid = bundle.getString("teamid");
        gridView = v.findViewById(R.id.GVgalldet);
        populateGridView();

        return v;
    }

    private void populateGridView() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_GALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("galldata");
                            for (int i =0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                imagelink.add(data.getString("imagelink"));
                            }
                            gridViewGallDetAdapter = new GridViewGallDetAdapter(getContext(),imagelink);
                            gridView.setAdapter(gridViewGallDetAdapter);
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
