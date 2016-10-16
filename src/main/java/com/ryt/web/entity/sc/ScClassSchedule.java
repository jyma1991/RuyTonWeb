package com.ryt.web.entity.sc;

import java.io.Serializable;
import java.util.List;

public class ScClassSchedule implements Serializable{
	private String beginDate;
	private String endDate;
	private Integer d1;
	private Integer d2;
	private Integer d3;
	private Integer d4;
	private Integer d5;
	private Integer d6;
	private Integer d7;
	private String remark;
	private Integer classId;
	private Integer schoolId;
	private Integer agentId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private ScCourse course1;
	private ScCourse course2;
	private ScCourse course3;
	private ScCourse course4;
	private ScCourse course5;
	private ScCourse course6;
	private ScCourse course7;
	private ScClass scClass;
	private Object courses;
	private Integer wDay; //传递传递app课程查询 每周的第几天
	private String courseName; //传递app课程查询 课程名称
	List<ScCourse> courseList;

	public Integer getwDay() {
		return wDay;
	}

	public void setwDay(Integer wDay) {
		this.wDay = wDay;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setBeginDate(String beginDate){
		this.beginDate = beginDate;
	}

	public String getBeginDate(){
		return this.beginDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return this.endDate;
	}

	public void setD1(Integer d1){
		this.d1 = d1;
	}

	public Integer getD1(){
		return this.d1;
	}

	public void setD2(Integer d2){
		this.d2 = d2;
	}

	public Integer getD2(){
		return this.d2;
	}

	public void setD3(Integer d3){
		this.d3 = d3;
	}

	public Integer getD3(){
		return this.d3;
	}

	public void setD4(Integer d4){
		this.d4 = d4;
	}

	public Integer getD4(){
		return this.d4;
	}

	public void setD5(Integer d5){
		this.d5 = d5;
	}

	public Integer getD5(){
		return this.d5;
	}

	public void setD6(Integer d6){
		this.d6 = d6;
	}

	public Integer getD6(){
		return this.d6;
	}

	public void setD7(Integer d7){
		this.d7 = d7;
	}

	public Integer getD7(){
		return this.d7;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setClassId(Integer classId){
		this.classId = classId;
	}

	public Integer getClassId(){
		return this.classId;
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

	public ScCourse getCourse1() {
		return course1;
	}

	public void setCourse1(ScCourse course1) {
		this.course1 = course1;
	}

	public ScCourse getCourse2() {
		return course2;
	}

	public void setCourse2(ScCourse course2) {
		this.course2 = course2;
	}

	public ScCourse getCourse3() {
		return course3;
	}

	public void setCourse3(ScCourse course3) {
		this.course3 = course3;
	}

	public ScCourse getCourse4() {
		return course4;
	}

	public void setCourse4(ScCourse course4) {
		this.course4 = course4;
	}

	public ScCourse getCourse5() {
		return course5;
	}

	public void setCourse5(ScCourse course5) {
		this.course5 = course5;
	}

	public ScCourse getCourse6() {
		return course6;
	}

	public void setCourse6(ScCourse course6) {
		this.course6 = course6;
	}

	public ScCourse getCourse7() {
		return course7;
	}

	public void setCourse7(ScCourse course7) {
		this.course7 = course7;
	}
	
	public ScClass getScClass() {
		return scClass;
	}

	public void setScClass(ScClass scClass) {
		this.scClass = scClass;
	}

	public Object getCourses() {
		return courses;
	}

	public void setCourses(Object courses) {
		this.courses = courses;
	}

	public List<ScCourse> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<ScCourse> courseList) {
		this.courseList = courseList;
	}
	
}