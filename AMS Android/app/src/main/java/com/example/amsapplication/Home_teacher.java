package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Home_teacher extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        b1=findViewById(R.id.button27);
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
                Intent ii=new Intent(getApplicationContext(),addchat.class);
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
                Intent i=new Intent(getApplicationContext(),addstudymat.class);
                startActivity(i);


            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addfeedback.class);
                startActivity(i);


            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addsurvey.class);
                startActivity(i);


            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Attendancemark.class);
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