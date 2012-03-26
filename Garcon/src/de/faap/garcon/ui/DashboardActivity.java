package de.faap.garcon.ui;

import android.content.*;
import android.os.*;
import android.view.*;

import com.actionbarsherlock.app.*;

import de.faap.garcon.*;

public class DashboardActivity extends SherlockActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		// add button listeners
		this.findViewById(R.id.home_btn_post_dish).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources().getString(
								R.string.btn_post_dish));
					}

				});

		this.findViewById(R.id.home_btn_nearby_restaurants).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources().getString(
								R.string.btn_nearby_restaurants));
					}

				});

		this.findViewById(R.id.home_btn_nearby_dishes).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources().getString(
								R.string.btn_nearby_dishes));
					}

				});

		this.findViewById(R.id.home_btn_search_nearby_dish).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources().getString(
								R.string.btn_search_nearby_dish));
					}

				});

		this.findViewById(R.id.home_btn_search_restaurant).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources().getString(
								R.string.btn_search_restaurant));
					}

				});

		this.findViewById(R.id.home_btn_feedback).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// TODO add behaviour
						setTitle(getResources()
								.getString(R.string.btn_feedback));
						startFindRestaurantActivity();
					}

				});
	}

	protected void startFindRestaurantActivity() {
		Intent intent = new Intent(this, FindRestNameActivity.class);
		intent.putExtra(IntentData.REST_NAME.toString(), "Der Wirt");
		startActivity(intent);
	}
}
