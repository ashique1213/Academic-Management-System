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

public class STDviewexam extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView l1;
    SharedPreferences sh;
    ArrayList<String> Subject,Topic,dateofsub,time;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewexam);
        l1=findViewById(R.id.list7);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/ssviewtexam";
        RequestQueue queue = Volley.newRequestQueue(STDviewexam.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Subject= new ArrayList<>();
                    Topic= new ArrayList<>();
                    dateofsub= new ArrayList<>();
                    time= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        Subject.add(jo.getString("exam_id"));
                        dateofsub.add(jo.getString("date"));
                        Topic.add(jo.getString("topic"));
                        time.add(jo.getString("time"));


                    }

                     ArrayAdapter<String> ad=new ArrayAdapter<>(STDviewexam.this,android.R.layout.simple_list_item_1,Topic);
                    l1.setAdapter(ad);

//                    l1.setAdapter(new Custom(viewuser.this,name,place));
                    l1.setOnItemClickListener(STDviewexam.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDviewexam.this, "err"+error, Toast.LENGTH_SHORT).show();
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
        edp.putString("pid",Subject.get(position));
        edp.commit();

        Intent ik = new Intent(getApplicationContext(), online_test.class);
        ik.putExtra("pid",Subject.get(position));
        ik.putExtra("time",time.get(position));
        startActivity(ik);
    }
}