package de.faap.garcon.ui.widget;

import java.util.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import de.faap.garcon.*;

public class RestaurantListAdapter extends BaseAdapter {

    private Context mActivity;
    private int itemLayoutResourceId;

    // TODO replace String with Restaurant as possible
    private ArrayList<String> restaurants;

    public RestaurantListAdapter(Context context, int itemLayoutResourceId,
            ArrayList<String> restaurants) {
        this.mActivity = context;
        this.itemLayoutResourceId = itemLayoutResourceId;
        this.restaurants = restaurants;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public String getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO give Restaurant a getId() Method and use this here
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableLayout v =
                (TableLayout) LayoutInflater.from(mActivity)
                        .inflate(itemLayoutResourceId, parent, false);

        TextView PosIndicator =
                (TextView) v.findViewById(R.id.restlistitem_indicator);
        PosIndicator.setText(Integer.toString(position));

        TextView Distance =
                (TextView) v.findViewById(R.id.restlistitem_distance);
        Distance.setText("13,37 km");

        TextView Name = (TextView) v.findViewById(R.id.restlistitem_name);
        Name.setText(getItem(position));

        TextView Description =
                (TextView) v.findViewById(R.id.restlistitem_description);
        Description.setText("No Description");

        return v;
    }

}
