package de.faap.garcon.ui;

import android.os.*;
import com.actionbarsherlock.app.*;
import de.faap.garcon.*;

public class FindRestNameActivity extends SherlockFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findrestaurantname);
        // getIntent().getExtras().getString(IntentData.REST_NAME.toString(),
        // "");
    }

}
