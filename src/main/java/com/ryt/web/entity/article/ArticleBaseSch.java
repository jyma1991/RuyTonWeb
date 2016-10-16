package com.ryt.web.entity.article;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ArticleBaseSch extends SearchEasyUI{

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


    private String subjectSch;
    private String picIdSch;
    private String authorSch;
    private String sourceSch;
    private String briefSch;
    private String keyWordsSch;
    private String contentSch;
    private Integer hitsSch;
    private Integer parentIdSch;
    private Integer replyArticleBaseIdSch;//2016.1.29新增 by wyp
    private Integer articlePropertySch;
    private Integer articleReceiverSch;
    private Integer sysClassIdSch;
    
    private Integer agentIdSch;
	private Integer schoolIdSch;
	private Integer classIdSch;
	private Integer teacherIdSch;
	private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;
    
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

	public Integer getClassIdSch() {
		return classIdSch;
	}

	public void setClassIdSch(Integer classIdSch) {
		this.classIdSch = classIdSch;
	}

	public Integer getTeacherIdSch() {
		return teacherIdSch;
	}

	public void setTeacherIdSch(Integer teacherIdSch) {
		this.teacherIdSch = teacherIdSch;
	}

    public void setSubjectSch(String subjectSch){
        this.subjectSch = subjectSch;
    }
    
    @LikeDoubleField(column = "subject")
    public String getSubjectSch(){
        return this.subjectSch;
    }

    public void setPicIdSch(String picIdSch){
        this.picIdSch = picIdSch;
    }
    
    @ValueField(column = "picId")
    public String getPicIdSch(){
        return this.picIdSch;
    }

    public void setAuthorSch(String authorSch){
        this.authorSch = authorSch;
    }
    
    @ValueField(column = "author")
    public String getAuthorSch(){
        return this.authorSch;
    }

    public void setSourceSch(String sourceSch){
        this.sourceSch = sourceSch;
    }
    
    @ValueField(column = "source")
    public String getSourceSch(){
        return this.sourceSch;
    }

    public void setBriefSch(String briefSch){
        this.briefSch = briefSch;
    }
    
    @ValueField(column = "brief")
    public String getBriefSch(){
        return this.briefSch;
    }

    public void setKeyWordsSch(String keyWordsSch){
        this.keyWordsSch = keyWordsSch;
    }
    
    @LikeDoubleField(column = "keyWords")
    public String getKeyWordsSch(){
        return this.keyWordsSch;
    }

    public void setContentSch(String contentSch){
        this.contentSch = contentSch;
    }
    
    @LikeDoubleField(column = "content")
    public String getContentSch(){
        return this.contentSch;
    }

    public void setHitsSch(Integer hitsSch){
        this.hitsSch = hitsSch;
    }
    
    @ValueField(column = "hits")
    public Integer getHitsSch(){
        return this.hitsSch;
    }

	public void setReplyArticleBaseIdSch(Integer replyArticleBaseIdSch) {
		this.replyArticleBaseIdSch = replyArticleBaseIdSch;
	}
    
	@ValueField(column = "replyArticleBaseId")
    public Integer getReplyArticleBaseIdSch() {
		return replyArticleBaseIdSch;
	}


	public void setParentIdSch(Integer parentIdSch){
        this.parentIdSch = parentIdSch;
    }
    
    @ValueField(column = "parentId")
    public Integer getParentIdSch(){
        return this.parentIdSch;
    }

    public void setArticlePropertySch(Integer articlePropertySch){
        this.articlePropertySch = articlePropertySch;
    }
    
    @ValueField(column = "articleProperty")
    public Integer getArticlePropertySch(){
        return this.articlePropertySch;
    }

    public void setArticleReceiverSch(Integer articleReceiverSch){
        this.articleReceiverSch = articleReceiverSch;
    }
    
    @ValueField(column = "articleReceiver")
    public Integer getArticleReceiverSch(){
        return this.articleReceiverSch;
    }

    public void setSysClassIdSch(Integer sysClassIdSch){
        this.sysClassIdSch = sysClassIdSch;
    }
    
    @ValueField(column = "sysClassId")
    public Integer getSysClassIdSch(){
        return this.sysClassIdSch;
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