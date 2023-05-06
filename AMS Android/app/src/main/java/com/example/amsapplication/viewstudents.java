package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

public class viewstudents extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1;
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> name,addno,smester,gender,dob,address,phone,jointdate,photo;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudents);
        s1 = findViewById(R.id.spinner2);
        l1 = findViewById(R.id.list20);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        String year[] = {"1999", "2000", "2001", "2002", "2003", "2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024"};


        ArrayAdapter<String> ad = new ArrayAdapter<>(viewstudents.this, android.R.layout.simple_spinner_dropdown_item, year);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(viewstudents.this);

//

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        url ="http://"+sh.getString("ip", "") + ":5000/viewstudents";
        RequestQueue queue = Volley.newRequestQueue(viewstudents.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    addno= new ArrayList<>();
                    smester= new ArrayList<>();
                    gender=new ArrayList<>();
                    dob=new ArrayList<>();
                    address=new ArrayList<>();
                    phone=new ArrayList<>();
                    photo=new ArrayList<>();
                    jointdate=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        addno.add(jo.getString("addmission no"));
                        smester.add(jo.getString("smester"));
                        gender.add(jo.getString("gender"));
                        dob.add(jo.getString("dob"));
                        address.add(jo.getString("address"));
                        phone.add(jo.getString("phone"));
                        photo.add(jo.getString("photo"));
                        jointdate.add(jo.getString("jointdate"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new customforviewstudent(viewstudents.this,name,addno,smester,gender,address,dob,phone,photo,jointdate));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewstudents.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("year", s1.getSelectedItem().toString());
                return params;
            }
        };
        queue.add(stringRequest);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}