package com.ryt.web.entity.sys;

import java.util.Date;

public class SysClassType {
	private String name;
	private String description;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private Date editedTime;
	private Date createdTime;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
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

}