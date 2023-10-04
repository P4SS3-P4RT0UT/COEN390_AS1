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
        editor.putString("counter1Name", settings.getCounter1Name());
        editor.putString("counter2Name", settings.getCounter2Name());
        editor.putString("counter3Name", settings.getCounter3Name());
        editor.putInt("maxCounts", settings.getMaxCounts());
        editor.commit();
    }

    public void resetSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public Settings getSettings() {
        String counter1Name = sharedPreferences.getString("counter1Name", null);
        String counter2Name = sharedPreferences.getString("counter2Name", null);
        String counter3Name = sharedPreferences.getString("counter3Name", null);
        int maxCounts = sharedPreferences.getInt("maxCounts", -1);
        return new Settings(counter1Name, counter2Name, counter3Name, maxCounts);
    }

    /*
    Indicates whether settings have been initialized
     */
    public boolean uninitializedSettings(){
        Settings currentSettings = getSettings();
        if (currentSettings.getCounter1Name() == null ||
                currentSettings.getCounter2Name() == null ||
                currentSettings.getCounter3Name() == null ||
                currentSettings.getMaxCounts() == -1) return true;
        else return false;
    }
}
