package org.peimari.rgdomain;

import java.io.Serializable;

public class Competitor implements Serializable {
	
	private int id;
	private String name;
	private int time;
	private int[] splits;
	private Point[] routePoints;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public void setSplitTimes(int[] splits) {
		this.splits = splits;
	}
	
	public int[] getSplitTimes() {
		return splits;
	}

	public void setRoutePoints(Point[] routePoints) {
		this.routePoints = routePoints;
	}
	
	public Point[] getRoutePoints() {
		return routePoints;
	}
	
	@Override
	public String toString() {
		return name + (routePoints != null ? "*" : "");
	}
}
