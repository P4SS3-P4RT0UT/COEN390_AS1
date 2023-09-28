
package com.example.coen390_as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Define TAG for debugging purposes
    protected final static String TAG = "MainActivity";
    // Declare layout buttons
    protected Button settingsBtn = null;
    protected Button counter1Btn = null;
    protected Button counter2Btn = null;
    protected Button counter3Btn = null;
    protected Button showMyCountsBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Log.d(TAG, "OnCreate() event");
        
        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void setupUI() {
        //
        settingsBtn = (Button)findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(onClickSettingsBtn);

        settingsBtn = (Button)findViewById(R.id.counter1_btn);
        counter1Btn.setOnClickListener(onClickCounter1Btn);

        settingsBtn = (Button)findViewById(R.id.counter2_btn);
        counter2Btn.setOnClickListener(onClickCounter2Btn);

        settingsBtn = (Button)findViewById(R.id.counter3_btn);
        counter3Btn.setOnClickListener(onClickCounter3Btn);

        settingsBtn = (Button)findViewById(R.id.showmycounts_btn);
        showMyCountsBtn.setOnClickListener(onClickShowMyCountsBtn);
    }

    private View.OnClickListener onClickSettingsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            goToSettings();
        }
    };

    private View.OnClickListener onClickCounter1Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    private View.OnClickListener onClickCounter2Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    private View.OnClickListener onClickCounter3Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    private View.OnClickListener onClickShowMyCountsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    void goToSettings() {
        Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settings);
    }
}