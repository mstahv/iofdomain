package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

public class Team  implements Serializable {
	private String teamname;
	
	private List<Person> persons = new ArrayList<Person>();
	
	public String getName() {
		return teamname;
	}
	
	public void setName(String name) {
		this.teamname = name;
	}
	
	@XmlElement(name="person")
	@XmlIDREF
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
