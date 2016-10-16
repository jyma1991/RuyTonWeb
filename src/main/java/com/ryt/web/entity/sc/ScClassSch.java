package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;


public class ScClassSch extends SearchEasyUI{

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

    private Integer schoolIdSch;
    private Integer agentIdSch;
    private Integer managerIdSch;
    private String classCodeSch;
    private String classNameSch;
    private Integer gradeSch;
    private Integer maxAmountSch;
    private Integer amountSch;
    private Date startDateSch;
    private String remarkSch;
    private Integer placeTypeIdSch;
    private Integer streamIdSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private Date signDateSch;//获取签到日期
	private String videoStart;
	private String videoStop;
	//新增场地视频截图地址
	private String snapShotStreamUrlSch;
    
    @DateTimeFormat(pattern="yyyy-MM-dd") 
    public Date getSignDateSch() {
		return signDateSch;
	}

	public void setSignDateSch(Date signDateSch) {
		this.signDateSch = signDateSch;
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

    public void setManagerIdSch(Integer managerIdSch){
        this.managerIdSch = managerIdSch;
    }
    
    @ValueField(column = "managerId")
    public Integer getManagerIdSch(){
        return this.managerIdSch;
    }

    public void setClassCodeSch(String classCodeSch){
        this.classCodeSch = classCodeSch;
    }
    
    @ValueField(column = "classCode")
    public String getClassCodeSch(){
        return this.classCodeSch;
    }

    public void setClassNameSch(String classNameSch){
        this.classNameSch = classNameSch;
    }
    
    @LikeDoubleField(column = "className")
    public String getClassNameSch(){
        return this.classNameSch;
    }

    public void setGradeSch(Integer gradeSch){
        this.gradeSch = gradeSch;
    }
    
    @ValueField(column = "grade")
    public Integer getGradeSch(){
        return this.gradeSch;
    }

    public void setMaxAmountSch(Integer maxAmountSch){
        this.maxAmountSch = maxAmountSch;
    }
    
    @ValueField(column = "maxAmount")
    public Integer getMaxAmountSch(){
        return this.maxAmountSch;
    }

    public void setAmountSch(Integer amountSch){
        this.amountSch = amountSch;
    }
    
    @ValueField(column = "amount")
    public Integer getAmountSch(){
        return this.amountSch;
    }

    public void setStartDateSch(Date startDateSch){
        this.startDateSch = startDateSch;
    }
    
    @ValueField(column = "startDate")
    public Date getStartDateSch(){
        return this.startDateSch;
    }

    public void setRemarkSch(String remarkSch){
        this.remarkSch = remarkSch;
    }
    
    @ValueField(column = "remark")
    public String getRemarkSch(){
        return this.remarkSch;
    }

    public void setPlaceTypeIdSch(Integer placeTypeIdSch){
        this.placeTypeIdSch = placeTypeIdSch;
    }
    
    @ValueField(column = "placeTypeId")
    public Integer getPlaceTypeIdSch(){
        return this.placeTypeIdSch;
    }

    public void setStreamIdSch(Integer streamIdSch){
        this.streamIdSch = streamIdSch;
    }
    
    @ValueField(column = "streamId")
    public Integer getStreamIdSch(){
        return this.streamIdSch;
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
    
    @ValueField(column = "videoStart")
	public String getVideoStart() {
		return videoStart;
	}

	public void setVideoStart(String videoStart) {
		this.videoStart = videoStart;
	}
	
	@ValueField(column = "videoStop")
	public String getVideoStop() {
		return videoStop;
	}

	public void setVideoStop(String videoStop) {
		this.videoStop = videoStop;
	}
	@ValueField(column = "snapShotStreamUrl")
	public String getSnapShotStreamUrlSch() {
		return snapShotStreamUrlSch;
	}

	public void setSnapShotStreamUrlSch(String snapShotStreamUrlSch) {
		this.snapShotStreamUrlSch = snapShotStreamUrlSch;
	}
	
	
    

}