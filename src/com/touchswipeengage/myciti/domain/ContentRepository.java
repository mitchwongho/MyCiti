/**
 * 
 */
package com.touchswipeengage.myciti.domain;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import com.touchswipeengage.myciti.R;

/**
 * @author mwho
 *
 */
public class ContentRepository {
	/** maintains a singleton instance of this class*/
	private static final ContentRepository theInstance = new ContentRepository();
	
	private List<Route> routes = new Vector<Route>();
	private List<Station> stations = new Vector<Station>();
	private Hashtable<Class, Object> cache;
	
	/**
	 * Private contructor
	 */
	private ContentRepository() {
		cache = new Hashtable<Class, Object>();
		initRoutes();
		initStations();
	}
	
	private void initRoutes() {
		Route route = null;
		routes.add(route = new Route(0, "Table View - Civic Centre","The fare for this route is R10 per trip. Maximum charge for any travel combination is R10 per trip, excluding the Civic Centre - Airport route.", R.drawable.da_marker_red));
		LinkedHashMap<String, String> weekdays = route.getRouteTimes().getWeekdays();
		weekdays.put("05:45 to 06:30", "Every 20 minutes");
		weekdays.put("06:30 to 09:00", "Every 10 minutes");
		weekdays.put("09:00 to 15:30", "Every 20 minutes");
		weekdays.put("15:30 to 18:00", "Every 10 minutes");
		weekdays.put("18:00 to 21:00", "Every 20 minutes");
		LinkedHashMap<String, String> saturdays = route.getRouteTimes().getSaturdays();
		saturdays.put("06:45 to 21:00", "Every 20 minutes");
		LinkedHashMap<String, String> sundays = route.getRouteTimes().getSundaysAndHolidays();
		sundays.put("07:45 to 21:00", "Every 20 minutes");
		//
		routes.add(route = new Route(1, "Table View Feeder Routes","The fare for this route is R5 per trip. Maximum charge for any travel combination is R10 per trip, excluding the Civic Centre - Airport route.", R.drawable.da_marker_blue));
		weekdays = route.getRouteTimes().getWeekdays();
		weekdays.put("05:45 to 06:30", "Every 20 minutes");
		weekdays.put("06:30 to 09:00", "Every 15 minutes");
		weekdays.put("09:00 to 15:30", "Every 20 minutes");
		weekdays.put("15:30 to 18:00", "Every 15 minutes");
		weekdays.put("18:00 to 21:30", "Every 20 minutes");
		saturdays = route.getRouteTimes().getSaturdays();
		saturdays.put("06:45 to 21:30", "Every 20 minutes");
		sundays = route.getRouteTimes().getSundaysAndHolidays();
		sundays.put("07:45 to 21:30", "Every 20 minutes");
		//
		routes.add(route = new Route(2, "Gardens - Civic - Waterfront","The fare for this route is R5 per trip. Maximum charge for any travel combination is R10 per trip, excluding the Civic Centre - Airport route.", R.drawable.da_marker_yellow));
		weekdays = route.getRouteTimes().getWeekdays();
		weekdays.put("06:00 to 06:30", "Every 20 minutes");
		weekdays.put("06:30 to 09:00", "Every 10 minutes");
		weekdays.put("09:00 to 15:30", "Every 20 minutes");
		weekdays.put("15:30 to 18:00", "Every 10 minutes");
		weekdays.put("18:00 to 20:00", "Every 20 minutes");
		saturdays = route.getRouteTimes().getSaturdays();
		saturdays.put("07:00 to 20:00", "Every 20 minutes");
		sundays = route.getRouteTimes().getSundaysAndHolidays();
		sundays.put("08:00 to 20:00", "Every 20 minutes");
		//
		routes.add(route = new Route(3, "Civic Centre - Airport","The fare for this route is R50 per trip.", R.drawable.da_marker_destination));
		weekdays = route.getRouteTimes().getWeekdays();
		weekdays.put("04:20 to 21:00", "Every 20 minutes");
		saturdays = route.getRouteTimes().getSaturdays();
		saturdays.put("04:20 to 21:00", "Every 20 minutes");
		sundays = route.getRouteTimes().getSundaysAndHolidays();
		sundays.put("04:20 to 21:00", "Every 20 minutes");
		routes.add(route = new Route(4, "Airport - Civic Centre","The fare for this route is R50 per trip.", R.drawable.da_marker_destination));
		weekdays = route.getRouteTimes().getWeekdays();
		weekdays.put("05:10 to 21:50", "Every 20 minutes");
		saturdays = route.getRouteTimes().getSaturdays();
		saturdays.put("05:10 to 21:50", "Every 20 minutes");
		sundays = route.getRouteTimes().getSundaysAndHolidays();
		sundays.put("05:10 to 21:50", "Every 20 minutes");
	}
	
