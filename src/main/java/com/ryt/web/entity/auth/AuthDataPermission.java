package com.ryt.web.entity.auth;

import java.util.Date;

public class AuthDataPermission {
	private Integer dpId;
	private Byte expressionType;
	private String dataColumn;
	private String equal;
	private String dataValue;
	private String remark;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;

	public void setDpId(Integer dpId){
		this.dpId = dpId;
	}

	public Integer getDpId(){
		return this.dpId;
	}

	public void setExpressionType(Byte expressionType){
		this.expressionType = expressionType;
	}

	public Byte getExpressionType(){
		return this.expressionType;
	}

	public void setDataColumn(String dataColumn){
		this.dataColumn = dataColumn;
	}

	public String getDataColumn(){
		return this.dataColumn;
	}

	public void setEqual(String equal){
		this.equal = equal;
	}

	public String getEqual(){
		return this.equal;
	}

	public void setDataValue(String dataValue){
		this.dataValue = dataValue;
	}

	public String getDataValue(){
		return this.dataValue;
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

}