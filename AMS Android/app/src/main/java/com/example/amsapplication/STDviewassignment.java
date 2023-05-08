package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class STDviewassignment extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;

    SharedPreferences sh;
    ArrayList<String>topic,Descrption,lastdate,asid;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewassignment);
        l1 = findViewById(R.id.list16);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = "http://" + sh.getString("ip", "") + ":5000/ttviewtassignment1";
        RequestQueue queue = Volley.newRequestQueue(STDviewassignment.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);
//                    Toast.makeText(STDviewassignment.this, "ccccc"+response, Toast.LENGTH_SHORT).show();

                    topic = new ArrayList<>();
                    Descrption = new ArrayList<>();
                    lastdate = new ArrayList<>();
                    asid = new ArrayList<>();

                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        topic.add(jo.getString("topic"));
                        Descrption.add(jo.getString("description"));
                        lastdate.add(jo.getString("last date"));
                        asid.add(jo.getString("ass_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom3(STDviewassignment.this, topic, Descrption, lastdate));
                    l1.setOnItemClickListener(STDviewassignment.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDviewassignment.this, "err" + error, Toast.LENGTH_SHORT).show();
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
        edp.putString("aid",asid.get(position));
        edp.commit();
        Intent ik = new Intent(getApplicationContext(), uploadassigment.class);
        ik.putExtra("aid",asid.get(position));
        startActivity(ik);





    }
}