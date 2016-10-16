package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScCookBookSch extends SearchEasyUI{

	//根据时间进行搜索的部分
	private Date createdStartSch;
	private Date createdEndSch;
	private Date publishDateStartSch;
	private Date publishDateEndSch;
	
	@ValueField(column = "publishDate", equal = ">")
	public Date getPublishDateStartSch() {
		return publishDateStartSch;
	}

	public void setPublishDateStartSch(Date publishDateStartSch) {
		this.publishDateStartSch = publishDateStartSch;
	}
	@ValueField(column = "publishDate", equal = "<")
	public Date getPublishDateEndSch() {
		return publishDateEndSch;
	}

	public void setPublishDateEndSch(Date publishDateEndSch) {
		this.publishDateEndSch = publishDateEndSch;
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
	
	//根据publishDate进行搜索的部分
		private Date publishDateStartLSch;
		private Date publishDateEndLSch;
		@ValueField(column = "publishDate", equal = ">=")
		public Date getPublishDateStartLSch() {
			return publishDateStartLSch;
		}
		public void setPublishDateStartLSch(Date publishDateStartLSch) {
			this.publishDateStartLSch = publishDateStartLSch;
		}
		@ValueField(column = "publishDate", equal = "<")
		public Date getPublishDateEndLSch() {
			if (publishDateEndLSch != null) {
				return DateUtil.getDateAfterDay(publishDateEndLSch, 1);
			}
			return publishDateEndLSch;
		}
		public void setPublishDateEndLSch(Date publishDateEndLSch) {
			this.publishDateEndLSch = publishDateEndLSch;
		}

    private String publishDateSch;
    private Integer foodTypeIdSch;
    private String detailSch;
    private Integer foodPicIdSch;
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

    public void setPublishDateSch(String publishDateSch){
        this.publishDateSch = publishDateSch;
    }
    
    @ValueField(column = "publishDate")
    public String getPublishDateSch(){
        return this.publishDateSch;
    }

    public void setFoodTypeIdSch(Integer foodTypeIdSch){
        this.foodTypeIdSch = foodTypeIdSch;
    }
    
    @ValueField(column = "foodTypeId")
    public Integer getFoodTypeIdSch(){
        return this.foodTypeIdSch;
    }

    public void setDetailSch(String detailSch){
        this.detailSch = detailSch;
    }
    
    @ValueField(column = "detail")
    public String getDetailSch(){
        return this.detailSch;
    }

    public void setFoodPicIdSch(Integer foodPicIdSch){
        this.foodPicIdSch = foodPicIdSch;
    }
    
    @ValueField(column = "foodPicId")
    public Integer getFoodPicIdSch(){
        return this.foodPicIdSch;
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