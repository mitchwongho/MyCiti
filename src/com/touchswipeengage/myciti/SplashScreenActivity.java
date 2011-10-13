/**
 * 
 */
package com.touchswipeengage.myciti;

import com.touchswipeengage.myciti.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author mwho
 *
 */
public class SplashScreenActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
	}
	
	private void showActivity(Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		super.startActivity(intent);
	}

}
