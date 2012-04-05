package de.faap.garcon.util;

import com.google.android.maps.*;

public class StubRestaurant implements IRestaurant {

  private GeoPoint mGeoPoint = new GeoPoint(0, 0);
  private String name = "Der Wirt am Platz";

  public StubRestaurant() {

  }

  public StubRestaurant(int i) {
    int lat, lng;

    switch (i) {
    case 0:
      name = "Berger";
      lat = (int) (49.2478999 * 1E6);
      lng = (int) (12.306737200000001 * 1E6);
      mGeoPoint = new GeoPoint(lat, lng);
      break;

    case 1:
      name = "Enzianstüberl";
      lat = (int) (49.252083 * 1E6);
      lng = (int) (12.3056197 * 1E6);
      mGeoPoint = new GeoPoint(lat, lng);
      break;

    case 2:
      name = "San Remo";
      lat = (int) (49.2503854 * 1E6);
      lng = (int) (12.308205 * 1E6);
      mGeoPoint = new GeoPoint(lat, lng);
      break;

    default:
      break;
    }

  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getShortDescription() {
    return "Kleines Wirtshaus mit traditionellen Speisen und einheimischem Flair.";
  }

  @Override
  public String getDistance() {
    return "13,37 km";
  }

  @Override
  public long getGlobalId() {
    return 0;
  }

  @Override
  public GeoPoint getGeoPoint() {
    return mGeoPoint;
  }

}
