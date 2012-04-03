package de.faap.garcon.ui;

import java.io.*;
import java.util.*;
import android.location.*;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import de.faap.garcon.*;
import de.faap.garcon.ui.widget.*;

public class AddressListDialogFragment extends DialogFragment {

  private static final int MAX_RESULTS = 10;

  private String search;
  private List<Address> addressList;

  public static AddressListDialogFragment newInstance(String search) {
    AddressListDialogFragment f = new AddressListDialogFragment();
    Bundle args = new Bundle();
    args.putString(IntentData.ADDRESS_SEARCH.toString(), search);
    f.setArguments(args);
    return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    search = getArguments().getString(IntentData.ADDRESS_SEARCH.toString());
    Geocoder mGeocoder = new Geocoder(getActivity());
    try {
      // TODO show user that progress is made in background
      addressList = mGeocoder.getFromLocationName(search, MAX_RESULTS);
    } catch (IOException e) {
      // TODO make sure user understands that there was a problem
      addressList = new ArrayList<Address>();
    }

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    getDialog().setTitle(getResources()
                             .getString(R.string.dialog_addresslist_title));
    ListView lv = (ListView) inflater.inflate(R.layout.dialog_addresslist,
                                              container, false);

    lv.setAdapter(new AddressListAdapter(getActivity(), addressList));

    return lv;
  }

}
