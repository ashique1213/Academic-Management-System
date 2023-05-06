package com.example.amsapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class online_testFEEDBACK extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    SharedPreferences sh;
    String url;
    ArrayList<String>question,op1,op2,op3,op4,answer,qid;
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    String qstn_id;
    public int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_testfeedback);
        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        final TextView counttime=findViewById(R.id.textView9);
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                counttime.setText("Time Over");
                Toast.makeText(getApplicationContext(), "Time Over...", Toast.LENGTH_SHORT).show();

                Intent in=new Intent(getApplicationContext(),Home_student.class);
                startActivity(in);
            }
        }.start();
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/get_questionfeedback";
        RequestQueue queue = Volley.newRequestQueue(online_testFEEDBACK.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    qid= new ArrayList<>();

                    question= new ArrayList<>();
                    op1= new ArrayList<>();
                    op2= new ArrayList<>();
                    op3=new ArrayList<>();
                    op4=new ArrayList<>();
                    answer=new ArrayList<>();
//                    photo=new ArrayList<>();
                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        qid.add(jo.getString("feed_id"));
                        question.add(jo.getString("feed_questions"));
                        op1.add(jo.getString("op1"));
                        op2.add(jo.getString("op2"));
                        op3.add(jo.getString("op3"));
                        op4.add(jo.getString("op4"));
                        answer.add(jo.getString("ans"));

                    }

                    tv=(TextView) findViewById(R.id.tvque);

                    radio_g=(RadioGroup)findViewById(R.id.answersgrp);
                    rb1=(RadioButton)findViewById(R.id.radioButton);
                    rb2=(RadioButton)findViewById(R.id.radioButton2);
                    rb3=(RadioButton)findViewById(R.id.radioButton3);
                    rb4=(RadioButton)findViewById(R.id.radioButton4);
                    tv.setText(question.get(flag));
                    rb1.setText(op1.get(flag));
                    rb2.setText(op2.get(flag));
                    rb3.setText(op3.get(flag));
                    rb4.setText(op4.get(flag));




                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

//                    l1.setAdapter(new custom_booking(view_booking.this,cname,course,sno,sid));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(online_testFEEDBACK.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("pid",getIntent().getStringExtra("pid").toString());
                params.put("pid", sh.getString("pid",""));


                return params;
            }
        };
        queue.add(stringRequest);


        final TextView score = (TextView)findViewById(R.id.textView4);

//        TextView textView=(TextView)findViewById(R.id.DispName);
//        Intent intent = getIntent();
//        String name= intent.getStringExtra("myname");
//
//        if (name.trim().equals(""))
//            textView.setText("Hello User");
//        else
//            textView.setText("Hello " + name);




//
//


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if (ansText.equals(answer.get(flag))) {
                    correct++;
                    SharedPreferences.Editor e1=sh.edit();
                    e1.putString("res","1");
                    e1.putString("qid",qid.get(flag));
                    e1.commit();
                    String url = "http://" + sh.getString("ip", "") + ":5000/answertestfeddback";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");
//                            radio_g.clearCheck();
                                if (res.equalsIgnoreCase("success")) {

                                } else {

                                    Toast.makeText(online_testFEEDBACK.this, "Invalid ", Toast.LENGTH_SHORT).show();

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

//                        final String value = ((RadioButton) findViewById(radio_g.getCheckedRadioButtonId())).getText().toString();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("qid", sh.getString("qid",""));
                            params.put("ans",ansText);
                            params.put("res", "1");

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                    Toast.makeText(getApplicationContext(), "Correct"+qid.get(flag), Toast.LENGTH_SHORT).show();
                } else {
                    wrong++;
                    SharedPreferences.Editor e1=sh.edit();
                    e1.putString("res","0");
                    e1.putString("qid",qid.get(flag));
                    e1.commit();
                    String url = "http://" + sh.getString("ip", "") + ":5000/answertestfeddback";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");
//                            radio_g.clearCheck();
                                if (res.equalsIgnoreCase("success")) {

                                } else {

                                    Toast.makeText(online_testFEEDBACK.this, "Invalid ", Toast.LENGTH_SHORT).show();

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

//                        final String value = ((RadioButton) findViewById(radio_g.getCheckedRadioButtonId())).getText().toString();
                            params.put("lid", sh.getString("lid", ""));
                            params.put("qid", sh.getString("qid",""));
                            params.put("ans",ansText);
                            params.put("res", "0");

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                    Toast.makeText(getApplicationContext(), "Wrong"+qid.get(flag), Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText("" + correct);
//                Toast.makeText(getApplicationContext(), "(((***:"+flag+":"+question.size(), Toast.LENGTH_SHORT).show();

                if (flag < question.size()) {
                    tv.setText(question.get(flag));
                    rb1.setText(op1.get(flag));
                    rb2.setText(op2.get(flag));
                    rb3.setText(op3.get(flag));
                    rb4.setText(op4.get(flag));

                } else {
                    Toast.makeText(getApplicationContext(), "Exam finished...", Toast.LENGTH_SHORT).show();
                    marks=correct;
//                    Toast.makeText(online_test.this, "marks"+marks, Toast.LENGTH_SHORT).show();


                                AlertDialog.Builder builder = new AlertDialog.Builder(online_testFEEDBACK.this);
                                builder.setTitle("Exam Over!!! Press OK to Exit");
                                builder.setItems(new CharSequence[]
                                                {"Ok"},
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // The 'which' argument contains the index position
                                                // of the selected item
                                                switch (which) {
                                                    case 0:

                                                    {
                                                        Intent in = new Intent(getApplicationContext(),Home_student.class);
                                                        startActivity(in);
                                                    }


                                                    break;


                                                }
                                            }
                                        });
                                builder.create().show();

            }
           radio_g.clearCheck();
        }
    });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),STDviewexam.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent ik = new Intent(getApplicationContext(), Home_student.class);
        startActivity(ik);
    }
}
