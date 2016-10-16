package com.ryt.web.entity.finance;

import java.math.BigDecimal;
import java.util.Date;

import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;


public class FinanceChargeOrderSch extends SearchEasyUI{

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


    private Integer productIdSch;
    private Integer orderTypeSch;
    private Integer quantitySch;
    private String buyerEmailSch;
    private BigDecimal totalFeeSch;
    private String chargeAccountIdSch;
    private Integer payStatusSch;
    private Integer payTypeSch;
    private String payTimeSch;
    private Integer confirmUserIdSch;
    private String confirmTimeSch;
    private Integer studentIdSch;
    private Integer classIdSch;
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

    public void setProductIdSch(Integer productIdSch){
        this.productIdSch = productIdSch;
    }
    
    @ValueField(column = "productId")
    public Integer getProductIdSch(){
        return this.productIdSch;
    }

    public void setOrderTypeSch(Integer orderTypeSch){
        this.orderTypeSch = orderTypeSch;
    }
    
    @ValueField(column = "orderType")
    public Integer getOrderTypeSch(){
        return this.orderTypeSch;
    }

    public void setQuantitySch(Integer quantitySch){
        this.quantitySch = quantitySch;
    }
    
    @ValueField(column = "quantity")
    public Integer getQuantitySch(){
        return this.quantitySch;
    }

    public void setBuyerEmailSch(String buyerEmailSch){
        this.buyerEmailSch = buyerEmailSch;
    }
    
    @ValueField(column = "buyerEmail")
    public String getBuyerEmailSch(){
        return this.buyerEmailSch;
    }

    public void setTotalFeeSch(BigDecimal totalFeeSch){
        this.totalFeeSch = totalFeeSch;
    }
    
    @ValueField(column = "totalFee")
    public BigDecimal getTotalFeeSch(){
        return this.totalFeeSch;
    }

    public void setChargeAccountIdSch(String chargeAccountIdSch){
        this.chargeAccountIdSch = chargeAccountIdSch;
    }
    
    @LikeDoubleField(column = "chargeAccountId")
    public String getChargeAccountIdSch(){
        return this.chargeAccountIdSch;
    }

    public void setPayStatusSch(Integer payStatusSch){
        this.payStatusSch = payStatusSch;
    }
    
    @ValueField(column = "payStatus")
    public Integer getPayStatusSch(){
        return this.payStatusSch;
    }

    public void setPayTypeSch(Integer payTypeSch){
        this.payTypeSch = payTypeSch;
    }
    
    @ValueField(column = "payType")
    public Integer getPayTypeSch(){
        return this.payTypeSch;
    }

    public void setPayTimeSch(String payTimeSch){
        this.payTimeSch = payTimeSch;
    }
    
    @ValueField(column = "payTime")
    public String getPayTimeSch(){
        return this.payTimeSch;
    }

    public void setConfirmUserIdSch(Integer confirmUserIdSch){
        this.confirmUserIdSch = confirmUserIdSch;
    }
    
    @ValueField(column = "confirmUserId")
    public Integer getConfirmUserIdSch(){
        return this.confirmUserIdSch;
    }

    public void setConfirmTimeSch(String confirmTimeSch){
        this.confirmTimeSch = confirmTimeSch;
    }
    
    @ValueField(column = "confirmTime")
    public String getConfirmTimeSch(){
        return this.confirmTimeSch;
    }

    public void setStudentIdSch(Integer studentIdSch){
        this.studentIdSch = studentIdSch;
    }
    
    @ValueField(column = "studentId")
    public Integer getStudentIdSch(){
        return this.studentIdSch;
    }

    public void setClassIdSch(Integer classIdSch){
        this.classIdSch = classIdSch;
    }
    
    @ValueField(column = "classId")
    public Integer getClassIdSch(){
        return this.classIdSch;
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