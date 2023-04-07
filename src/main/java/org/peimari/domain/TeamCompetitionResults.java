package org.peimari.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamCompetitionResults  implements Serializable {
	
	private Map<Team,TeamResult> teamToResult = new HashMap<Team, TeamResult>();
	@XmlTransient
	private Set<String> warnings = new HashSet<String>();
	
	PersonStatistics personStatistics = new PersonStatistics();
	
	List<TeamResult> getTeamResults() {
		return null;
	}
	
	public TeamResult getTeamResult(Team team) {
		TeamResult teamResult = teamToResult.get(team);
		if(teamResult == null) {
			teamResult = new TeamResult();
			teamResult.setTeam(team);
			teamToResult.put(team, teamResult);
		}
		return teamResult;
	}

	public void addWarning(String string) {
		warnings.add(string);
	}
	
	public Set<String> getWarnings() {
		return warnings;
	}

	public PersonStatistics getPersonStatistics() {
		return personStatistics;
	}

}
