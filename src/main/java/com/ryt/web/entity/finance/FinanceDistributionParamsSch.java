/*    */ package com.ryt.web.entity.finance;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.durcframework.core.expression.annotation.ValueField;
/*    */ import org.durcframework.core.support.SearchEasyUI;
/*    */ import org.durcframework.core.util.DateUtil;
/*    */ 
/*    */ public class FinanceDistributionParamsSch extends SearchEasyUI
/*    */ {
/*    */   private Date createdStartSch;
/*    */   private Date createdEndSch;
/*    */   private Integer idSch;
/*    */   private Integer levelIdSch;
/*    */   private Integer saleTypeSch;
/*    */   private Integer rateSch;
/*    */   private String editedTimeSch;
/*    */   private String createdTimeSch;
/*    */   private Integer schoolNumSch;
/*    */ 
/*    */   @ValueField(column="createdTime", equal=">=")
/*    */   public Date getCreatedStartSch()
/*    */   {
/* 18 */     return this.createdStartSch;
/*    */   }
/*    */ 
/*    */   public void setCreatedStartSch(Date createdStartSch) {
/* 22 */     this.createdStartSch = createdStartSch;
/*    */   }
/*    */ 
/*    */   @ValueField(column="createdTime", equal="<")
/*    */   public Date getCreatedEndSch() {
/* 27 */     if (this.createdEndSch != null) {
/* 28 */       return DateUtil.getDateAfterDay(this.createdEndSch, 1);
/*    */     }
/* 30 */     return this.createdEndSch;
/*    */   }
/*    */ 
/*    */   public void setCreatedEndSch(Date createdEndSch) {
/* 34 */     this.createdEndSch = createdEndSch;
/*    */   }
/*    */ 
/*    */   @ValueField(column="id")
/*    */   public Integer getIdSch()
/*    */   {
/* 47 */     return this.idSch;
/*    */   }
/*    */ 
/*    */   public void setIdSch(Integer idSch) {
/* 51 */     this.idSch = idSch;
/*    */   }
/*    */   @ValueField(column="levelId")
/*    */   public Integer getLevelIdSch() {
/* 55 */     return this.levelIdSch;
/*    */   }
/*    */ 
/*    */   public void setLevelIdSch(Integer levelIdSch) {
/* 59 */     this.levelIdSch = levelIdSch;
/*    */   }
/*    */   @ValueField(column="saleType")
/*    */   public Integer getSaleTypeSch() {
/* 63 */     return this.saleTypeSch;
/*    */   }
/*    */ 
/*    */   public void setSaleTypeSch(Integer saleTypeSch) {
/* 67 */     this.saleTypeSch = saleTypeSch;
/*    */   }
/*    */   @ValueField(column="rate")
/*    */   public Integer getRateSch() {
/* 71 */     return this.rateSch;
/*    */   }
/*    */ 
/*    */   public void setRateSch(Integer rateSch) {
/* 75 */     this.rateSch = rateSch;
/*    */   }
/*    */   @ValueField(column="editedTime")
/*    */   public String getEditedTimeSch() {
/* 79 */     return this.editedTimeSch;
/*    */   }
/*    */ 
/*    */   public void setEditedTimeSch(String editedTimeSch) {
/* 83 */     this.editedTimeSch = editedTimeSch;
/*    */   }
/*    */   @ValueField(column="createdTime")
/*    */   public String getCreatedTimeSch() {
/* 87 */     return this.createdTimeSch;
/*    */   }
/*    */ 
/*    */   public void setCreatedTimeSch(String createdTimeSch) {
/* 91 */     this.createdTimeSch = createdTimeSch;
/*    */   }
/*    */   @ValueField(column="schoolNum")
/*    */   public Integer getSchoolNumSch() {
/* 95 */     return this.schoolNumSch;
/*    */   }
/*    */ 
/*    */   public void setSchoolNumSch(Integer schoolNumSch) {
/* 99 */     this.schoolNumSch = schoolNumSch;
/*    */   }
/*    */ }

/* Location:           D:\distribution\微信上线版本\RuyTonWeb\WEB-INF\classes\
 * Qualified Name:     com.ryt.web.entity.finance.FinanceDistributionParamsSch
 * JD-Core Version:    0.6.2
 */