package org.peimari.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.HashSet;
import java.util.Set;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonStatisticsEntry {
    private String person;
    @XmlTransient
    private Set<String> events = new HashSet<String>();
    private int eventCount;
    private int kmCount;
    @XmlTransient
    private Person person_;

    public void setPerson(Person person) {
        this.person = person.getId();
        this.person_ = person;
    }

    public void saveResult(String eventId, String className) {
        events.add(eventId);
        eventCount = events.size();
        if (className != null) {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < className.length(); i++) {
                char ch = className.charAt(i);
                if (Character.isDigit(ch)) {
                    strBuilder.append(ch);
                } else if (!strBuilder.isEmpty()) {
                    break;
                }
            }
            if (!strBuilder.isEmpty()) {
                int kilometers = Integer.parseInt(strBuilder.toString());
                kmCount = kmCount + kilometers;
            }
        }
    }

    public int getEventCount() {
        return eventCount;
    }

    public int getKmCount() {
        return kmCount;
    }
}
