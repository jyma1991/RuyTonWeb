package com.ryt.web.entity.sys;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class SysClassSch extends SearchEasyUI{

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


    private Integer parentIdSch;
    private String classNameSch;
    private String descriptionSch;
    private String urlSch;
    private String urlTargetSch;
    private Integer sysClassTypeIdSch;
    private String formObjectListSch;
    private Integer classPropertySch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    public void setParentIdSch(Integer parentIdSch){
        this.parentIdSch = parentIdSch;
    }
    
    @ValueField(column = "parentId")
    public Integer getParentIdSch(){
        return this.parentIdSch;
    }

    public void setClassNameSch(String classNameSch){
        this.classNameSch = classNameSch;
    }
    
    @ValueField(column = "className")
    public String getClassNameSch(){
        return this.classNameSch;
    }

    public void setDescriptionSch(String descriptionSch){
        this.descriptionSch = descriptionSch;
    }
    
    @ValueField(column = "description")
    public String getDescriptionSch(){
        return this.descriptionSch;
    }

    public void setUrlSch(String urlSch){
        this.urlSch = urlSch;
    }
    
    @ValueField(column = "url")
    public String getUrlSch(){
        return this.urlSch;
    }

    public void setUrlTargetSch(String urlTargetSch){
        this.urlTargetSch = urlTargetSch;
    }
    
    @ValueField(column = "urlTarget")
    public String getUrlTargetSch(){
        return this.urlTargetSch;
    }

    public void setSysClassTypeIdSch(Integer sysClassTypeIdSch){
        this.sysClassTypeIdSch = sysClassTypeIdSch;
    }
    
    @ValueField(column = "sysClassTypeId")
    public Integer getSysClassTypeIdSch(){
        return this.sysClassTypeIdSch;
    }

    public void setFormObjectListSch(String formObjectListSch){
        this.formObjectListSch = formObjectListSch;
    }
    
    @ValueField(column = "formObjectList")
    public String getFormObjectListSch(){
        return this.formObjectListSch;
    }

    public void setClassPropertySch(Integer classPropertySch){
        this.classPropertySch = classPropertySch;
    }
    
    @ValueField(column = "classProperty")
    public Integer getClassPropertySch(){
        return this.classPropertySch;
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