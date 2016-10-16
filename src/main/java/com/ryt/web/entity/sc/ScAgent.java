package com.ryt.web.entity.sc;

import com.ryt.web.entity.user.User;

public class ScAgent {
	private Integer agentId;
	private String agentCode;
	private Integer levelId;
	private String identityCardNo;
	private Integer commandUserId;
	private String fax;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private User user;
	private String trueName;
	private Integer schoolNum;
	
	private String cardNo;
	
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	public Integer getAgentId(){
		return this.agentId;
	}

	public void setAgentCode(String agentCode){
		this.agentCode = agentCode;
	}

	public String getAgentCode(){
		return this.agentCode;
	}

	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	public Integer getLevelId(){
		return this.levelId;
	}

	public void setIdentityCardNo(String identityCardNo){
		this.identityCardNo = identityCardNo;
	}

	public String getIdentityCardNo(){
		return this.identityCardNo;
	}

	public void setCommandUserId(Integer commandUserId){
		this.commandUserId = commandUserId;
	}

	public Integer getCommandUserId(){
		return this.commandUserId;
	}

	public void setFax(String fax){
		this.fax = fax;
	}

	public String getFax(){
		return this.fax;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Integer getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(Integer schoolNum) {
		this.schoolNum = schoolNum;
	}

		public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}