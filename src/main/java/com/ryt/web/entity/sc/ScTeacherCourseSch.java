package com.ryt.web.entity.sc;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class ScTeacherCourseSch extends SearchEasyUI{

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
    private Integer teacherIdSch;
    private Integer courseIdSch;
    private Integer isDeletedSch;
    private Integer operateIdSch;
    private Date editedTimeSch;
    private Date createdTimeSch;

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }

    public void setTeacherIdSch(Integer teacherIdSch){
        this.teacherIdSch = teacherIdSch;
    }
    
    @ValueField(column = "teacherId")
    public Integer getTeacherIdSch(){
        return this.teacherIdSch;
    }

    public void setCourseIdSch(Integer courseIdSch){
        this.courseIdSch = courseIdSch;
    }
    
    @ValueField(column = "courseId")
    public Integer getCourseIdSch(){
        return this.courseIdSch;
    }

    public void setIsDeletedSch(Integer isDeletedSch){
        this.isDeletedSch = isDeletedSch;
    }
    
    @ValueField(column = "isDeleted")
    public Integer getIsDeletedSch(){
        return this.isDeletedSch;
    }

    public void setOperateIdSch(Integer operateIdSch){
        this.operateIdSch = operateIdSch;
    }
    
    @ValueField(column = "operateId")
    public Integer getOperateIdSch(){
        return this.operateIdSch;
    }

    public void setEditedTimeSch(Date editedTimeSch){
        this.editedTimeSch = editedTimeSch;
    }
    
    @ValueField(column = "editedTime")
    public Date getEditedTimeSch(){
        return this.editedTimeSch;
    }

    public void setCreatedTimeSch(Date createdTimeSch){
        this.createdTimeSch = createdTimeSch;
    }
    
    @ValueField(column = "createdTime")
    public Date getCreatedTimeSch(){
        return this.createdTimeSch;
    }


}