package org.peimari.rgdomain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CompetitionClass implements Serializable {

	private int id;
	private String name;
	private Map<Integer, Competitor> competitors = new LinkedHashMap<Integer, Competitor>();
	private Course course;

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

	public void addCompetitor(Competitor c) {
		competitors.put(c.getId(), c);
	}

	public Collection<Competitor> getCompetitors() {
		return competitors.values();
	}

	public Competitor getCompetitor(int parseInt) {
		return competitors.get(parseInt);
	}
	
	@Override
	public String toString() {
		return name;
	}

	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
}
