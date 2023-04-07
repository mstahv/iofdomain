package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.OutputKeys;

@XmlRootElement
public class TeamCompetition implements Serializable {
	private static final int FEMALE_BONUS = 2;
	private static final int VETERANBONUS_THRESHOLD = 34;
	private static final int JUNIORBONUS_THRESHOLD = 19;
	private static final double JUNIORBONUS_PER_YEAR = 0.4;
	private static final double VETERANBONUS_PER_YEAR = 0.1;
	private static final double MAX_POINTS = 26;
	private static final double MIN_POINTS = 0;
	private String name;
	private String mainText;
	private String naviText;
	private int competitionYear;
	private List<Event> events = new ArrayList<Event>();
	private List<Team> teams = new ArrayList<Team>();
	private Map<String, Double> classReductions = new HashMap<String, Double>();
	private Set<String> easyCourses = new HashSet<String>();
	@SuppressWarnings("serial")
	private List<Person> persons = new ArrayList<Person>() {
		public boolean add(Person e) {
			if (!contains(e)) {
				return super.add(e);
			}
			throw new RuntimeException("Duplicate person:" + e.getId());
		}
	};

	@XmlElement(name = "team")
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * PEIMARIN RASTIEN JOUKKUEKILPAILUN PISTELASKENTA
	 * 
	 * <pre>
	 * Pisteisiin vaikuttavat:
	 * 
	 * kilpailijan ikä
	 * kilpailijan luokka (edell. vuosi)
	 * kilpailijan sukupuoli
	 * rata
	 * sijoitus radalla
	 * 
	 * H21-sarjan I- luokan suunnistaja, joka voittaa 6 km:n radan saa 26 pistettä.
	 * 
	 * Muut tekijät vaikuttavat seuraavasti:
	 * ikä (vuoden lopussa) 35-v tai yli 		+0.1 pistettä/vuosi (35: +0.1 p, 36: +0.2 p, …)
	 * ikä (vuoden lopussa) 18-v tai alle 		+0.4 pistettä/vuosi (18: +0.4 p, 17: +0.8 p, …)
	 * naiset 						+ 2 pistettä
	 * suunnistusluokka edellisenä vuonna: 
	 * 3. luokka tai luokaton 			+ 2 pistettä
	 * 2. luokka				+ 1 pistettä
	 * rata: 4 km A					- 2 pistettä
	 * rata: 4 km B					- 4 pistettä
	 * rata: 3 km					- 4 pistettä
	 * rata: 2 km					- 6 pistettä
	 * sijoitus 					- 0.2/sija (2. -0,2 p, 3. -0,4 p, …)
	 * 
	 * Poikkeukset:
	 * 2 km:n radalla vain 65-v ja yli sekä 12-v ja alle saavat pisteitä sijoituksen mukaan
	 * 2 km:n radalla 13 - 64-v saavat vakio 12 pistettä
	 * järjestäjänä toimiva saa vakio 25 pistettä (2 kertaa/kausi)
	 * maksimipistemäärä on kuitenkin 26 (ikä tai muista hyvityksistä huolimatta)
	 * 
	 * Yöradoilla saa pisteitä samoilla säännöillä.
	 * Pitkällä radalla kuten 6 km:llä
	 * 
	 * 
	 * Joukkueen viikoittaiseen tulokseen lasketaan kolme parasta pistemäärää.
	 * </pre>
	 * 
	 * 
	 */
	public TeamCompetitionResults computeResults(ResultListProvider provider) {
		TeamCompetitionResults teamCompetitionResults = new TeamCompetitionResults();
		Map<Person, Person> competingPersons = getCompetingPersons();

		PersonOrganizingPointsCounter popc = new PersonOrganizingPointsCounter();
		for (Event e : getEvents()) {
			if (e.isResultsVisible()) {

				ResultList resultList = provider.getResultList(e);

				final Map<Person, PersonWeekResult> personalResults = new HashMap<Person, PersonWeekResult>();

				List<ClassResult> classResults = resultList.getClassResults();
				for (ClassResult classResult : classResults) {
					if (classResult.getResults().isEmpty()
							|| classResult.getResults().iterator().next()
									.getCompetitorStatus() != CompetitorStatus.OK) {
						// Skip if no results or nobody without ok result
						continue;
					}
					classResult.analyzeSplitTimes();
					List<Result> results = classResult.getResults();
					for (Result result : results) {
						teamCompetitionResults.getPersonStatistics()
								.saveResult(e.getId(), result);
						if (result.getCompetitorStatus() != CompetitorStatus.OK) {
							continue;
						}
						if (competingPersons.containsKey(result.getPerson())) {
							// Switch to person instance that has proper details
							// for calculations
							result.setPerson(competingPersons.get(result
									.getPerson()));
							PersonWeekResult personWeekResult = new PersonWeekResult();
							personWeekResult.setCourse(classResult
									.getClassName());
							personWeekResult.setPos(result.getPosition());

							double points = 0;
							// compute points
							// * ikä (vuoden lopussa) 35-v tai yli +0.1
							// pistettä/vuosi (35: +0.1 p, 36: +0.2 p, …)
							int over34 = getCompetitionYear()
									- result.getPerson().getBirthYear()
									- VETERANBONUS_THRESHOLD;
							if (over34 > 0) {
								points += over34 * VETERANBONUS_PER_YEAR;
							}

							// * ikä (vuoden lopussa) 18-v tai alle +0.4
							// pistettä/vuosi (18: +0.4 p, 17: +0.8 p, …)
							int lessThan19 = JUNIORBONUS_THRESHOLD
									- (getCompetitionYear() - result
											.getPerson().getBirthYear());
							if (lessThan19 > 0) {
								points += lessThan19 * JUNIORBONUS_PER_YEAR;
							}

							// * naiset + 2 pistettä
							if (result.getPerson().isFemale()) {
								points += FEMALE_BONUS;
							}

							// * suunnistusluokka edellisenä vuonna:
							// * 3. luokka tai luokaton + 2 pistettä
							Level level = result.getPerson().getLevel();
							if (level == Level.III || level == Level.X) {
								points += 2;
							} else if (level == Level.II) {
								points += 1;
							}

							// * 2. luokka + 1 pistettä
							// * rata: 4 km A - 2 pistettä
							// * rata: 4 km B - 4 pistettä
							// * rata: 3 km - 4 pistettä
							// * rata: 2 km - 6 pistettä
							// These are configurable
							String className = classResult.getClassName();
							// the meaningful class name id is the one until
							// first space
							int indexOf = className.indexOf(" ");
							if (indexOf != -1) {
								className = className.substring(0, indexOf);
							}
							Double classReduction = getClassReductions().get(
									className);
							if (classReduction != null) {
								points += classReduction;
							} else {
								teamCompetitionResults
										.addWarning("No class reductions defined for "
												+ classResult.getClassName());
							}

							// * Poikkeukset:
							// * 2 km:n radalla vain 65-v ja yli sekä 12-v ja
							// alle
							// saavat pisteitä sijoituksen mukaan
							// * 2 km:n radalla 13 - 64-v saavat vakio 12
							// pistettä
							int age = getCompetitionYear()
									- result.getPerson().getBirthYear();
							boolean isYoungOrOld = age < 13 || age > 64;
							if (!isYoungOrOld
									&& getEasyCourses().contains(className)) {
								// no position bonus, but a static value
								points = 12;
							} else {
								// * sijoitus - 0.2/sija (2. -0,2 p, 3. -0,4 p,
								// …)
								double positionReduction = (result
										.getPosition() - 1) * 0.2;
								points += 26 - positionReduction;
							}

							// * järjestäjänä toimiva saa vakiot väh. 25
							// pistettä (
							// kertaa/kausi)
							// TODO
							// personWeekResult.setCourse(classResult.getClassName());
							// personWeekResult.setPos(result.getPosition());

							// * maksimipistemäärä on kuitenkin 26 (ikä tai
							// muista
							// hyvityksistä huolimatta)
							if (points > MAX_POINTS) {
								points = MAX_POINTS;
							} else if (points < MIN_POINTS) {
								points = MIN_POINTS;
							}

							personWeekResult.setPoints(points);
							PersonWeekResult oldResult = personalResults
									.get(result.getPerson());
							if (oldResult == null
									|| oldResult.getPoints() < personWeekResult
											.getPoints()) {
								personalResults.put(result.getPerson(),
										personWeekResult);
							}
						}
					}
				}

				Collection<Person> organizers = e.getOrganizers();
				for (Person person : organizers) {
					if (competingPersons.containsKey(person)) {
						PersonWeekResult personWeekResult = personalResults
								.get(person);
						if ((personWeekResult == null
								|| personWeekResult.getPoints() < 25)
								&& popc.getCount(person) < 4) {
							personWeekResult = new PersonWeekResult();
							personWeekResult.setCourse("Järj.");
							personWeekResult.setPoints(25);
							personWeekResult.setPos(1);
							personalResults.put(person, personWeekResult);
							popc.increment(person);
						}
					}
				}

				for (Team t : getTeams()) {
					// add three best scores to teams weekly pot
					List<Person> persons = new ArrayList<Person>(t.getPersons());
					Collections.sort(persons, new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							PersonWeekResult double1 = personalResults.get(o1);
							PersonWeekResult double2 = personalResults.get(o2);
							if (double1 == double2) {
								return 0;
							}
							if (double1 == null) {
								return 1;
							}
							if (double2 == null) {
								return -1;
							}
							return (double1.getPoints() - double2.getPoints()) < 0 ? 1
									: -1;
						}
					});
					TeamResult teamResult = teamCompetitionResults
							.getTeamResult(t);
					double weekSum = 0;
					int i = 0;
					for (Person person : persons) {
						PersonWeekResult weeklypoints = personalResults
								.get(person);
						if (weeklypoints != null) {
							if (i < 3) {
								// only three best scores for team per week are
								// counted
								weekSum += weeklypoints.getPoints();
							}
							teamResult.addPersonalPoints(e, person,
									weeklypoints);
						}
						i++;
					}
					teamResult.addPoints(e, weekSum);

				}
			}
		}
		teamCompetitionResults.getPersonStatistics().getPersonStatistics();
		return teamCompetitionResults;
	}

	private Map<Person, Person> getCompetingPersons() {
		Map<Person, Person> hashSet = new HashMap<Person, Person>();
		for (Team t : getTeams()) {
			for (Person p : t.getPersons()) {
				hashSet.put(p, p);
			}
		}
		return hashSet;
	}

	public Map<String, Double> getClassReductions() {
		return classReductions;
	}

	public void setClassReductions(Map<String, Double> classReductions) {
		this.classReductions = classReductions;
	}

	public int getCompetitionYear() {
		return competitionYear;
	}

	public void setCompetitionYear(int competitionYear) {
		this.competitionYear = competitionYear;
	}

	public Set<String> getEasyCourses() {
		return easyCourses;
	}

	public void setEasyCourses(Set<String> easyCourses) {
		this.easyCourses = easyCourses;
	}

	@XmlElement(name = "event")
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@XmlElement(name = "person")
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getMainText() {
		return mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public String getNaviText() {
		return naviText;
	}

	public void setNaviText(String naviText) {
		this.naviText = naviText;
	}

}
