package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
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

public class STDviewsubject extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView l1 ;
    Spinner s1;
    SharedPreferences sh;
    ArrayList<String> Subject,Exam,mark;
    String year[] = {"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewsubject);
        l1=findViewById(R.id.list9);
        s1=findViewById(R.id.spinner11);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ArrayAdapter<String> ad = new ArrayAdapter<>(STDviewsubject.this, android.R.layout.simple_spinner_dropdown_item, year);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(STDviewsubject.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String url ="http://"+sh.getString("ip", "") + ":5000/ciewsubject1";
        RequestQueue queue = Volley.newRequestQueue(STDviewsubject.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Subject= new ArrayList<>();
                    Exam= new ArrayList<>();
                    mark= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        Subject.add(jo.getString("subject name"));
                        Exam.add(jo.getString("semester"));
                        mark.add(jo.getString("credit"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom3(STDviewsubject.this,Subject,Exam,mark));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDviewsubject.this, "err"+error, Toast.LENGTH_SHORT).show();
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}