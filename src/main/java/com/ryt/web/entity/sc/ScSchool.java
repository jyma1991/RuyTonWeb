package com.ryt.web.entity.sc;

import java.io.Serializable;

import com.ryt.web.entity.user.User;

public class ScSchool implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private Integer agentId;
	private String schoolTypeId;
	private String schoolCode;
	private String schoolName;
	private String businessLicence;
	private String articleId;
	private Integer levelId;
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
	// 签到机抬头图片url
	private String sign_pic;
	private String erweima_pic;
	// 签到机的广告图片
	private String advertisement_one;
	private String advertisement_two;
	private String advertisement_three;
	//统计学校的学生，家长，教师数量
	private Integer studentNum;
	private Integer teacherNum;
	private Integer parentNum;
	//观看视频免费天数
	private Integer freeVideoDay;

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setSchoolTypeId(String schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}

	public String getSchoolTypeId() {
		return this.schoolTypeId;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolCode() {
		return this.schoolCode;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

	public String getBusinessLicence() {
		return this.businessLicence;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleId() {
		return this.articleId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getLevelId() {
		return this.levelId;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Integer getSortId() {
		return this.sortId;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setOperaterId(Integer operaterId) {
		this.operaterId = operaterId;
	}

	public Integer getOperaterId() {
		return this.operaterId;
	}

	public void setEditedTime(String editedTime) {
		this.editedTime = editedTime;
	}

	public String getEditedTime() {
		return this.editedTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSign_pic() {
		return sign_pic;
	}

	public void setSign_pic(String sign_pic) {
		this.sign_pic = sign_pic;
	}

	public String getErweima_pic() {
		return erweima_pic;
	}

	public void setErweima_pic(String erweima_pic) {
		this.erweima_pic = erweima_pic;
	}

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Integer getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}

	public Integer getParentNum() {
		return parentNum;
	}

	public void setParentNum(Integer parentNum) {
		this.parentNum = parentNum;
	}
	
	
	public String getAdvertisement_one() {
		return advertisement_one;
	}

	public void setAdvertisement_one(String advertisement_one) {
		this.advertisement_one = advertisement_one;
	}

	public String getAdvertisement_two() {
		return advertisement_two;
	}

	public void setAdvertisement_two(String advertisement_two) {
		this.advertisement_two = advertisement_two;
	}

	public String getAdvertisement_three() {
		return advertisement_three;
	}

	public void setAdvertisement_three(String advertisement_three) {
		this.advertisement_three = advertisement_three;
	}

	public Integer getFreeVideoDay() {
		return freeVideoDay;
	}

	public void setFreeVideoDay(Integer freeVideoDay) {
		this.freeVideoDay = freeVideoDay;
	}
	
}