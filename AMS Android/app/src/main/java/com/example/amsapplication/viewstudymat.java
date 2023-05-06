package com.example.amsapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import androidx.appcompat.app.AppCompatActivity;

public class viewstudymat extends AppCompatActivity {
    ListView l1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> subject,topic,material,date;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudymat);
        l1=findViewById(R.id.list18);
        b1=findViewById(R.id.button13);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/ttviewstudymat";
        RequestQueue queue = Volley.newRequestQueue(viewstudymat.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    subject= new ArrayList<>();
                    topic= new ArrayList<>();
                    material= new ArrayList<>();
                    date=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        subject.add(jo.getString("sub_id"));
                        topic.add(jo.getString("topic"));
                        material.add(jo.getString("materials"));
                        date.add(jo.getString("date"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

//                    l1.setAdapter(new Custom(viewuser.this,name,place));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
             @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewstudymat.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}