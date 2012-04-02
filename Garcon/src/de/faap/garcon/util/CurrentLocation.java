package de.faap.garcon.util;

import java.io.*;
import java.util.*;
import android.content.*;
import android.location.*;
import android.os.*;
import com.google.android.maps.*;

public class CurrentLocation extends Observable implements ICurrentLocation {

  private static final CurrentLocation instance = new CurrentLocation();

  private Address mAddress;
  private GeoPoint location;
  private boolean isFineLocation;
  private LocationManager mLocationManager;

  private CurrentLocation() {
    this.mAddress = null;
    this.location = null;
    this.isFineLocation = false;
    this.mLocationManager = null;
  }

  public static CurrentLocation getInstance() {
    return instance;
  }

  @Override
  public GeoPoint getLocation() {
    return location;
  }

  @Override
  public Address getAddress() {
    return mAddress;
  }

  @Override
  public boolean hasLocation() {
    if (location == null) {
      return false;
    }
    return true;
  }

  @Override
  public boolean hasFineLocation() {
    return isFineLocation;
  }

  @Override
  public void updateCoarseLocation(final Context context) {
    mLocationManager = (LocationManager) context
        .getSystemService(Context.LOCATION_SERVICE);

    LocationListener mLocationListener = new LocationListener() {
      @Override
      public void onLocationChanged(Location mLocation) {
        Double lat = mLocation.getLatitude() * 1E6;
        Double lng = mLocation.getLongitude() * 1E6;
        setLocation(context, new GeoPoint(lat.intValue(), lng.intValue()),
                    false);
        mLocationManager.removeUpdates(this);
      }

      @Override
      public void onStatusChanged(String provider, int status, Bundle extras) {
        // do nothing
      }

      @Override
      public void onProviderEnabled(String provider) {
        // do nothing
      }

      @Override
      public void onProviderDisabled(String provider) {
        // do nothing
      }
    };

    try {
      mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                              0, 0, mLocationListener);
    } catch (IllegalArgumentException e) {
      // TODO
    }

  }

  @Override
  public void updateGPSLocation(final Context context) {
    mLocationManager = (LocationManager) context
        .getSystemService(Context.LOCATION_SERVICE);

    LocationListener mLocationListener = new LocationListener() {
      @Override
      public void onLocationChanged(Location mLocation) {
        Double lat = mLocation.getLatitude() * 1E6;
        Double lng = mLocation.getLongitude() * 1E6;
        setLocation(context, new GeoPoint(lat.intValue(), lng.intValue()), true);
        mLocationManager.removeUpdates(this);
      }

      @Override
      public void onStatusChanged(String provider, int status, Bundle extras) {
        // do nothing
      }

      @Override
      public void onProviderEnabled(String provider) {
        // do nothing
      }

      @Override
      public void onProviderDisabled(String provider) {
        // do nothing
      }
    };

    try {
      mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                                              0, mLocationListener);
    } catch (IllegalArgumentException e) {
      // TODO
    }

  }

  @Override
  public void updateLocationFromAddress(Address mLocation) {
    setLocation(mLocation);
  }

  private void setLocation(final Context context, GeoPoint l, boolean isFine) {
    this.location = l;
    this.isFineLocation = isFine;
    final double lat = l.getLatitudeE6() / 1E6;
    final double lng = l.getLongitudeE6() / 1E6;

    // get address in background, because this may take some time
    Runnable mRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          Geocoder mGeocoder = new Geocoder(context);
          List<Address> addresses = mGeocoder.getFromLocation(lat, lng, 1);
          mAddress = addresses.get(0);
        } catch (IOException e) {
          mAddress = null;
        }
        // inform attached activities/fragments that location has changed
        setChanged();
        notifyObservers();
      }
    };

    new Thread(mRunnable).start();
  }

  private void setLocation(Address a) {
    this.mAddress = a;
    this.isFineLocation = true;
    Double lat = a.getLatitude() * 1E6;
    Double lng = a.getLongitude() * 1E6;
    this.location = new GeoPoint(lat.intValue(), lng.intValue());

    // inform attached activities/fragments that location has changed
    setChanged();
    notifyObservers();
  }

}
