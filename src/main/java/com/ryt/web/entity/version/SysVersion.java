package com.ryt.web.entity.version;

import java.util.Date;

public class SysVersion {
	private String versionName;
	private String editedTime;
	private String createdTime;
	private String content;
	private String loadUrl;
	private Byte must;
	private String versionId;
	private Integer id;

	public void setVersionName(String versionName){
		this.versionName = versionName;
	}

	public String getVersionName(){
		return this.versionName;
	}

	public void setEditedTime(String editedTime){
		this.editedTime = editedTime;
	}

	public String getEditedTime(){
		return this.editedTime;
	}

	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}

	public String getCreatedTime(){
		return this.createdTime;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setLoadUrl(String loadUrl){
		this.loadUrl = loadUrl;
	}

	public String getLoadUrl(){
		return this.loadUrl;
	}

	public void setMust(Byte must){
		this.must = must;
	}

	public Byte getMust(){
		return this.must;
	}

	public void setVersionId(String versionId){
		this.versionId = versionId;
	}

	public String getVersionId(){
		return this.versionId;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

}