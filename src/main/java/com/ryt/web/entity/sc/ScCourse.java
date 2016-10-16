package com.ryt.web.entity.sc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ryt.web.entity.user.User;

public class ScCourse implements Serializable {
	private String courseName;
	private Integer schoolId;
	private Integer length;
	private Integer price;
	private Integer teacherId;
	private String remark;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private String img;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private String picId;
	private List<Integer> teachers;
	private User user;
	
	public ScCourse(){
		
	}
	
	public ScCourse(ScCourse course){
		this.courseName = course.getCourseName();
		this.schoolId = course.getSchoolId();
		this.length = course.getLength();
		this.price = course.getPrice();
		this.teacherId = course.getTeacherId();
		this.remark = course.remark;
		this.id = course.getId();
		this.uuid = course.getUuid();
		this.userId = course.getUserId();
		this.userName = course.getUserName();
		this.sortId = course.getSortId();
		this.isDeleted = course.getIsDeleted();
		this.operaterId = course.getOperaterId();
		this.editedTime = course.getEditedTime();
		this.createdTime = course.getCreatedTime();
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	public String getCourseName(){
		return this.courseName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public void setLength(Integer length){
		this.length = length;
	}

	public Integer getLength(){
		return this.length;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public Integer getPrice(){
		return this.price;
	}

	public void setTeacherId(Integer teacherId){
		this.teacherId = teacherId;
	}

	public Integer getTeacherId(){
		return this.teacherId;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
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
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the picId
	 */
	public String getPicId() {
		return picId;
	}

	/**
	 * @param picId the picId to set
	 */
	public void setPicId(String picId) {
		this.picId = picId;
	}

	/**
	 * @return the teachers
	 */
	public List<Integer> getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers the teachers to set
	 */
	public void setTeachers(List<Integer> teachers) {
		this.teachers = teachers;
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


}