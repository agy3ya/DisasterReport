package com.example.disasterreport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


import java.util.ArrayList;



public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquakes>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String mUrl;
    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartloading called");
        forceLoad();
        super.onStartLoading();

    }

    @Override
    public ArrayList<Earthquakes> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground called");
        if (mUrl == null) {
            return null;
        }

        return QueryUtils.fetchEarthquakeData(mUrl);
    }
}


