/**
 * 
 */
package com.touchswipeengage.myciti.services.weather;



import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

import com.touchswipeengage.myciti.domain.WeatherEntity;

/**
 * @author mwho
 *
 */
public class WeatherAPIParser {
	public final static SimpleDateFormat FEED_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // e.g Tues, 06 Sept 2011
	public final static String ELE_ROOT = "xml_api_reply";
	public final static String ELE_WEATHER = "weather";
	public final static String ELE_FORECAST_INFO = "forecast_information";
	public final static String ELE_CURRENT_CONDITIONS = "current_conditions";
	public final static String ELE_FORECAST_CONDITIONS = "forecast_conditions";

	
	final InputStream is;
	
    /**
     * @param feedUrl
     */
    public WeatherAPIParser(InputStream is){
        this.is = is;
    }

    /**
	 * @return a list of <code>WeatherEntity</code> instances
	 */
	public List<WeatherEntity> parse() {
		final WeatherEntity weatherEntity = new WeatherEntity();
        RootElement root = new RootElement(ELE_ROOT);
        final List<WeatherEntity> weatherEntities = new ArrayList<WeatherEntity>();
        Element weather = root.getChild(ELE_WEATHER);
        Element currentConditions = weather.getChild(ELE_CURRENT_CONDITIONS); 
        Element forecastConditions = weather.getChild(ELE_FORECAST_CONDITIONS); 
        /**
         * CURRENT CONDITIONS
         */
        currentConditions.setEndElementListener(new EndElementListener() {
        	
        	@Override
        	public void end() {
        		weatherEntity.setCurrentCondition(true);
        		weatherEntities.add(weatherEntity.clone());
        		
        	}
        });
        currentConditions.getChild("temp_f").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setCurrentTemp(Integer.parseInt(attributes.getValue("data")));
        	}
        });
        currentConditions.getChild("humidity").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setHumidity(attributes.getValue("data"));
        	}
        });
        currentConditions.getChild("icon").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setIconPath(attributes.getValue("data"));
        	}
        });
        currentConditions.getChild("wind_condition").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setWindCondition(attributes.getValue("data"));
        	}
        });
        /**
         * FORECASE CONDITIONS
         */
        forecastConditions.setEndElementListener(new EndElementListener() {
			
			@Override
			public void end() {
				weatherEntity.setCurrentCondition(false);
				weatherEntities.add(weatherEntity.clone());
				
			}
		});
        forecastConditions.getChild("day_of_week").setStartElementListener(new StartElementListener() {
			
			@Override
			public void start(Attributes attributes) {
				weatherEntity.setName(attributes.getValue("data"));
			}
		});
        forecastConditions.getChild("low").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setMinTemp(Integer.parseInt(attributes.getValue("data")));
        	}
        });
        forecastConditions.getChild("high").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setMaxTemp(Integer.parseInt(attributes.getValue("data")));
        	}
        });
        forecastConditions.getChild("icon").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setIconPath(attributes.getValue("data"));
        	}
        });
        forecastConditions.getChild("condition").setStartElementListener(new StartElementListener() {
        	
        	@Override
        	public void start(Attributes attributes) {
        		weatherEntity.setCondition(attributes.getValue("data"));
        	}
        });
        /**
         * PASS IN THE STREAM AND CONTENTHANDLER TO THE PARSER    	
         */
        try {
            Xml.parse(this.is, Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        	try { if (this.is != null) this.is.close(); } catch (Exception e) {}
        }
        return weatherEntities;
    }
}
