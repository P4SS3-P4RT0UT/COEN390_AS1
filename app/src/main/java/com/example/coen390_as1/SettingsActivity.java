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

    protected SharedPreferencesHelper sharedPreferencesHelper;

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
        sharedPreferencesHelper = new SharedPreferencesHelper(SettingsActivity.this);
    }

    protected void setupUI() {

        counter1Name = findViewById(R.id.counter1edittext);
        counter2Name = findViewById(R.id.counter2edittext);
        counter3Name = findViewById(R.id.counter3edittext);
        maxCounts = findViewById(R.id.maxcountedittxt);

        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(onClickSaveBtn);
    }

    private final View.OnClickListener onClickSaveBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String counter1 = counter1Name.getText().toString();
            String counter2 = counter2Name.getText().toString();
            String counter3 = counter3Name.getText().toString();
            int max = Integer.parseInt(maxCounts.getText().toString());
            sharedPreferencesHelper.saveSettings(new Settings(counter1, counter2, counter3, max));
            goToMain();
        }
    };

    void goToMain() {
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }
}