package com.ryt.web.entity.sys;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class SysUploadSch extends SearchEasyUI{

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


    private String clientNameSch;
    private String serverNameSch;
    private String fileExtSch;
    private Integer fileSizeSch;
    private Integer fileWidthSch;
    private Integer fileHeightSch;
    private String moduleIdSch;
    private String funcIdSch;
    private String dataIdSch;
    private String fileFullPathSch;
    private Integer zipIdSch;
    private String proCodeSch;
    private String proFileTypeSch;
    private String createdBySch;
    private String fileTypesSch;
    private Integer customerIdSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Byte isDeletedSch;
    private Integer sortIdSch;
    private Integer operaterIidSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private String durationSch;

    public String getDurationSch() {
		return durationSch;
	}

	public void setDurationSch(String durationSch) {
		this.durationSch = durationSch;
	}

	public void setClientNameSch(String clientNameSch){
        this.clientNameSch = clientNameSch;
    }
    
    @LikeDoubleField(column = "clientName")
    public String getClientNameSch(){
        return this.clientNameSch;
    }

    public void setServerNameSch(String serverNameSch){
        this.serverNameSch = serverNameSch;
    }
    
    @ValueField(column = "serverName")
    public String getServerNameSch(){
        return this.serverNameSch;
    }

    public void setFileExtSch(String fileExtSch){
        this.fileExtSch = fileExtSch;
    }
    
    @ValueField(column = "fileExt")
    public String getFileExtSch(){
        return this.fileExtSch;
    }

    public void setFileSizeSch(Integer fileSizeSch){
        this.fileSizeSch = fileSizeSch;
    }
    
    @ValueField(column = "fileSize")
    public Integer getFileSizeSch(){
        return this.fileSizeSch;
    }

    public void setFileWidthSch(Integer fileWidthSch){
        this.fileWidthSch = fileWidthSch;
    }
    
    @ValueField(column = "fileWidth")
    public Integer getFileWidthSch(){
        return this.fileWidthSch;
    }

    public void setFileHeightSch(Integer fileHeightSch){
        this.fileHeightSch = fileHeightSch;
    }
    
    @ValueField(column = "fileHeight")
    public Integer getFileHeightSch(){
        return this.fileHeightSch;
    }

    public void setModuleIdSch(String moduleIdSch){
        this.moduleIdSch = moduleIdSch;
    }
    
    @ValueField(column = "moduleId")
    public String getModuleIdSch(){
        return this.moduleIdSch;
    }

    public void setFuncIdSch(String funcIdSch){
        this.funcIdSch = funcIdSch;
    }
    
    @ValueField(column = "funcId")
    public String getFuncIdSch(){
        return this.funcIdSch;
    }

    public void setDataIdSch(String dataIdSch){
        this.dataIdSch = dataIdSch;
    }
    
    @ValueField(column = "dataId")
    public String getDataIdSch(){
        return this.dataIdSch;
    }

    public void setFileFullPathSch(String fileFullPathSch){
        this.fileFullPathSch = fileFullPathSch;
    }
    
    @ValueField(column = "fileFullPath")
    public String getFileFullPathSch(){
        return this.fileFullPathSch;
    }

    public void setZipIdSch(Integer zipIdSch){
        this.zipIdSch = zipIdSch;
    }
    
    @ValueField(column = "zipId")
    public Integer getZipIdSch(){
        return this.zipIdSch;
    }

    public void setProCodeSch(String proCodeSch){
        this.proCodeSch = proCodeSch;
    }
    
    @ValueField(column = "proCode")
    public String getProCodeSch(){
        return this.proCodeSch;
    }

    public void setProFileTypeSch(String proFileTypeSch){
        this.proFileTypeSch = proFileTypeSch;
    }
    
    @ValueField(column = "proFileType")
    public String getProFileTypeSch(){
        return this.proFileTypeSch;
    }

    public void setCreatedBySch(String createdBySch){
        this.createdBySch = createdBySch;
    }
    
    @ValueField(column = "createdBy")
    public String getCreatedBySch(){
        return this.createdBySch;
    }

    public void setFileTypesSch(String fileTypesSch){
        this.fileTypesSch = fileTypesSch;
    }
    
    @ValueField(column = "fileTypes")
    public String getFileTypesSch(){
        return this.fileTypesSch;
    }

    public void setCustomerIdSch(Integer customerIdSch){
        this.customerIdSch = customerIdSch;
    }
    
    @ValueField(column = "customerId")
    public Integer getCustomerIdSch(){
        return this.customerIdSch;
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

    public void setSortIdSch(Integer sortIdSch){
        this.sortIdSch = sortIdSch;
    }
    
    @ValueField(column = "sortId")
    public Integer getSortIdSch(){
        return this.sortIdSch;
    }

    public void setOperaterIidSch(Integer operaterIidSch){
        this.operaterIidSch = operaterIidSch;
    }
    
    @ValueField(column = "operaterIid")
    public Integer getOperaterIidSch(){
        return this.operaterIidSch;
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