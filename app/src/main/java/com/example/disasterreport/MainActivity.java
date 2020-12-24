
package com.example.disasterreport;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Earthquakes>> {


    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private MainAdapter mAdapter;
    private static final String USGS_REQUEST_URL=  "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=20";
    Context context;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String LOG_TAG= MainActivity.class.getName();
    private TextView emptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        emptyStateTextView = findViewById(R.id.empty_view);
      /*  EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);*/

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager =getLoaderManager();
            Log.i(LOG_TAG,"TEST: calling init loader");
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet_connection);
        }



    }

    @Override
    public Loader<ArrayList<Earthquakes>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"TEST: OnCreate called");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquakes>> loader, ArrayList<Earthquakes> data) {
        Log.i(LOG_TAG,"TEST: OnLoadFinished called");
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mAdapter = new MainAdapter(MainActivity.this,data);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquakes>> loader) {
        Log.i(LOG_TAG,"TEST: OnLoaderReset called");
    }




    /*private class EarthquakeAsyncTask extends AsyncTask<String,Void,ArrayList<Earthquakes>> {

        @Override
        protected ArrayList<Earthquakes> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquakes> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquakes> earthquakes) {
            mAdapter = new MainAdapter(MainActivity.this,earthquakes);
            recyclerView.setAdapter(mAdapter);
            super.onPostExecute(earthquakes);



        }
    }*/

}
