package org.peimari.domain;

import java.util.HashMap;

public class PersonOrganizingPointsCounter {
	
	HashMap<String, Integer> counts = new HashMap<String, Integer>();
	
	public int getCount(Person p) {
		Integer integer = counts.get(p.getId());
		if(integer != null) {
			return integer;
		}
		return 0;
	}
	
	public void increment(Person p) {
		counts.put(p.getId(), getCount(p) + 1);
	}
	

}
