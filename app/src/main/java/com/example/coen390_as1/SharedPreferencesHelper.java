package com.example.coen390_as1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    protected final static String TAG = "SharedPreferencesHelpers";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("Settings",
                Context.MODE_PRIVATE );
    }

    public void saveSettings(Settings settings) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter1Name", settings.Counter1Name);
        editor.putString("counter2Name", settings.Counter2Name);
        editor.putString("counter3Name", settings.Counter3Name);
        editor.putInt("maxCounts", settings.maxCounts);
        editor.commit();
    }

    public Settings getSettings() {
        String counter1Name = sharedPreferences.getString("counter1Name", null);
        String counter2Name = sharedPreferences.getString("counter2Name", null);
        String counter3Name = sharedPreferences.getString("counter3Name", null);
        int maxCounts = sharedPreferences.getInt("maxCounts", -1);
        return new Settings(counter1Name, counter2Name, counter3Name, maxCounts);
    }
}
