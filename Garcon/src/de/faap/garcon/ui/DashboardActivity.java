package de.faap.garcon.ui;

import android.content.*;
import android.os.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import de.faap.garcon.*;

public class DashboardActivity extends SherlockActivity {
    // keep a handle to MenuItems which have an ActionView to collapse them when
    // needed
    protected MenuItem searchRestaurant;
    protected MenuItem searchDish;

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

        // If we're on a phone with at least honeycomb we use the searchview
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // configure search restaurant
            searchRestaurant = menu.findItem(R.id.menu_search_restaurant);
            SearchView restaurantSearchView =
                    (SearchView) searchRestaurant.getActionView();
            restaurantSearchView.setQueryHint(getResources()
                    .getString(R.string.menu_search_restaurant_hint));
            restaurantSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            restaurantSearchView
                    .setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            searchRestaurant.collapseActionView();
                            startFindRestaurantActivity(query);
                            return false;
                        }

                    });

            // configure search dish
            searchDish = menu.findItem(R.id.menu_search_dish);
            SearchView dishSearchView = (SearchView) searchDish.getActionView();
            dishSearchView.setQueryHint(getResources()
                    .getString(R.string.menu_search_dish_hint));
            restaurantSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            dishSearchView
                    .setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return true;
                        }

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            // TODO fire intent
                            setTitle(query);
                            searchDish.collapseActionView();
                            return true;
                        }

                    });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_nearby_restaurants:
            // TODO add behaviour
            setTitle(getResources().getString(R.string.btn_nearby_restaurants));
            return true;

        case R.id.menu_nearby_dishes:
            // TODO add behaviour
            setTitle(getResources().getString(R.string.btn_nearby_dishes));
            return true;

        case R.id.menu_search:
            // before starting a new search hide old search views
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                searchRestaurant.collapseActionView();
                searchDish.collapseActionView();
            }
            return true;

        case R.id.menu_search_restaurant:
            // if we are on a pre-honeycomb device we can't start a searchview,
            // so we start the new activity with an empty search request
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                // TODO autschi fragen, wie er mit leeren suchanfragen umgehen
                // will :)
                startFindRestaurantActivity("Der Wirt");
            }
            return true;

        case R.id.menu_search_dish:
            // if we are on a pre-honeycomb device we can't start a searchview,
            // so we start the new activity with an empty search request
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                // TODO fire intent
                setTitle("leere suche");
            }
            return true;

        case R.id.menu_post_dish:
            // TODO add behaviour
            setTitle(getResources().getString(R.string.btn_post_dish));
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    protected void startFindRestaurantActivity(String restName) {
        Intent intent = new Intent(this, FindRestNameActivity.class);
        intent.putExtra(IntentData.REST_NAME.toString(), restName);
        startActivity(intent);
    }
}
