package com.ryt.web.entity.user;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;

public class UserSch extends SearchEasyUI{

	//鏍规嵁鏃堕棿杩涜鎼滅储鐨勯儴鍒�
	private Date createdStartSch;
	private Date createdEndSch;

	@ValueField(column = "createdTime", equal = ">=")
	public Date getCreatedStartSch() {
		return createdStartSch;
	}

	public void setCreatedStartSch(Date createdStartSch) {
		this.createdStartSch = createdStartSch;
	}

	@ValueField(column = "createdTime", equal = "<")
	public Date getCreatedEndSch() {
		if (createdEndSch != null) {
			return DateUtil.getDateAfterDay(createdEndSch, 1);
		}
		return createdEndSch;
	}

	public void setCreatedEndSch(Date createdEndSch) {
		this.createdEndSch = createdEndSch;
	}


    private String nickNameSch;
    private String trueNameSch;
    private String userPwdSch;
    private Integer provinceIdSch;
    
    private Integer avatarSch;
	private Integer sexSch;
	private Date birthdaySch;

	private Integer districtId;
    private Integer cityIdSch;
    private Integer rolePropertySch;

	private String addressDetailSch;
    private String fixedPhoneSch;
    private String mobilePhoneSch;
    private String remarkSch;
    private String weiXinSch;
    private Integer qqSch;
    private String emailSch;
    private Date lastLoginDateSch;
    private String tokenSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private String defaultAvatarSch;
    
    public String getDefaultAvatarSch() {
		return defaultAvatarSch;
	}

	public void setDefaultAvatarSch(String defaultAvatarSch) {
		this.defaultAvatarSch = defaultAvatarSch;
	}

	public Integer getAvatarSch() {
		return avatarSch;
	}

	public void setAvatarSch(Integer avatarSch) {
		this.avatarSch = avatarSch;
	}
	@LikeDoubleField(column = "sex")
	public Integer getSexSch() {
		return sexSch;
	}

	public void setSexSch(Integer sexSch) {
		this.sexSch = sexSch;
	}

	public Date getBirthdaySch() {
		return birthdaySch;
	}

	public void setBirthdaySch(Date birthdaySch) {
		this.birthdaySch = birthdaySch;
	}
	@ValueField(column = "roleProperty")
    public Integer getRolePropertySch() {
		return rolePropertySch;
	}

	public void setRolePropertySch(Integer rolePropertySch) {
		this.rolePropertySch = rolePropertySch;
	}

    public void setNickNameSch(String nickNameSch){
        this.nickNameSch = nickNameSch;
    }
    
    @ValueField(column = "nickName")
    public String getNickNameSch(){
        return this.nickNameSch;
    }

    public void setTrueNameSch(String trueNameSch){
        this.trueNameSch = trueNameSch;
    }
    
    @LikeDoubleField(column = "trueName")
    public String getTrueNameSch(){
        return this.trueNameSch;
    }

    public void setUserPwdSch(String userPwdSch){
        this.userPwdSch = userPwdSch;
    }
    
    @ValueField(column = "userPwd")
    public String getUserPwdSch(){
        return this.userPwdSch;
    }



    public void setProvinceIdSch(Integer provinceIdSch){
        this.provinceIdSch = provinceIdSch;
    }
    
    @ValueField(column = "provinceId")
    public Integer getProvinceIdSch(){
        return this.provinceIdSch;
    }



    public void setCityIdSch(Integer cityIdSch){
        this.cityIdSch = cityIdSch;
    }
    
    @ValueField(column = "cityId")
    public Integer getCityIdSch(){
        return this.cityIdSch;
    }

    public void setAddressDetailSch(String addressDetailSch){
        this.addressDetailSch = addressDetailSch;
    }
    
    @ValueField(column = "addressDetail")
    public String getAddressDetailSch(){
        return this.addressDetailSch;
    }

    public void setFixedPhoneSch(String fixedPhoneSch){
        this.fixedPhoneSch = fixedPhoneSch;
    }
    
    @ValueField(column = "fixedPhone")
    public String getFixedPhoneSch(){
        return this.fixedPhoneSch;
    }

    public void setMobilePhoneSch(String mobilePhoneSch){
        this.mobilePhoneSch = mobilePhoneSch;
    }
    
    @LikeDoubleField(column = "mobilePhone")
    public String getMobilePhoneSch(){
        return this.mobilePhoneSch;
    }

    public void setRemarkSch(String remarkSch){
        this.remarkSch = remarkSch;
    }
    
    @ValueField(column = "remark")
    public String getRemarkSch(){
        return this.remarkSch;
    }

    public void setWeiXinSch(String weiXinSch){
        this.weiXinSch = weiXinSch;
    }
    
    @ValueField(column = "weiXin")
    public String getWeiXinSch(){
        return this.weiXinSch;
    }

    public void setQqSch(Integer qqSch){
        this.qqSch = qqSch;
    }
    
    @ValueField(column = "qq")
    public Integer getQqSch(){
        return this.qqSch;
    }

    public void setEmailSch(String emailSch){
        this.emailSch = emailSch;
    }
    
    @ValueField(column = "email")
    public String getEmailSch(){
        return this.emailSch;
    }

    public void setLastLoginDateSch(Date lastLoginDateSch){
        this.lastLoginDateSch = lastLoginDateSch;
    }
    
    @ValueField(column = "lastLoginDate")
    public Date getLastLoginDateSch(){
        return this.lastLoginDateSch;
    }

    public void setTokenSch(String tokenSch){
        this.tokenSch = tokenSch;
    }
    
    @ValueField(column = "token")
    public String getTokenSch(){
        return this.tokenSch;
    }

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setUuidSch(String uuidSch){
        this.uuidSch = uuidSch;
    }
    
    @ValueField(column = "uuid")
    public String getUuidSch(){
        return this.uuidSch;
    }

    public void setUserIdSch(Integer userIdSch){
        this.userIdSch = userIdSch;
    }
    
    @ValueField(column = "userId")
    public Integer getUserIdSch(){
        return this.userIdSch;
    }

    public void setUserNameSch(String userNameSch){
        this.userNameSch = userNameSch;
    }
    
    @LikeDoubleField(column = "userName")
    public String getUserNameSch(){
        return this.userNameSch;
    }

    public void setSortIdSch(Integer sortIdSch){
        this.sortIdSch = sortIdSch;
    }
    
    @ValueField(column = "sortId")
    public Integer getSortIdSch(){
        return this.sortIdSch;
    }

    public void setIsDeletedSch(Byte isDeletedSch){
        this.isDeletedSch = isDeletedSch;
    }
    
    @ValueField(column = "isDeleted")
    public Byte getIsDeletedSch(){
    	if(null== this.isDeletedSch){
    		return 0;
    	}else{
    		return this.isDeletedSch;
    	}
        
    }

    public void setOperaterIdSch(Integer operaterIdSch){
        this.operaterIdSch = operaterIdSch;
    }
    
    @ValueField(column = "operaterId")
    public Integer getOperaterIdSch(){
        return this.operaterIdSch;
    }

    public void setEditedTimeSch(String editedTimeSch){
        this.editedTimeSch = editedTimeSch;
    }
    
    @ValueField(column = "editedTime")
    public String getEditedTimeSch(){
        return this.editedTimeSch;
    }

    public void setCreatedTimeSch(String createdTimeSch){
        this.createdTimeSch = createdTimeSch;
    }
    
    @ValueField(column = "createdTime")
    public String getCreatedTimeSch(){
        return this.createdTimeSch;
    }

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}



}