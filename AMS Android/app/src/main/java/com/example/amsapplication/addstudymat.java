package com.example.amsapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addstudymat extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText e1,e2,e3;
    Spinner s1;
    Button b1,b2;
    SharedPreferences sh;
    String ssid,subject,topic,data,date,url;
    ArrayList<String> sub,sid;
    String PathHolder = "";
    String title,pid;
    byte[] filedt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudymat);
        e1 = findViewById(R.id.editTextTextPersonName18);
        e2 = findViewById(R.id.editTextTextPersonName19);
        e3 = findViewById(R.id.editTextTextPersonName20);
        b1 = findViewById(R.id.button11);
        b2 = findViewById(R.id.button12);
        s1 = findViewById(R.id.spinner7);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url3 = "http://" + sh.getString("ip", "") + ":5000/viewsubject";
        RequestQueue queue1 = Volley.newRequestQueue(addstudymat.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {

                    JSONArray ar = new JSONArray(response);
                    Toast.makeText(addstudymat.this, "hgggg"+response, Toast.LENGTH_SHORT).show();
                    sid = new ArrayList<>();
                    sub = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        sid.add(jo.getString("subj_id"));
                        sub.add(jo.getString("subject name"));


                    }

                    ArrayAdapter<String> ad = new ArrayAdapter<>(addstudymat.this, android.R.layout.simple_spinner_dropdown_item, sub);
                    s1.setAdapter(ad);
//                    l1.setAdapter(new custom2(STDviewannoucement.this,Ancment,date));
                    s1.setOnItemSelectedListener(addstudymat.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(addstudymat.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue1.add(stringRequest1);

        url = "http://" + sh.getString("ip", "") + ":5000/addstudym";

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                topic = e1.getText().toString();

                date = e3.getText().toString();
                uploadBitmap(title);
            }
        });
    }
        ProgressDialog pd;
        private void uploadBitmap(final String title) {
            pd=new ProgressDialog(addstudymat.this);
            pd.setMessage("Uploading....");
            pd.show();
            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response1) {
                            pd.dismiss();
                            String x=new String(response1.data);
                            try {
                                JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                                if (obj.getString("task").equalsIgnoreCase("success")) {

                                    Toast.makeText(addstudymat.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),Home_teacher.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();




                    params.put("lid",sh.getString("lid",""));
                    params.put("sid",ssid);
                    params.put("topic",topic);
                    params.put("date",date);


                    return params;
                }

                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();


                    params.put("image", new DataPart(PathHolder , filedt ));















                    return params;
                }
            };

            Volley.newRequestQueue(this).add(volleyMultipartRequest);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 7:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        Log.d("File Uri", "File Uri: " + uri.toString());
                        // Get the path
                        try {
                            PathHolder = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                            filedt = getbyteData(PathHolder);
                            Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                            e2.setText(PathHolder);
                        }
                        catch (Exception e){
                            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }

        private byte[] getbyteData(String pathHolder) {
            Log.d("path", pathHolder);
            File fil = new File(pathHolder);
            int fln = (int) fil.length();
            byte[] byteArray = null;
            try {
                InputStream inputStream = new FileInputStream(fil);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[fln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                byteArray = bos.toByteArray();
                inputStream.close();
            } catch (Exception e) {
            }
            return byteArray;


        }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ssid=sid.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}