/**
 * 
 */
package com.touchswipeengage.myciti.services.twitter;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;

import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.TweetItem;
import com.touchswipeengage.myciti.domain.network.RESTClientWrapper;
import com.touchswipeengage.myciti.fragments.TwitterFragment;

/**
 * The <code>MyCitiUpdateTwitterService</code> service is responsible for updating the twitter feed 
 * @author mwho
 */
public class MyCitiUpdateTwitterService extends IntentService {

	/**
	 * Contructor
	 */
	public MyCitiUpdateTwitterService() {
		super("MyCitiUpdateTwitterService");
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent arg0) {
		// Get the latest tweets
		try {
			List<TweetItem> retval = RESTClientWrapper.getInstance().getTwitterUserUpdate("MyCitiBus");
			// Update ContentRepository cache
			ContentRepository.getInstance().setTweets(retval);
			// Broadcast update
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction(TwitterFragment.MyCitiUpdateTwitterBroadcasrReciever.ACTION_RESP);
			broadcastIntent.addCategory(Intent.ACTION_DEFAULT);
			sendBroadcast(broadcastIntent);
		} catch (Exception e) {
			//TODO Handle Exception
		}
	}

}
