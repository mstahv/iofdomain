package org.peimari.rgdomain;

public class Graphic extends Point {

	public enum Type {
		UNDEFINED,
		CONTROLCIRCLE,
		SMALLCIRCLE,
		CONTROLNUMBER,
		LINE
	}

	private Double lon2;
	private Double lat2;
	private Integer ordinal;
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getLon2() {
		return lon2;
	}

	public void setLon2(double lon2) {
		this.lon2 = lon2;
	}

	public double getLat2() {
		return lat2;
	}

	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}
