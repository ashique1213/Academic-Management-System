package com.example.amsapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewinternalmarks extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener{
    ListView l1;
    Spinner s1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> subject,student,exam,eid,mark,date,ttid;
    String url,eeid,tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinternalmarks);
        l1=findViewById(R.id.list);
        s1=findViewById(R.id.spinner);
        b1=findViewById(R.id.button5);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent ii=new Intent(getApplicationContext(),addinternalmarks.class);
        startActivity(ii);
    }
    });
         String url1 ="http://"+sh.getString("ip", "") + ":5000/viewsubjectonlyforexam";
        RequestQueue queue1 = Volley.newRequestQueue(viewinternalmarks.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewinternalmarks.this, "vvv"+response, Toast.LENGTH_SHORT).show();
                    exam= new ArrayList<>();
                    eid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);


                        exam.add(jo.getString("subject name"));
                        eid.add(jo.getString("subj_id"));
                    }

                     ArrayAdapter<String> ad=new ArrayAdapter<>(viewinternalmarks.this,android.R.layout.simple_spinner_dropdown_item,exam);
                    s1.setAdapter(ad);
                  s1.setOnItemSelectedListener(viewinternalmarks.this);
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

                        Toast.makeText(viewinternalmarks.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };
        queue1.add(stringRequest1);






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
eeid=eid.get(position);

        url ="http://"+sh.getString("ip", "") + ":5000/ttviewtinternal";
        RequestQueue queue = Volley.newRequestQueue(viewinternalmarks.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewinternalmarks.this, ""+response, Toast.LENGTH_SHORT).show();
                    subject= new ArrayList<>();
                    student= new ArrayList<>();
//
                    mark=new ArrayList<>();
                    date=new ArrayList<>();
                    ttid=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        subject.add(jo.getString("smester"));
                        student.add(jo.getString("name"));
//
                        mark.add(jo.getString("mark"));
                        date.add(jo.getString("date"));
                        ttid.add(jo.getString("in_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom3(viewinternalmarks.this,student,mark,subject));
                    l1.setOnItemClickListener(viewinternalmarks.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewinternalmarks.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("eid",eeid);

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tid=ttid.get(i);


        AlertDialog.Builder ald=new AlertDialog.Builder(viewinternalmarks.this);
        ald.setTitle("do you want to delete")
                .setPositiveButton(" delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {

                            RequestQueue queue = Volley.newRequestQueue(viewinternalmarks.this);
                            url = "http://" + sh.getString("ip","") + ":5000/deleinnn";

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

                                            Toast.makeText(viewinternalmarks.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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

                        Intent i=new Intent(getApplicationContext(),viewinternalmarks.class);

                        startActivity(i);
                    }
                });

        AlertDialog al=ald.create();
        al.show();
    }
}