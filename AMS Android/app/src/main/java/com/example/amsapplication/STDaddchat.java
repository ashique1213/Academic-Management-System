package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class STDaddchat extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView e1;

    ArrayList<String> feename,amount;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdaddchat);
        e1=findViewById(R.id.list1);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url ="http://"+sh.getString("ip", "") + ":5000/viewstafftochat";
        RequestQueue queue = Volley.newRequestQueue(STDaddchat.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(STDaddchat.this, "fffff"+response, Toast.LENGTH_SHORT).show();
                   feename= new ArrayList<>();
                  amount= new ArrayList<>();

//                    status=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        feename.add(jo.getString("login_id"));
                        amount.add(jo.getString("name"));


//                        status.add(jo.getString("description"));

                    }
//
                     ArrayAdapter<String> ad=new ArrayAdapter<>(STDaddchat.this,android.R.layout.simple_list_item_1,amount);
                    e1.setAdapter(ad);

//                    e1.setAdapter(new custom4viewfeestatus(STDaddchat.this,amount,amountpaid,amountdue,lastdate));
                    e1.setOnItemClickListener(STDaddchat.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDaddchat.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid",feename.get(position));
        edp.commit();

        Intent ik = new Intent(getApplicationContext(), Test.class);
        ik.putExtra("uid",feename.get(position));
        startActivity(ik);


                            }
}