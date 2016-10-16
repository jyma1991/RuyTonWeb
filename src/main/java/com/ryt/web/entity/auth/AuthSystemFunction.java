package com.ryt.web.entity.auth;

import java.util.Date;

public class AuthSystemFunction {
	private Integer systemResourceId;
	private String functionName;
	private String operateCode;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private String editedTime;
	private String createdTime;

	public void setSystemResourceId(Integer systemResourceId){
		this.systemResourceId = systemResourceId;
	}

	public Integer getSystemResourceId(){
		return this.systemResourceId;
	}

	public void setFunctionName(String functionName){
		this.functionName = functionName;
	}

	public String getFunctionName(){
		return this.functionName;
	}

	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}

	public String getOperateCode(){
		return this.operateCode;
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