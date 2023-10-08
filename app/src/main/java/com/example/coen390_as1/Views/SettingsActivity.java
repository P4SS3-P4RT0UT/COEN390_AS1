/*
 * Laurie Anne Laberge
 * COEN 390 Section E CP
 * 40173077
 * 2023-10-08
 */

package com.example.coen390_as1.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coen390_as1.R;
import com.example.coen390_as1.Models.Settings;
import com.example.coen390_as1.Controllers.SharedPreferencesHelper;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    // For debugging
    private final static String TAG = "SettingsActivity";

    // To manage settings
    private SharedPreferencesHelper sharedPreferencesHelper;

    // Layout elements
    private Toolbar toolbar;
    private EditText counter1Name;
    private EditText counter2Name;
    private EditText counter3Name;
    private EditText maxCounts;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Setup layout elements
        setupToolbar();
        setupTextFields();
        setupSaveBtn();
        // Instantiate helper
        sharedPreferencesHelper = new SharedPreferencesHelper(SettingsActivity.this);
        // Edit mode disabled by default
        disableEditMode();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // If settings have not been initialized, enable edit mode
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
        // Inflate toolbar menu
        getMenuInflater().inflate(R.menu.menu_settings_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_edit) {
            // Enable edit mode when 'edit' selected in toolbar
            enableEditMode();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Methods to setup layout elements
    */
    private void setupToolbar() {
        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
    private void setupTextFields() {
        counter1Name = findViewById(R.id.counter1edittext);
        counter2Name = findViewById(R.id.counter2edittext);
        counter3Name = findViewById(R.id.counter3edittext);
        maxCounts = findViewById(R.id.maxcountedittxt);
    }
    private void setupSaveBtn() {
        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(onClickSaveBtn);
    }

    /*
    Method to define behavior of save button click
    - Valid settings inputs: Settings are updated
    - Invalid settings inputs: Toast message is shown
    */
    private final View.OnClickListener onClickSaveBtn = new View.OnClickListener(){
        @Override
        public void onClick(View v){
                if(validUserInput()) {
                    updateSettings();
                    disableEditMode();
                } else {
                    showToast();
                }
        }
    };

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
        counter1Name.setHint(getString(R.string.write_name_here));
        counter2Name.setHint(getString(R.string.write_name_here));
        counter3Name.setHint(getString(R.string.write_name_here));
        maxCounts.setHint(getString(R.string.write_max_count_here));
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
                maxCounts.getText().length() != 0 &&
                Integer.parseInt(maxCounts.getText().toString()) >= 5 &&
                Integer.parseInt(maxCounts.getText().toString()) <= 200;
    }

    /*
    Method to show toast message
    */
    public void showToast() {
        // Toast message
        String message = "You must fill all text fields with valid values. Please try again";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(SettingsActivity.this, message, duration);
        toast.show();
    }
}