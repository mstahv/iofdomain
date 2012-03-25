package org.peimari.domain;

public enum CompetitorStatus {
	OK,
	DIDNOTFINISH,
	DISQUALIFIED,
	NOTCOMPETING,
	DNS,
	INACTIVE;
	
	@Override
	public String toString() {
		if(this == DIDNOTFINISH)
			return "DNF";
		if(this == DISQUALIFIED)
			return "DSQ";
		if(this == DISQUALIFIED)
			return "DSQ";
		else
		return super.toString();
	}

	
}
