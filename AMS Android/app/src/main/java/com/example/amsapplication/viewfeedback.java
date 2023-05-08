package com.example.amsapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

public class viewfeedback extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> qn,op1,op2,op3,op4,ans,qid;
    String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedback);
        l1=findViewById(R.id.list17);
        b1=findViewById(R.id.button9);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url ="http://"+sh.getString("ip", "") + ":5000/viewfeedback";
        RequestQueue queue = Volley.newRequestQueue(viewfeedback.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewfeedback.this, "ccccc"+response, Toast.LENGTH_SHORT).show();
                    qn= new ArrayList<>();
                    op1= new ArrayList<>();
                    op2= new ArrayList<>();
                    op3= new ArrayList<>();
                    op4= new ArrayList<>();
                    ans= new ArrayList<>();
                    qid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);

                        qn.add(jo.getString("feed_questions"));
                        op1.add(jo.getString("op1"));
                        op2.add(jo.getString("op2"));

                        op3.add(jo.getString("op3"));
                        op4.add(jo.getString("op4"));
                        ans.add(jo.getString("ldate"));
                        qid.add(jo.getString("feed_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom6(viewfeedback.this,qn,op1,op2,op3,op4,ans));
                    l1.setOnItemClickListener(viewfeedback.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewfeedback.this, "err"+error, Toast.LENGTH_SHORT).show();
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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik = new Intent(getApplicationContext(), addfeedback.class);
                startActivity(ik);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tid=qid.get(i);

        AlertDialog.Builder ald=new AlertDialog.Builder(viewfeedback.this);
        ald.setTitle("do you want to delete")
                .setPositiveButton(" delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {

                            RequestQueue queue = Volley.newRequestQueue(viewfeedback.this);
                            String url = "http://" + sh.getString("ip","") + ":5000/delectfeed";

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
//                                            String lid = json.getString("id");
//                                            String type = json.getString("type");
//                                            SharedPreferences.Editor edp = sh.edit();
//                                            edp.putString("lid", lid);
//                                            edp.commit();
                                            Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                            startActivity(ik);

                                        } else {

                                            Toast.makeText( viewfeedback.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                                    params.put("tid", tid);


                                    return params;
                                }
                            };
                            queue.add(stringRequest);



                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(" cencel ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent i=new Intent(getApplicationContext(),viewfeedback.class);

                        startActivity(i);
                    }
                });

        AlertDialog al=ald.create();
        al.show();





    }
}