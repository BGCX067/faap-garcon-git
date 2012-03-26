package de.faap.garcon.ui;

import android.os.*;
import android.view.*;
import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import de.faap.garcon.*;

public class DashboardActivity extends SherlockActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // make the home button not clickable
        getSupportActionBar().setHomeButtonEnabled(false);

        // add button listeners
        this.findViewById(R.id.home_btn_post_dish)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_post_dish));
                    }

                });

        this.findViewById(R.id.home_btn_nearby_restaurants)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_nearby_restaurants));
                    }

                });

        this.findViewById(R.id.home_btn_nearby_dishes)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_nearby_dishes));
                    }

                });

        this.findViewById(R.id.home_btn_search_nearby_dish)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_search_nearby_dish));
                    }

                });

        this.findViewById(R.id.home_btn_search_restaurant)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_search_restaurant));
                    }

                });

        this.findViewById(R.id.home_btn_feedback)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO add behaviour
                        setTitle(getResources()
                                .getString(R.string.btn_feedback));
                    }

                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.dashboard_menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_nearby_restaurants:
            setTitle(getResources().getString(R.string.btn_nearby_restaurants));
            return true;

        case R.id.menu_nearby_dishes:
            setTitle(getResources().getString(R.string.btn_nearby_dishes));
            return true;

        case R.id.menu_search_restaurant:
            return true;

        case R.id.menu_search_dish:
            return true;

        case R.id.menu_post_dish:
            setTitle(getResources().getString(R.string.btn_post_dish));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
