package com.example.amsapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class staffviewattendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView t1,t2,t3,t4;
    SharedPreferences sh;
    String total,abbb,Attendance,tot,per;
    Spinner s1;
    Button b1,b2;
    ArrayList<String>stdent,perc,sub,sid;
    String siid,url;
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffviewattendance);
        t1=findViewById(R.id.textView95);
        t2=findViewById(R.id.textView67);
        t3=findViewById(R.id.textView68);
        t4=findViewById(R.id.textView97);
        s1=findViewById(R.id.spinner13);
        b1=findViewById(R.id.button16);
        b2=findViewById(R.id.button25);
        l1=findViewById(R.id.eee);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Attendancemark.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url ="http://"+sh.getString("ip", "") + ":5000/staffviewattendance";
                RequestQueue queue = Volley.newRequestQueue(staffviewattendance.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++",response);
                        try {

                            JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewexam.this, "ggg"+response, Toast.LENGTH_SHORT).show();
                            stdent= new ArrayList<>();
                            perc= new ArrayList<>();


                            for(int i=0;i<ar.length();i++)
                            {
                                JSONObject jo=ar.getJSONObject(i);
                                stdent.add(jo.getString("name"));
                                perc.add(jo.getString("per"));


                            }

                            // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                            //lv.setAdapter(ad);

                            l1.setAdapter(new custion2forall(staffviewattendance.this,stdent,perc));
//                    l1.setOnItemClickListener(viewuser.this);

                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                        }

                    }

                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(staffviewattendance.this, "err"+error, Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("sid",siid);

                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });

        String url1 ="http://"+sh.getString("ip", "") + ":5000/viewsubjectonlyforexam";
        RequestQueue queue1 = Volley.newRequestQueue(staffviewattendance.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewinternalmarks.this, "vvv"+response, Toast.LENGTH_SHORT).show();
                    sub= new ArrayList<>();
                    sid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        sub.add(jo.getString("subject name"));
                        sid.add(jo.getString("subj_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<String >(staffviewattendance.this,android.R.layout.simple_spinner_dropdown_item,sub);
                    s1.setAdapter(ad);
                    s1.setOnItemSelectedListener(staffviewattendance.this);
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

                        Toast.makeText(staffviewattendance.this, "err"+error, Toast.LENGTH_SHORT).show();
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        siid=sid.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}