package de.faap.garcon.ui.widget;

import java.util.*;
import android.content.*;
import android.location.*;
import android.support.v4.app.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import de.faap.garcon.ui.*;
import de.faap.garcon.util.*;

public class AddressListAdapter extends BaseAdapter {

  private Context mActivity;
  private List<Address> addressList;

  public AddressListAdapter(Context context, List<Address> addressList) {
    this.mActivity = context;
    this.addressList = addressList;
    // TODO list always only 1 result?
    Log.d("faap.garcon", "l=" + addressList.size());
  }

  @Override
  public int getCount() {
    return addressList.size();
  }

  @Override
  public Address getItem(int position) {
    return addressList.get(position);
  }

  @Override
  public long getItemId(int position) {
    // TODO ?
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView v = (TextView) LayoutInflater.from(mActivity)
        .inflate(android.R.layout.simple_list_item_1, parent, false);

    final Address a = getItem(position);

    // build string to display
    boolean commaNeeded = false;
    String country = a.getCountryName();
    String postalCode = a.getPostalCode();
    String city = a.getLocality();
    String address = a.getAddressLine(0);

    String text = "";
    if (country != null) {
      text = text + country;
      commaNeeded = true;
    }
    if (postalCode != null) {
      if (commaNeeded) {
        text = text + ", ";
        commaNeeded = false;
      }
      text = text + postalCode + " " + city;
      commaNeeded = true;
    }
    if (address != null) {
      if (commaNeeded) {
        text = text + ", ";
        commaNeeded = false;
      }
      text = text + address;
    }
    v.setText(text);

    v.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View view) {
        ICurrentLocation lm = CurrentLocation.getInstance();
        // update location ...
        lm.updateLocationFromAddress(a);

        // ... and dismiss dialogs
        FragmentTransaction ft = ((FragmentActivity) mActivity)
            .getSupportFragmentManager().beginTransaction();
        DialogFragment addDia = (DialogFragment) ((FragmentActivity) mActivity)
            .getSupportFragmentManager()
            .findFragmentByTag(FragmentTags.AddressDialogFragment.toString());
        if (addDia != null) {
          ft.remove(addDia);
          addDia.dismiss();
        }
        ft.addToBackStack(null);

        ft = ((FragmentActivity) mActivity).getSupportFragmentManager()
            .beginTransaction();
        DialogFragment addDiaList = (DialogFragment) ((FragmentActivity) mActivity)
            .getSupportFragmentManager()
            .findFragmentByTag(FragmentTags.AddressListDialogFragment
                                   .toString());
        if (addDiaList != null) {
          ft.remove(addDiaList);
          addDiaList.dismiss();
        }
        ft.addToBackStack(null);
      }
    });

    return v;
  }

}
