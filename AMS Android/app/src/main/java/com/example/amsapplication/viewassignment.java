package com.example.amsapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewassignment extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String>topic,Descrption,lastdate,ttid;
    String url,tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewassignment);
        l1=findViewById(R.id.list16);
        b1=findViewById(R.id.button4);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/ttviewtassignment";
        RequestQueue queue = Volley.newRequestQueue(viewassignment.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(viewassignment.this, "ccccc"+response, Toast.LENGTH_SHORT).show();
                    ttid= new ArrayList<>();
                    topic= new ArrayList<>();
                    Descrption= new ArrayList<>();
                    lastdate= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);

                        ttid.add(jo.getString("ass_id"));
                        topic.add(jo.getString("topic"));
                        Descrption.add(jo.getString("description"));
                        lastdate.add(jo.getString("last date"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom3(viewassignment.this,topic,Descrption,lastdate));
                    l1.setOnItemClickListener(viewassignment.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewassignment.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii= new Intent(getApplicationContext(),addassignment.class);
                startActivity(ii);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       tid=ttid.get(position);


        AlertDialog.Builder ald=new AlertDialog.Builder(viewassignment.this);
        ald.setTitle("do you want to delete")
                .setPositiveButton(" delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {

                            RequestQueue queue = Volley.newRequestQueue(viewassignment.this);
                            url = "http://" + sh.getString("ip","") + ":5000/deleeassigment";

                            // Request a string response from the provided URL.
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the response string.
                                    Log.d("+++++++++++++++++", response);
                                    try {
                                        JSONObject json = new JSONObject(response);
                                        String res = json.getString("task");

                                        if (res.equalsIgnoreCase("success")) {
//                                            String lid = json.getString("id");
//                                            String type = json.getString("type");
//                                            SharedPreferences.Editor edp = sh.edit();
//                                            edp.putString("lid", lid);
//                                            edp.commit();
                                            Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                            startActivity(ik);

                                        } else {

                                            Toast.makeText(viewassignment.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {


                                    Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("tid", tid);


                                    return params;
                                }
                            };
                            queue.add(stringRequest);



                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(" cencel ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent i=new Intent(getApplicationContext(),viewassignment.class);

                        startActivity(i);
                    }
                });

        AlertDialog al=ald.create();
        al.show();

    }
}