package de.faap.garcon.ui;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import de.faap.garcon.*;

public class AddressDialogFragment extends DialogFragment {

  public static AddressDialogFragment newInstance() {
    return new AddressDialogFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    getDialog().requestWindowFeature(STYLE_NO_TITLE);
    View v = inflater.inflate(R.layout.dialog_address, container, false);

    final TextView country = (TextView) v
        .findViewById(R.id.addressdialog_country);
    final TextView city = (TextView) v.findViewById(R.id.addressdialog_city);
    final TextView address = (TextView) v
        .findViewById(R.id.addressdialog_address);
    Button mButton = (Button) v.findViewById(R.id.addressdialog_button);

    mButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        String search = country.getText() + " " + city.getText() + " " +
            address.getText();
      }

    });

    return v;
  }

}
