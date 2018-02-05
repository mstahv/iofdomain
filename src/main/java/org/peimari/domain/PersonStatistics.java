package org.peimari.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonStatistics {

	@XmlTransient
	private Map<String, PersonStatisticsEntry> personIdToEntry = new HashMap<String, PersonStatisticsEntry>();
	
	PersonStatisticsEntry[] persons;

	public void saveResult(String eventId, Result result) {
		Person p = result.getPerson();
		PersonStatisticsEntry personEntry = personIdToEntry.get(p.getId());
		if (personEntry == null) {
			personEntry = new PersonStatisticsEntry();
			personEntry.setPerson(p);
			personIdToEntry.put(p.getId(), personEntry);
		}
		personEntry.saveResult(eventId);
	}
	
	public int totalRunners() {
		return personIdToEntry.size();
	}

	/**
	 * Sorts list (by result count) of persons for persistence and analysis.
	 * 
	 * @return
	 */
	public List<PersonStatisticsEntry> getPersonStatistics() {
		List<PersonStatisticsEntry> list = new ArrayList<PersonStatisticsEntry>(
				personIdToEntry.values());
		Collections.sort(list, new Comparator<PersonStatisticsEntry>() {
			@Override
			public int compare(PersonStatisticsEntry o1,
					PersonStatisticsEntry o2) {
				return o1.getEventCount() - o2.getEventCount();
			}
		});
		persons = list.toArray(new PersonStatisticsEntry[list.size()]);
		return list;
	}
	

}
