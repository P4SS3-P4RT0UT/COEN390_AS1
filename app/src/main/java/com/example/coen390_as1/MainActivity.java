
package com.example.coen390_as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
// update all attributes from protected to private
public class MainActivity extends AppCompatActivity {
    // Define TAG for debugging purposes
    protected final static String TAG = "MainActivity";

    protected SharedPreferencesHelper sharedPreferencesHelper;

    // Declare layout buttons
    protected Button settingsBtn;
    protected Button counter1Btn;
    protected Button counter2Btn;
    protected Button counter3Btn;
    protected TextView counts;
    protected Button showMyCountsBtn;

    protected static int counter1 = 0;
    protected static int counter2 = 0;
    protected static int counter3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate() event");
        setupUI();
        sharedPreferencesHelper = new SharedPreferencesHelper(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //String counter1Name = sharedPreferencesHelper.getSettings().getCounter1Name();
        //if(counter1Name == null) goToSettings();
        if(sharedPreferencesHelper.uninitializedSettings()) goToSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        counter1Btn.setText(sharedPreferencesHelper.getSettings().getCounter1Name());
        counter2Btn.setText(sharedPreferencesHelper.getSettings().getCounter2Name());
        counter3Btn.setText(sharedPreferencesHelper.getSettings().getCounter3Name());
    }

    protected void setupUI() {

        counts = findViewById(R.id.totalcountview);

        settingsBtn = findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(onClickSettingsBtn);

        counter1Btn = findViewById(R.id.counter1_btn);
        counter1Btn.setOnClickListener(onClickCounter1Btn);

        counter2Btn = findViewById(R.id.counter2_btn);
        counter2Btn.setOnClickListener(onClickCounter2Btn);

        counter3Btn = findViewById(R.id.counter3_btn);
        counter3Btn.setOnClickListener(onClickCounter3Btn);

        showMyCountsBtn= findViewById(R.id.showmycounts_btn);
        showMyCountsBtn.setOnClickListener(onClickShowMyCountsBtn);
    }

    private final View.OnClickListener onClickSettingsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            goToSettings();
        }
    };

    private final View.OnClickListener onClickCounter1Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(belowMaxCounts()) counter1++;
            updateCounter();
        }
    };

    private final View.OnClickListener onClickCounter2Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(belowMaxCounts()) counter2++;
            updateCounter();
        }
    };

    private final View.OnClickListener onClickCounter3Btn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(belowMaxCounts()) counter3++;
            updateCounter();
        }
    };

    private final View.OnClickListener onClickShowMyCountsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){

        }
    };

    public void goToSettings() {
        Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settings);
    }

    public int getCounts() {
        return counter1 + counter2 + counter3;
    }

    public void updateCounter() {
        counts.setText("Total Count: " + String.valueOf(getCounts()));
    }

    public boolean belowMaxCounts(){
        int maxCounts = sharedPreferencesHelper.getSettings().getMaxCounts();
        return getCounts() < maxCounts;
    }
}