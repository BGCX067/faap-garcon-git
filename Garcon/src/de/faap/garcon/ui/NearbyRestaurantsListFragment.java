package de.faap.garcon.ui;

import java.util.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;
import de.faap.garcon.ui.widget.*;
import de.faap.garcon.util.*;

public class NearbyRestaurantsListFragment extends SherlockFragment {
    // Tag for FragmentManager
    public static final String TAG = "NearbyRestaurantsListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
                R.layout.restaurant_list_item_position, restaurants));
    }
}
