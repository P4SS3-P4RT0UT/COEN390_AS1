package com.example.coen390_as1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    protected final static String TAG = "SettingsActivity";

    protected SharedPreferencesHelper sharedPreferencesHelper;

    protected Toolbar toolbar;
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

    @Override
    protected void onResume() {
        super.onResume();
        counter1Name.setHint(sharedPreferencesHelper.getSettings().getCounter1Name());
        counter2Name.setHint(sharedPreferencesHelper.getSettings().getCounter2Name());
        counter3Name.setHint(sharedPreferencesHelper.getSettings().getCounter3Name());
        maxCounts.setHint(String.valueOf(sharedPreferencesHelper.getSettings().getMaxCounts()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_edit) {
            counter1Name.setEnabled(true);
            counter2Name.setEnabled(true);
            counter3Name.setEnabled(true);
            maxCounts.setEnabled(true);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupUI() {

        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        counter1Name = findViewById(R.id.counter1edittext);
        counter2Name = findViewById(R.id.counter2edittext);
        counter3Name = findViewById(R.id.counter3edittext);
        maxCounts = findViewById(R.id.maxcountedittxt);

        counter1Name.setEnabled(false);
        counter2Name.setEnabled(false);
        counter3Name.setEnabled(false);
        maxCounts.setEnabled(false);

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