/**
 * 
 */
package com.touchswipeengage.myciti.fragments;

import com.touchswipeengage.myciti.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author mwho
 *
 */
public class InfoFragment extends Fragment {
	private final static String HTML_CONTENT = 
			"For assistance with MyCiTi services call the City of Cape Town - Transport Information Centre on 0800 65 64 63.<p/>"
		+ "Other useful numbers<br/>"
		+ "City of Cape Town general help-line<br/>"
		+ "Call 0860 103 089 <br/>"
		+ "Flight information </br>"
		+ "Call 086 72 77 888 <br/>"
		+ "<p/>"
		+ "Emergency (fire/ambulance)<br/>"
		+ "Call 107 from a landline or 021 480 7700 from a cell phone. "
		+ "<p/>"
		+ "The content and information presented in this application is provided as is and can be found at the MyCiTi website http://www.capetown.gov.za/en/MyCiti";
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
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
		View v = (LinearLayout)inflater.inflate(R.layout.notifications_layout, container, false);
		TextView tView = (TextView)v.findViewById(R.id.info_text_body);
		tView.setText(Html.fromHtml(InfoFragment.HTML_CONTENT));
		return v;
	}
}
