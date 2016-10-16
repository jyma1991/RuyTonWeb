package com.ryt.web.entity.article;

import java.util.Date;

public class ArticleExtend {
	private String cellName;
	private String cellValue;
	private Integer sysClassId;
	private Integer articleBaseId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	
	public Integer getArticleBaseId() {
		return articleBaseId;
	}

	public void setArticleBaseId(Integer articleBaseId) {
		this.articleBaseId = articleBaseId;
	}

	public void setCellName(String cellName){
		this.cellName = cellName;
	}

	public String getCellName(){
		return this.cellName;
	}

	public void setCellValue(String cellValue){
		this.cellValue = cellValue;
	}

	public String getCellValue(){
		return this.cellValue;
	}

	public void setSysClassId(Integer sysClassId){
		this.sysClassId = sysClassId;
	}

	public Integer getSysClassId(){
		return this.sysClassId;
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