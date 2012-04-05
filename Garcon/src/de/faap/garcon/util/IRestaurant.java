package de.faap.garcon.util;

import com.google.android.maps.*;

public interface IRestaurant {
  public String getName();

  public String getShortDescription();

  public String getDistance();

  public long getGlobalId();

  public GeoPoint getGeoPoint();

}
