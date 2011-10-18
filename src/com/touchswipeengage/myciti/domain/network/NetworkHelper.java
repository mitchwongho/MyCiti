/**
 * 
 */
package com.touchswipeengage.myciti.domain.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * @author mwho
 *
 */
public class NetworkHelper {
	
	/**
	 * Returns <code>true</code> if the device is online (wifi/3G)
	 * @param aContext a centext to the application
	 * @return <code>true</code> if the device is online (wifi/3G)
	 */
	public static boolean isOnline(Context aContext) {
		ConnectivityManager cm = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	/**
	 * @param url
	 * @return
	 */
	public static Bitmap loadImage(String url) {
		InputStream is = null;
		Bitmap bmp = null;
		try {
			HttpGet httpRequest = new HttpGet(URI.create(url) );
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			is = bufHttpEntity.getContent();
			bmp = BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			Log.getStackTraceString(e);
			Log.e("DCU", "Here Be Dragons");
			e.printStackTrace();
			return null;
		} finally {
			try { if (is != null) is.close(); } catch (Exception ee) {}
		}
		return bmp;
	}
	/**
	 * @param url
	 * @return
	 */
	public static String loadContent(String url) throws Exception {
		InputStream is = null;
		String retval = null;
		try {
			HttpGet httpRequest = new HttpGet(URI.create(url) );
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
			HttpEntity entity = response.getEntity();
			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
			is = bufHttpEntity.getContent();
			byte[] content = new byte[is.available()];
			is.read(content);
			retval = new String(content);
		} finally {
			try { if (is != null) is.close(); } catch (Exception ee) {}
		}
		return retval;
	}
}
