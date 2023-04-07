package org.peimari.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements Serializable {

	private String id;
	private Date date = new Date();
	private String map = "";
	private Location guidance = new Location();
	private Location location = new Location();
	private String comments = "";
	private String storyTitle = "";
	private String story = "";
	private String shortStory = "";
	private boolean resultsVisible;

	@XmlElement(name = "organizer")
	@XmlIDREF
	private Collection<Person> organizers = new HashSet<Person>();

	public Collection<Person> getOrganizers() {
		return organizers;
	}

	public void setOrganizers(Collection<Person> organizers) {
		this.organizers = organizers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public Location getGuidance() {
		return guidance;
	}

	public void setGuidance(Location guidance) {
		this.guidance = guidance;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public boolean isResultsVisible() {
		return resultsVisible;
	}

	public void setResultsVisible(boolean resultsVisible) {
		this.resultsVisible = resultsVisible;
	}

	public String getShortStory() {
		return shortStory;
	}

	public void setShortStory(String shortStory) {
		this.shortStory = shortStory;
	}

}
