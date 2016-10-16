package com.ryt.web.entity.sc;

import java.io.Serializable;
import java.util.List;

public class ScClassCourse implements Serializable {
	private String className;
	private List<ScClassSchedule> scClassSchedules;

	public ScClassCourse() {
		super();
	}

	public ScClassCourse(String className, List<ScClassSchedule> scClassSchedules) {
		super();
		this.className = className;
		this.scClassSchedules = scClassSchedules;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ScClassSchedule> getScClassSchedules() {
		return scClassSchedules;
	}

	public void setScClassSchedules(List<ScClassSchedule> scClassSchedules) {
		this.scClassSchedules = scClassSchedules;
	}

}
