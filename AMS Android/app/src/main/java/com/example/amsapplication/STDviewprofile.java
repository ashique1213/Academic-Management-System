package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class STDviewprofile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdviewprofile);
        e1=findViewById(R.id.editTextTextPersonName23);
        e2=findViewById(R.id.editTextTextPersonName31);
        e3=findViewById(R.id.editTextTextPersonName32);
        e4=findViewById(R.id.editTextTextPersonName33);
        e5=findViewById(R.id.editTextTextPersonName34);
        e6=findViewById(R.id.editTextTextPersonName35);
        e7=findViewById(R.id.editTextTextPersonName36);
        e8=findViewById(R.id.editTextTextPersonName37);
        e9=findViewById(R.id.editTextTextPersonName38);
        b1=findViewById(R.id.button26);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}