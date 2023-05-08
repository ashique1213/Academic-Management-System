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

public class addquestion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1;
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    SharedPreferences sh;
    String examname,question,op1,op2,op3,op4,ans,eid;
ArrayList<String> name,sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        s1=findViewById(R.id.spinner6);
        e1=findViewById(R.id.editTextTextPersonName12);
        e2=findViewById(R.id.editTextTextPersonName13);
        e3=findViewById(R.id.editTextTextPersonName14);
        e4=findViewById(R.id.editTextTextPersonName15);
        e5=findViewById(R.id.editTextTextPersonName16);
        e6=findViewById(R.id.editTextTextPersonName17);
        b1=findViewById(R.id.button10);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       String url1 ="http://"+sh.getString("ip", "") + ":5000/ssviewtexam";
        RequestQueue queue1 = Volley.newRequestQueue(addquestion.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(addquestion.this, "", Toast.LENGTH_SHORT).show();
                    name= new ArrayList<>();
                    sid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("topic"));
                        sid.add(jo.getString("exam_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(addquestion.this,android.R.layout.simple_spinner_dropdown_item,name);
                    s1.setAdapter(ad);

//                    l1.setAdapter(new Custom(viewuser.this,name,place));
                    s1.setOnItemSelectedListener(addquestion.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addquestion.this, "err"+error, Toast.LENGTH_SHORT).show();
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

                question=e1.getText().toString();
                op1=e2.getText().toString();
                op2=e3.getText().toString();
                op3=e4.getText().toString();
                op4=e5.getText().toString();
                ans=e6.getText().toString();
                if (question.equalsIgnoreCase(""))
                {
                    e1.setError("Enter feedback");
                }
                else  if (op1.equalsIgnoreCase(""))
                {
                    e2.setError("Enter option1");
                }
                else if (op2.equalsIgnoreCase(""))
                {
                    e3.setError("Enter opion2");
                }
                else if (op3.equalsIgnoreCase(""))
                {
                    e4.setError("Enter option3");
                }
                else if (op4.equalsIgnoreCase(""))
                {
                    e5.setError("option4");
                }
                else if (ans.equalsIgnoreCase(""))
                {
                    e6.setError("Enteranswer");
                }


                else {


                    RequestQueue queue = Volley.newRequestQueue(addquestion.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/addquestion";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("valid")) {
//                                String lid = json.getString("id");
//                                String type = json.getString("type");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();\
                                    Toast.makeText(addquestion.this, "success", Toast.LENGTH_SHORT).show();

                                    Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(addquestion.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("question", question);
                            params.put("op1", op1);
                            params.put("op2", op2);
                            params.put("op3", op3);
                            params.put("op4", op4);
                            params.put("ans", ans);
                            params.put("eid", eid);

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        eid=sid.get(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}