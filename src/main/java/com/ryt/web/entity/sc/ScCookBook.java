package com.ryt.web.entity.sc;

import java.io.Serializable;
import java.util.Date;

public class ScCookBook implements Serializable{
	private Date publishDate;
	private Integer foodTypeId;
	private String detail;
	private Integer foodPicId;
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
	private String fileFullPath;
	private String foodType;


	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void setPublishDate(Date publishDate){
		this.publishDate = publishDate;
	}

	public Date getPublishDate(){
		return this.publishDate;
	}

	public void setFoodTypeId(Integer foodTypeId){
		this.foodTypeId = foodTypeId;
	}

	public Integer getFoodTypeId(){
		return this.foodTypeId;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setFoodPicId(Integer foodPicId){
		this.foodPicId = foodPicId;
	}

	public Integer getFoodPicId(){
		return this.foodPicId;
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


}