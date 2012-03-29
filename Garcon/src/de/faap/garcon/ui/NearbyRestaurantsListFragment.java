package de.faap.garcon.ui;

import android.os.*;
import android.view.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;

public class NearbyRestaurantsListFragment extends SherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v =
                inflater.inflate(R.layout.fragment_nearbyrestaurantslist,
                                 container, false);
        return v;
    }
}
