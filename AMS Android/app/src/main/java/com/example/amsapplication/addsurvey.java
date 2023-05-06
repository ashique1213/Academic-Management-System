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

public class addsurvey extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    SharedPreferences sh;
    String feedback,op1,op2,op3,op4,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsurvey);
        e1=findViewById(R.id.editTextTextPersonName21);
        e2=findViewById(R.id.editTextTextPersonName22);
        e3=findViewById(R.id.editTextTextPersonName23);
        e4=findViewById(R.id.editTextTextPersonName24);
        e5=findViewById(R.id.editTextTextPersonName25);
        e6=findViewById(R.id.editTextTextPersonName26);
        b1=findViewById(R.id.button14);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedback=e1.getText().toString();
                op1=e2.getText().toString();
                op2=e3.getText().toString();
                op3=e4.getText().toString();
                op4=e5.getText().toString();
                ans=e6.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(addsurvey.this);
                String url = "http://" + sh.getString("ip","") + ":5000/addsurvey";

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
//                                edp.commit();
                                Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(addsurvey.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("question", feedback);
                        params.put("op1", op1);
                        params.put("op2", op2);
                        params.put("op3", op3);
                        params.put("op4", op4);
                        params.put("ans", ans);

                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}