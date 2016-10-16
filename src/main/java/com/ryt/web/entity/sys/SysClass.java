package com.ryt.web.entity.sys;

import java.io.Serializable;

public class SysClass implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer parentId;
	private String className;
	private String description;
	private String url;
	private String urlTarget;
	private Integer sysClassTypeId;
	private String formObjectList;
	private Integer classProperty;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private Integer cid;
	private Integer picId;
	private String icon;
	private String picUrl;
	//班级通知和园方通知的未读数量
	private Integer unReadCount;
	
	private Integer articleCount;
	
	private Integer articleCommentCount;
	
	public Integer getArticleCommentCount() {
		return articleCommentCount;
	}

	public void setArticleCommentCount(Integer articleCommentCount) {
		this.articleCommentCount = articleCommentCount;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getParentId(){
		return this.parentId;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return this.className;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setUrlTarget(String urlTarget){
		this.urlTarget = urlTarget;
	}

	public String getUrlTarget(){
		return this.urlTarget;
	}

	public void setSysClassTypeId(Integer sysClassTypeId){
		this.sysClassTypeId = sysClassTypeId;
	}

	public Integer getSysClassTypeId(){
		return this.sysClassTypeId;
	}

	public void setFormObjectList(String formObjectList){
		this.formObjectList = formObjectList;
	}

	public String getFormObjectList(){
		return this.formObjectList;
	}

	public void setClassProperty(Integer classProperty){
		this.classProperty = classProperty;
	}

	public Integer getClassProperty(){
		return this.classProperty;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(Integer unReadCount) {
		this.unReadCount = unReadCount;
	}

	
	

}