package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addfeedback extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    SharedPreferences sh;
    String feedback,op1,op2,op3,op4,ldate;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfeedback);
        e1=findViewById(R.id.editTextTextPersonName21);
        e2=findViewById(R.id.editTextTextPersonName22);
        e3=findViewById(R.id.editTextTextPersonName23);
        e4=findViewById(R.id.editTextTextPersonName24);
        e5=findViewById(R.id.editTextTextPersonName25);
        e6=findViewById(R.id.editTextTextPersonName9);

        b1=findViewById(R.id.button14);
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

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addfeedback.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedback=e1.getText().toString();
                op1=e2.getText().toString();
                op2=e3.getText().toString();
                op3=e4.getText().toString();
                op4=e5.getText().toString();
                ldate=e6.getText().toString();

                if (feedback.equalsIgnoreCase(""))
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

                else if(ldate.equalsIgnoreCase(""))
                {
                    e6.setError("enter last date");
                }



                else {


                    RequestQueue queue = Volley.newRequestQueue(addfeedback.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/addfeedback";

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
//
                                    Intent ik = new Intent(getApplicationContext(), Home_teacher.class);
                                    startActivity(ik);

                                } else {

                                    Toast.makeText(addfeedback.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

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
                            params.put("ldate", ldate);
                            params.put("lid", sh.getString("lid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });
    }
    private void updateLabel(){
        String myFormat="MM-dd-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        e6.setText(dateFormat.format(myCalendar.getTime()));
    }
}