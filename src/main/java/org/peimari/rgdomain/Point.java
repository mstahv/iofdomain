package org.peimari.rgdomain;

import java.io.Serializable;

public class Point implements Serializable {
	
	private double lon;
	private double lat;
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point p2 = (Point) obj;
			if(p2.lat == lat && p2.lon == lon) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}