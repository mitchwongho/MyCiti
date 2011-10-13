/**
 * 
 */
package com.touchswipeengage.myciti.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.Route;

/**
 * @author mwho
 *
 */
public class RouteInfoTabsActivity extends TabActivity {

	private Route mRoute;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.route_info_tab_layout);
		TabHost tabHost = /*(TabHost)super.findViewById(android.R.id.tabhost)*/getTabHost();
		
		Resources res = getResources();
		
		int routeId = super.getIntent().getIntExtra("routeId", 0);
		this.mRoute = ContentRepository.getInstance().getRoutes().get(routeId);
		
		Intent intentInfo = new Intent(this, RouteInfoActivity.class);
		intentInfo.putExtra("routeId", routeId);
		Intent intentMap = new Intent(this, RoutesMapActivity.class);
		intentMap.putExtra("routeId", routeId);
		
		TabHost.TabSpec tabSpec = tabHost.newTabSpec("schedule").setIndicator(getString(R.string.tabs_timetable_fare),res.getDrawable(R.drawable.tab_ic_timetable)).setContent(intentInfo);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("map").setIndicator(getString(R.string.tabs_map),res.getDrawable(R.drawable.tab_ic_map)).setContent(intentMap);
		tabHost.addTab(tabSpec);
		
		tabHost.setCurrentTabByTag("schedule");
	}
	

}
