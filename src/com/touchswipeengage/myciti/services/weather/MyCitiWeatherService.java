/**
 * 
 */
package com.touchswipeengage.myciti.services.weather;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.touchswipeengage.myciti.domain.ContentRepository;
import com.touchswipeengage.myciti.domain.WeatherEntity;
import com.touchswipeengage.myciti.domain.network.NetworkHelper;
import com.touchswipeengage.myciti.fragments.TwitterFragment;
import com.touchswipeengage.myciti.fragments.WeatherFragment;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * @author mwho
 *
 */
public class MyCitiWeatherService extends IntentService {

	private final static String GOOGLE_WEATHER_API_URL = "http://www.google.co.za/ig/api?weather=Cape+Town&hl=en_US";
	public MyCitiWeatherService() {
		super("MyCitiWeatherService");
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent arg0) {
		// Fetch the data from Google Weather API
		try {
			String xml = NetworkHelper.loadContent(GOOGLE_WEATHER_API_URL);
			List<WeatherEntity> entities = new WeatherAPIParser(new ByteArrayInputStream(xml.getBytes())).parse();
			for (WeatherEntity entity : entities) {
				entity.setBitmap(NetworkHelper.loadImage("http://www.google.com"+entity.getIconPath()));
			}
			ContentRepository.getInstance().setWeatherForecast(entities);
			// Broadcast update
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction(WeatherFragment.MyCitiWeatherBroadcasrReceiver.ACTION_RESP);
			broadcastIntent.addCategory(Intent.ACTION_DEFAULT);
			sendBroadcast(broadcastIntent);
		} catch (Exception e) {
		} finally {
		}
	}

}
