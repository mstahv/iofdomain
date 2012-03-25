package org.peimari.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PersonResult implements Serializable {

	// for xml
	@SuppressWarnings("unused")
	private double totalPoints = 0;

	// for xml
	@SuppressWarnings("unused")
	private int visits = 0;

	private Map<String, PersonWeekResult> weekToResult = new LinkedHashMap<String, PersonWeekResult>();

	public void addPoints(Event e, PersonWeekResult r) {
		weekToResult.put(e.getId(), r);
		totalPoints += r.getPoints();
		visits = weekToResult.size();
	}

}
