package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class STDviewprofile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e9;
    ImageView e8;
    Button b1,b2;
    SharedPreferences sh;
    String name,addno,sem,gender,dob,address,phone,photo,dateofadd;
    String url;
    String PathHolder = "";
    byte[] filedt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewprofile);
        e1=findViewById(R.id.editTextTextPersonName23);
        e2=findViewById(R.id.editTextTextPersonName31);
        e3=findViewById(R.id.editTextTextPersonName32);
        e4=findViewById(R.id.editTextTextPersonName33);
        e5=findViewById(R.id.editTextTextPersonName34);
        e6=findViewById(R.id.editTextTextPersonName35);
        e7=findViewById(R.id.editTextTextPersonName36);
        e8=findViewById(R.id.imageView2);
        e9=findViewById(R.id.editTextTextPersonName38);
        b1=findViewById(R.id.button26);
        b2=findViewById(R.id.button17);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/ssviewprofile";
        RequestQueue queue = Volley.newRequestQueue(STDviewprofile.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    {
//                        Toast.makeText(STDviewprofile.this, "fdff"+response, Toast.LENGTH_SHORT).show();
                        JSONObject jo = ar.getJSONObject(0);
                        e1.setText(jo.getString("name"));
                        e2.setText(jo.getString("addmission no"));
                        e2.setEnabled(false);
                        e3.setText(jo.getString("smester"));
                        e4.setText(jo.getString("gender"));
                        e5.setText(jo.getString("dob"));
                        e6.setText(jo.getString("address"));
                        e7.setText(jo.getString("phone"));
//                        e8.setText(jo.getString("photo"));
                        e9.setText(jo.getString("jointdate"));
                        e9.setEnabled(false);

                        if(Build.VERSION.SDK_INT>9)
                        {
                            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }

//        i2.setImageDrawable(Drawable.createFromPath(getIntent().getStringExtra("photo"))));
                        java.net.URL thumb_u;
                        try {

                            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

                            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/static/photos/"+jo.getString("photo"));
                            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
                            e8.setImageDrawable(thumb_d);

                        }
                        catch (Exception e)
                        {
                            Log.d("errsssssssssssss",""+e);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ii=new Intent(getApplicationContext(),editimage.class);
                startActivity(ii);



            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=e1.getText().toString();
                addno=e2.getText().toString();
                sem=e3.getText().toString();
                gender=e4.getText().toString();
                dob=e5.getText().toString();
                address=e6.getText().toString();
                phone=e7.getText().toString();
//                photo=e8.getText().toString();
                dateofadd=e9.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(STDviewprofile.this);
                String url = "http://" + sh.getString("ip","") + ":5000/editstdprofile";

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
                                Intent ik = new Intent(getApplicationContext(), Home_student.class);
                                startActivity(ik);

                            } else {

                                Toast.makeText(STDviewprofile.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                        params.put("name", name);
                        params.put("ano", addno);
                        params.put("sem", sem);
                        params.put("gender", gender);
                        params.put("dob", dob);
                        params.put("add", address);
                        params.put("ph", phone);
                        params.put("doj", dateofadd);
                        params.put("lid", sh.getString("lid",""));


                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });

    }
}