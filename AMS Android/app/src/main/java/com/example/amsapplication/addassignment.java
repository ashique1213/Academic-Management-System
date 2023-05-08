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

public class addassignment extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;
    String topic,details,date,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addassignment);
        e1=findViewById(R.id.editTextTextPersonName4);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName6);
        b1=findViewById(R.id.button3);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                topic=e1.getText().toString();
                details=e2.getText().toString();
                date=e3.getText().toString();
                if (topic.equalsIgnoreCase(""))
                {
                    e1.setError("Enter topic");
                }
                else if (details.equalsIgnoreCase(""))
                {
                    e2.setError("Enter details");
                }
                else if (date.equalsIgnoreCase(""))
                {
                    e1.setError("Enter date");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(addassignment.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/addassigment";

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
//                                String lid = json.getString("id");
//                                String type = json.getString("type");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();
                                    Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(addassignment.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("topic", topic);
                            params.put("des", details);
                            params.put("ldate", date);
                            params.put("lid", sh.getString("lid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }





            }
        });
    }
}