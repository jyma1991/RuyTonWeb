package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScSchoolSch extends SearchEasyUI{

	//根据时间进行搜索的部分
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


    private Integer schoolIdSch;
    private Integer agentIdSch;
    private String schoolTypeIdSch;
    private String schoolCodeSch;
    private String schoolNameSch;
    private String businessLicenceSch;
    private String articleIdSch;
    private Integer levelIdSch;
    private String faxSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    public void setSchoolIdSch(Integer schoolIdSch){
        this.schoolIdSch = schoolIdSch;
    }
    
    @ValueField(column = "schoolId")
    public Integer getSchoolIdSch(){
        return this.schoolIdSch;
    }

    public void setAgentIdSch(Integer agentIdSch){
        this.agentIdSch = agentIdSch;
    }
    
    @ValueField(column = "agentId")
    public Integer getAgentIdSch(){
        return this.agentIdSch;
    }

    public void setSchoolTypeIdSch(String schoolTypeIdSch){
        this.schoolTypeIdSch = schoolTypeIdSch;
    }
    
    @ValueField(column = "schoolTypeId")
    public String getSchoolTypeIdSch(){
        return this.schoolTypeIdSch;
    }

    public void setSchoolCodeSch(String schoolCodeSch){
        this.schoolCodeSch = schoolCodeSch;
    }
    
    @ValueField(column = "schoolCode")
    public String getSchoolCodeSch(){
        return this.schoolCodeSch;
    }

    public void setSchoolNameSch(String schoolNameSch){
        this.schoolNameSch = schoolNameSch;
    }
    
    @LikeDoubleField(column = "schoolName")
    public String getSchoolNameSch(){
        return this.schoolNameSch;
    }

    public void setBusinessLicenceSch(String businessLicenceSch){
        this.businessLicenceSch = businessLicenceSch;
    }
    
    @ValueField(column = "businessLicence")
    public String getBusinessLicenceSch(){
        return this.businessLicenceSch;
    }

    public void setArticleIdSch(String articleIdSch){
        this.articleIdSch = articleIdSch;
    }
    
    @ValueField(column = "articleId")
    public String getArticleIdSch(){
        return this.articleIdSch;
    }

    public void setLevelIdSch(Integer levelIdSch){
        this.levelIdSch = levelIdSch;
    }
    
    @ValueField(column = "levelId")
    public Integer getLevelIdSch(){
        return this.levelIdSch;
    }

    public void setFaxSch(String faxSch){
        this.faxSch = faxSch;
    }
    
    @ValueField(column = "fax")
    public String getFaxSch(){
        return this.faxSch;
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
    
    @ValueField(column = "userName")
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


}