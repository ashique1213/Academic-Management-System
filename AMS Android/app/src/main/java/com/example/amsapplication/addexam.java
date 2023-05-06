package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class addexam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText e1,e2;
    Spinner s1;
    Button b1;
    SharedPreferences sh;
    String subject,date,topic;
ArrayList<String> exam,eid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexam);
        e1=findViewById(R.id.editTextTextPersonName10);
        e2=findViewById(R.id.editTextTextPersonName11);
        s1=findViewById(R.id.spinner5);
        b1=findViewById(R.id.button7);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url1 ="http://"+sh.getString("ip", "") + ":5000/viewsubject";
        RequestQueue queue1 = Volley.newRequestQueue(addexam.this);

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

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addexam.this,android.R.layout.simple_spinner_dropdown_item,exam);
                    s1.setAdapter(ad);
                    s1.setOnItemSelectedListener(addexam.this);
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

                        Toast.makeText(addexam.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue1.add(stringRequest1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date=e1.getText().toString();
                topic=e2.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(addexam.this);
                String url = "http://" + sh.getString("ip","") + ":5000/addexam";

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
//                                String lid = json.getString("id");
//                                String type = json.getString("type");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();
                                Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(addexam.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("topic", topic);
                        params.put("date", date);
                        params.put("sid", subject);

                        return params;
                    }
                };
                queue.add(stringRequest);









            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject=eid.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}