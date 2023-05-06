package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class STDviewinternalmark extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> Subject,Exam,mark,date;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewinternalmark);
        l1=findViewById(R.id.list8);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/ssviewtinternal";
        RequestQueue queue = Volley.newRequestQueue(STDviewinternalmark.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(STDviewinternalmark.this, "ddd"+response, Toast.LENGTH_SHORT).show();
                    Subject= new ArrayList<>();
                    Exam= new ArrayList<>();
                    mark= new ArrayList<>();
                    date=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        Subject.add(jo.getString("subject name"));
                        Exam.add(jo.getString("topic"));
                        mark.add(jo.getString("mark"));
                        date.add(jo.getString("date"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom4(STDviewinternalmark.this,Subject,Exam,mark,date));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDviewinternalmark.this, "err"+error, Toast.LENGTH_SHORT).show();
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
    }
}