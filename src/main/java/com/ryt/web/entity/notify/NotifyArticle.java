package com.ryt.web.entity.notify;

import java.util.Date;
import java.util.List;

import com.ryt.web.entity.sc.ScClass;


public class NotifyArticle {
	private Integer parentId;
	private Integer senderId;
	private Integer schoolId;
	private Integer classId;
	private Integer articleId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private String operaterId;
	private Integer sortId;
	private Byte isDeleted;
	private String editedTime;
	private String createdTime;
	private Integer notifyType;
	
	//查詢使用的參數
	List<Integer> classIdsSch;

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getParentId(){
		return this.parentId;
	}

	public void setSenderId(Integer senderId){
		this.senderId = senderId;
	}

	public Integer getSenderId(){
		return this.senderId;
	}

	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public void setClassId(Integer classId){
		this.classId = classId;
	}

	public Integer getClassId(){
		return this.classId;
	}

	public void setArticleId(Integer articleId){
		this.articleId = articleId;
	}

	public Integer getArticleId(){
		return this.articleId;
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

	public void setNotifyType(Integer notifyType){
		this.notifyType = notifyType;
	}

	public Integer getNotifyType(){
		return this.notifyType;
	}

	public List<Integer> getClassIdsSch() {
		return classIdsSch;
	}

	public void setClassIdsSch(List<Integer> classIdsSch) {
		this.classIdsSch = classIdsSch;
	}

}