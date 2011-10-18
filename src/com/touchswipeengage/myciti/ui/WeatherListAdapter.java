/**
 * 
 */
package com.touchswipeengage.myciti.ui;



import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.touchswipeengage.myciti.R;
import com.touchswipeengage.myciti.domain.WeatherEntity;

/**
 * @author mwho
 * The <code>WeatherListAdapter</code> binds data to the ListView
 */
public class WeatherListAdapter extends BaseAdapter {
	private final static NumberFormat TEMP_FORMATTER = NumberFormat.getIntegerInstance();
	private final static WeatherListAdapter theInstance = new WeatherListAdapter();
	private List<WeatherEntity> entities;
	private LayoutInflater mInflater;
	private Resources res;
	/**
	 * @author mwho
	 */
	private class ViewHolder {
		public ImageView theIcon;
		public TextView weatherConditionText;
		public TextView lowTempText;
		public TextView highTempText;
		public TextView dowText;
		/**
		 * Constructor
		 * @param parent the parent view
		 */
		public ViewHolder(View parent) {
			this.theIcon = (ImageView)parent.findViewById(R.id.weather_icon);
			this.dowText = (TextView)parent.findViewById(R.id.weather_dow_text);
			this.weatherConditionText = (TextView)parent.findViewById(R.id.weather_condition_text);
			this.lowTempText = (TextView)parent.findViewById(R.id.weather_low_temp_text);
			this.highTempText = (TextView)parent.findViewById(R.id.weather_high_temp_text);
		}
	}
	/**
	 * Class constructor
	 */
	private WeatherListAdapter() {
		this.entities = new ArrayList<WeatherEntity>();
		TEMP_FORMATTER.setMaximumFractionDigits(0);
	} 
	
	/**
	 * @return a Singleton instance of this class
	 */
	public final static WeatherListAdapter getInstance() {
		return WeatherListAdapter.theInstance;
	}
	
	/**
	 * @param tweets
	 * @param context
	 */
	public void loadData(Context context, List<WeatherEntity> entities) {
		this.res = context.getResources();
		this.entities.clear();
		this.entities.addAll(entities);
		this.mInflater = LayoutInflater.from(context);
		this.notifyDataSetChanged();
	}
	
	/**
	 * @param tweets
	 */
	public void appendData(List<WeatherEntity> entities) {
		this.entities.addAll(entities);
		this.notifyDataSetChanged();
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return (this.entities == null) ? 0 : this.entities.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return this.entities.get(position);
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
			convertView = this.mInflater.inflate(R.layout.weather_row_layout, null); 
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		//update viewholder
		WeatherEntity weather = this.entities.get(position);
		//holder.theIcon.setImageResource(R.drawable.ic_twitter_avatar);	
		holder.theIcon.setImageBitmap(weather.getBitmap());	
		holder.dowText.setText(weather.isCurrentCondition() ? this.res.getText(R.string.weather_label__current): weather.getName());
		holder.lowTempText.setText(weather.isCurrentCondition() ? 
				formatTemp(R.string.weather_label__curr_temp, weather.getCurrentTemp()) :
					formatTemp(R.string.weather_label__low_temp, weather.getMinTemp()));
		holder.highTempText.setText(weather.isCurrentCondition() ? 
				"" :
					formatTemp(R.string.weather_label__high_temp, weather.getMaxTemp()));
		holder.weatherConditionText.setText(weather.isCurrentCondition() ? 
				weather.getWindCondition() + ' ' + weather.getHumidity() :
					weather.getCondition());
		return convertView;
	}
	
	/**
	 * Returns a formatted String for the tempreture
	 * @param resId
	 * @param temp
	 * @return
	 */
	private String formatTemp(int resId, int temp) {
		StringBuffer sb = new StringBuffer(this.res.getText(resId));
		sb.append(' ');
		sb.append( TEMP_FORMATTER.format((0.555)*(temp - 32)) );
		sb.append(' ');
		sb.append(this.res.getText(R.string.weather_label__celcius));
		return sb.toString();
	}

}
