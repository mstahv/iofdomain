package org.peimari.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WeekResult implements Serializable {
	
	private Map<String, Double> personToResult = new HashMap<String,Double>();
	
	public WeekResult() {
		
	}
	
	public WeekResult(Map<Person, Double> personalResults) {
		for (Entry<Person, Double> e : personalResults.entrySet()) {
			setResult(e.getKey(), e.getValue());
		}
	}

	public Double getResult(Person p) {
		return personToResult.get(p.getId());
	}
	
	public void setResult(Person p, Double d) {
		personToResult.put(p.getId(), d);
	}

}
