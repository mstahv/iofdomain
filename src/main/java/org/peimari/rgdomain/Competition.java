package org.peimari.rgdomain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Competition implements Serializable {
	
	private int id;
	private String mapId;
	private Date date;
	private String name;
	private CompetitionMap map;
	private Map<Integer,Course> idToCource = new HashMap<Integer, Course>();
	private Map<Integer,CompetitionClass> idToClass = new HashMap<Integer, CompetitionClass>();
	
	public Course getCourse(int i) {
		return idToCource.get(i);
	}
	
	public void addCourse(Course c) {
		idToCource.put(c.getId(), c);
	}

	public CompetitionClass getCompetitionClass(int i) {
		return idToClass.get(i);
	}
	
	public void addCompetitionClass(CompetitionClass c) {
		idToClass.put(c.getId(), c);
	}
	
	public Collection<CompetitionClass> getCompetitionClasses() {
		return idToClass.values();
	}

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

	public CompetitionMap getMap() {
		return map;
	}

	public void setMap(CompetitionMap map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return name + "("+ date + ")";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
}
