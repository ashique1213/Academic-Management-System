package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class STDstudymaterials extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> topic,materials,mmid;
    String url,mid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdstudymaterials);
        l1=findViewById(R.id.list3);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/ssviewstudymat";
        RequestQueue queue = Volley.newRequestQueue(STDstudymaterials.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(STDstudymaterials.this, ""+response, Toast.LENGTH_SHORT).show();
                    ArrayList<String> topic,materials;
                    topic= new ArrayList<>();
                    mmid= new ArrayList<>();
                    materials= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        topic.add(jo.getString("topic"));
                        materials.add(jo.getString("materials"));
                        mmid.add(jo.getString("mat_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custion2forall(STDstudymaterials.this,topic,materials));
                    l1.setOnItemClickListener(STDstudymaterials.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDstudymaterials.this, "err"+error, Toast.LENGTH_SHORT).show();
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

        String url2="http://"+sh.getString("ip","")+":5000/static/photos/"+materials.get(position);
        Intent dwnl=new Intent(Intent.ACTION_VIEW,
                Uri.parse(url2));
        startActivity(dwnl);
    }
}
