package com.example.coen390_as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    protected final static String TAG = "SettingsActivity";

    protected EditText counter1Name;
    protected EditText counter2Name;
    protected EditText counter3Name;
    protected EditText maxCounts;
    protected Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(TAG, "OnCreate() event");

        setupUI();
    }

    protected void setupUI() {

        counter1Name = (EditText)findViewById(R.id.counter1edittext);
        counter2Name = (EditText)findViewById(R.id.counter2edittext);
        counter3Name = (EditText)findViewById(R.id.counter3edittext);
        maxCounts = (EditText)findViewById(R.id.maxcountedittxt);

        saveBtn = (Button)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(onClickSaveBtn);
    }

    private View.OnClickListener onClickSaveBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };
}