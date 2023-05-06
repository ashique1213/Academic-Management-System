package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class STDfeedetails extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    ArrayList<String> feename,amount,amountpaid,amountdue,lastdate,status;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdfeedetails);
        l1=findViewById(R.id.list1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/viewfeedetails";
        RequestQueue queue = Volley.newRequestQueue(STDfeedetails.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    Toast.makeText(STDfeedetails.this, "fffff"+response, Toast.LENGTH_SHORT).show();
                    feename= new ArrayList<>();
                    amount= new ArrayList<>();
                    amountpaid=new ArrayList<>();
                    amountdue=new ArrayList<>();
                    lastdate=new ArrayList<>();
                    status=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        feename.add(jo.getString("f_id"));
                        amount.add(jo.getString("semester"));
                        amountpaid.add(jo.getString("amount"));
                        amountdue.add(jo.getString("lastdate"));
                        lastdate.add(jo.getString("title"));
                        status.add(jo.getString("description"));

                    }
//
//                     ArrayAdapter<String> ad=new ArrayAdapter<>(STDfeedetails.this,android.R.layout.simple_list_item_1,amount,amountpaid,amountdue,lastdate,status);
//                    l1.setAdapter(ad);

                    l1.setAdapter(new STDfeedcustom(STDfeedetails.this,amount,amountpaid,amountdue,lastdate,status));
                    l1.setOnItemClickListener(STDfeedetails.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(STDfeedetails.this, "err"+error, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("rid",feename.get(position));
        edp.commit();
        AlertDialog.Builder builder = new AlertDialog.Builder(STDfeedetails.this);
        builder.setTitle("options");
        builder.setItems(new CharSequence[]
                        {"view fee status","payment","Cancel"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:

                            {
                                Intent ik = new Intent(getApplicationContext(), STDVIEWFEDDSTATUS.class);
                                ik.putExtra("rid",feename);
                                startActivity(ik);


                            }


                            break;

                            case 1:
                            {
                                Intent ik = new Intent(getApplicationContext(), Payment.class);
                                ik.putExtra("rid",feename.get(position));
                                ik.putExtra("amt",amountpaid.get(position));
                                startActivity(ik);

                            }



                            break;

                            case 2:
                            {
                                Intent ik = new Intent(getApplicationContext(), STDfeedetails.class);
                                startActivity(ik);
                            }

                            break;


                        }
                    }
                });
        builder.create().show();




    }
}