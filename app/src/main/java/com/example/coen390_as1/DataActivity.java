package com.example.coen390_as1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private RecyclerView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        // Setup layout elements
        setupToolbar();
        setupTextViews();
        setupRecyclerView();
        // Instantiate helper
        sharedPreferencesHelper = new SharedPreferencesHelper(DataActivity.this);
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
        eventList = findViewById(R.id.event_list);
        //eventList.setAdapter(customAdapter);
    }

    /*
    Methods to enable and disable button number mode
    Default is disabled (event name mode)
     */
    private void enableButtonNumberMode() {
        String counter1 = String.valueOf(MainActivity.getCounter1());
        String counter1event = getString(R.string.counter_1) + counter1 + getString(R.string.events_txt);
        counter1Events.setText(counter1event);
        String counter2 = String.valueOf(MainActivity.getCounter2());
        String counter2event = getString(R.string.counter_2) + counter2 + getString(R.string.events_txt);
        counter2Events.setText(counter2event);
        String counter3 = String.valueOf(MainActivity.getCounter3());
        String counter3event = getString(R.string.counter_3) + counter3 + getString(R.string.events_txt);
        counter3Events.setText(counter3event);
        String totalCounts = String.valueOf(MainActivity.getCounts());
        String totalCountEvents = getString(R.string.total_events_txt) + totalCounts + getString(R.string.events_txt);
        totalEvents.setText(totalCountEvents);
    }
    private void disableButtonNumberMode() {
        String counter1 = String.valueOf(MainActivity.getCounter1());
        String counter1event = sharedPreferencesHelper.getSettings().getCounter1Name() + ": " + counter1 + getString(R.string.events_txt);
        counter1Events.setText(counter1event);
        String counter2 = String.valueOf(MainActivity.getCounter2());
        String counter2event = sharedPreferencesHelper.getSettings().getCounter2Name() + ": " + counter2 + getString(R.string.events_txt);
        counter2Events.setText(counter2event);
        String counter3 = String.valueOf(MainActivity.getCounter3());
        String counter3event = sharedPreferencesHelper.getSettings().getCounter3Name() + ": " + counter3 + getString(R.string.events_txt);
        counter3Events.setText(counter3event);
        String totalCounts = String.valueOf(MainActivity.getCounts());
        String totalCountEvents = getString(R.string.total_events_txt) + totalCounts + getString(R.string.events_txt);
        totalEvents.setText(totalCountEvents);
    }
}