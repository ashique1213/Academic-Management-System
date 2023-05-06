package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class STDVIEWFEDDSTATUS extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> feename,amount,amountpaid,amountdue,lastdate,status;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewfeddstatus);
        l1=findViewById(R.id.list1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/viewfee1";
        RequestQueue queue = Volley.newRequestQueue(STDVIEWFEDDSTATUS.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(STDVIEWFEDDSTATUS.this, "fffff"+response, Toast.LENGTH_SHORT).show();
                    feename= new ArrayList<>();
                    amount= new ArrayList<>();
                    amountpaid=new ArrayList<>();
                    amountdue=new ArrayList<>();
                    lastdate=new ArrayList<>();
//                    status=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        feename.add(jo.getString("f_id"));
                        amount.add(jo.getString("amount paid"));
                        amountpaid.add(jo.getString("amount due"));
                        amountdue.add(jo.getString("date paid"));
                        lastdate.add(jo.getString("status"));
//                        status.add(jo.getString("description"));

                    }
//
//                     ArrayAdapter<String> ad=new ArrayAdapter<>(STDfeedetails.this,android.R.layout.simple_list_item_1,amount,amountpaid,amountdue,lastdate,status);
//                    l1.setAdapter(ad);

                    l1.setAdapter(new custom4viewfeestatus(STDVIEWFEDDSTATUS.this,amount,amountpaid,amountdue,lastdate));
//                    l1.setOnItemClickListener(STDfeedetails.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDVIEWFEDDSTATUS.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("rid",sh.getString("rid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

   }