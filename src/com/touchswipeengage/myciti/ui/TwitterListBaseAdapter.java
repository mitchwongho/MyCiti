/**
 * 
 */
package com.touchswipeengage.myciti.ui;



import java.util.ArrayList;
import java.util.List;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.TweetItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author mwho
 * The <code>TwitterListBaseAdapter</code> binds data to the ListView
 */
public class TwitterListBaseAdapter extends BaseAdapter {
	private final static TwitterListBaseAdapter theInstance = new TwitterListBaseAdapter();
	private List<TweetItem> tweets;
	private LayoutInflater mInflater;
	/**
	 * @author mwho
	 */
	private class ViewHolder {
		public ImageView theAvatar;
		public TextView theTweet;
		public TextView theTimestamp;
		/**
		 * Constructor
		 * @param parent the parent view
		 */
		public ViewHolder(View parent) {
			this.theAvatar = (ImageView)parent.findViewById(R.id.tweet_avatar_view);
			this.theTweet = (TextView)parent.findViewById(R.id.tweet_message_view);
			//this.theTweet.setMovementMethod(LinkMovementMethod.getInstance()); //makes the hyperlink clickable
			this.theTimestamp = (TextView)parent.findViewById(R.id.tweet_timestamp_view);
		}
	}
	/**
	 * Class constructor
	 */
	private TwitterListBaseAdapter() {
		this.tweets = new ArrayList<TweetItem>();
	} 
	
	/**
	 * @return a Singleton instance of this class
	 */
	public final static TwitterListBaseAdapter getInstance() {
		return TwitterListBaseAdapter.theInstance;
	}
	
	/**
	 * @param tweets
	 * @param context
	 */
	public void loadData(Context context, List<TweetItem> tweets) {
		this.tweets.clear();
		this.tweets.addAll(tweets);
		this.mInflater = LayoutInflater.from(context);
		this.notifyDataSetChanged();
	}
	
	/**
	 * @param tweets
	 */
	public void appendData(List<TweetItem> tweets) {
		this.tweets.addAll(tweets);
		this.notifyDataSetChanged();
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return (this.tweets == null) ? 0 : this.tweets.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return this.tweets.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView != null) {
			holder = (ViewHolder)convertView.getTag();
		} else {
			convertView = this.mInflater.inflate(R.layout.tweetrowlayout, null); 
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		//update viewholder
		TweetItem tweet = this.tweets.get(position);
		holder.theAvatar.setImageResource(R.drawable.ic_twitter_avatar);	
		holder.theTweet.setText(tweet.getTheTweet());
		holder.theTimestamp.setText(tweet.getTimeststamp());
		return convertView;
	}

}
