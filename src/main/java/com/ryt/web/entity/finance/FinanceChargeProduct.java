package com.ryt.web.entity.finance;

import java.math.BigDecimal;

public class FinanceChargeProduct {
	private String productName;
	private String picIds;
	private Integer productType;
	private String remark;
	private BigDecimal originalPrice;
	private BigDecimal presentPrice;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return this.productName;
	}

	public void setPicIds(String picIds){
		this.picIds = picIds;
	}

	public String getPicIds(){
		return this.picIds;
	}

	public void setProductType(Integer productType){
		this.productType = productType;
	}

	public Integer getProductType(){
		return this.productType;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setOriginalPrice(BigDecimal originalPrice){
		this.originalPrice = originalPrice;
	}

	public BigDecimal getOriginalPrice(){
		return this.originalPrice;
	}

	public void setPresentPrice(BigDecimal presentPrice){
		this.presentPrice = presentPrice;
	}

	public BigDecimal getPresentPrice(){
		return this.presentPrice;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
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

}