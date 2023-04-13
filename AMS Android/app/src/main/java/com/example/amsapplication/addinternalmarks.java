package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class addinternalmarks extends AppCompatActivity {
    Spinner s1,s2;
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinternalmarks);
        s1=findViewById(R.id.spinner3);
        s1=findViewById(R.id.spinner4);
        e1=findViewById(R.id.editTextTextPersonName7);
        e2=findViewById(R.id.editTextTextPersonName8);
        e3=findViewById(R.id.editTextTextPersonName9);
        b1=findViewById(R.id.button6);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}