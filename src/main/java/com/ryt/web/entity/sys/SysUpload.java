package com.ryt.web.entity.sys;

import java.io.Serializable;

public class SysUpload implements Serializable{

	private static final long serialVersionUID = 1L;
	private String clientName;
	private String serverName;
	private String fileExt;
	private Integer fileSize;
	private Integer fileWidth;
	private Integer fileHeight;
	private String moduleId;
	private String funcId;
	private String dataId;
	private String fileFullPath;
	private Integer zipId;
	private String proCode;
	private String proFileType;
	private String createdBy;
	private String fileTypes;
	private Integer customerId;
	private String duration;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Byte isDeleted;
	private Integer sortId;
	private Integer operaterIid;
	private String editedTime;
	private String createdTime;
	private Integer classId;//导入班级学生时候需要的班级ID  add by jyma 2015-11-09 23:10:36
	
	
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	//缩略图路径
	private String thumbFileFullPath;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public String getClientName(){
		return this.clientName;
	}

	public void setServerName(String serverName){
		this.serverName = serverName;
	}

	public String getServerName(){
		return this.serverName;
	}

	public void setFileExt(String fileExt){
		this.fileExt = fileExt;
	}

	public String getFileExt(){
		return this.fileExt;
	}

	public void setFileSize(Integer fileSize){
		this.fileSize = fileSize;
	}

	public Integer getFileSize(){
		return this.fileSize;
	}

	public void setFileWidth(Integer fileWidth){
		this.fileWidth = fileWidth;
	}

	public Integer getFileWidth(){
		return this.fileWidth;
	}

	public void setFileHeight(Integer fileHeight){
		this.fileHeight = fileHeight;
	}

	public Integer getFileHeight(){
		return this.fileHeight;
	}

	public void setModuleId(String moduleId){
		this.moduleId = moduleId;
	}

	public String getModuleId(){
		return this.moduleId;
	}

	public void setFuncId(String funcId){
		this.funcId = funcId;
	}

	public String getFuncId(){
		return this.funcId;
	}

	public void setDataId(String dataId){
		this.dataId = dataId;
	}

	public String getDataId(){
		return this.dataId;
	}

	public void setFileFullPath(String fileFullPath){
		this.fileFullPath = fileFullPath;
	}

	public String getFileFullPath(){
		return this.fileFullPath;
	}

	public void setZipId(Integer zipId){
		this.zipId = zipId;
	}

	public Integer getZipId(){
		return this.zipId;
	}

	public void setProCode(String proCode){
		this.proCode = proCode;
	}

	public String getProCode(){
		return this.proCode;
	}

	public void setProFileType(String proFileType){
		this.proFileType = proFileType;
	}

	public String getProFileType(){
		return this.proFileType;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return this.createdBy;
	}

	public void setFileTypes(String fileTypes){
		this.fileTypes = fileTypes;
	}

	public String getFileTypes(){
		return this.fileTypes;
	}

	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}

	public Integer getCustomerId(){
		return this.customerId;
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

	public void setIsDeleted(Byte isDeleted){
		this.isDeleted = isDeleted;
	}

	public Byte getIsDeleted(){
		return this.isDeleted;
	}

	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}

	public Integer getSortId(){
		return this.sortId;
	}

	public void setOperaterIid(Integer operaterIid){
		this.operaterIid = operaterIid;
	}

	public Integer getOperaterIid(){
		return this.operaterIid;
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

	public String getThumbFileFullPath() {
		return thumbFileFullPath;
	}

	public void setThumbFileFullPath(String thumbFileFullPath) {
		this.thumbFileFullPath = thumbFileFullPath;
	}
	

}