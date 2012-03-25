package org.peimari.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamCompetitionResults  implements Serializable {
	
	private Map<Team,TeamResult> teamToResult = new HashMap<Team, TeamResult>();
	@XmlTransient
	private Set<String> warnings = new HashSet<String>();
	
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

}
