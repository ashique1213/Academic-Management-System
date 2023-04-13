package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class viewprofile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        e1=findViewById(R.id.editTextTextPersonName24);
        e2=findViewById(R.id.editTextTextPersonName25);
        e3=findViewById(R.id.editTextTextPersonName26);
        e4=findViewById(R.id.editTextTextPersonName27);
        e5=findViewById(R.id.editTextTextPersonName28);
        e6=findViewById(R.id.editTextTextPersonName29);
        b1=findViewById(R.id.button25);
    }
}