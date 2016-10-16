package com.ryt.web.entity.sc;

import java.util.Date;

import com.ryt.web.entity.user.User;

public class ScTeacherCourse {
	private Integer id;
	private Integer teacherId;
	private Integer courseId;
	private Integer isDeleted;
	private Integer operateId;
	private User user;
	private Date editedTime;
	private Date createdTime;
	private ScCourse scCourse;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setTeacherId(Integer teacherId){
		this.teacherId = teacherId;
	}

	public Integer getTeacherId(){
		return this.teacherId;
	}

	public void setCourseId(Integer courseId){
		this.courseId = courseId;
	}

	public Integer getCourseId(){
		return this.courseId;
	}

	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	public Integer getIsDeleted(){
		return this.isDeleted;
	}

	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}

	public Integer getOperateId(){
		return this.operateId;
	}

	public void setEditedTime(Date editedTime){
		this.editedTime = editedTime;
	}

	public Date getEditedTime(){
		return this.editedTime;
	}

	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}

	public Date getCreatedTime(){
		return this.createdTime;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the scCourse
	 */
	public ScCourse getScCourse() {
		return scCourse;
	}

	/**
	 * @param scCourse the scCourse to set
	 */
	public void setScCourse(ScCourse scCourse) {
		this.scCourse = scCourse;
	}

}