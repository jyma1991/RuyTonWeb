package com.ryt.web.entity.finance;

import java.math.BigDecimal;
import java.util.Date;

import javax.management.loading.PrivateClassLoader;

import com.ryt.web.entity.user.User;


public class FinanceChargeOrderBak {
	private Integer productId;
	private Integer orderType;
	private Integer quantity;
	private String buyerEmail;
	private BigDecimal totalFee;
	private String chargeAccountId;
	private Integer payStatus;
	private Integer payType;
	private String payTime;
	private Integer confirmUserId;
	private String confirmTime;
	private Integer studentId;
	private Integer classId;
	private Integer schoolId;
	private Integer agentId;
	private Integer id;
	private String uuid;
	private Integer userId;
	private String userName;
	private Integer sortId;
	private Byte isDeleted;
	private Integer operaterId;
	private String editedTime;
	private String createdTime;
	//产品
	private FinanceChargeProduct product;
	//学生
	private User student;
	//审核人
	private User comfirmUser;

	public void setProductId(Integer productId){
		this.productId = productId;
	}

	public Integer getProductId(){
		return this.productId;
	}

	public void setOrderType(Integer orderType){
		this.orderType = orderType;
	}

	public Integer getOrderType(){
		return this.orderType;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public Integer getQuantity(){
		return this.quantity;
	}

	public void setBuyerEmail(String buyerEmail){
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerEmail(){
		return this.buyerEmail;
	}

	public void setTotalFee(BigDecimal totalFee){
		this.totalFee = totalFee;
	}

	public BigDecimal getTotalFee(){
		return this.totalFee;
	}

	public void setChargeAccountId(String chargeAccountId){
		this.chargeAccountId = chargeAccountId;
	}

	public String getChargeAccountId(){
		return this.chargeAccountId;
	}

	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}

	public Integer getPayStatus(){
		return this.payStatus;
	}

	public void setPayType(Integer payType){
		this.payType = payType;
	}

	public Integer getPayType(){
		return this.payType;
	}

	public void setPayTime(String payTime){
		this.payTime = payTime;
	}

	public String getPayTime(){
		return this.payTime;
	}

	public void setConfirmUserId(Integer confirmUserId){
		this.confirmUserId = confirmUserId;
	}

	public Integer getConfirmUserId(){
		return this.confirmUserId;
	}

	public void setConfirmTime(String confirmTime){
		this.confirmTime = confirmTime;
	}

	public String getConfirmTime(){
		return this.confirmTime;
	}

	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	public Integer getStudentId(){
		return this.studentId;
	}

	public void setClassId(Integer classId){
		this.classId = classId;
	}

	public Integer getClassId(){
		return this.classId;
	}

	public void setSchoolId(Integer schoolId){
		this.schoolId = schoolId;
	}

	public Integer getSchoolId(){
		return this.schoolId;
	}

	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}

	public Integer getAgentId(){
		return this.agentId;
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

	public FinanceChargeProduct getProduct() {
		return product;
	}

	public void setProduct(FinanceChargeProduct product) {
		this.product = product;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getComfirmUser() {
		return comfirmUser;
	}

	public void setComfirmUser(User comfirmUser) {
		this.comfirmUser = comfirmUser;
	}

	
}