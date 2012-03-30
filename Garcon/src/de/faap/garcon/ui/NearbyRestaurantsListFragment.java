package de.faap.garcon.ui;

import java.util.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.util.*;
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

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Acquire a reference to the system Location Manager
        locationManager =
                (LocationManager) getActivity()
                        .getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location
                // provider.
                showOnMap(location);
            }

            @Override
            public void onStatusChanged(String provider, int status,
                    Bundle extras) {
                // do nada
            }

            @Override
            public void onProviderEnabled(String provider) {
                // do nada
            }

            @Override
            public void onProviderDisabled(String provider) {
                // do nada
            }
        };

        // Register the listener with the Location Manager to receive location
        // updates
        locationManager
                .requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                                        locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                                               0, locationListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v =
                inflater.inflate(R.layout.fragment_nearbyrestaurantslist,
                                 container, false);

        setUpMap((ViewGroup) v
                .findViewById(R.id.nearbyrestlistfrag_map_container));

        setUpList((ListView) v.findViewById(R.id.nearbyrestlistfrag_list_view));

        return v;
    }

    private void setUpMap(ViewGroup container) {
        NearbyRestaurantsActivity.MapSingleton.mMapView.setClickable(true);
        NearbyRestaurantsActivity.MapSingleton.mMapView
                .setBuiltInZoomControls(true);

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

    private void showOnMap(Location l) {
        Log.d("faap.garcon", "showOnMap called");
        MapController mapController =
                NearbyRestaurantsActivity.MapSingleton.mMapView.getController();
        mapController.setZoom(16);
        Double lat = l.getLatitude() * 1E6;
        Double lng = l.getLongitude() * 1E6;
        GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
        mapController.setCenter(geoPoint);
    }
}
