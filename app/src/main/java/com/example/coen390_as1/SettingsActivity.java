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
import android.widget.Toast;

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
        disableEditMode();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPreferencesHelper.uninitializedSettings()) {
            showDefaultHints();
            enableEditMode();
        }
        else showCurrentHints();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_edit) {
            enableEditMode();
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

        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(onClickSaveBtn);
    }

    private final View.OnClickListener onClickSaveBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
                if(validUserInput()) {
                    updateSettings();
                    goToMain();
                } else {
                    showToast();
                }
        }
    };

    /*
    Method to go back to main activity
    */
    public void goToMain() {
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }

    /*
    Method to update settings
     */
    public void updateSettings() {
        String counter1 = counter1Name.getText().toString();
        String counter2 = counter2Name.getText().toString();
        String counter3 = counter3Name.getText().toString();
        int max = Integer.parseInt(maxCounts.getText().toString());
        sharedPreferencesHelper.saveSettings(new Settings(counter1, counter2, counter3, max));
    }

    /*
    Method to show default hints in text fields
     */
    public void showDefaultHints() {
        counter1Name.setHint(getString(R.string.writenamehere));
        counter2Name.setHint(getString(R.string.writenamehere));
        counter3Name.setHint(getString(R.string.writenamehere));
        maxCounts.setHint(getString(R.string.writemaxcounthere));
    }

    /*
    Method to show user defined preferences as hint in text fields
     */
    public void showCurrentHints() {
        counter1Name.setHint(sharedPreferencesHelper.getSettings().getCounter1Name());
        counter2Name.setHint(sharedPreferencesHelper.getSettings().getCounter2Name());
        counter3Name.setHint(sharedPreferencesHelper.getSettings().getCounter3Name());
        maxCounts.setHint(String.valueOf(sharedPreferencesHelper.getSettings().getMaxCounts()));
    }

    /*
    Methods to enable and disable editing
    Default mode is display mode
     */
    public void enableEditMode() {
        counter1Name.setEnabled(true);
        counter2Name.setEnabled(true);
        counter3Name.setEnabled(true);
        maxCounts.setEnabled(true);
        saveBtn.setVisibility(View.VISIBLE);
    }
    public void disableEditMode() {
        counter1Name.setEnabled(false);
        counter2Name.setEnabled(false);
        counter3Name.setEnabled(false);
        maxCounts.setEnabled(false);
        saveBtn.setVisibility(View.INVISIBLE);
    }

    /*
    Methods to check whether all text fields have been filled
     */
    public boolean validUserInput() {
        return counter1Name.getText().length() != 0 &&
                counter2Name.getText().length() != 0 &&
                counter3Name.getText().length() != 0 &&
                maxCounts.getText().length() != 0;
    }

    /*
    Method to show toast message
    */
    public void showToast() {
        // Toast message
        String message = "You must fill all text fields. Please try again";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(SettingsActivity.this, message, duration);
        toast.show();
    }
}