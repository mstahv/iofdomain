package org.peimari.iofjavahelper;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.junit.Test;
import org.peimari.domain.Event;
import org.peimari.domain.Level;
import org.peimari.domain.Person;
import org.peimari.domain.ResultList;
import org.peimari.domain.ResultListProvider;
import org.peimari.domain.Team;
import org.peimari.domain.TeamCompetition;
import org.peimari.helper.ResultListService;

public class TeamCompetitionTest {

	public static void main(String[] args) throws JAXBException {
		TeamCompetitionTest t = new TeamCompetitionTest();
		t.testCompetitionMarshaling();
		t.testUnmarshaling();
		t.testResultCalculation();
	}

	private void testResultCalculation() {
		TeamCompetition competition = readFromFile("src/test/resources/teams.xml");
		
		final ResultList resultList = ResultListService.unmarshal(new File(
				ResultListServiceTest.SRC_TEST_RESOURCES_TASTI2_XML));
		competition.computeResults(new ResultListProvider() {
			@Override
			public ResultList getResultList(Event e) {
				return resultList;
			}
		});
	}

	@Test
	public void testUnmarshaling() {
		TeamCompetition competition = readFromFile("src/test/resources/teams.xml");
		Team team = (Team) competition.getTeams().get(0);
		System.out.println(team.getName());
		System.out.println(competition);
	}

	private TeamCompetition readFromFile(String string) {
		try {
			File file = new File("src/test/resources/teams.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TeamCompetition.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TeamCompetition tc = (TeamCompetition) jaxbUnmarshaller
					.unmarshal(file);
			return tc;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testCompetitionMarshaling() throws JAXBException {

		TeamCompetition teamCompetition = new TeamCompetition();
		teamCompetition.getClassReductions().put("6", 0d);
		teamCompetition.getClassReductions().put("4A", -2d);
		teamCompetition.getClassReductions().put("4B", -4d);
		teamCompetition.getClassReductions().put("3", -4d);
		teamCompetition.getClassReductions().put("2", -6d);
		teamCompetition.getClassReductions().put("RR", -6d);
		teamCompetition.getEasyCourses().add("2");
		teamCompetition.getEasyCourses().add("RR");
		teamCompetition.setName("Joukkupeimaritesti 2012");
		teamCompetition.setCompetitionYear(2012);

		Team team = new Team();
		team.setName("Turot");

		Person person = new Person();
		person.setFirstName("Pasi");
		person.setLastName("Lehtimäki");
		person.setBirthYear(1966);
		person.setLevel(Level.I);
		team.getPersons().add(person);

		teamCompetition.getTeams().add(team);

		JAXBContext jaxbContext = JAXBContext
				.newInstance(TeamCompetition.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed xml
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(teamCompetition, System.out);

	}

}
