/*     */ package com.ryt.web.entity.finance;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.durcframework.core.expression.annotation.ValueField;
/*     */ import org.durcframework.core.support.SearchEasyUI;
/*     */ import org.durcframework.core.util.DateUtil;
/*     */ 
/*     */ public class FinanceDistributionManageSch extends SearchEasyUI
/*     */ {
/*     */   private Date createdStartSch;
/*     */   private Date createdEndSch;
/*     */   private Integer idSch;
/*     */   private Integer agentIdSch;
/*     */   private Integer levelIdSch;
/*     */   private Integer amountSch;
/*     */   private Integer incomeSch;
/*     */   private Integer withdrawAmtSch;
/*     */   private Integer saleTypeSch;
/*     */   private String flagSch;
/*     */   private String nameSch;
/*     */   private String cardNoSch;
/*     */   private String identityCardNoSch;
/*     */   private String phoneSch;
/*     */   private String editedTimeSch;
/*     */   private String createdTimeSch;
/*     */ 
/*     */   @ValueField(column="createdTime", equal=">=")
/*     */   public Date getCreatedStartSch()
/*     */   {
/*  18 */     return this.createdStartSch;
/*     */   }
/*     */ 
/*     */   public void setCreatedStartSch(Date createdStartSch) {
/*  22 */     this.createdStartSch = createdStartSch;
/*     */   }
/*     */ 
/*     */   @ValueField(column="createdTime", equal="<")
/*     */   public Date getCreatedEndSch() {
/*  27 */     if (this.createdEndSch != null) {
/*  28 */       return DateUtil.getDateAfterDay(this.createdEndSch, 1);
/*     */     }
/*  30 */     return this.createdEndSch;
/*     */   }
/*     */ 
/*     */   public void setCreatedEndSch(Date createdEndSch) {
/*  34 */     this.createdEndSch = createdEndSch;
/*     */   }
/*     */ 
/*     */   @ValueField(column="id")
/*     */   public Integer getIdSch()
/*     */   {
/*  55 */     return this.idSch;
/*     */   }
/*     */ 
/*     */   public void setIdSch(Integer idSch) {
/*  59 */     this.idSch = idSch;
/*     */   }
/*     */   @ValueField(column="agentId")
/*     */   public Integer getAgentIdSch() {
/*  63 */     return this.agentIdSch;
/*     */   }
/*     */ 
/*     */   public void setAgentIdSch(Integer agentIdSch) {
/*  67 */     this.agentIdSch = agentIdSch;
/*     */   }
/*     */   @ValueField(column="levelId")
/*     */   public Integer getLevelIdSch() {
/*  71 */     return this.levelIdSch;
/*     */   }
/*     */ 
/*     */   public void setLevelIdSch(Integer levelIdSch) {
/*  75 */     this.levelIdSch = levelIdSch;
/*     */   }
/*     */   @ValueField(column="amount")
/*     */   public Integer getAmountSch() {
/*  79 */     return this.amountSch;
/*     */   }
/*     */ 
/*     */   public void setAmountSch(Integer amountSch) {
/*  83 */     this.amountSch = amountSch;
/*     */   }
/*     */   @ValueField(column="income")
/*     */   public Integer getIncomeSch() {
/*  87 */     return this.incomeSch;
/*     */   }
/*     */ 
/*     */   public void setIncomeSch(Integer incomeSch) {
/*  91 */     this.incomeSch = incomeSch;
/*     */   }
/*     */   @ValueField(column="withdrawAmt")
/*     */   public Integer getWithdrawAmtSch() {
/*  95 */     return this.withdrawAmtSch;
/*     */   }
/*     */ 
/*     */   public void setWithdrawAmtSch(Integer withdrawAmtSch) {
/*  99 */     this.withdrawAmtSch = withdrawAmtSch;
/*     */   }
/*     */   @ValueField(column="saleType")
/*     */   public Integer getSaleTypeSch() {
/* 103 */     return this.saleTypeSch;
/*     */   }
/*     */ 
/*     */   public void setSaleTypeSch(Integer saleTypeSch) {
/* 107 */     this.saleTypeSch = saleTypeSch;
/*     */   }
/*     */   @ValueField(column="flag")
/*     */   public String getFlagSch() {
/* 111 */     return this.flagSch;
/*     */   }
/*     */ 
/*     */   public void setFlagSch(String flagSch) {
/* 115 */     this.flagSch = flagSch;
/*     */   }
/*     */   @ValueField(column="name")
/*     */   public String getNameSch() {
/* 119 */     return this.nameSch;
/*     */   }
/*     */ 
/*     */   public void setNameSch(String nameSch) {
/* 123 */     this.nameSch = nameSch;
/*     */   }
/*     */   @ValueField(column="cardNo")
/*     */   public String getCardNoSch() {
/* 127 */     return this.cardNoSch;
/*     */   }
/*     */ 
/*     */   public void setCardNoSch(String cardNoSch) {
/* 131 */     this.cardNoSch = cardNoSch;
/*     */   }
/*     */   @ValueField(column="identityCardNo")
/*     */   public String getIdentityCardNoSch() {
/* 135 */     return this.identityCardNoSch;
/*     */   }
/*     */ 
/*     */   public void setIdentityCardNoSch(String identityCardNoSch) {
/* 139 */     this.identityCardNoSch = identityCardNoSch;
/*     */   }
/*     */   @ValueField(column="phone")
/*     */   public String getPhoneSch() {
/* 143 */     return this.phoneSch;
/*     */   }
/*     */ 
/*     */   public void setPhoneSch(String phoneSch) {
/* 147 */     this.phoneSch = phoneSch;
/*     */   }
/*     */   @ValueField(column="editedTime")
/*     */   public String getEditedTimeSch() {
/* 151 */     return this.editedTimeSch;
/*     */   }
/*     */ 
/*     */   public void setEditedTimeSch(String editedTimeSch) {
/* 155 */     this.editedTimeSch = editedTimeSch;
/*     */   }
/*     */   @ValueField(column="createdTime")
/*     */   public String getCreatedTimeSch() {
/* 159 */     return this.createdTimeSch;
/*     */   }
/*     */ 
/*     */   public void setCreatedTimeSch(String createdTimeSch) {
/* 163 */     this.createdTimeSch = createdTimeSch;
/*     */   }
/*     */ }

/* Location:           D:\distribution\微信上线版本\RuyTonWeb\WEB-INF\classes\
 * Qualified Name:     com.ryt.web.entity.finance.FinanceDistributionManageSch
 * JD-Core Version:    0.6.2
 */