package com.example.coen390_as1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DataActivity extends AppCompatActivity {
    protected final static String TAG = "DataActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Log.d(TAG, "OnCreate() event");
    }
}