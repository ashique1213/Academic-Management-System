package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class viewsurvey extends AppCompatActivity {
    RadioButton r1,r2,r3,r4;
    Button b1,b2;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsurvey);
        r1=findViewById(R.id.radioButton);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton4);
        r4=findViewById(R.id.radioButton3);
        b1=findViewById(R.id.button15);
        b2=findViewById(R.id.button16);
    }
}