package com.example.disasterreport;

import java.util.ArrayList;

public class Earthquakes {
    private double mMagnitude;
    private String mLocation;
    private long mtimeInMs;
    private String url;

    public Earthquakes(double mMagnitude, String mLocation, long mtimeInMs,String url) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mtimeInMs = mtimeInMs;
        this.url = url;
    }


    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getMtimeInMs() {
        return mtimeInMs;
    }
    public String getUrl() {
        return url;
    }



}
