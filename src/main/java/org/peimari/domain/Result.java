package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

	private Person person;

	private int position;

	private long time;

	private CompetitorStatus competitorStatus;

	private List<SplitTime> splitTimes = new ArrayList<SplitTime>();

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public CompetitorStatus getCompetitorStatus() {
		return competitorStatus;
	}

	public void setCompetitorStatus(CompetitorStatus competitorStatus) {
		this.competitorStatus = competitorStatus;
	}

	public List<SplitTime> getSplitTimes() {
		return splitTimes;
	}

	public void setSplitTimes(List<SplitTime> splitTimes) {
		this.splitTimes = splitTimes;
	}

	@Override
	public String toString() {
		return person.toString();
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
