package de.faap.garcon.ui;

import java.util.*;
import android.location.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import com.google.android.maps.*;
import de.faap.garcon.*;
import de.faap.garcon.ui.widget.*;
import de.faap.garcon.util.*;

public class NearbyRestaurantsListFragment extends SherlockFragment implements
    Observer {
  // Tag for FragmentManager
  public static final String TAG = "NearbyRestaurantsListFragment";

  private ListView mListView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // the view creates a dependency, so start observing CurrentLocation
    CurrentLocation.getInstance().addObserver(this);

    View v = inflater.inflate(R.layout.fragment_nearbyrestaurantslist,
                              container, false);

    // init mapview
    NearbyRestaurantsActivity.MapSingleton.mMapView.setClickable(true);
    NearbyRestaurantsActivity.MapSingleton.mMapView
        .setBuiltInZoomControls(true);
    ((ViewGroup) v.findViewById(R.id.nearbyrestlistfrag_map_container))
        .addView(NearbyRestaurantsActivity.MapSingleton.mMapView);

    mListView = (ListView) v.findViewById(R.id.nearbyrestlistfrag_list_view);

    setContent();

    return v;
  }

  @Override
  public void onDestroyView() {
    // dependency will get destroyed with view -> unsubscribe
    CurrentLocation.getInstance().deleteObserver(this);
    super.onDestroyView();
  }

  @Override
  public void update(Observable observable, Object data) {
    // called when location has changed
    // since we're doing ui changes, we have to run this on ui thread
    getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        setContent();
      }
    });
  }

  private void setContent() {
    handleList();
    handleTitle();
    handleMap();
  }

  private void handleMap() {
    MapController mapController = NearbyRestaurantsActivity.MapSingleton.mMapView
        .getController();
    mapController.setZoom(16);

    ICurrentLocation locationManager = CurrentLocation.getInstance();
    if (locationManager.hasLocation()) {
      mapController.setCenter(locationManager.getLocation());
    }

    // If an exception occurs that the MapView already has a parent,
    // uncomment code below

    // final ViewGroup parent =
    // (ViewGroup) NearbyRestaurantsActivity.MapSingleton.mMapView
    // .getParent();
    // if (parent != null)
    // parent.removeView(NearbyRestaurantsActivity.MapSingleton.mMapView);
  }

  private void handleList() {
    ArrayList<IRestaurant> restaurants = new ArrayList<IRestaurant>();
    for (int i = 0; i < 7; i++) {
      restaurants.add(new StubRestaurant());
    }

    mListView.setAdapter(new RestaurantListAdapter(getActivity(),
        R.layout.list_item_restaurant_position, restaurants));
  }

  private void handleTitle() {
    ICurrentLocation locationManager = CurrentLocation.getInstance();
    if (locationManager.hasLocation()) {
      Address mAddress = locationManager.getAddress();
      if (mAddress == null) {
        getActivity()
            .setTitle(getResources()
                          .getString(R.string.nearby_restaurants_no_locality));
      } else {
        String title = getResources()
            .getString(R.string.nearby_restaurants_base) +
            " " + mAddress.getLocality();
        getActivity().setTitle(title);
      }
    }
  }
}
