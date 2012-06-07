package org.peimari.rgdomain;

import java.io.Serializable;

public class Course implements Serializable {

	private int id;
	private String name;
	private Graphic[] graphics;
	private Point[] controlPoints;

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

	public Graphic[] getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphic[] points) {
		this.graphics = points;
	}

	public Point[] getControlPoints() {
		return controlPoints;
	}

	public void setControlPoints(Point[] controlPoints) {
		this.controlPoints = controlPoints;
	}
	
}
