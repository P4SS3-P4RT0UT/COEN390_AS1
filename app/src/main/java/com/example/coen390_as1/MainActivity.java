
package com.example.coen390_as1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // For debugging
    private final static String TAG = "MainActivity";

    // To manage settings
    private SharedPreferencesHelper sharedPreferencesHelper;

    // Layout elements
    private Button settingsBtn;
    private Button counter1Btn;
    private Button counter2Btn;
    private Button counter3Btn;
    private TextView counts;
    private Button showMyCountsBtn;

    // Counters
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static int counter3 = 0;

    // File to write events to
    private static final String FILE_PATH ="./main_activity_events.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup layout elements
        setupCountView();
        setupCountersBtn();
        setupSettingsBtn();
        setupShowMyCountsBtn();
        // Instantiate helper
        sharedPreferencesHelper = new SharedPreferencesHelper(MainActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // If settings have not been initialized, go directly to SettingsActivity
        if(sharedPreferencesHelper.uninitializedSettings())
            goToSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get current counter names for buttons
        updateCounterBtnName();
        // Get current counter value
        updateCounter();
    }

    /*
    Methods to setup layout elements
    */
    private void setupCountView() {
        counts = findViewById(R.id.totalcountview);
    }
    private void setupSettingsBtn() {
        settingsBtn = findViewById(R.id.settings_btn);
        settingsBtn.setOnClickListener(onClickSettingsBtn);
    }
    private void setupCountersBtn() {
        // Counter 1
        counter1Btn = findViewById(R.id.counter1_btn);
        counter1Btn.setOnClickListener(onClickCounter1Btn);
        // Counter 2
        counter2Btn = findViewById(R.id.counter2_btn);
        counter2Btn.setOnClickListener(onClickCounter2Btn);
        // Counter 3
        counter3Btn = findViewById(R.id.counter3_btn);
        counter3Btn.setOnClickListener(onClickCounter3Btn);
    }
    private void setupShowMyCountsBtn() {
        showMyCountsBtn= findViewById(R.id.showmycounts_btn);
        showMyCountsBtn.setOnClickListener(onClickShowMyCountsBtn);
    }

    /*
    Methods to define behavior of settings button click
    - Go to SettingsActivity
    */
    private final View.OnClickListener onClickSettingsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            goToSettings();
        }
    };

    /*
    Methods to define behavior of counter button clicks
    - Max count not reached: Counter is incremented
    - Max count reached: Counter is not incremented
    */
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

    /*
    Methods to define behavior of show my counts button click
    - Go to DataActivity
    */
    private final View.OnClickListener onClickShowMyCountsBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            goToData();
        }
    };

    /*
    Method to update the counter buttons name
    */
    public void updateCounterBtnName(){
        counter1Btn.setText(sharedPreferencesHelper.getSettings().getCounter1Name());
        counter2Btn.setText(sharedPreferencesHelper.getSettings().getCounter2Name());
        counter3Btn.setText(sharedPreferencesHelper.getSettings().getCounter3Name());
    }

    /*
    Method to go to settings
    */
    public void goToSettings() {
        Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(settings);
    }

    /*
    Method to go to data
    */
    public void goToData() {
        Intent data = new Intent(getApplicationContext(), DataActivity.class);
        startActivity(data);
    }

    /*
    Methods to get the total and individual number of counts
    */
    public static int getCounts() {
        return counter1 + counter2 + counter3;
    }
    public static int getCounter1() {
        return counter1;
    }
    public static int getCounter2() {
        return counter2;
    }
    public static int getCounter3() {
        return counter3;
    }

    /*
    Method to update the display counter
    */
    public void updateCounter() {
        String count = String.valueOf(getCounts());
        String displayText = getString(R.string.total_count) + count;
        counts.setText(displayText);
    }

    /*
    Method to check if the total count is below the maximal value
    */
    public boolean belowMaxCounts(){
        int maxCounts = sharedPreferencesHelper.getSettings().getMaxCounts();
        return getCounts() < maxCounts;
    }

    public void writeToFile() {

    }
}