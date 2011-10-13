package com.touchswipeengage.myciti.domain;

import java.util.LinkedList;
import java.util.List;

public class Route {
	private long id;
	private String name;
	private String description;
	private RouteTimes routeTimes;
	private LinkedList<Station> stations;
	private int marker_resource_id;
	/**
	 * @param id
	 * @param name
	 * @param description
	 */
	public Route(long id, String name, String description, int marker_resource_id) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.routeTimes = new RouteTimes();
		this.stations = new LinkedList<Station>();
		this.marker_resource_id = marker_resource_id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
	/**
	 * @return the routeTimes
	 */
	public RouteTimes getRouteTimes() {
		return routeTimes;
	}
	/**
	 * @param routeTimes the routeTimes to set
	 */
	public void setRouteTimes(RouteTimes routeTimes) {
		this.routeTimes = routeTimes;
	}
	
	/**
	 * @param station
	 */
	public void addStation(Station station) {
		this.stations.add(station);
	}
	
	/**
	 * @return
	 */
	public List<Station> getStations() {
		return this.stations;
	}
	
	/**
	 * @return
	 */
	public int getMarker() {
		return this.marker_resource_id;
	}
}
