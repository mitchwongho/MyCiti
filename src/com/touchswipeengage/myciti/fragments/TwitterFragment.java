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
import com.touchswipeengage.myciti.domain.TweetItem;
import com.touchswipeengage.myciti.ui.TwitterListBaseAdapter;

/**
 * @author mwho
 *
 */
public class TwitterFragment extends Fragment {
	private TwitterListBaseAdapter adapter;
	private MyCitiUpdateTwitterBroadcasrReciever receiver;
	private View mView;
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
		this.mView = inflater.inflate(R.layout.twitter_layout, container, false);
		//
		// REGISTER BROADCAST RECEVIED
		//
		IntentFilter filter = new IntentFilter(TwitterFragment.MyCitiUpdateTwitterBroadcasrReciever.ACTION_RESP);
		filter.addCategory(Intent.ACTION_DEFAULT);
		this.receiver = new MyCitiUpdateTwitterBroadcasrReciever();
		super.getActivity().registerReceiver(receiver, filter);
		//
		// INSTANTIATE LIST ADAPTER
		//
		ListView lv = (ListView)TwitterFragment.this.mView.findViewById(R.id.twitter_layout_listview);
		TwitterFragment.this.adapter = TwitterListBaseAdapter.getInstance();
		lv.setAdapter(TwitterFragment.this.adapter);
		//
		this.showList();
		//
		return this.mView;
	}
	
	/**
	 * Loads list data
	 */
	private void showList() {
		try {
			List<TweetItem> tweets = ContentRepository.getInstance().getTweets();
			if (tweets == null || tweets.isEmpty())
				return;
			((ProgressBar)TwitterFragment.this.mView.findViewById(R.id.twitter_progressbar)).setVisibility(View.GONE);
			TwitterFragment.this.adapter.loadData(TwitterFragment.this.getActivity(), tweets);
			
		} catch (Exception e) {
			//TODO Handle exception
		}
	}

	/**
	 * 
	 * @author mwho
	 *
	 */
	public class MyCitiUpdateTwitterBroadcasrReciever extends BroadcastReceiver {
		public final static String ACTION_RESP = "com.touchswipeengage.myciti.intent.action.TWITTER_UPDATED";
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// Handle broadcast intent
			TwitterFragment.this.showList();
		}
	}
	
}
