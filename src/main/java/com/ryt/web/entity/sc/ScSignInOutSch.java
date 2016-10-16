package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScSignInOutSch extends SearchEasyUI{

	//根据时间进行搜索的部分
	private Date createdStartSch;
	private Date createdEndSch;
	private Date signInTimeStartSch;
	private Date signInTimeEndSch;
    private Date signOutTimeStartSch;	
    private Date signOutTimeEndSch;   
    private Date signDateSch;   
    @ValueField(column = "signInTime", equal = ">=")
	public Date getSignInTimeStartSch() {
		return signInTimeStartSch;
	}

	public void setSignInTimeStartSch(Date signInTimeStartSch) {
		this.signInTimeStartSch = signInTimeStartSch;
	}
	
	@ValueField(column = "signInTime", equal = "<")
	public Date getSignInTimeEndSch() {
		return signInTimeEndSch;
	}

	public void setSignInTimeEndSch(Date signInTimeEndSch) {
		this.signInTimeEndSch = signInTimeEndSch;
	}
	
	@ValueField(column = "signOutTime", equal = ">=")
	public Date getSignOutTimeStartSch() {
		return signOutTimeStartSch;
	}

	public void setSignOutTimeStartSch(Date signOutTimeStartSch) {
		this.signOutTimeStartSch = signOutTimeStartSch;
	}
	
	@ValueField(column = "signOutTime", equal = "<")
	public Date getSignOutTimeEndSch() {
		return signOutTimeEndSch;
	}

	public void setSignOutTimeEndSch(Date signOutTimeEndSch) {
		this.signOutTimeEndSch = signOutTimeEndSch;
	}

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


    private Integer studentIdSch;
    private Integer picIdSch;
    private Date signInTimeSch;
    private Date signOutTimeSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    @ValueField(column = "studentId")
    public Integer getStudentIdSch() {
		return studentIdSch;
	}

	public void setStudentIdSch(Integer studentIdSch) {
		this.studentIdSch = studentIdSch;
	}

	public void setPicIdSch(Integer picIdSch){
        this.picIdSch = picIdSch;
    }
    
    @ValueField(column = "picId")
    public Integer getPicIdSch(){
        return this.picIdSch;
    }

    public void setSignInTimeSch(Date signInTimeSch){
        this.signInTimeSch = signInTimeSch;
    }
    
    @ValueField(column = "signInTime")
    public Date getSignInTimeSch(){
        return this.signInTimeSch;
    }

    public void setSignOutTimeSch(Date signOutTimeSch){
        this.signOutTimeSch = signOutTimeSch;
    }
    
    @ValueField(column = "signOutTime")
    public Date getSignOutTimeSch(){
        return this.signOutTimeSch;
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
    
    @ValueField(column="signDate")
	public Date getSignDateSch() {
		return signDateSch;
	}

	public void setSignDateSch(Date signDateSch) {
		this.signDateSch = signDateSch;
	}
    


}