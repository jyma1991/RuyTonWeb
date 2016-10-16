package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScClassScheduleSch extends SearchEasyUI{

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
    private String beginDateSch;
    private String endDateSch;
    private Integer d1Sch;
    private Integer d2Sch;
    private Integer d3Sch;
    private Integer d4Sch;
    private Integer d5Sch;
    private Integer d6Sch;
    private Integer d7Sch;
    private String remarkSch;
    private Integer classIdSch;
    private Integer schoolIdSch;
    private Integer agentIdSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    public void setBeginDateSch(String beginDateSch){
        this.beginDateSch = beginDateSch;
    }
    
    @ValueField(column = "beginDate")
    public String getBeginDateSch(){
        return this.beginDateSch;
    }

    public void setEndDateSch(String endDateSch){
        this.endDateSch = endDateSch;
    }
    
    @ValueField(column = "endDate")
    public String getEndDateSch(){
        return this.endDateSch;
    }

    public void setD1Sch(Integer d1Sch){
        this.d1Sch = d1Sch;
    }
    
    @ValueField(column = "d1")
    public Integer getD1Sch(){
        return this.d1Sch;
    }

    public void setD2Sch(Integer d2Sch){
        this.d2Sch = d2Sch;
    }
    
    @ValueField(column = "d2")
    public Integer getD2Sch(){
        return this.d2Sch;
    }

    public void setD3Sch(Integer d3Sch){
        this.d3Sch = d3Sch;
    }
    
    @ValueField(column = "d3")
    public Integer getD3Sch(){
        return this.d3Sch;
    }

    public void setD4Sch(Integer d4Sch){
        this.d4Sch = d4Sch;
    }
    
    @ValueField(column = "d4")
    public Integer getD4Sch(){
        return this.d4Sch;
    }

    public void setD5Sch(Integer d5Sch){
        this.d5Sch = d5Sch;
    }
    
    @ValueField(column = "d5")
    public Integer getD5Sch(){
        return this.d5Sch;
    }

    public void setD6Sch(Integer d6Sch){
        this.d6Sch = d6Sch;
    }
    
    @ValueField(column = "d6")
    public Integer getD6Sch(){
        return this.d6Sch;
    }

    public void setD7Sch(Integer d7Sch){
        this.d7Sch = d7Sch;
    }
    
    @ValueField(column = "d7")
    public Integer getD7Sch(){
        return this.d7Sch;
    }

    public void setRemarkSch(String remarkSch){
        this.remarkSch = remarkSch;
    }
    
    @ValueField(column = "remark")
    public String getRemarkSch(){
        return this.remarkSch;
    }

    public void setClassIdSch(Integer classIdSch){
        this.classIdSch = classIdSch;
    }
    
    @ValueField(column = "classId")
    public Integer getClassIdSch(){
        return this.classIdSch;
    }

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