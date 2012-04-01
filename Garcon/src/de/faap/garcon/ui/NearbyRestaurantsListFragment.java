package de.faap.garcon.ui;

import java.io.*;
import java.util.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import com.google.android.maps.*;
import de.faap.garcon.*;
import de.faap.garcon.ui.widget.*;
import de.faap.garcon.util.*;

public class NearbyRestaurantsListFragment extends SherlockFragment {
  // Tag for FragmentManager
  public static final String TAG = "NearbyRestaurantsListFragment";

  private Location mLocation;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_nearbyrestaurantslist,
                              container, false);

    setUpMap((ViewGroup) v.findViewById(R.id.nearbyrestlistfrag_map_container));

    setUpList((ListView) v.findViewById(R.id.nearbyrestlistfrag_list_view));

    return v;
  }

  private void setUpMap(ViewGroup container) {
    NearbyRestaurantsActivity.MapSingleton.mMapView.setClickable(true);
    NearbyRestaurantsActivity.MapSingleton.mMapView
        .setBuiltInZoomControls(true);

    // get last know position
    LocationManager mLocationManager = (LocationManager) getActivity()
        .getSystemService(Context.LOCATION_SERVICE);
    mLocation = mLocationManager
        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

    // set zoom and center map on location if possible
    MapController mapController = NearbyRestaurantsActivity.MapSingleton.mMapView
        .getController();
    mapController.setZoom(16);
    if (mLocation != null) {
      Double lat = mLocation.getLatitude() * 1E6;
      Double lng = mLocation.getLongitude() * 1E6;
      GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
      mapController.setCenter(geoPoint);
      // also set up title here
      setUpTitle(mLocation.getLatitude(), mLocation.getLongitude());
    }

    // add MapView to the container
    container.addView(NearbyRestaurantsActivity.MapSingleton.mMapView);

    // If an exception occurs that the MapView already has a parent,
    // uncomment code below

    // final ViewGroup parent =
    // (ViewGroup) NearbyRestaurantsActivity.MapSingleton.mMapView
    // .getParent();
    // if (parent != null)
    // parent.removeView(NearbyRestaurantsActivity.MapSingleton.mMapView);
  }

  private void setUpList(ListView lv) {
    ArrayList<IRestaurant> restaurants = new ArrayList<IRestaurant>();
    for (int i = 0; i < 7; i++) {
      restaurants.add(new StubRestaurant());
    }

    lv.setAdapter(new RestaurantListAdapter(getActivity(),
        R.layout.list_item_restaurant_position, restaurants));
  }

  private void setUpTitle(double lat, double lng) {
    Geocoder mGeocoder = new Geocoder(getActivity());
    try {
      List<Address> addresses = mGeocoder.getFromLocation(lat, lng, 1);
      String locality = addresses.get(0).getLocality();
      getActivity().setTitle(getActivity().getResources()
                                 .getString(R.string.nearby_restaurants_base) +
                                 " " + locality);
    } catch (IOException e) {
      getActivity().setTitle(R.string.nearby_restaurants_title);
    }
  }
}
