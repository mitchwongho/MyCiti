/**
 * 
 */
package com.touchswipeengage.myciti.fragments;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.WeatherEntity;
import com.touchswipeengage.myciti.ui.WeatherListAdapter;

/**
 * @author mwho
 *
 */
public class WeatherFragment extends Fragment {

	private View mView;
	private WeatherListAdapter adapter;
	private MyCitiWeatherBroadcasrReceiver receiver;
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("MyCiti", "onCreateView()");
		if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
		this.mView = inflater.inflate(R.layout.weather_layout, container, false);
		//
		// Register for broadcast intent
		//
		IntentFilter filter = new IntentFilter(WeatherFragment.MyCitiWeatherBroadcasrReceiver.ACTION_RESP);
		filter.addCategory(Intent.ACTION_DEFAULT);
		this.receiver = new MyCitiWeatherBroadcasrReceiver();
		super.getActivity().registerReceiver(receiver, filter);
		//
		// Setup List adapter
		//
		ListView lv = (ListView)this.mView.findViewById(R.id.weather_layout_listview);
		this.adapter = WeatherListAdapter.getInstance();
		lv.setAdapter(WeatherFragment.this.adapter);
		this.displayList();
		//
		return this.mView;
	}
	
	/**
	 * Displays the list (if data uis available)
	 */
	private void displayList() {
		try {
			List<WeatherEntity> entities = ContentRepository.getInstance().getWeatherForecast();
			if (entities == null || entities.isEmpty())
				return;
			((ProgressBar)WeatherFragment.this.mView.findViewById(R.id.weather_progressbar)).setVisibility(View.GONE);
			WeatherFragment.this.adapter.loadData(WeatherFragment.this.getActivity(), entities);
		} catch (Exception e) {
			Log.e("MyCiti", e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @author mwho
	 *
	 */
	public class MyCitiWeatherBroadcasrReceiver extends BroadcastReceiver {
		public final static String ACTION_RESP = "com.touchswipeengage.myciti.intent.action.WEATHER_UPDATED";
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Log.d("MyCiti","Weather Broadcast Recevier : onReceive");
			// Handle broadcast intent
			WeatherFragment.this.displayList();
		}
	}
}
