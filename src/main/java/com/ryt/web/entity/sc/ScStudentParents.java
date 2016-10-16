package com.ryt.web.entity.sc;

import com.ryt.web.entity.user.User;

public class ScStudentParents {
	private Integer studentId;
	private Integer relatedTypeId;
	private Integer parentId;
	private String cardNo;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private Integer schoolId;//1229新增字段
	private String vipEndDate;//1230新增字段
	private User user;
	private Integer roleProperty;
	private String bindCardDate;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	public Integer getStudentId(){
		return this.studentId;
	}

	public void setRelatedTypeId(Integer relatedTypeId){
		this.relatedTypeId = relatedTypeId;
	}

	public Integer getRelatedTypeId(){
		return this.relatedTypeId;
	}

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getParentId(){
		return this.parentId;
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
	
	public String getVipEndDate() {
		return vipEndDate;
	}

	public void setVipEndDate(String vipEndDate) {
		this.vipEndDate = vipEndDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the roleProperty
	 */
	public Integer getRoleProperty() {
		return roleProperty;
	}

	/**
	 * @param roleProperty the roleProperty to set
	 */
	public void setRoleProperty(Integer roleProperty) {
		this.roleProperty = roleProperty;
	}

	/**
	 * @return the schoolId
	 */
	public Integer getSchoolId() {
		return schoolId;
	}

	/**
	 * @param schoolId the schoolId to set
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getBindCardDate() {
		return bindCardDate;
	}

	public void setBindCardDate(String bindCardDate) {
		this.bindCardDate = bindCardDate;
	}
	

}