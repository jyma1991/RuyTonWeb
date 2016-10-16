package com.ryt.web.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.durcframework.core.IUser;

import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScTeacherCourse;

public class User implements IUser, Serializable {

	private static final long serialVersionUID = 1L;
	private String nickName;
	private String trueName;

	private Integer avatar;
	private Integer sex;
	private Date birthday;

	private String userPwd;
	private Integer roleProperty;
	private Integer provinceId;
	private Integer districtId;
	private Integer cityId;
	private String addressDetail;
	private String fixedPhone;
	private String mobilePhone;
	private String remark;
	private String weiXin;
	private String qq;
	private String email;
	private Date lastLoginDate;
	private String token;
	private Integer id;
	private String uuid;
	private Integer userId;

	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	private String sexStr;

	// 所属区
	private String districName;
	// 所属城市
	private String cityName;
	// 所属省
	private String provinceName;
	// 学校名称
	private String schoolName;
	// 班级名称
	private String scClassName;
	// 用户头像url
	private String babyHead;
	private Integer classId;
	private Integer schoolId;
	private String artHead;
	private String md5Pwd;
	// 用户和孩子的关系
	private String relativeName;
	// 孩子的id
	private Integer childId;
	// 标识该用户是否被
	private String streamIncluded;
	private String base64Data;
	private String defaultAvatar;
	private String babyName;
	// //用来记录一个老师多个班级的情况
	private List<Integer> classIds;
	private List<ScClass> scClasses;
	private List<User> childrens;
	// 是否是会员
	private boolean memberFlag;
	private String cardNo;
	private Integer relatedTypeId;
	private String bindCardDate;
	private Integer agentId;
	//在线状态
	private short onlineStatus;
	public String getDefaultAvatar() {
		return defaultAvatar;
	}

	public void setDefaultAvatar(String defaultAvatar) {
		this.defaultAvatar = defaultAvatar;
	}

	public Integer getAvatar() {
		return avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sexStr = (sex == 1 ? "男" : "女");
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getRoleProperty() {
		return roleProperty;
	}

	public void setRoleProperty(Integer roleProperty) {
		this.roleProperty = roleProperty;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getFixedPhone() {
		return this.fixedPhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getWeiXin() {
		return this.weiXin;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQq() {
		return this.qq;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
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

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPwd;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDistricName() {
		return districName;
	}

	public void setDistricName(String districName) {
		this.districName = districName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getSexStr() {
		return sexStr;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getScClassName() {
		return scClassName;
	}

	public void setScClassName(String scClassName) {
		this.scClassName = scClassName;
	}

	public String getBabyHead() {
		return babyHead;
	}

	public void setBabyHead(String babyHead) {
		this.babyHead = babyHead;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getArtHead() {
		return artHead;
	}

	public void setArtHead(String artHead) {
		this.artHead = artHead;
	}

	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}

	public String getMd5Pwd() {
		return md5Pwd;
	}

	public void setMd5Pwd(String md5Pwd) {
		this.md5Pwd = md5Pwd;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getStreamIncluded() {
		return streamIncluded;
	}

	public void setStreamIncluded(String streamIncluded) {
		this.streamIncluded = streamIncluded;
	}

	public String getBase64Data() {
		return base64Data;
	}

	public void setBase64Data(String base64Data) {
		this.base64Data = base64Data;
	}

	public List<Integer> getClassIds() {
		return classIds;
	}

	public void setClassIds(List<Integer> classIds) {
		this.classIds = classIds;
	}

	public List<User> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<User> childrens) {
		this.childrens = childrens;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public List<ScClass> getScClasses() {
		return scClasses;
	}

	public void setScClasses(List<ScClass> scClasses) {
		this.scClasses = scClasses;
	}

	public boolean isMemberFlag() {
		return memberFlag;
	}

	public void setMemberFlag(boolean memberFlag) {
		this.memberFlag = memberFlag;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getRelatedTypeId() {
		return relatedTypeId;
	}

	public void setRelatedTypeId(Integer relatedTypeId) {
		this.relatedTypeId = relatedTypeId;
	}

	public String getBindCardDate() {
		return bindCardDate;
	}

	public void setBindCardDate(String bindCardDate) {
		this.bindCardDate = bindCardDate;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public short getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(short onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
}