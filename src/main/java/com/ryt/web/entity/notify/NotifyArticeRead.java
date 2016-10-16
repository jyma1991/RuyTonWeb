package com.ryt.web.entity.notify;

import java.util.Date;

public class NotifyArticeRead {
	private Integer articleId;
	private String uuid;
	private Integer userId;
	private String userName;
	private String operaterId;
	private Integer sortId;
	private Byte isDeleted;
	private String editedTime;
	private String createdTime;
	private Integer id;

	public void setArticleId(Integer articleId){
		this.articleId = articleId;
	}

	public Integer getArticleId(){
		return this.articleId;
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

	public void setOperaterId(String operaterId){
		this.operaterId = operaterId;
	}

	public String getOperaterId(){
		return this.operaterId;
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

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

}