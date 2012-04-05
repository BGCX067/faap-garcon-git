package de.faap.garcon.ui.widget;

import java.util.*;
import android.content.*;
import android.graphics.drawable.*;
import com.google.android.maps.*;
import de.faap.garcon.util.*;

public class RestaurantItemizedOverlay extends ItemizedOverlay<OverlayItem> {

  private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
  @SuppressWarnings("unused")
  private Context mActivity; // may be needed for recognition of user input

  public RestaurantItemizedOverlay(Drawable defaultMarker, Context context,
      List<IRestaurant> restaurants) {
    super(boundCenterBottom(defaultMarker));
    this.mActivity = context;
    this.initialize(restaurants);
  }

  private void initialize(List<IRestaurant> restaurants) {
    for (IRestaurant r : restaurants) {
      mOverlays.add(new OverlayItem(r.getGeoPoint(), "erstes argument",
          "zweites argument"));
      populate();
    }
  }

  @Override
  protected OverlayItem createItem(int i) {
    return mOverlays.get(i);
  }

  @Override
  public int size() {
    return mOverlays.size();
  }

}
