package com.example.amsapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Home_teacher extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b100;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        b1=findViewById(R.id.button27);
        b100=findViewById(R.id.button50);
        b2=findViewById(R.id.button28);
        b3=findViewById(R.id.button29);
        b4 =findViewById(R.id.button30);
        b5=findViewById(R.id.button31);
        b6=findViewById(R.id.button32);
        b7=findViewById(R.id.button33);
        b8=findViewById(R.id.button34);
        b9=findViewById(R.id.button35);
        b10=findViewById(R.id.button36);
        b11=findViewById(R.id.button37);
        b12=findViewById(R.id.button23);
        b13=findViewById(R.id.button24);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ald=new AlertDialog.Builder(Home_teacher.this);
                ald.setTitle("choose")
                        .setPositiveButton(" add actualplan", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                try
                                {



                                    String url1="http://"+sh.getString("ip","")+":5000/addactualplan?id="+sh.getString("lid","")+"";
                                    Intent dwnl1=new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(url1));
                                    startActivity(dwnl1);



                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton(" add proposedplan ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                                String url3="http://"+sh.getString("ip","")+":5000/addproposedplan?id="+sh.getString("lid","")+"";
                                Intent dwnl3=new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(url3));
                                startActivity(dwnl3);

                            }
                        });

                AlertDialog al=ald.create();
                al.show();


            }

        });
        b100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(), viewquestion.class);
                startActivity(ii);

            }

        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url2="http://"+sh.getString("ip","")+":5000/timetable";
                Intent dwnl=new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url2));
                startActivity(dwnl);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewstudents.class);
                startActivity(i);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewassignment.class);
                startActivity(i);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewinternalmarks.class);
                startActivity(i);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewexam.class);
                startActivity(i);


            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewstudymat.class);
                startActivity(i);


            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewfeedback.class);
                startActivity(i);


            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewsurvey.class);
                startActivity(i);


            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),staffviewattendance.class);
                startActivity(i);


            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),viewprofile.class);
                startActivity(i);

            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addchat.class);
                startActivity(i);

            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);

            }
        });
    }
}