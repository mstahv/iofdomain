package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetitionGroup implements Serializable {

	private String groupName;

	private List<TeamCompetition> competitions = new ArrayList<TeamCompetition>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@XmlElement(name = "competition")
	public List<TeamCompetition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<TeamCompetition> competitions) {
		this.competitions = competitions;
	}

}
