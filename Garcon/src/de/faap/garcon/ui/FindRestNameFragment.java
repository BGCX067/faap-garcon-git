package de.faap.garcon.ui;

import java.util.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;
import de.faap.garcon.ui.widget.*;
import de.faap.garcon.util.*;

public class FindRestNameFragment extends SherlockFragment {
  private ListView restList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_findrestaurantname, container,
                              false);
    setupList(v);

    return v;
  }

  private void setupList(View fragmentView) {
    restList = (ListView) fragmentView.findViewById(R.id.lst_find_rest_name);
    ArrayList<IRestaurant> restaurants = new ArrayList<IRestaurant>();
    for (int i = 0; i < 7; i++) {
      restaurants.add(new StubRestaurant());
    }

    restList.setAdapter(new RestaurantListAdapter(getActivity(),
        R.layout.list_item_restaurant_position, restaurants));

  }
}
