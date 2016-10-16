package com.ryt.web.entity.article;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ArticleCommentSch extends SearchEasyUI{

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


    private Integer articleIdSch;
    private String subjectSch;
    private String createdIPSch;
    private String contentSch;
    private Integer sysClassIdSch;
    private Integer articleBaseIdSch;
    private Integer commentPropertySch;
    private Integer parentIdSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private Integer operaterIdSch;
    private String editedTimeSch;
    private String createdTimeSch;

    public void setArticleIdSch(Integer articleIdSch){
        this.articleIdSch = articleIdSch;
    }
    
    @ValueField(column = "articleId")
    public Integer getArticleIdSch(){
        return this.articleIdSch;
    }

    public void setSubjectSch(String subjectSch){
        this.subjectSch = subjectSch;
    }
    
    @ValueField(column = "subject")
    public String getSubjectSch(){
        return this.subjectSch;
    }

    public void setCreatedIPSch(String createdIPSch){
        this.createdIPSch = createdIPSch;
    }
    
    @ValueField(column = "createdIP")
    public String getCreatedIPSch(){
        return this.createdIPSch;
    }

    public void setContentSch(String contentSch){
        this.contentSch = contentSch;
    }
    
    @ValueField(column = "content")
    public String getContentSch(){
        return this.contentSch;
    }

    public void setSysClassIdSch(Integer sysClassIdSch){
        this.sysClassIdSch = sysClassIdSch;
    }
    
    @ValueField(column = "sysClassId")
    public Integer getSysClassIdSch(){
        return this.sysClassIdSch;
    }

    public void setArticleBaseIdSch(Integer ArticleBaseIdSch){
        this.articleBaseIdSch = ArticleBaseIdSch;
    }
    
    @ValueField(column = "ArticleBaseId")
    public Integer getArticleBaseIdSch(){
        return this.articleBaseIdSch;
    }

    public void setCommentPropertySch(Integer commentPropertySch){
        this.commentPropertySch = commentPropertySch;
    }
    
    @ValueField(column = "commentProperty")
    public Integer getCommentPropertySch(){
        return this.commentPropertySch;
    }

    public void setParentIdSch(Integer parentIdSch){
        this.parentIdSch = parentIdSch;
    }
    
    @ValueField(column = "parentId")
    public Integer getParentIdSch(){
        return this.parentIdSch;
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