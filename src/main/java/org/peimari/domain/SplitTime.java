package org.peimari.domain;

import java.io.Serializable;

public class SplitTime implements Serializable {

	private int controlCode;
	private long time;
	private Long deltaTime;
	private Integer position;
	private Integer deltaPosition;

	public int getControlCode() {
		return controlCode;
	}

	public void setControlCode(int controlCode) {
		this.controlCode = controlCode;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(Long deltaTime) {
		this.deltaTime = deltaTime;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getDeltaPosition() {
		return deltaPosition;
	}

	public void setDeltaPosition(Integer deltaPosition) {
		this.deltaPosition = deltaPosition;
	}
}
