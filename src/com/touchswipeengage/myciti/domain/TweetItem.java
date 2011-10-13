package com.touchswipeengage.myciti.domain;


/**
 * @author mwho
 * The <code>TweetItem</code> represents a tweet
 */
public class TweetItem {
	private long id;
	private String avatar_url;
	private String theTweet;
	private String timeststamp;
	/**
	 * @param id
	 * @param avatar_url
	 * @param theTweet
	 * @param timeststamp
	 */
	public TweetItem(long id, String avatar_url, String theTweet, String timeststamp) {
		super();
		this.id = id;
		this.avatar_url = avatar_url;
		this.theTweet = theTweet;
		this.timeststamp = timeststamp;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the avatar_id
	 */
	public String getAvatarId() {
		return avatar_url;
	}
	/**
	 * @param avatar_id the avatar_id to set
	 */
	public void setAvatarId(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	/**
	 * @return the theTweet
	 */
	public String getTheTweet() {
		return theTweet;
	}
	/**
	 * @param theTweet the theTweet to set
	 */
	public void setTheTweet(String theTweet) {
		this.theTweet = theTweet;
	}
	/**
	 * @return the timeststamp
	 */
	public String getTimeststamp() {
		return timeststamp;
	}
	/**
	 * @param timeststamp the timeststamp to set
	 */
	public void setTimeststamp(String timeststamp) {
		this.timeststamp = timeststamp;
	}
	
}
