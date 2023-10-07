package com.example.coen390_as1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    // For debugging
    private final static String TAG = "DataActivity";

    // To access settings
    private SharedPreferencesHelper sharedPreferencesHelper;

    // Layout elements
    private Toolbar toolbar;
    private TextView counter1Events;
    private TextView counter2Events;
    private TextView counter3Events;
    private TextView totalEvents;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        // Instantiate helper
        sharedPreferencesHelper = new SharedPreferencesHelper(DataActivity.this);
        // Setup layout elements
        setupToolbar();
        setupTextViews();
        setupRecyclerView();
        disableButtonNumberMode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate toolbar menu
        getMenuInflater().inflate(R.menu.menu_data_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_toggle_event_names) {
            // Enable button number mode
            enableButtonNumberMode();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Methods to setup layout elements
    */
    private void setupToolbar() {
        toolbar = findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupTextViews() {
        counter1Events = findViewById(R.id.counter_1_events);
        counter2Events = findViewById(R.id.counter_2_events);
        counter3Events = findViewById(R.id.counter_3_events);
        totalEvents = findViewById(R.id.total_events);
    }
    private void setupRecyclerView() {
        CustomAdapter customAdapter = new CustomAdapter(sharedPreferencesHelper.getEventList().split(","));
        recyclerView = findViewById(R.id.event_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
    }

    /*
    Methods to enable and disable button number mode
    Default is disabled (event name mode)
     */
    private void enableButtonNumberMode() {
        String events = getString(R.string.events_txt);
        // Counter 1 name and value
        String counter1Name = getString(R.string.counter_1);
        int counter1Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_1_value));
        counter1Events.setText(counter1Name + counter1Value + events);
        // Counter 2 name and value
        String counter2Name = getString(R.string.counter_2);
        int counter2Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_2_value));
        counter2Events.setText(counter2Name + counter2Value + events);
        // Counter 3 name and value
        String counter3Name = getString(R.string.counter_3);
        int counter3Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_3_value));
        counter3Events.setText(counter3Name + counter3Value + events);
        // Total counts
        String totalEventsTxt = getString(R.string.total_events_txt);
        int totalCounts = sharedPreferencesHelper.getTotalCount();
        totalEvents.setText(totalEventsTxt + totalCounts + events);
        // Event names
    }
    private void disableButtonNumberMode() {
        String events = getString(R.string.events_txt);
        // Counter 1 name and value
        String counter1Name = sharedPreferencesHelper.getSettings().getCounter1Name();
        int counter1Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_1_value));
        counter1Events.setText(counter1Name + ": " + counter1Value + events);
        // Counter 2 name and value
        String counter2Name = sharedPreferencesHelper.getSettings().getCounter2Name();
        int counter2Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_2_value));
        counter2Events.setText(counter2Name + ": " + counter2Value + events);
        // Counter 3 name and value
        String counter3Name = sharedPreferencesHelper.getSettings().getCounter3Name();
        int counter3Value = sharedPreferencesHelper.getCounterValue(getString(R.string.counter_3_value));
        counter3Events.setText(counter3Name + ": " + counter3Value + events);
        // Total counts
        String totalEventsTxt = getString(R.string.total_events_txt);
        int totalCounts = sharedPreferencesHelper.getTotalCount();
        totalEvents.setText(totalEventsTxt + ": " + totalCounts + events);
    }
}