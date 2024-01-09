package ro.ase.csie.ie.dma;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e("GeofenceBroadcastReceiver", errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT ) {

            Location triggeringLocation = geofencingEvent.getTriggeringLocation();
            Location homeLocation = new Location(LocationManager.GPS_PROVIDER);
            homeLocation.setLatitude(MapsActivity.homeLocation.latitude);
            homeLocation.setLongitude(MapsActivity.homeLocation.longitude);
            float distanceTo = triggeringLocation.distanceTo(homeLocation);

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context.getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(triggeringLocation.getLatitude(), triggeringLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

            // Send notification and log the transition details.
            Toast.makeText(context.getApplicationContext(), "Child left home, transition type:" + geofenceTransition, Toast.LENGTH_LONG).show();
            Toast.makeText(context.getApplicationContext(), "He is " + distanceTo/1000 + " Km, from home.", Toast.LENGTH_LONG).show();
            Toast.makeText(context.getApplicationContext(), "He is in " + city + ", address: "+address, Toast.LENGTH_LONG).show();
        }
        else if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
        {
            Toast.makeText(context.getApplicationContext(), "Child returned home, transition type: " + geofenceTransition, Toast.LENGTH_LONG).show();
        }
        else {
            // Log the error.
            Toast.makeText(context.getApplicationContext(), "Error Transition detected: ", Toast.LENGTH_LONG).show();
        }
    }
}