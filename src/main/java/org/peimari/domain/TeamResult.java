package org.peimari.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamResult implements Serializable {
	
	private Team team;
	
	private double total;
	
	private Map<String,Double> eventPoints = new LinkedHashMap<String, Double>();
	
	private Map<String,PersonResult> nameToPersonResult = new HashMap<String, PersonResult>();

	
	public double getTotal() {
		return total;
	}
	
	void addPoints(Event e, double points) {
		eventPoints.put(e.getId(), points);
		total += points;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void addPersonalPoints(Event e, Person person,
			PersonWeekResult weeklypoints) {
		PersonResult pr = getPersonResult(person);
		pr.addPoints(e, weeklypoints);
	}

	private PersonResult getPersonResult(Person person) {
		String key = person.getFirstName() + " " + person.getLastName();
		PersonResult personResult = nameToPersonResult.get(key);
		if(personResult == null) {
			personResult = new PersonResult();
			nameToPersonResult.put(key, personResult);
		}
		return personResult;
	}
	

}
