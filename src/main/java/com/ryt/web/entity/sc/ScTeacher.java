package com.ryt.web.entity.sc;

import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;

import com.ryt.web.entity.user.User;

public class ScTeacher {
	private Integer teacherId;
	private Integer schoolId;
	private Integer agentId;
	private Integer levelId;
	private String teacherTypeId;
	private Integer statusId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private User user;
	private Integer classId;
	private Integer roleProperty;
	private List<Integer> classes;
	
	
	public void setTeacherId(Integer techerId){
		this.teacherId = techerId;
	}

	public Integer getTeacherId(){
		return this.teacherId;
	}

	public void setSchoolId(Integer schoolId){
		this.schoolId = schoolId;
	}

	public Integer getSchoolId(){
		return this.schoolId;
	}

	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	public Integer getAgentId(){
		return this.agentId;
	}

	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	public Integer getLevelId(){
		return this.levelId;
	}

	public void setTeacherTypeId(String teacherTypeId){
		this.teacherTypeId = teacherTypeId;
	}

	public String getTeacherTypeId(){
		return this.teacherTypeId;
	}

	public void setStatusId(Integer statusId){
		this.statusId = statusId;
	}

	public Integer getStatusId(){
		return this.statusId;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}

	public Integer getSortId(){
		return this.sortId;
	}

	public void setIsDeleted(Byte isDeleted){
		this.isDeleted = isDeleted;
	}

	public Byte getIsDeleted(){
		return this.isDeleted;
	}

	public void setOperaterId(Integer operaterId){
		this.operaterId = operaterId;
	}

	public Integer getOperaterId(){
		return this.operaterId;
	}

	public void setEditedTime(String editedTime){
		this.editedTime = editedTime;
	}

	public String getEditedTime(){
		return this.editedTime;
	}

	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}

	public String getCreatedTime(){
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
	 * @return the classId
	 */
	public Integer getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	/**
	 * @return the roleProperty
	 */
	public Integer getRoleProperty() {
		return roleProperty;
	}

	/**
	 * @param roleProperty the roleProperty to set
	 */
	public void setRoleProperty(Integer roleProperty) {
		this.roleProperty = roleProperty;
	}

	/**
	 * @return the classes
	 */
	public List<Integer> getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<Integer> classes) {
		this.classes = classes;
	}






}