package com.ryt.web.entity.sc;

import java.util.Date;
import com.ryt.web.entity.user.User;

public class ScStudent {
	private Integer studentId;
	private Integer classId;
	private Integer teacherId;
	private String studentCode;
	private Integer schoolId;
	private Integer grade;
	private Integer studentStatusId;
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
	
	
	private Date signInTime;
	private Date signOutTime;
	private Integer signStatu=0;
	//签到状态
	private Integer signOutStatus = 0;
	

	public Date getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}

	public Date getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(Date signOutTime) {
		this.signOutTime = signOutTime;
	}

	public Integer getSignStatu() {
		return signStatu;
	}

	public void setSignStatu(Integer signStatu) {
		this.signStatu = signStatu;
	}

	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	public Integer getStudentId(){
		return this.studentId;
	}

	public void setClassId(Integer classId){
		this.classId = classId;
	}

	public Integer getClassId(){
		return this.classId;
	}

	public void setTeacherId(Integer teacherId){
		this.teacherId = teacherId;
	}

	public Integer getTeacherId(){
		return this.teacherId;
	}

	public void setStudentCode(String studentCode){
		this.studentCode = studentCode;
	}

	public String getStudentCode(){
		return this.studentCode;
	}

	public void setSchoolId(Integer schoolId){
		this.schoolId = schoolId;
	}

	public Integer getSchoolId(){
		return this.schoolId;
	}

	public void setGrade(Integer grade){
		this.grade = grade;
	}

	public Integer getGrade(){
		return this.grade;
	}

	public void setStudentStatusId(Integer studentStatusId){
		this.studentStatusId = studentStatusId;
	}

	public Integer getStudentStatusId(){
		return this.studentStatusId;
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

	public Integer getSignOutStatus() {
		return signOutStatus;
	}

	public void setSignOutStatus(Integer signOutStatus) {
		this.signOutStatus = signOutStatus;
	}
	


}