package com.example.coen390_as1;

import android.content.Context;
import android.content.SharedPreferences;;

public class SharedPreferencesHelper {

    // For debugging
    private final static String TAG = "SharedPreferencesHelper";

    // To manage settings
    private static SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("Settings",
                Context.MODE_PRIVATE );

    }

    /*
    Method to save settings
    */
    public void saveSettings(Settings settings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Counter 1, key and value
        editor.putString("counter1Name", settings.getCounter1Name());
        // Counter 2, key and value
        editor.putString("counter2Name", settings.getCounter2Name());
        // Counter 3, key and value
        editor.putString("counter3Name", settings.getCounter3Name());
        // Max counts
        editor.putInt("maxCounts", settings.getMaxCounts());
        // Commit changes
        editor.commit();
    }

    /*
    Method to add events to the list of events
    */
    public void addEvent(String counterName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save the counter name and append it to the existing event list
        // Add a comma at the end to separate the values
        editor.putString("eventList", getEventList() + counterName + ",");
        editor.commit();
    }

    /*
    Method to update counter
    */
    public void updateCounter(String counterValueKey) {
        // Check that limit of counts is not reached
        if(getTotalCount() < getSettings().getMaxCounts()) {
            // Editor of SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // Increment the counter value
            int counterValue = getCounterValue(counterValueKey);
            counterValue++;
            // Update the counter value
            editor.putInt(counterValueKey, counterValue);
            // Commit changes
            editor.commit();
        }
    }

    /*
    Method to get the total number of counts
    */
    public int getTotalCount() {
        // Get the number of counts for each counter
        int counter1 = getCounterValue("counter1Value");
        int counter2 = getCounterValue("counter2Value");
        int counter3 = getCounterValue("counter3Value");
        // Return the sum of all counters
        return counter1 + counter2 + counter3;
    }

    /*
    Method to get settings
    */
    public Settings getSettings() {
        // Retrieve all settings
        String counter1Name = sharedPreferences.getString("counter1Name", "");
        String counter2Name = sharedPreferences.getString("counter2Name", "");
        String counter3Name = sharedPreferences.getString("counter3Name", "");
        int maxCounts = sharedPreferences.getInt("maxCounts", -1);
        // Return a settings object initialized with settings information
        return new Settings(counter1Name, counter2Name, counter3Name, maxCounts);
    }

    /*
    Method to get a counter value
    */
    public int getCounterValue(String counterValueKey) {
        return sharedPreferences.getInt(counterValueKey, 0);
    }

    /*
    Method to get the event list as a CSV string
    */
    public String getEventList() {
        return sharedPreferences.getString("eventList", "");
    }

    /*
    Method to indicate whether settings have been initialized
     */
    public boolean uninitializedSettings(){
        // Get current settings
        Settings currentSettings = getSettings();
        // Check for uninitialized settings values
        return currentSettings.getCounter1Name().equals("") ||
                currentSettings.getCounter2Name().equals("") ||
                currentSettings.getCounter3Name().equals("") ||
                currentSettings.getMaxCounts() == -1;
    }
}
