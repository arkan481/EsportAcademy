package com.example.esportacademy.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.esportacademy.R;
import com.example.esportacademy.adapters.RVMemberDetAdapter;
import com.example.esportacademy.app.RequestHandler;
import com.example.esportacademy.models.TeamMemberModel;
import com.example.esportacademy.utils.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragmentDet extends Fragment {

    private RecyclerView recyclerView;
    private RVMemberDetAdapter rvMemberDetAdapter;
    private TextView tvmemidk,tvmemdesc;
    private ImageView ivmemphoto;
    private ProgressDialog progressDialog,progressDialog2;
    private String teamid;
    private ArrayList<TeamMemberModel> teamMemberModels = new ArrayList<>();

    public MemberFragmentDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_member_det, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog2 = new ProgressDialog(getContext());
        tvmemidk = v.findViewById(R.id.tvmemidk);
        Bundle bundle = getArguments();
        teamid = bundle.getString("teamid");
        ivmemphoto = v.findViewById(R.id.ivmemphoto12);
        tvmemdesc = v.findViewById(R.id.tvmemDesc);
        recyclerView = v.findViewById(R.id.rvmemberdet);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setNestedScrollingEnabled(false);
        populateMember();
        populateOwner();
        return v;
    }

    private void populateOwner() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_MEM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("memdata");
                            for (int i =0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                tvmemidk.setText(data.getString("memname"));
                                tvmemdesc.setText(data.getString("memdesc"));
                                Picasso.get().load(data.getString("memphoto")).into(ivmemphoto);
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

    private void populateMember() {
        progressDialog2.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_MEMBER_ALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog2.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("teamdata");
                            for (int i =0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                teamMemberModels.add(new TeamMemberModel(data.getString("username"),data.getString("userphoto")));
                            }
                            rvMemberDetAdapter = new RVMemberDetAdapter(getContext(),teamMemberModels);
                            recyclerView.setAdapter(rvMemberDetAdapter);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog2.dismiss();
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
