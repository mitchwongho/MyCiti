/**
 * 
 */
package com.touchswipeengage.myciti.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.Route;
import com.touchswipeengage.myciti.ui.RouteInfoTabsActivity;

/**
 * @author mwho
 *
 */
public class RoutesFragment extends Fragment implements OnItemClickListener {

	private static String[] ROUTES = new String[4];
	private Route[] routes;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
	 */
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
		super.setHasOptionsMenu(false);
		View view = inflater.inflate(R.layout.routes_layout, container, false);
		return view;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Get routes from repository
		this.routes = ContentRepository.getInstance().getRoutes().toArray(new Route[ContentRepository.getInstance().getRoutes().size()]);
		String[] routeNames = new String[routes.length];
		for (int i = 0; i< routes.length; i++)
			routeNames[i] = routes[i].getName();
		ListView list = (ListView)view.findViewById(R.id.routes_list);
		list.setAdapter(new ArrayAdapter<String>(this.getActivity().getBaseContext(), android.R.layout.simple_list_item_1, routeNames));
		list.setOnItemClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
		Intent intent = new Intent(getActivity(), RouteInfoTabsActivity.class);
		intent.putExtra("routeId", pos);
		super.startActivity(intent);
	}
	
}
