package com.ryt.web.entity.version;

import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class SysVersionSch extends SearchEasyUI{

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


    private String versionNameSch;
    private String editedTimeSch;
    private String createdTimeSch;
    private String contentSch;
    private String loadUrlSch;
    private Byte mustSch;
    private String versionIdSch;
    private Integer idSch;

    public void setVersionNameSch(String versionNameSch){
        this.versionNameSch = versionNameSch;
    }
    
    @ValueField(column = "versionName")
    public String getVersionNameSch(){
        return this.versionNameSch;
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

    public void setContentSch(String contentSch){
        this.contentSch = contentSch;
    }
    
    @ValueField(column = "content")
    public String getContentSch(){
        return this.contentSch;
    }

    public void setLoadUrlSch(String loadUrlSch){
        this.loadUrlSch = loadUrlSch;
    }
    
    @ValueField(column = "loadUrl")
    public String getLoadUrlSch(){
        return this.loadUrlSch;
    }

    public void setMustSch(Byte mustSch){
        this.mustSch = mustSch;
    }
    
    @ValueField(column = "must")
    public Byte getMustSch(){
        return this.mustSch;
    }

    public void setVersionIdSch(String versionIdSch){
        this.versionIdSch = versionIdSch;
    }
    
    @LikeDoubleField(column = "versionId")
    public String getVersionIdSch(){
        return this.versionIdSch;
    }

    public void setIdSch(Integer idSch){
        this.idSch = idSch;
    }
    
    @ValueField(column = "id")
    public Integer getIdSch(){
        return this.idSch;
    }


}