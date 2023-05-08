package com.example.amsapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

public class viewuploadedfile extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;

    SharedPreferences sh;
    ArrayList<String> name,file,date,adm;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewuploadedfile);
        l1=findViewById(R.id.list18);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url ="http://"+sh.getString("ip", "") + ":5000/viewuploadedfiles";
        RequestQueue queue = Volley.newRequestQueue(viewuploadedfile.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    date= new ArrayList<>();
                    file= new ArrayList<>();
                    adm=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("name"));
                        date.add(jo.getString("data"));
                        file.add(jo.getString("file"));
                        adm.add(jo.getString("addmission no"));


                    }

//                     ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
//                    lv.setAdapter(ad);

                    l1.setAdapter(new custom4(viewuploadedfile.this,name,date,file,adm));
//                    l1.setAdapter(new Custom(viewuser.this,name,place));
                    l1.setOnItemClickListener(viewuploadedfile.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
             @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewuploadedfile.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("aaid",getIntent().getStringExtra("asid"));

                return params;
            }
        };
        queue.add(stringRequest);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url2="http://"+sh.getString("ip","")+":5000/static/photos/"+file.get(i);
        Intent dwnl=new Intent(Intent.ACTION_VIEW,
                Uri.parse(url2));
        startActivity(dwnl);
    }
}