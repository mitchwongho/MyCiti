/**
 * 
 */
package com.touchswipeengage.myciti.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.Route;

/**
 * @author mwho
 *
 */
public class RouteInfoActivity extends Activity {

	private Route route;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_info_layout);
		List<Route> routes = ContentRepository.getInstance().getRoutes();
		this.route = routes.get(super.getIntent().getExtras().getInt("routeId"));
		((TextView)super.findViewById(R.id.article_layout_header)).setText(this.route.getName());
		((TextView)super.findViewById(R.id.article_layout_description)).setText(this.route.getDescription());
		this.updateWeekdayTimes(super.getApplicationContext());
		this.updateSaturdayTimes(super.getApplicationContext());
		this.updateSundayTimes(super.getApplicationContext());
	}
	
	/**
	 * @param v
	 */
	private void updateWeekdayTimes(Context context) {
		LinkedHashMap<String, String> ht = this.route.getRouteTimes().getWeekdays();
		Iterator<Entry<String, String>> it = ht.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addTableRow(context, findViewById(R.id.route_info_view), R.id.table_timetable_weekdays, entry.getKey(), entry.getValue());
		}
	}
	/**
	 * @param v
	 */
	private void updateSaturdayTimes(Context context) {
		LinkedHashMap<String, String> ht = this.route.getRouteTimes().getSaturdays();
		Iterator<Entry<String, String>> it = ht.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addTableRow(context, findViewById(R.id.route_info_view), R.id.table_timetable_saterdays, entry.getKey(), entry.getValue());
		}
	}
	/**
	 * @param v
	 */
	private void updateSundayTimes(Context context) {
		LinkedHashMap<String, String> ht = this.route.getRouteTimes().getSundaysAndHolidays();
		Iterator<Entry<String, String>> it = ht.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			this.addTableRow(context, findViewById(R.id.route_info_view), R.id.table_timetable_sundays, entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * @param context
	 * @param parent
	 * @param tableRId
	 * @param col1
	 * @param col2
	 */
	private void addTableRow(Context context, View parent, int tableRId, String col1, String col2) {
		TableRow row = new TableRow(context);
		row.setPadding(0, 4, 0, 4);
		TextView txt1 = new TextView(context);
		txt1.setText(col1);
		txt1.setGravity(Gravity.LEFT);
		txt1.setTextAppearance(context, R.style.table_font);
		TextView txt2 = new TextView(context);
		txt2.setText(col2);
		txt2.setGravity(Gravity.RIGHT);
		txt2.setTextAppearance(context, R.style.table_font_bold);
		row.addView(txt1);
		row.addView(txt2);
		TableLayout table = (TableLayout)parent.findViewById(tableRId);
		table.addView(row);
	}
}
