package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class STDviewattendance extends AppCompatActivity {
    TextView t1,t2,t3;
    SharedPreferences sh;
    String total,abbb,Attendance,tot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewattendance);
        t1=findViewById(R.id.textView72);
        t2=findViewById(R.id.textView67);
        t3=findViewById(R.id.textView68);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


       String url ="http://"+sh.getString("ip", "") + ":5000/viewattendance";
        RequestQueue queue = Volley.newRequestQueue(STDviewattendance.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
                    JSONObject js=new JSONObject(response);
                    total=js.getString("res1");
                    t1.setText(js.getString("res1"));

                    Attendance=js.getString("res2");
                    t2.setText(js.getString("res2"));

                    abbb=js.getString("res3");
                    t3.setText(js.getString("res3"));
//
//
//
//                    JSONArray ar=new JSONArray(response);
//                    total= new ArrayList<>();
//                    abbb= new ArrayList<>();
//                    Attendance= new ArrayList<>();
//
//                    for(int i=0;i<ar.length();i++)
//                    {
//                        JSONObject jo=ar.getJSONObject(i);
//                        total.add(jo.getString("totalworking"));
//                        abbb.add(jo.getString("present"));
//                        Attendance.add(jo.getString("absent"));
//
//
//                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

//                    l1.setAdapter(new custom3(STDviewattendance.this,total,abbb,Attendance));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDviewattendance.this, "err"+error, Toast.LENGTH_SHORT).show();
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