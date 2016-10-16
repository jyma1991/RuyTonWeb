package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScAuthUserStreamSch extends SearchEasyUI{

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


    private Integer streamSchoolIdSch;
    private Integer streamClassIdSch;
    private Integer streamIdSch;
    private String streamUrlSch;
    private Integer uIdSch;
    private Integer uSchoolIdSch;
    private Integer uClassIdSch;
    private Integer authTypeSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    public void setStreamSchoolIdSch(Integer streamSchoolIdSch){
        this.streamSchoolIdSch = streamSchoolIdSch;
    }
    
    @ValueField(column = "streamSchoolId")
    public Integer getStreamSchoolIdSch(){
        return this.streamSchoolIdSch;
    }

    public void setStreamClassIdSch(Integer streamClassIdSch){
        this.streamClassIdSch = streamClassIdSch;
    }
    
    @ValueField(column = "streamClassId")
    public Integer getStreamClassIdSch(){
        return this.streamClassIdSch;
    }

    public void setStreamIdSch(Integer streamIdSch){
        this.streamIdSch = streamIdSch;
    }
    
    @ValueField(column = "streamId")
    public Integer getStreamIdSch(){
        return this.streamIdSch;
    }

    public void setStreamUrlSch(String streamUrlSch){
        this.streamUrlSch = streamUrlSch;
    }
    
    @ValueField(column = "streamUrl")
    public String getStreamUrlSch(){
        return this.streamUrlSch;
    }

    public void setUIdSch(Integer uIdSch){
        this.uIdSch = uIdSch;
    }
    
    @ValueField(column = "uId")
    public Integer getUIdSch(){
        return this.uIdSch;
    }

    public void setUSchoolIdSch(Integer uSchoolIdSch){
        this.uSchoolIdSch = uSchoolIdSch;
    }
    
    @ValueField(column = "uSchoolId")
    public Integer getUSchoolIdSch(){
        return this.uSchoolIdSch;
    }

    public void setUClassIdSch(Integer uClassIdSch){
        this.uClassIdSch = uClassIdSch;
    }
    
    @ValueField(column = "uClassId")
    public Integer getUClassIdSch(){
        return this.uClassIdSch;
    }

    public void setAuthTypeSch(Integer authTypeSch){
        this.authTypeSch = authTypeSch;
    }
    
    @ValueField(column = "authType")
    public Integer getAuthTypeSch(){
        return this.authTypeSch;
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
    	if(null == this.isDeletedSch){
    		this.setIsDeletedSch(new Byte("0"));
    	}
        return this.isDeletedSch;
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