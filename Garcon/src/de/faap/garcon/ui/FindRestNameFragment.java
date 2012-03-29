package de.faap.garcon.ui;

import android.os.*;
import android.view.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;

public class FindRestNameFragment extends SherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v =
                inflater.inflate(R.layout.fragment_findrestaurantname,
                                 container, false);
        System.out.println("Bla reached.");
        return v;
    }

}
