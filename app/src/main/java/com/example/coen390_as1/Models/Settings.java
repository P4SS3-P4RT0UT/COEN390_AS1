package com.example.coen390_as1.Models;

public class Settings {

    private String Counter1Name;
    private String Counter2Name;
    private String Counter3Name;
    private int maxCounts;

    public Settings(String counter1Name, String counter2Name, String counter3Name, int maxCounts) {
        Counter1Name = counter1Name;
        Counter2Name = counter2Name;
        Counter3Name = counter3Name;
        this.maxCounts = maxCounts;
    }

    public String getCounter1Name() {
        return Counter1Name;
    }

    public void setCounter1Name(String counter1Name) {
        Counter1Name = counter1Name;
    }

    public String getCounter2Name() {
        return Counter2Name;
    }

    public void setCounter2Name(String counter2Name) {
        Counter2Name = counter2Name;
    }

    public String getCounter3Name() {
        return Counter3Name;
    }

    public void setCounter3Name(String counter3Name) {
        Counter3Name = counter3Name;
    }

    public int getMaxCounts() {
        return maxCounts;
    }

    public void setMaxCounts(int maxCounts) {
        this.maxCounts = maxCounts;
    }
}
