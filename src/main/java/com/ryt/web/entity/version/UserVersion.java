package com.ryt.web.entity.version;

import java.util.Date;

public class UserVersion {
	private String createTime;
	private Integer userId;
	private Integer versionId;
	private Integer id;

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setVersionId(Integer versionId){
		this.versionId = versionId;
	}

	public Integer getVersionId(){
		return this.versionId;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

}