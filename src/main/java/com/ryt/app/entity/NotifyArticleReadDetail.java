package com.ryt.app.entity;

import java.util.List;

import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.user.User;

public class NotifyArticleReadDetail {
	// 未读人员
	private List<User> unreadUserList;
	// 已读
	private List<User> readUserList;
	// 班级
	private ScClass scClass;

	Integer allUserCount;
	Integer readUserCount;

	public List<User> getUnreadUserList() {
		return unreadUserList;
	}

	public void setUnreadUserList(List<User> unreadUserList) {
		this.unreadUserList = unreadUserList;
	}

	public List<User> getReadUserList() {
		return readUserList;
	}

	public void setReadUserList(List<User> readUserList) {
		this.readUserList = readUserList;
	}

	public ScClass getScClass() {
		return scClass;
	}

	public void setScClass(ScClass scClass) {
		this.scClass = scClass;
	}

	public Integer getAllUserCount() {
		return allUserCount;
	}

	public void setAllUserCount(Integer allUserCount) {
		this.allUserCount = allUserCount;
	}

	public Integer getReadUserCount() {
		return readUserCount;
	}

	public void setReadUserCount(Integer readUserCount) {
		this.readUserCount = readUserCount;
	}

}
