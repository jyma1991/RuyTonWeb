package com.ryt.web.entity.sc;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.ryt.web.entity.stream.StStream;
import com.ryt.web.entity.user.User;

public class ScClass implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private Integer agentId;
	private Integer managerId;
	private String classCode;
	private String className;
	private Integer grade;
	private Integer maxAmount;
	private Integer amount;
	private Date startDate;
	private String remark;
	private Integer placeTypeId;
	private Integer streamId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private String videoStart;
	private String videoStop;
	
	private List<User> parentsContacts;
	private String streamUrl;
	private String streamPic;
	//新增场地视频截图地址
	private String snapShotStreamUrl;
	//新增属性用来存储流对象
	private StStream stream;
	
	public ScClass(){
		
	}
	
	public ScClass(ScClass scClass){
		this.schoolId = scClass.getSchoolId();
		this.agentId = scClass.getAgentId();
		this.managerId =scClass.getManagerId();
		this.classCode = scClass.getClassCode();
		this.className = scClass.getClassName();
		this.grade = scClass.getGrade();
		this.maxAmount = scClass.getMaxAmount();
		this.amount = scClass.getAmount();
		this.startDate = scClass.getStartDate();
		this.remark = scClass.getRemark();
		this.placeTypeId = scClass.getPlaceTypeId();
		this.streamId = scClass.getStreamId();
		this.id = scClass.getId();
		this.uuid = scClass.getUuid();
		this.userId = scClass.getUserId();
		this.userName = scClass.getUserName();
		this.sortId = scClass.getSortId();
		this.isDeleted = scClass.getIsDeleted();
		this.operaterId = scClass.getOperaterId();
		this.editedTime = scClass.getEditedTime();
		this.createdTime = scClass.getCreatedTime();
		this.videoStart = scClass.getVideoStart();
		this.videoStop = scClass.getVideoStop();
		this.streamUrl = scClass.getStreamUrl();
		this.snapShotStreamUrl = scClass.getSnapShotStreamUrl();
	}
	
	public String getStreamPic() {
		return streamPic;
	}

	public void setStreamPic(String streamPic) {
		this.streamPic = streamPic;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	public void setSchoolId(Integer schoolId){
		this.schoolId = schoolId;
	}

	public Integer getSchoolId(){
		return this.schoolId;
	}

	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	public Integer getAgentId(){
		return this.agentId;
	}

	public void setManagerId(Integer managerId){
		this.managerId = managerId;
	}

	public Integer getManagerId(){
		return this.managerId;
	}

	public void setClassCode(String classCode){
		this.classCode = classCode;
	}

	public String getClassCode(){
		return this.classCode;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return this.className;
	}

	public void setGrade(Integer grade){
		this.grade = grade;
	}

	public Integer getGrade(){
		return this.grade;
	}

	public void setMaxAmount(Integer maxAmount){
		this.maxAmount = maxAmount;
	}

	public Integer getMaxAmount(){
		return this.maxAmount;
	}

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public Integer getAmount(){
		return this.amount;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	public Date getStartDate(){
		return this.startDate;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setPlaceTypeId(Integer placeTypeId){
		this.placeTypeId = placeTypeId;
	}

	public Integer getPlaceTypeId(){
		return this.placeTypeId;
	}

	public void setStreamId(Integer streamId){
		this.streamId = streamId;
	}

	public Integer getStreamId(){
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

	public List<User> getParentsContacts() {
		return parentsContacts;
	}

	public void setParentsContacts(List<User> parentsContacts) {
		this.parentsContacts = parentsContacts;
	}

	public String getVideoStart() {
		return videoStart;
	}

	public void setVideoStart(String videoStart) {
		this.videoStart = videoStart;
	}

	public String getVideoStop() {
		return videoStop;
	}

	public void setVideoStop(String videoStop) {
		this.videoStop = videoStop;
	}

	public String getSnapShotStreamUrl() {
		return snapShotStreamUrl;
	}

	public void setSnapShotStreamUrl(String snapShotStreamUrl) {
		this.snapShotStreamUrl = snapShotStreamUrl;
	}

	public StStream getStream() {
		return stream;
	}

	public void setStream(StStream stream) {
		this.stream = stream;
	}

}