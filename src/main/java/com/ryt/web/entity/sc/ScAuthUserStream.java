package com.ryt.web.entity.sc;

import java.io.Serializable;

public class ScAuthUserStream implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer streamSchoolId;
	private Integer streamClassId;
	private Integer streamId;
	private String streamUrl;
	private Integer uId;
	private Integer uSchoolId;
	private Integer uClassId;
	private Integer authType;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;

	public void setStreamSchoolId(Integer streamSchoolId){
		this.streamSchoolId = streamSchoolId;
	}

	public Integer getStreamSchoolId(){
		return this.streamSchoolId;
	}

	public void setStreamClassId(Integer streamClassId){
		this.streamClassId = streamClassId;
	}

	public Integer getStreamClassId(){
		return this.streamClassId;
	}

	public void setStreamId(Integer streamId){
		this.streamId = streamId;
	}

	public Integer getStreamId(){
		return this.streamId;
	}

	public void setStreamUrl(String streamUrl){
		this.streamUrl = streamUrl;
	}

	public String getStreamUrl(){
		return this.streamUrl;
	}

	public void setUId(Integer uId){
		this.uId = uId;
	}

	public Integer getUId(){
		return this.uId;
	}

	public void setUSchoolId(Integer uSchoolId){
		this.uSchoolId = uSchoolId;
	}

	public Integer getUSchoolId(){
		return this.uSchoolId;
	}

	public void setUClassId(Integer uClassId){
		this.uClassId = uClassId;
	}

	public Integer getUClassId(){
		return this.uClassId;
	}

	public void setAuthType(Integer authType){
		this.authType = authType;
	}

	public Integer getAuthType(){
		return this.authType;
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