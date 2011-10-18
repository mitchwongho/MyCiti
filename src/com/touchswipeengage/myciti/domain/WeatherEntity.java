package com.touchswipeengage.myciti.domain;

import android.graphics.Bitmap;

public class WeatherEntity {
	private long id;
	private String name; //Day of Week or Current
	private int currentTemp, minTemp, maxTemp;
	private String condition;
	private String humidity;
	private String windCondition;
	private String iconPath;
	private boolean isCurrentCondition;
	private Bitmap bitmap;
	
	public WeatherEntity() {
		
	}
	public WeatherEntity(long id) {
		this.id = id;
	}

	
	/**
	 * @param id
	 * @param name
	 * @param currentTemp
	 * @param minTemp
	 * @param maxTemp
	 * @param condition
	 * @param humidity
	 * @param windCondition
	 * @param iconPath
	 */
	public WeatherEntity(long id, String name, int currentTemp, int minTemp,
			int maxTemp, String condition, String humidity,
			String windCondition, String iconPath, boolean isCurrentCondition,
			Bitmap bitmap) {
		super();
		this.id = id;
		this.name = name;
		this.currentTemp = currentTemp;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.condition = condition;
		this.humidity = humidity;
		this.windCondition = windCondition;
		this.iconPath = iconPath;
		this.isCurrentCondition = isCurrentCondition;
		this.bitmap = bitmap;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currentTemp
	 */
	public int getCurrentTemp() {
		return currentTemp;
	}

	/**
	 * @param currentTemp the currentTemp to set
	 */
	public void setCurrentTemp(int currentTemp) {
		this.currentTemp = currentTemp;
	}

	/**
	 * @return the minTemp
	 */
	public int getMinTemp() {
		return minTemp;
	}

	/**
	 * @param minTemp the minTemp to set
	 */
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}

	/**
	 * @return the maxTemp
	 */
	public int getMaxTemp() {
		return maxTemp;
	}

	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the windCondition
	 */
	public String getWindCondition() {
		return windCondition;
	}

	/**
	 * @param windCondition the windCondition to set
	 */
	public void setWindCondition(String windCondition) {
		this.windCondition = windCondition;
	}

	/**
	 * @return the iconPath
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * @param iconPath the iconPath to set
	 */
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	/**
	 * @return the bitmap
	 */
	public Bitmap getBitmap() {
		return bitmap;
	}
	/**
	 * @param bitmap the bitmap to set
	 */
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
	/**
	 * @Rreturn a clone of this instance
	 * 
	 */
	public WeatherEntity clone() {
		return new WeatherEntity(this.id,
				this.name,
				this.currentTemp,
				this.minTemp,
				this.maxTemp,
				this.condition,
				this.humidity,
				this.windCondition,
				this.iconPath,
				this.isCurrentCondition,
				this.bitmap);
	}
	/**
	 * @return the isCurrentCondition
	 */
	public boolean isCurrentCondition() {
		return isCurrentCondition;
	}
	/**
	 * @param isCurrentCondition the isCurrentCondition to set
	 */
	public void setCurrentCondition(boolean isCurrentCondition) {
		this.isCurrentCondition = isCurrentCondition;
	}
	
}
