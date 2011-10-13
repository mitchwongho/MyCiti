/**
 * 
 */
package com.touchswipeengage.myciti;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * @author mwho
 *
 */
public class BaseActivity extends FragmentActivity {

	/**
	 * Shows the activity as specified by <code>clazz</code>
	 * @param clazz the activity class to show
	 */
	private void showActivity(Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		super.startActivity(intent);
	}
}
