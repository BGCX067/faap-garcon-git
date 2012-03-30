package de.faap.garcon.ui.widget;

import java.util.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import de.faap.garcon.*;
import de.faap.garcon.util.*;

public class RestaurantListAdapter extends BaseAdapter {

  private Context mActivity;
  private int itemLayoutResourceId;

  private ArrayList<IRestaurant> restaurants;

  public RestaurantListAdapter(Context context, int itemLayoutResourceId,
      ArrayList<IRestaurant> restaurants) {
    this.mActivity = context;
    this.itemLayoutResourceId = itemLayoutResourceId;
    this.restaurants = restaurants;
  }

  @Override
  public int getCount() {
    return restaurants.size();
  }

  @Override
  public IRestaurant getItem(int position) {
    return restaurants.get(position);
  }

  @Override
  public long getItemId(int position) {
    return restaurants.get(position).getGlobalId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TableLayout v = (TableLayout) LayoutInflater.from(mActivity)
        .inflate(itemLayoutResourceId, parent, false);

    TextView PosIndicator = (TextView) v
        .findViewById(R.id.restlistitem_indicator);
    PosIndicator.setText(Integer.toString(position));

    TextView Distance = (TextView) v.findViewById(R.id.restlistitem_distance);
    Distance.setText(getItem(position).getDistance());

    TextView Name = (TextView) v.findViewById(R.id.restlistitem_name);
    Name.setText(getItem(position).getName());

    TextView Description = (TextView) v
        .findViewById(R.id.restlistitem_description);
    Description.setText(getItem(position).getShortDescription());

    return v;
  }

}
