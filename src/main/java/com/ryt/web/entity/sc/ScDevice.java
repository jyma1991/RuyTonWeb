package com.ryt.web.entity.sc;

public class ScDevice {
	private String deviceName;
	private Integer deviceTypeId;
	private Integer agentId;
	private String remark;
	private Integer isValid;
	private String streamId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setDeviceTypeId(Integer deviceTypeId){
		this.deviceTypeId = deviceTypeId;
	}

	public Integer getDeviceTypeId(){
		return this.deviceTypeId;
	}

	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	public Integer getAgentId(){
		return this.agentId;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setIsValid(Integer isValid){
		this.isValid = isValid;
	}

	public Integer getIsValid(){
		return this.isValid;
	}

	public void setStreamId(String streamId){
		this.streamId = streamId;
	}

	public String getStreamId(){
		return this.streamId;
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