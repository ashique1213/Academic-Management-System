package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.Subject;

public class Attendancemark extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1,s2,s3;
    EditText e1;
    Button  b1;
    RadioButton r1,r2;
    SharedPreferences sh;
    String subject,datee,hour,student,att;
    ArrayList<String> eid,exam,sub,sid;
    String hur[]={"1","2","3","5","6","7"};

    final Calendar myCalendar= Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancemark);
        s1=findViewById(R.id.spinner8);
        s2=findViewById(R.id.spinner9);
        s3=findViewById(R.id.spinner10);
        e1=findViewById(R.id.editTextTextPersonName30);
        r1=findViewById(R.id.radioButton9);
        r2=findViewById(R.id.radioButton10);
        b1=findViewById(R.id.button18);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Attendancemark.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ArrayAdapter<String> ad = new ArrayAdapter<>(Attendancemark.this, android.R.layout.simple_spinner_dropdown_item, hur);
        s2.setAdapter(ad);
//        s1.setOnItemSelectedListener(Attendancemark.this);

        String url1 ="http://"+sh.getString("ip", "") + ":5000/viewsubject";
        RequestQueue queue1 = Volley.newRequestQueue(Attendancemark.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
//                    Toast.makeText(viewinternalmarks.this, "vvv"+response, Toast.LENGTH_SHORT).show();
                    exam= new ArrayList<>();
                    eid= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        exam.add(jo.getString("subject name"));
                        eid.add(jo.getString("subj_id"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(Attendancemark.this,android.R.layout.simple_spinner_dropdown_item,exam);
                    s1.setAdapter(ad);
                    s1.setOnItemSelectedListener(Attendancemark.this);
//                    l1.setAdapter(new Custom(viewuser.this,name,place));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Attendancemark.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue1.add(stringRequest1);



       String url5 ="http://"+sh.getString("ip", "") + ":5000/viewstudents1";
        RequestQueue queue5 = Volley.newRequestQueue(Attendancemark.this);

        StringRequest stringRequest5 = new StringRequest(Request.Method.POST, url5,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    sid= new ArrayList<>();
                    sub= new ArrayList<>();


                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        sid.add(jo.getString("login_id"));
                        sub.add(jo.getString("name"));



                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(Attendancemark.this,android.R.layout.simple_list_item_1,sub);
                    s3.setAdapter(ad);
                    s1.setOnItemSelectedListener(Attendancemark.this);
//                    l1.setAdapter(new Custom(viewuser.this,name,place));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Attendancemark.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue5.add(stringRequest5);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datee=e1.getText().toString();
                att="";
                if(r1.isChecked())
                {
                    att=r1.getText().toString();
                }
                else
                {
                    att=r2.getText().toString();
                }

                RequestQueue queue = Volley.newRequestQueue(Attendancemark.this);
                String url = "http://" + sh.getString("ip","") + ":5000/addatten";

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

                                Toast.makeText(Attendancemark.this, "Invalid", Toast.LENGTH_SHORT).show();

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

                        params.put("date", datee);
                        params.put("eid", subject);
                        params.put("sid", student);
                        params.put("att", att);
                        params.put("hur", s2.getSelectedItem().toString());

                        return params;
                    }
                };
                queue.add(stringRequest);



            }
        });

    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        e1.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject=eid.get(position);
        student=sid.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}