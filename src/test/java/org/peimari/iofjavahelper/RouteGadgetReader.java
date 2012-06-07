package org.peimari.iofjavahelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.peimari.rgdomain.Competition;
import org.peimari.rgdomain.CompetitionClass;
import org.peimari.rgdomain.CompetitionMap;
import org.peimari.rgdomain.Competitor;
import org.peimari.rgdomain.Course;
import org.peimari.rgdomain.Graphic;
import org.peimari.rgdomain.Point;
import org.peimari.rgdomain.Graphic.Type;

public class RouteGadgetReader {

	public static Competition readCompetition(File rootDirectory, String id)
			throws Exception {

		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"kisat.txt"), "iso8859-1");
		for (String l : lines) {
			String[] split = l.split("\\|");
			if (split[0].equals(id)) {
				Competition competition = new Competition();
				competition.setId(Integer.parseInt(split[0]));
				competition.setName(split[3]);
				readCompetition(rootDirectory, competition, split[1]);
				return competition;
			}
		}
		throw new Exception("Competition not found!");
	}

	private static void readCompetition(File rootDirectory,
			Competition competition, String mapId) throws IOException {
		readMap(rootDirectory, competition, mapId);
		readCourses(rootDirectory, competition);
		readCoursePoints(rootDirectory, competition);
		readClasses(rootDirectory, competition);
		readCompetitors(rootDirectory, competition);
		readRoutes(rootDirectory, competition);
	}

	private static void readRoutes(File rootDirectory, Competition competition) throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"merkinnat_" + competition.getId() + ".txt"), "iso8859-1");
		for (String l : lines) {
			String[] flds = l.split("\\|");
			CompetitionClass competitionClass = competition.getCompetitionClass(Integer.parseInt(flds[0]));
			Competitor competitor = competitionClass.getCompetitor(Integer.parseInt(flds[1]));
			String[] pStr = flds[4].split("N");
			Point[] routePoints = new Point[pStr.length-1];
			for (int i = 1; i < pStr.length; i++) {
				String[] lonlatstr = pStr[i].split(";");
				Point point = new Point();
				point.setLon(Integer.parseInt(lonlatstr[0]));
				point.setLat(Integer.parseInt(lonlatstr[1]));
				routePoints[i-1] = point;
			}
			competitor.setRoutePoints(routePoints);
		}
	}

	private static void readCompetitors(File rootDirectory,
			Competition competition) throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"kilpailijat_" + competition.getId() + ".txt"), "iso8859-1");
		for (String l : lines) {
			String[] flds = l.split("\\|");
			Competitor c = new Competitor();
			c.setId(Integer.parseInt(flds[0]));
			c.setName(flds[3]);
			// 4 ok disq?? start time?
			int time = Integer.parseInt(flds[5]);
			c.setTime(time);
			
			String[] split = flds[8].split(";");
			int[] splits = new int[split.length];
			for (int i = 1; i < splits.length; i++) {
				splits[i] = Integer.parseInt(split[i-1]);
			}
			c.setSplitTimes(splits);
			int classId = Integer.parseInt(flds[1]);
			competition.getCompetitionClass(classId).addCompetitor(c);
			
			
		}
		
	}

	private static void readCoursePoints(File rootDirectory,
			Competition competition) throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"ratapisteet_" + competition.getId() + ".txt"), "iso8859-1");
		for (String l : lines) {
			String[] fields = l.split("\\|");
			Course course = competition.getCourse(Integer.parseInt(fields[0]));
			String[] courcepoints = fields[1].split("N");
			Point[] points = new Point[courcepoints.length];
			for (int i = 0; i < points.length; i++) {
				Point p = points[i] = new Point();
				String[] split = courcepoints[i].split(";");
				p.setLon(Integer.parseInt(split[0]));
				p.setLon(Integer.parseInt(split[1]));
			}
			course.setControlPoints(points);
		}
	}

	private static void readClasses(File rootDirectory, Competition competition) throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"sarjat_" + competition.getId() + ".txt"), "iso8859-1");
		for (String l : lines) {
			String[] fields = l.split("\\|");
			CompetitionClass c = new CompetitionClass();
			c.setId(Integer.parseInt(fields[0]));
			c.setName(fields[1]);
			competition.addCompetitionClass(c);
		}
		
	}

	private static void readCourses(File rootDirectory, Competition competition)
			throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"radat_" + competition.getId() + ".txt"), "iso8859-1");
		for (String l : lines) {
			String[] fields = l.split("\\|");
			Course course = new Course();
			course.setId(Integer.parseInt(fields[0]));
			course.setName(fields[2]);
			competition.addCourse(course);
			String[] pointStrs = fields[3].split("N");
			Graphic[] points = new Graphic[pointStrs.length];
			for (int i = 0; i < points.length; i++) {
				Graphic p = points[i] = new Graphic();
				String[] pointFields = pointStrs[i].split(";");
				Type type = Graphic.Type.values()[Integer.parseInt(pointFields[0])];
				p.setType(type);
				p.setLon(Integer.parseInt(pointFields[1]));
				p.setLat(Integer.parseInt(pointFields[2]));
				if(type == Type.LINE) {
					p.setLon2(Integer.parseInt(pointFields[3]));
					p.setLat2(Integer.parseInt(pointFields[4]));
				}
			}
			course.setGraphics(points);
		}
	}

	private static void readMap(File rootDirectory, Competition competition,
			String mapId) throws IOException {
		List<String> lines = FileUtils.readLines(new File(rootDirectory,
				"kartat.txt"), "iso8859-1");
		for (String l : lines) {
			String[] split = l.split("\\|");
			if (split[0].equals(mapId)) {
				CompetitionMap map = new CompetitionMap();
				map.setId(Integer.parseInt(split[0]));
				map.setName(split[1]);
				competition.setMap(map);
				return;
			}
		}
	}

}
