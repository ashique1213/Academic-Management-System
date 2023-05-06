package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    SharedPreferences sh;
    String uname,pswd,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.editTextTextPersonName2);
        e2=findViewById(R.id.editTextTextPersonName3);
        b1=findViewById(R.id.button2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname=e1.getText().toString();
                pswd=e2.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                url = "http://" + sh.getString("ip","") + ":5000/login_code";

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
                                String lid = json.getString("lid");
                                String type = json.getString("type");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("lid", lid);
                                edp.commit();
                                if (type.equalsIgnoreCase("student")) {

                                    Intent ii = new Intent(getApplicationContext(), LocationServiceno.class);
                                    startService(ii);

                                    Intent ik = new Intent(getApplicationContext(), Home_student.class);
                                    startActivity(ik);
                                }
                                else {
                                    Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                    startActivity(ik);
                                }
                            } else {

                                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("uname", uname);
                        params.put("pword", pswd);

                        return params;
                    }
                };
                queue.add(stringRequest);




            }
        });

    }
}