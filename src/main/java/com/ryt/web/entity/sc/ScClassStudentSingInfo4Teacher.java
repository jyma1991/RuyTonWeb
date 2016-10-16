package com.ryt.web.entity.sc;

import java.util.ArrayList;
import java.util.List;

public class ScClassStudentSingInfo4Teacher {
	private String className;
	private Integer signInCount;
	private Integer signOutCount;
	private Integer studentCount;
	private List<ScStudent> listSignIn = new ArrayList<ScStudent>();
	
	
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	public Integer getSignOutCount() {
		return signOutCount;
	}
	public void setSignOutCount(Integer signOutCount) {
		this.signOutCount = signOutCount;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getSignInCount() {
		return signInCount;
	}
	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}
	public List<ScStudent> getListSignIn() {
		return listSignIn;
	}
	public void setListSignIn(List<ScStudent> listSignIn) {
		this.listSignIn = listSignIn;
	}
}
