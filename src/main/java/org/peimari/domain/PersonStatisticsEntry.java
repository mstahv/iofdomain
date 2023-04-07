package org.peimari.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonStatisticsEntry {
	private String person;
	@XmlTransient
	private Set<String> events = new HashSet<String>();
	private int eventCount;

	public void setPerson(Person person) {
		this.person = person.getId();
	}

	public void saveResult(String eventId) {
		events.add(eventId);
		eventCount = events.size();
	}
	
	public int getEventCount() {
		return eventCount;
	}
	
}