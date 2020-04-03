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
public class FragmentAchDet extends Fragment {

    private LinearLayout lrAch;
    private String teamid;
    private ProgressDialog progressDialog;

    public FragmentAchDet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ach_det, container, false);
        Bundle bundle = getArguments();
        progressDialog = new ProgressDialog(getContext());
        this.teamid = bundle.getString("teamid");
        lrAch = v.findViewById(R.id.LRachlistdet);
        populateAch(container);
        return v;
    }

    private void populateAch(final ViewGroup container) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, Server.URL_GET_TEAM_ACH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("achdata");
                            for (int i =0;i<jsonArray.length();i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                View v = LayoutInflater.from(getContext()).inflate(R.layout.achitemdet,container,false);
                                TextView tvname,tvdesc;
                                ImageView imageView;
                                tvname = v.findViewById(R.id.tvachnamedet);
                                tvdesc = v.findViewById(R.id.tvachdescdet);
                                imageView = v.findViewById(R.id.ivachimagedet);
                                tvname.setText(data.getString("achname"));
                                tvdesc.setText(data.getString("achdesc"));
                                Picasso.get().load(data.getString("imagelink")).into(imageView);
                                lrAch.addView(v);
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
