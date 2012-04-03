package de.faap.garcon.ui;

import android.os.*;
import android.support.v4.app.*;
import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;
import com.google.android.maps.*;
import de.faap.garcon.*;
import de.faap.garcon.util.*;

public class NearbyRestaurantsActivity extends SherlockFragmentActivity {

  private NearbyRestaurantsListFragment mNearbyRestaurantsListFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nearbyrestaurants);

    // TODO what to do with home button?

    // create MapView
    MapSingleton.mMapView = new MapView(this,
        "0qWWE8QtyZjzSnDl0NvQG5h9-_4c9ZTlirFOJJQ");

    // show NearbyRestaurantsListFragment
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    mNearbyRestaurantsListFragment = (NearbyRestaurantsListFragment) getSupportFragmentManager()
        .findFragmentByTag(FragmentTags.NearbyRestaurantsListFragment
                               .toString());
    if (mNearbyRestaurantsListFragment == null) {
      mNearbyRestaurantsListFragment = new NearbyRestaurantsListFragment();
      ft.add(R.id.nearbyrest_frag_container, mNearbyRestaurantsListFragment,
             FragmentTags.NearbyRestaurantsListFragment.toString());
    }

    ft.commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getSupportMenuInflater().inflate(R.menu.nearby_restaurants_list_menu_items,
                                     menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.menu_gps:
      CurrentLocation.getInstance().updateGPSLocation(this);
      return true;

    case R.id.menu_network:
      CurrentLocation.getInstance().updateCoarseLocation(this);
      return true;

    case R.id.menu_address:
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      AddressDialogFragment.newInstance()
          .show(ft, FragmentTags.AddressDialogFragment.toString());
      return true;

    default:
      return super.onOptionsItemSelected(item);
    }
  }

  /**
   * This class keeps a single reference to a MapView which is created by the
   * activity and can be used by Fragments.
   */
  public static class MapSingleton {
    public static MapView mMapView;
  }
}
