package de.faap.garcon.ui;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.inputmethod.*;
import android.widget.*;
import android.widget.TextView.OnEditorActionListener;
import de.faap.garcon.*;

public class AddressDialogFragment extends DialogFragment {

  public static AddressDialogFragment newInstance() {
    return new AddressDialogFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    getDialog().setTitle(getResources()
                             .getString(R.string.dialog_address_title));
    View v = inflater.inflate(R.layout.dialog_address, container, false);

    final TextView country = (TextView) v
        .findViewById(R.id.addressdialog_country);
    final TextView city = (TextView) v.findViewById(R.id.addressdialog_city);
    final TextView address = (TextView) v
        .findViewById(R.id.addressdialog_address);
    final Button mButton = (Button) v.findViewById(R.id.addressdialog_button);

    // listen for go click and delegate to button
    address.setOnEditorActionListener(new OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView tv, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
          mButton.performClick();
          return true;
        }
        return false;
      }
    });

    // Listen for button click
    mButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View view) {
        String search = country.getText() + ", " + city.getText() + ", " +
            address.getText();

        FragmentTransaction ft = getActivity().getSupportFragmentManager()
            .beginTransaction();

        // show list
        AddressListDialogFragment.newInstance(search)
            .show(ft, FragmentTags.AddressListDialogFragment.toString());

      }

    });

    return v;
  }

}
