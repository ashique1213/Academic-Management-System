package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Home_student extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        b1=findViewById(R.id.button38);
        b2=findViewById(R.id.button39);
        b3=findViewById(R.id.button40);
        b4 =findViewById(R.id.button41);
        b5=findViewById(R.id.button42);
        b6=findViewById(R.id.button43);
        b7=findViewById(R.id.button44);
        b8=findViewById(R.id.button19);
        b9=findViewById(R.id.button45);
        b10=findViewById(R.id.button46);
        b11=findViewById(R.id.button47);
        b12=findViewById(R.id.button48);
        b13=findViewById(R.id.button49);
        b14=findViewById(R.id.button20);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewsubject.class);
                startActivity(i);


            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url2="http://"+sh.getString("ip","")+":5000/timetable";
                Intent dwnl=new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url2));
                startActivity(dwnl);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewassignment.class);
                startActivity(i);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewinternalmark.class);
                startActivity(i);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewexam.class);
                startActivity(i);

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDstudymaterials.class);
                startActivity(i);

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),stdviewstaff.class);
                startActivity(i);

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),online_testSURVEY.class);
                startActivity(i);

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewattendance.class);
                startActivity(i);

            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewannoucement.class);
                startActivity(i);

            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDfeedetails.class);
                startActivity(i);

            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDaddchat.class);
                startActivity(i);
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),STDviewprofile.class);
                startActivity(i);

            }
        });

    }
}