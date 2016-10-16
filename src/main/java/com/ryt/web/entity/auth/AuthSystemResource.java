package com.ryt.web.entity.auth;

import java.util.Date;
import java.util.List;

import com.ryt.web.common.TreeAware;

public class AuthSystemResource implements TreeAware{
	private Integer parentId;
	private String sysResName;
	private Integer dataTypeResource;
	private String url;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;

    private List<AuthSystemResource> children;
    private List<AuthSystemFunction> aSystemFunctions;
	
    /**
     * 暂不清楚
     * @return
     */
    /*
    @JsonProperty("parentId")
    public int getEasyUIParentId() {
        return parentId;
    }
    */
    /*
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        return this.sysResourceId == ((AuthSystemResource) obj).sysResourceId;
	}
	*/

	public String getText() {
		return this.sysResName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public void setSysResName(String sysResName){
		this.sysResName = sysResName;
	}

	public String getSysResName(){
		return this.sysResName;
	}

	public void setDataTypeResource(Integer dataTypeResource){
		this.dataTypeResource = dataTypeResource;
	}

	public Integer getDataTypeResource(){
		return this.dataTypeResource;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setSortId(Integer sortId){
		this.sortId = sortId;
	}

	public Integer getSortId(){
		return this.sortId;
	}

	public void setIsDeleted(Byte isDeleted){
		this.isDeleted = isDeleted;
	}

	public Byte getIsDeleted(){
		return this.isDeleted;
	}

	public void setOperaterId(Integer operaterId){
		this.operaterId = operaterId;
	}

	public Integer getOperaterId(){
		return this.operaterId;
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

	public List<AuthSystemResource> getChildren() {
		return children;
	}

	public void setChildren(List<AuthSystemResource> children) {
		this.children = children;
	}

	public List<AuthSystemFunction> getaSystemFunctions() {
		return aSystemFunctions;
	}

	public void setaSystemFunctions(List<AuthSystemFunction> aSystemFunctions) {
		this.aSystemFunctions = aSystemFunctions;
	}
	
	

}