	private void initStations() {
		Station station = null;
		station = new Station(1, "Civic Centre Station", "Transfer Station on Hertzog Boulevard at the City of Cape Town offices and Cape Town Civic Centre", -33.920328,18.429163);
		this.routes.get(2).addStation(station);
		station = new Station(2, "Granger Bay Station", "Granger Bay Station on Granger Bay Boulevard", -33.904509,18.414738);
		this.routes.get(2).addStation(station);
		station = new Station(3, "Stadium Station", "Stadium Station on Western Boulevard ", -33.907982,18.412158);
		this.routes.get(2).addStation(station);
		station = new Station(4, "V&A Waterfront", "Victoria & Alfred Waterfront on Breakwater Boulevard", -33.903501,18.419073);
		this.routes.get(2).addStation(station);
		station = new Station(5, "Cape Town Convention Centre", "Cape Town Convention Centre on Coen Steytler Ave", -33.9163,18.426722);
		this.routes.get(2).addStation(station);
		station = new Station(6, "Adderley Street", "Adderley Street Bus Terminal", -33.920593,18.425172);
		this.routes.get(2).addStation(station);
		station = new Station(7, "Castle Street", "Castle of Good Hope", -33.925428,18.426197);
		this.routes.get(2).addStation(station);
		station = new Station(8, "Longmarket Street", "Corner of Longmarket Street and Long Street. Return route terminal on the corner of Longmarket Street and Loop Street", -33.922465,18.419137);
		this.routes.get(2).addStation(station);
		station = new Station(9, "Dorp Street ", "Corner of Dorp Street and Long Street. Return route terminal on the corner of Dorp Street and Loop Street", -33.924015,18.417377);
		this.routes.get(2).addStation(station);
		station = new Station(10, "Bloem Street", "Corner of Bloem Street and Long Street. Return route terminal on the corner of Bloem Street and Loop Street", -33.925577,18.415698);
		this.routes.get(2).addStation(station);
		station = new Station(11, "Buitensingel Street", "Corner of Buitensingel Street and Long Street. Return route terminal on the corner of Buitensingel Street and Loop Street", -33.927253,18.414129);
		this.routes.get(2).addStation(station);
		station = new Station(12, "Government Ave", "Corner of Government Ave and Orange Street. Opposite the Mount Nelson Hotel", -33.930921,18.413499);
		this.routes.get(2).addStation(station);
		station = new Station(13, "Gardens Center", "The Gardens Center on Mill Street. This is the turn around point for this route", -33.934539,18.417914);
		this.routes.get(2).addStation(station);
		//
		station = new Station(1, "Civic Centre Station", "Transfer Station", -33.920328,18.429163);
		this.routes.get(0).addStation(station);
		station = new Station(2, "Woodstock Station", "Woodstock Station", -33.922855,18.446351);
		this.routes.get(0).addStation(station);
		station = new Station(3, "Paarden Eiland Station", "Paarden Eiland Station", -33.918007,18.466942);
		this.routes.get(0).addStation(station);
		station = new Station(4, "Neptune Station", "Neptune Station", -33.914985,18.469769);
		this.routes.get(0).addStation(station);
		station = new Station(5, "Section Station", "Section Station", -33.911695,18.472545);
		this.routes.get(0).addStation(station);
		station = new Station(6, "Vrystaat Station", "Vrystaat Station", -33.907448,18.476399);
		this.routes.get(0).addStation(station);
		station = new Station(7, "Zoarvlei Station", "Zoarvlei Station", -33.900647,18.48101);
		this.routes.get(0).addStation(station);
		station = new Station(8, "Lagoon Beach Station", "Lagoon Beach Station", -33.891648,18.485374);
		this.routes.get(0).addStation(station);
		station = new Station(9, "Woodbridge Station", "Woodbridge Station", -33.883358,18.491334);
		this.routes.get(0).addStation(station);
		station = new Station(10, "Milnerton Station", "Milnerton Station", -33.871905,18.495451);
		this.routes.get(0).addStation(station);
		station = new Station(11, "Racecourse Station", "Racecourse Station", -33.86144,18.500357);
		this.routes.get(0).addStation(station);
		station = new Station(12, "Sunset Beach Station", "Sunset Beach Station", -33.854174,18.496508);
		this.routes.get(0).addStation(station);
		station = new Station(13, "Table View Station", "Trunk Feeder Station", -33.824778,18.489153);
		this.routes.get(0).addStation(station);
		//
		station = new Station(1, "Table View Station", "Trunk Feeder Station", -33.824778,18.489153);
		this.routes.get(1).addStation(station);
		//
		station = new Station(1, "Civic Centre Station", "Transfer Station", -33.920328,18.429163);
		this.routes.get(3).addStation(station);
		station = new Station(2, "Cape Town International", "Cape Town International Airport", -33.969363,18.596356);
		this.routes.get(3).addStation(station);
		//
		station = new Station(1, "Civic Centre Station", "Transfer Station", -33.920328,18.429163);
		this.routes.get(4).addStation(station);
		station = new Station(2, "Cape Town International", "Cape Town International Airport", -33.969363,18.596356);
		this.routes.get(4).addStation(station);
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public List<TweetItem> getTweets() throws Exception {
		List<TweetItem> retval = null;
		if (this.cache.containsKey(TweetItem.class)) {
			retval = (List<TweetItem>)this.cache.get(TweetItem.class);
		}
		return retval;
	}
	/**
	 * @param tweets
	 */
	public void setTweets(List<TweetItem> tweets) {
		this.cache.put(TweetItem.class, tweets);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<WeatherEntity> getWeatherForecast() throws Exception {
		List<WeatherEntity> retval = null;
		if (this.cache.containsKey(WeatherEntity.class)) {
			retval = (List<WeatherEntity>)this.cache.get(WeatherEntity.class);
		}
		return retval;
	}
	
	/**
	 * @param entities
	 */
	public void setWeatherForecast(List<WeatherEntity> entities) {
		this.cache.put(WeatherEntity.class, entities);
	}
	/**
	 * Returns the instance of this class
	 * @return
	 */
	public final static ContentRepository getInstance() {
		return ContentRepository.theInstance;
	}
	
	/**
	 * @return
	 */
	public List<Route> getRoutes() {
		return this.routes;
	}
	/**
	 * @return
	 */
	public Route getRoute(int routeId) {
		return this.routes.get(routeId);
	}
	
	public List<Station> getAllStations() {
		return null;
	}
	
	public List<Station> getStationByRoute(Route route) {
		return null;
	}
}
