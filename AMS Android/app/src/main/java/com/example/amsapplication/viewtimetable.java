package com.example.amsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;

public class viewtimetable extends AppCompatActivity {
    WebView l1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtimetable);
        l1=findViewById(R.id.list19);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}