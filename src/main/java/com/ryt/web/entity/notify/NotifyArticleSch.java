package com.ryt.web.entity.notify;

import java.util.Date;
import java.util.List;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class NotifyArticleSch extends SearchEasyUI{

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


    private Integer parentIdSch;
    private Integer senderIdSch;
    private Integer schoolIdSch;
    private Integer classIdSch;
    private Integer articleIdSch;
    private Integer idSch;
    private String uuidSch;
    private Integer userIdSch;
    private String userNameSch;
    private String operaterIdSch;
    private Integer sortIdSch;
    private Byte isDeletedSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private Integer notifyTypeSch;
    private List<Integer> classIdsSch;
    private Integer firstResult = 0;

    public void setParentIdSch(Integer parentIdSch){
        this.parentIdSch = parentIdSch;
    }
    
    @ValueField(column = "parentId")
    public Integer getParentIdSch(){
        return this.parentIdSch;
    }

    public void setSenderIdSch(Integer senderIdSch){
        this.senderIdSch = senderIdSch;
    }
    
    @ValueField(column = "senderId")
    public Integer getSenderIdSch(){
        return this.senderIdSch;
    }


    
    @ValueField(column = "schoolId")
    public Integer getSchoolIdSch() {
		return schoolIdSch;
	}

	public void setSchoolIdSch(Integer schoolIdSch) {
		this.schoolIdSch = schoolIdSch;
	}

	public void setClassIdSch(Integer classIdSch){
        this.classIdSch = classIdSch;
    }
    
    @ValueField(column = "classId")
    public Integer getClassIdSch(){
        return this.classIdSch;
    }

    public void setArticleIdSch(Integer articleIdSch){
        this.articleIdSch = articleIdSch;
    }
    
    @ValueField(column = "articleId")
    public Integer getArticleIdSch(){
        return this.articleIdSch;
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

    public void setOperaterIdSch(String operaterIdSch){
        this.operaterIdSch = operaterIdSch;
    }
    
    @ValueField(column = "operaterId")
    public String getOperaterIdSch(){
        return this.operaterIdSch;
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

    public void setNotifyTypeSch(Integer notifyTypeSch){
        this.notifyTypeSch = notifyTypeSch;
    }
    
    @ValueField(column = "notifyType")
    public Integer getNotifyTypeSch(){
        return this.notifyTypeSch;
    }

	public List<Integer> getClassIdsSch() {
		return classIdsSch;
	}

	public void setClassIdsSch(List<Integer> classIdsSch) {
		this.classIdsSch = classIdsSch;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	


    
    

}