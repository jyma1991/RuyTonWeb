package com.ryt.web.entity.finance;

import java.util.Date;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchEasyUI;
import org.durcframework.core.util.DateUtil;

public class FinanceIncomeAmountSch extends SearchEasyUI
{
  private Date createdStartSch;
  private Date createdEndSch;
  private Integer agentIdSch;
  private Integer recDisAmtSch;
  private Integer recDisAmtInSch;
  private Integer serviceChComAmtSch;
  private Integer serviceChComAmtInSch;
  private Integer serviceChDisAmtSch;
  private Integer serviceChDisAmtInSch;
  private Integer marketDisAmtSch;
  private Integer marketDisAmtInSch;
  private Integer totalAmtSch;
  private Integer idSch;
  private String uuidSch;
  private Integer userIdSch;
  private String userNameSch;
  private String operaterIdSch;
  private Integer sortIdSch;
  private Byte isDeletedSch;
  private String editedTimeSch;
  private String createdTimeSch;

  @ValueField(column="createdTime", equal=">=")
  public Date getCreatedStartSch()
  {
    return this.createdStartSch;
  }

  public void setCreatedStartSch(Date createdStartSch) {
    this.createdStartSch = createdStartSch;
  }

  @ValueField(column="createdTime", equal="<")
  public Date getCreatedEndSch() {
    if (this.createdEndSch != null) {
      return DateUtil.getDateAfterDay(this.createdEndSch, 1);
    }
    return this.createdEndSch;
  }

  public void setCreatedEndSch(Date createdEndSch) {
    this.createdEndSch = createdEndSch;
  }

  public void setAgentIdSch(Integer agentIdSch)
  {
    this.agentIdSch = agentIdSch;
  }

  @ValueField(column="agentId")
  public Integer getAgentIdSch() {
    return this.agentIdSch;
  }

  public void setRecDisAmtSch(Integer recDisAmtSch) {
    this.recDisAmtSch = recDisAmtSch;
  }

  @ValueField(column="recDisAmt")
  public Integer getRecDisAmtSch() {
    return this.recDisAmtSch;
  }

  public void setRecDisAmtInSch(Integer recDisAmtInSch) {
    this.recDisAmtInSch = recDisAmtInSch;
  }

  @ValueField(column="recDisAmtIn")
  public Integer getRecDisAmtInSch() {
    return this.recDisAmtInSch;
  }

  public void setServiceChComAmtSch(Integer serviceChComAmtSch) {
    this.serviceChComAmtSch = serviceChComAmtSch;
  }

  @ValueField(column="serviceChComAmt")
  public Integer getServiceChComAmtSch() {
    return this.serviceChComAmtSch;
  }

  public void setServiceChComAmtInSch(Integer serviceChComAmtInSch) {
    this.serviceChComAmtInSch = serviceChComAmtInSch;
  }

  @ValueField(column="serviceChComAmtIn")
  public Integer getServiceChComAmtInSch() {
    return this.serviceChComAmtInSch;
  }

  public void setServiceChDisAmtSch(Integer serviceChDisAmtSch) {
    this.serviceChDisAmtSch = serviceChDisAmtSch;
  }

  @ValueField(column="serviceChDisAmt")
  public Integer getServiceChDisAmtSch() {
    return this.serviceChDisAmtSch;
  }

  public void setServiceChDisAmtInSch(Integer serviceChDisAmtInSch) {
    this.serviceChDisAmtInSch = serviceChDisAmtInSch;
  }

  @ValueField(column="serviceChDisAmtIn")
  public Integer getServiceChDisAmtInSch() {
    return this.serviceChDisAmtInSch;
  }

  public void setMarketDisAmtSch(Integer marketDisAmtSch) {
    this.marketDisAmtSch = marketDisAmtSch;
  }

  @ValueField(column="marketDisAmt")
  public Integer getMarketDisAmtSch() {
    return this.marketDisAmtSch;
  }

  public void setMarketDisAmtInSch(Integer marketDisAmtInSch) {
    this.marketDisAmtInSch = marketDisAmtInSch;
  }

  @ValueField(column="marketDisAmtIn")
  public Integer getMarketDisAmtInSch() {
    return this.marketDisAmtInSch;
  }

  public void setTotalAmtSch(Integer totalAmtSch) {
    this.totalAmtSch = totalAmtSch;
  }

  @ValueField(column="totalAmt")
  public Integer getTotalAmtSch() {
    return this.totalAmtSch;
  }

  public void setIdSch(Integer idSch) {
    this.idSch = idSch;
  }

  @ValueField(column="id")
  public Integer getIdSch() {
    return this.idSch;
  }

  public void setUuidSch(String uuidSch) {
    this.uuidSch = uuidSch;
  }

  @ValueField(column="uuid")
  public String getUuidSch() {
    return this.uuidSch;
  }

  public void setUserIdSch(Integer userIdSch) {
    this.userIdSch = userIdSch;
  }

  @ValueField(column="userId")
  public Integer getUserIdSch() {
    return this.userIdSch;
  }

  public void setUserNameSch(String userNameSch) {
    this.userNameSch = userNameSch;
  }

  @ValueField(column="userName")
  public String getUserNameSch() {
    return this.userNameSch;
  }

  public void setOperaterIdSch(String operaterIdSch) {
    this.operaterIdSch = operaterIdSch;
  }

  @ValueField(column="operaterId")
  public String getOperaterIdSch() {
    return this.operaterIdSch;
  }

  public void setSortIdSch(Integer sortIdSch) {
    this.sortIdSch = sortIdSch;
  }

  @ValueField(column="sortId")
  public Integer getSortIdSch() {
    return this.sortIdSch;
  }

  public void setIsDeletedSch(Byte isDeletedSch) {
    this.isDeletedSch = isDeletedSch;
  }

  @ValueField(column="isDeleted")
  public Byte getIsDeletedSch() {
    return this.isDeletedSch;
  }

  public void setEditedTimeSch(String editedTimeSch) {
    this.editedTimeSch = editedTimeSch;
  }

  @ValueField(column="editedTime")
  public String getEditedTimeSch() {
    return this.editedTimeSch;
  }

  public void setCreatedTimeSch(String createdTimeSch) {
    this.createdTimeSch = createdTimeSch;
  }

  @ValueField(column="createdTime")
  public String getCreatedTimeSch() {
    return this.createdTimeSch;
  }
}

/* Location:           D:\distribution\微信上线版本\RuyTonWeb\WEB-INF\classes\
 * Qualified Name:     com.ryt.web.entity.finance.FinanceIncomeAmountSch
 * JD-Core Version:    0.6.2
 */