package com.jvitatoe.locator;


import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements ConnectionCallbacks,
        OnConnectionFailedListener, LocationListener {

    protected static final String TAG = "MainActivity";
    protected GoogleApiClient mGoogleApiClient;
    protected static final String TAG1 ="location-updates-sample";
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 50;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    protected final static String REQUESTING_LOCATION_UPDATES_KEY =
            "requesting-location-updates-key";
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";
    protected boolean rCurrent;
    protected Location mLastLocation;
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected String mDistanceLabel;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;
    protected LocationRequest mLocationRequest;
    protected Location mCurrentLocation;
    protected boolean mRequestingLocationUpdates;
    protected String mLastUpdateTime;
    protected TextView mLatitudeTextView;
    protected TextView mLongitudeTextView;
    protected TextView mLastUpdateTimeTextView;
    protected TextView mDistanceCalculation;
    protected String mLastUpdateTimeLabel;
    Button b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mDistanceLabel = getResources().getString(R.string.distance_label);
        mLatitudeText = (TextView) findViewById((R.id.latitude_text));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text));
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);
        mLastUpdateTimeTextView = (TextView) findViewById(R.id.update_time);
        mLatitudeTextView = (TextView) findViewById(R.id.latitude_text_view);
        mLongitudeTextView = (TextView) findViewById(R.id.longitude_text_view);
        mDistanceCalculation = (TextView) findViewById(R.id.distance);
        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        updateValuesFromBundle(savedInstanceState);

        buildGoogleApiClient();

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDistance();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void startUpdatesButtonHandler(View view) {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            setButtonsEnabledState();
            startLocationUpdates();
        }
    }

    public void stopUpdatesButtonHandler(View view) {
        if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            setButtonsEnabledState();
            stopLocationUpdates();
        }
    }

    private void setButtonsEnabledState() {
        if (mRequestingLocationUpdates) {
            b2.setEnabled(false);
           b3.setEnabled(true);
        } else {
           b2.setEnabled(true);
           b3.setEnabled(false);
        }
    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void updateValuesFromBundle(Bundle savedInstanceState){
        if(savedInstanceState != null){
            if(savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)){
                mRequestingLocationUpdates = savedInstanceState.getBoolean
                        (REQUESTING_LOCATION_UPDATES_KEY);
            }
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }
            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();
        }
    }


    public void getDistance() {

        double lat1 = mLastLocation.getLatitude();
        double lat2 = mCurrentLocation.getLatitude();
        double lon1 = mLastLocation.getLongitude();
        double lon2 = mCurrentLocation.getLongitude();
        double latA = Math.toRadians(lat1);
        double lonA = Math.toRadians(lon1);
        double latB = Math.toRadians(lat2);
        double lonB = Math.toRadians(lon2);
        double cosAng = (Math.cos(latA) * Math.cos(latB) * Math.cos(lonB-lonA)) +
                (Math.sin(latA) * Math.sin(latB));
        double ang = Math.acos(cosAng);
        double dist = ang *6371;

        mDistanceCalculation.setText(String.format("%s: %f", mDistanceLabel,
                (dist / 0.0010000)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    private void updateUI() {
        mLatitudeTextView.setText(String.format("%s: %f", mLatitudeLabel,
                mCurrentLocation.getLatitude()));
        mLongitudeTextView.setText(String.format("%s: %f", mLongitudeLabel,
                mCurrentLocation.getLongitude()));
        mLastUpdateTimeTextView.setText(String.format("%s: %s", mLastUpdateTimeLabel,
                mLastUpdateTime));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
       mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.format("%s: %f", mLatitudeLabel,
                    mLastLocation.getLatitude()));
            mLongitudeText.setText(String.format("%s: %f", mLongitudeLabel,
                    mLastLocation.getLongitude()));
        } else {
            Toast.makeText(this, R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            updateUI();
        }
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }


        }


    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        Toast.makeText(this, getResources().getString(R.string.location_updated_message),
                Toast.LENGTH_SHORT).show();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }
}

