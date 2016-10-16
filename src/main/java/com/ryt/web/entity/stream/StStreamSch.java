package com.ryt.web.entity.stream;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class StStreamSch extends SearchEasyUI{

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


    private Integer idSch;
    private String streamIdSch;
    private String publishSecuritySch;
    private String publishKeySch;
    private String publishRtmpSch;
    private String publishRtmpUrlSch;
    private String playRtmpSch;
    private String playRtmpUrlSch;
    private String playHlsSch;
    private String playHlsUrlSch;
    private String streamStatusSch;
    private String titleSch;
    private String descriptionSch;
    private String uuidSch;
    private Integer userIdSch;
    private String addrSch;
    private Integer disabledSch;
    private String hubSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private String startTimeSch;
	private Float bytesPerSecondSch;
	private String deviceNameSch;
	private String deviceIdSch;
	private Integer agentIdSch;
	private Integer schoolIdSch;
	private Integer classIdSch;

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setStreamIdSch(String streamIdSch){
        this.streamIdSch = streamIdSch;
    }
    
    @ValueField(column = "streamId")
    public String getStreamIdSch(){
        return this.streamIdSch;
    }

    public void setPublishSecuritySch(String publishSecuritySch){
        this.publishSecuritySch = publishSecuritySch;
    }
    
    @ValueField(column = "publishSecurity")
    public String getPublishSecuritySch(){
        return this.publishSecuritySch;
    }

    public void setPublishKeySch(String publishKeySch){
        this.publishKeySch = publishKeySch;
    }
    
    @ValueField(column = "publishKey")
    public String getPublishKeySch(){
        return this.publishKeySch;
    }

    public void setPublishRtmpSch(String publishRtmpSch){
        this.publishRtmpSch = publishRtmpSch;
    }
    
    @ValueField(column = "publishRtmp")
    public String getPublishRtmpSch(){
        return this.publishRtmpSch;
    }

    public void setPublishRtmpUrlSch(String publishRtmpUrlSch){
        this.publishRtmpUrlSch = publishRtmpUrlSch;
    }
    
    @ValueField(column = "publishRtmpUrl")
    public String getPublishRtmpUrlSch(){
        return this.publishRtmpUrlSch;
    }

    public void setPlayRtmpSch(String playRtmpSch){
        this.playRtmpSch = playRtmpSch;
    }
    
    @ValueField(column = "playRtmp")
    public String getPlayRtmpSch(){
        return this.playRtmpSch;
    }

    public void setPlayRtmpUrlSch(String playRtmpUrlSch){
        this.playRtmpUrlSch = playRtmpUrlSch;
    }
    
    @ValueField(column = "playRtmpUrl")
    public String getPlayRtmpUrlSch(){
        return this.playRtmpUrlSch;
    }

    public void setPlayHlsSch(String playHlsSch){
        this.playHlsSch = playHlsSch;
    }
    
    @ValueField(column = "playHls")
    public String getPlayHlsSch(){
        return this.playHlsSch;
    }

    public void setPlayHlsUrlSch(String playHlsUrlSch){
        this.playHlsUrlSch = playHlsUrlSch;
    }
    
    @ValueField(column = "playHlsUrl")
    public String getPlayHlsUrlSch(){
        return this.playHlsUrlSch;
    }

    public void setStreamStatusSch(String streamStatusSch){
        this.streamStatusSch = streamStatusSch;
    }
    
    @ValueField(column = "streamStatus")
    public String getStreamStatusSch(){
        return this.streamStatusSch;
    }

    public void setTitleSch(String titleSch){
        this.titleSch = titleSch;
    }
    
    @LikeDoubleField(column = "title")
    public String getTitleSch(){
        return this.titleSch;
    }

    public void setDescriptionSch(String descriptionSch){
        this.descriptionSch = descriptionSch;
    }
    
    @ValueField(column = "description")
    public String getDescriptionSch(){
        return this.descriptionSch;
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

    public void setAddrSch(String addrSch){
        this.addrSch = addrSch;
    }
    
    @ValueField(column = "addr")
    public String getAddrSch(){
        return this.addrSch;
    }

    public void setDisabledSch(Integer disabledSch){
        this.disabledSch = disabledSch;
    }
    
    @ValueField(column = "disabled")
    public Integer getDisabledSch(){
        return this.disabledSch;
    }

    public void setHubSch(String hubSch){
        this.hubSch = hubSch;
    }
    
    @ValueField(column = "hub")
    public String getHubSch(){
        return this.hubSch;
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
    
    @LikeDoubleField(column = "createdTime")
    public String getCreatedTimeSch(){
        return this.createdTimeSch;
    }
    
    @ValueField(column = "startTime")
	public String getStartTimeSch() {
		return startTimeSch;
	}

	public void setStartTimeSch(String startTimeSch) {
		this.startTimeSch = startTimeSch;
	}
	
	@ValueField(column = "bytesPerSecond")
	public Float getBytesPerSecondSch() {
		return bytesPerSecondSch;
	}

	public void setBytesPerSecondSch(Float bytesPerSecondSch) {
		this.bytesPerSecondSch = bytesPerSecondSch;
	}
	
	@LikeDoubleField(column = "deviceName")
	public String getDeviceNameSch() {
		return deviceNameSch;
	}

	public void setDeviceNameSch(String deviceNameSch) {
		this.deviceNameSch = deviceNameSch;
	}
	
	@ValueField(column = "deviceId")
	public String getDeviceIdSch() {
		return deviceIdSch;
	}

	public void setDeviceIdSch(String deviceIdSch) {
		this.deviceIdSch = deviceIdSch;
	}
	
	@ValueField(column = "agentId")
	public Integer getAgentIdSch() {
		return agentIdSch;
	}

	public void setAgentIdSch(Integer agentIdSch) {
		this.agentIdSch = agentIdSch;
	}
	
	@ValueField(column = "schoolId")
	public Integer getSchoolIdSch() {
		return schoolIdSch;
	}

	public void setSchoolIdSch(Integer schoolIdSch) {
		this.schoolIdSch = schoolIdSch;
	}
	
	@ValueField(column = "classId")
	public Integer getClassIdSch() {
		return classIdSch;
	}

	public void setClassIdSch(Integer classIdSch) {
		this.classIdSch = classIdSch;
	}

    

}