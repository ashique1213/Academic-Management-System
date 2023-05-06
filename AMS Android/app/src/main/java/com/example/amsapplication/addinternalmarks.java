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

import androidx.appcompat.app.AppCompatActivity;

public class addinternalmarks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1,s2;
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> name,sid,subject,subid;
    String subjectid,studentid,examname,mark,date,url1,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinternalmarks);
        s1=findViewById(R.id.spinner3);
        s2=findViewById(R.id.spinner4);
 
        e2=findViewById(R.id.editTextTextPersonName8);

        b1=findViewById(R.id.button6);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/viewstudents1";
        RequestQueue queue = Volley.newRequestQueue(addinternalmarks.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    subid= new ArrayList<>();
                    subject= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        subid.add(jo.getString("login_id"));
                        subject.add(jo.getString("name"));



                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addinternalmarks.this,android.R.layout.simple_list_item_1,subject);
                    s2.setAdapter(ad);
s2.setOnItemSelectedListener(addinternalmarks.this);
//                    l1.setAdapter(new Custom(viewuser.this,name,place));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addinternalmarks.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);
        url1 ="http://"+sh.getString("ip", "") + ":5000/ssviewtexam";
        RequestQueue queue1 = Volley.newRequestQueue(addinternalmarks.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(addinternalmarks.this, "", Toast.LENGTH_SHORT).show();
                    name= new ArrayList<>();
                    sid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("topic"));
                        sid.add(jo.getString("exam_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addinternalmarks.this,android.R.layout.simple_spinner_dropdown_item,name);
                    s1.setAdapter(ad);

//                    l1.setAdapter(new Custom(viewuser.this,name,place));
                   s1.setOnItemSelectedListener(addinternalmarks.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addinternalmarks.this, "err"+error, Toast.LENGTH_SHORT).show();
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


                mark=e2.getText().toString();


                RequestQueue queue = Volley.newRequestQueue(addinternalmarks.this);
                url = "http://" + sh.getString("ip","") + ":5000/addinternal";

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
                                Toast.makeText(addinternalmarks.this, "success", Toast.LENGTH_SHORT).show();
                                Intent ik = new Intent(getApplicationContext(), viewinternalmarks.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(addinternalmarks.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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

                        params.put("mark", mark);

                        params.put("eid", subjectid);
                        params.put("sid", studentid);

                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        studentid=subid.get(i);
        subjectid=sid.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}