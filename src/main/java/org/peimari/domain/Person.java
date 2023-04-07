package org.peimari.domain;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

	@XmlID
	private String id = "NEW";
	private String firstName;
	private String lastName;
	private int birthYear;
	private Level level = Level.X;
	private boolean female = false;

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		resetId();
	}

	private void resetId() {
		id = firstName + " " + lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		resetId();
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Person) {
			Person p = (Person) obj;
			if (firstName != null && firstName.equals(p.firstName)
					&& lastName != null && lastName.equals(p.lastName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (firstName != null && lastName != null) {
			return firstName.hashCode() + lastName.hashCode();
		}
		return 0;
	}

}
