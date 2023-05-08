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
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewquestion extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ListView l1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> qn,op1,op2,op3,op4,ans,qid,name,sid;
    String tid;
    Spinner s1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewquestion);
        l1=findViewById(R.id.list17);
        b1=findViewById(R.id.button9);
        s1=findViewById(R.id.spinner9);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String url1 ="http://"+sh.getString("ip", "") + ":5000/ssviewtexam";
        RequestQueue queue1 = Volley.newRequestQueue(viewquestion.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(viewquestion.this, "", Toast.LENGTH_SHORT).show();
                    name= new ArrayList<>();
                    sid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("topic"));
                        sid.add(jo.getString("exam_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<String>(viewquestion.this,android.R.layout.simple_spinner_dropdown_item,name);
                    s1.setAdapter(ad);

//                    l1.setAdapter(new Custom(viewuser.this,name,place));
                    s1.setOnItemSelectedListener(viewquestion.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewquestion.this, "err"+error, Toast.LENGTH_SHORT).show();
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
                Intent ik = new Intent(getApplicationContext(), addquestion.class);
                startActivity(ik);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tid=qid.get(i);

        AlertDialog.Builder ald=new AlertDialog.Builder(viewquestion.this);
        ald.setTitle("do you want to delete")
                .setPositiveButton(" delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        try
                        {

                            RequestQueue queue = Volley.newRequestQueue(viewquestion.this);
                            String url = "http://" + sh.getString("ip","") + ":5000/selectqn";

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

                                            Toast.makeText( viewquestion.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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

                        Intent i=new Intent(getApplicationContext(),viewquestion.class);

                        startActivity(i);
                    }
                });

        AlertDialog al=ald.create();
        al.show();





    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String url ="http://"+sh.getString("ip", "") + ":5000/viewqn";
        RequestQueue queue = Volley.newRequestQueue(viewquestion.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewquestion.this, "ccccc"+response, Toast.LENGTH_SHORT).show();
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

                        qn.add(jo.getString("question"));
                        op1.add(jo.getString("op1"));
                        op2.add(jo.getString("op2"));

                        op3.add(jo.getString("op3"));
                        op4.add(jo.getString("op4"));
                        ans.add(jo.getString("ans"));
                        qid.add(jo.getString("q_id"));

                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom6(viewquestion.this,qn,op1,op2,op3,op4,ans));
                    l1.setOnItemClickListener(viewquestion.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(viewquestion.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("eeid",sid.get(i));

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}