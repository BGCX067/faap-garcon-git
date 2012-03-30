package de.faap.garcon.ui;

import android.os.*;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;

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
        String[] myList =
                new String[] { "Hello", "World", "Foo", "Bar", "World", "Foo",
                        "Bar" };
        lv.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, myList));
    }
}
