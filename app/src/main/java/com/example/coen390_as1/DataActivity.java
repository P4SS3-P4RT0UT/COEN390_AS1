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

    // To keep track of mode
    // Default mode: Event name mode
    // Second mode: Button number mode
    private Boolean buttonNumberModeEnabled;

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
            // Toggle the event names
            if(buttonNumberModeEnabled){
                disableButtonNumberMode();
            } else {
                enableButtonNumberMode();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Methods to setup layout elements
    */
    private void setupToolbar() {
        toolbar = findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);
        // To navigate back to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupTextViews() {
        counter1Events = findViewById(R.id.counter_1_events);
        counter2Events = findViewById(R.id.counter_2_events);
        counter3Events = findViewById(R.id.counter_3_events);
        totalEvents = findViewById(R.id.total_events);
    }
    private void setupRecyclerView() {
        // Create a custom adapter for list view
        String defaultCounterNames = sharedPreferencesHelper.getEventList().
                replaceAll(getString(R.string.counter_1_name), sharedPreferencesHelper.getSettings().getCounter1Name()).
                replaceAll(getString(R.string.counter_2_name), sharedPreferencesHelper.getSettings().getCounter2Name()).
                replaceAll(getString(R.string.counter_3_name), sharedPreferencesHelper.getSettings().getCounter3Name());
        CustomAdapter customAdapter = new CustomAdapter(defaultCounterNames.split(","));
        recyclerView = findViewById(R.id.event_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        recyclerView.setAdapter(customAdapter);


    }

    /*
    Methods to enable and disable button number mode
    Default is disabled (event name mode)
     */
    private void enableButtonNumberMode() {
        // Set button number mode as disabled
        buttonNumberModeEnabled = true;
        // Change user-defined counter name to "Counter 1"
        counter1Events.setText(getString(
                R.string.counter_1,
                getString(R.string.counter_1_txt),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_1_value)))
        );
        // Change user-defined counter name to "Counter 2"
        counter2Events.setText(getString(R.string.counter_2,
                getString(R.string.counter_2_txt),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_2_value)))
        );
        // Change user-defined counter name to "Counter 3"
        counter3Events.setText(getString(R.string.counter_3,
                getString(R.string.counter_3_txt),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_3_value)))
        );
        // Display the total number of events (no change in view)
        totalEvents.setText(getString(R.string.total_events, sharedPreferencesHelper.getTotalCount()));
        // Change the user-defined counter names in the event list to 1, 2 and 3
        String buttonNumberString = sharedPreferencesHelper.getEventList().
                replaceAll(getString(R.string.counter_1_name), "1").
                replaceAll(getString(R.string.counter_2_name), "2").
                replaceAll(getString(R.string.counter_3_name), "3");
        CustomAdapter customAdapter = new CustomAdapter(buttonNumberString.split(","));
        recyclerView.swapAdapter(customAdapter, true);
    }
    private void disableButtonNumberMode() {
        // Set button number mode as disabled
        buttonNumberModeEnabled = false;
        // Change counter 1 name to user-defined counter name
        counter1Events.setText(getString(
                R.string.counter_1,
                sharedPreferencesHelper.getSettings().getCounter1Name(),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_1_value)))
        );
        // Change counter 2 name to user-defined counter name
        counter2Events.setText(getString(
                R.string.counter_2,
                sharedPreferencesHelper.getSettings().getCounter2Name(),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_2_value)))
        );
        // Change counter 3 name to user-defined counter name
        counter3Events.setText(getString(
                R.string.counter_3,
                sharedPreferencesHelper.getSettings().getCounter3Name(),
                sharedPreferencesHelper.getCounterValue(getString(R.string.counter_3_value))));
        // Display the total number of events (no change in view)
        totalEvents.setText(getString(R.string.total_events, sharedPreferencesHelper.getTotalCount()));
        // Change the number counter names in the event list to user-defined names
        String defaultCounterNames = sharedPreferencesHelper.getEventList().
                replaceAll(getString(R.string.counter_1_name), sharedPreferencesHelper.getSettings().getCounter1Name()).
                replaceAll(getString(R.string.counter_2_name), sharedPreferencesHelper.getSettings().getCounter2Name()).
                replaceAll(getString(R.string.counter_3_name), sharedPreferencesHelper.getSettings().getCounter3Name());
        CustomAdapter customAdapter = new CustomAdapter(defaultCounterNames.split(","));
        recyclerView.swapAdapter(customAdapter, true);
    }
}