package com.example.disasterreport;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final ArrayList<Earthquakes> earthquakes;
    private final Context context;
    private static final String LOCATION_SEPARATOR = " of ";

    public MainAdapter(Context context,ArrayList<Earthquakes> earthquakes) {
        this.earthquakes=earthquakes;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_earthquakes_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
          Earthquakes currentEarthquake = earthquakes.get(position);

        Date date = new Date(currentEarthquake.getMtimeInMs());
        String dateToDisplay = formatDate(date);
        String timeToDisplay = formatTime(date);
        String originalLocation = currentEarthquake.getmLocation();
        String magnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.getTextView().getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = context.getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        holder.magnitudeView.setText(magnitude);
        holder.locationView.setText(primaryLocation);
        holder.dateView.setText(dateToDisplay);
        holder.timeView.setText(timeToDisplay);
        holder.locationView2.setText(locationOffset);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,earthquakeUri);
                context.startActivity(websiteIntent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return earthquakes.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView magnitudeView;
        private final TextView locationView;
        private final TextView dateView;
        private final TextView timeView;
        private final TextView locationView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            magnitudeView = (TextView) itemView.findViewById(R.id.magnitude_view);
            locationView =(TextView) itemView.findViewById(R.id.location_view1);
            dateView =(TextView) itemView.findViewById(R.id.date_view);
            timeView =(TextView) itemView.findViewById(R.id.time_view);
            locationView2 =(TextView) itemView.findViewById(R.id.location_view2);





        }
        public TextView getTextView(){
            return magnitudeView;
        }

    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
    int magnitudeColorResourceId;
    int magnitudeFloor = (int)Math.floor(magnitude);
    switch(magnitudeFloor) {
        case 0:

        case 1:
            magnitudeColorResourceId = R.color.magnitude1;
            break;
        case 2:
            magnitudeColorResourceId = R.color.magnitude2;
            break;
        case 3:
            magnitudeColorResourceId = R.color.magnitude3;
            break;
        case 4:
            magnitudeColorResourceId = R.color.magnitude4;
            break;
        case 5:
            magnitudeColorResourceId = R.color.magnitude5;
            break;
        case 6:
            magnitudeColorResourceId = R.color.magnitude6;
            break;
        case 7:
            magnitudeColorResourceId = R.color.magnitude7;
            break;
        case 8:
            magnitudeColorResourceId = R.color.magnitude8;
            break;
        case 9:
            magnitudeColorResourceId = R.color.magnitude9;
            break;
        default:
            magnitudeColorResourceId = R.color.magnitude10plus;
            break;
    }
    return ContextCompat.getColor(context,magnitudeColorResourceId);


    }




}


