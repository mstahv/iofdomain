package org.peimari.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultList implements Serializable {

	private List<ClassResult> classResults = new ArrayList<ClassResult>();

	public List<ClassResult> getClassResults() {
		return classResults;
	}

	public void setClassResults(List<ClassResult> classResults) {
		this.classResults = classResults;
	}

	public void analyzeSplitTimes() {
		for (ClassResult cr : getClassResults()) {
			cr.analyzeSplitTimes();
		}

	}

}
