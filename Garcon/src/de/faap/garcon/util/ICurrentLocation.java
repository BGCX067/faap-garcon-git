package de.faap.garcon.util;

import android.content.*;
import android.location.*;
import com.google.android.maps.*;

public interface ICurrentLocation {

  public GeoPoint getLocation();

  public Address getAddress();

  public boolean hasLocation();

  public boolean hasFineLocation();

  public void updateCoarseLocation(Context context);

  public void updateGPSLocation(Context context);

  public void updateLocationFromAddress(Address location);
}
