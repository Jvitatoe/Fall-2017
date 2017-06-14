package com.distance;

import com.google.android.maps.GeoPoint;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceBetweenTwoGeoPointsActivity extends Activity {
    /** Called when the activity is first created. */
	 TextView text;
	 GeoPoint startGP;
	 GeoPoint startGP1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         text = (TextView)findViewById(R.id.text);
        GeoPoint startGP12 = new GeoPoint(
				(int) (11.66924 * 1E6),
				(int) (78.139497 * 1E6));
        GeoPoint startGP123 = new GeoPoint(
				(int) (11.6667502 * 1E6),
				(int) (78.1437646 * 1E6));
        
        Button btn =(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  EditText source_lat      = (EditText)findViewById(R.id.editText1);
			        EditText source_lng      = (EditText)findViewById(R.id.editText2);
			        EditText destination_lat = (EditText)findViewById(R.id.editText3);
			        EditText destination_lag = (EditText)findViewById(R.id.editText4);
			        
			       if(!source_lat.getText().toString().equalsIgnoreCase("") || !source_lng.getText().toString().equalsIgnoreCase("") || !destination_lag.getText().toString().equalsIgnoreCase("") || !destination_lat.getText().toString().equalsIgnoreCase("")) 
				  
			    	   {startGP = new GeoPoint(
							(int) (Double.valueOf(source_lat.getText().toString()) * 1E6),
							(int) (Double.valueOf(source_lng.getText().toString() )* 1E6));
			         startGP1 = new GeoPoint(
							(int) (Double.valueOf(destination_lat.getText().toString() )* 1E6),
							(int) (Double.valueOf(destination_lag.getText().toString()) * 1E6));
			        text.setText(" distance :" + CalculationByDistance(startGP,startGP1) +"km");
			    	   }
			       else{
			    	   Toast.makeText(DistanceBetweenTwoGeoPointsActivity.this, "Enter the Values!", Toast.LENGTH_LONG).show();
			       }
			}
		});
        
       
        
    }
    


    public double CalculationByDistance(GeoPoint StartP, GeoPoint EndP) {
    	 
        double lat1 = StartP.getLatitudeE6()/1E6;
   
        double lat2 = EndP.getLatitudeE6()/1E6;
   
        double lon1 = StartP.getLongitudeE6()/1E6;
   
        double lon2 = EndP.getLongitudeE6()/1E6;
   
        double dLat = Math.toRadians(lat2-lat1);
   
        double dLon = Math.toRadians(lon2-lon1);
   
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
   
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
   
        Math.sin(dLon/2) * Math.sin(dLon/2);
   
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return  c*6378.1;
   
     }
   
}