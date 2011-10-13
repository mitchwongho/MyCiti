package com.touchswipeengage.myciti.domain.network;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.client.RestTemplate;

import com.touchswipeengage.myciti.domain.TweetItem;


/**
 * The <code>RESTClientWrapper</code> is responsible for providing a delegate-styled interface
 * for RESTful networking communications
 * @author mwho
 */
public class RESTClientWrapper {
	private final static RESTClientWrapper theInstance = new RESTClientWrapper();
	private static final String DRUPEL_URL = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q={query}";
	private RestTemplate rt;
	/**
	 * Untouchable constructor
	 */
	private RESTClientWrapper(){
		this.rt = new RestTemplate();
	}
	/**
	 * Returns a singleton instance of this class.
	 * @return  a singleton instance of this class.
	 */
	public static final RESTClientWrapper getInstance() {
		return RESTClientWrapper.theInstance;
	}
	
	/**
	 * @throws Exception
	 */
	public String doSomethingRestful() throws Exception {
		return "";
	}
	
	/**
	 * Fetches the specified Twitter user's feed	
	 * @param username a twitter handle
	 * @return the twitter feed
	 * @throws Exception upon any error
	 */
	public List<TweetItem> getTwitterUserUpdate(String username) throws Exception {
		List<TweetItem> retval = new ArrayList<TweetItem>();
		Twitter twitter = new TwitterTemplate();
		SearchResults sr = twitter.searchOperations().search("from:"+username);
		Date now = new Date();
		if (sr != null) {
			List<Tweet> tweets = sr.getTweets();
			for (Tweet tweet : tweets) {
				String time = (String) android.text.format.DateUtils.getRelativeTimeSpanString(tweet.getCreatedAt().getTime(), now.getTime(), android.text.format.DateUtils.HOUR_IN_MILLIS, android.text.format.DateUtils.FORMAT_NUMERIC_DATE);
				retval.add(new TweetItem(tweet.getId(), tweet.getProfileImageUrl(), tweet.getText(), time /*DateUtils.formatDate(tweet.getCreatedAt(), "E, dd MMM yyyy kk:mm:ss")*/));
			}
		}
		return retval;
	}
}
