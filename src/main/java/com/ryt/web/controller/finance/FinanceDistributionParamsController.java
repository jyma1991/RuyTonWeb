/*    */ package com.ryt.web.controller.finance;
/*    */ 
/*    */ import com.ryt.web.entity.finance.FinanceDistributionParams;
/*    */ import com.ryt.web.entity.finance.FinanceDistributionParamsSch;
/*    */ import com.ryt.web.service.finance.FinanceDistributionParamsService;
/*    */ import org.durcframework.core.GridResult;
/*    */ import org.durcframework.core.MessageResult;
/*    */ import org.durcframework.core.controller.CrudController;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ @Controller
/*    */ public class FinanceDistributionParamsController extends CrudController<FinanceDistributionParams, FinanceDistributionParamsService>
/*    */ {
/*    */   @RequestMapping({"/addFinanceDistributionParams.do"})
/*    */   @ResponseBody
/*    */   public MessageResult addFinanceDistributionParams(FinanceDistributionParams entity)
/*    */   {
/* 21 */     return save(entity);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/delFinanceDistributionParams.do"})
/*    */   @ResponseBody
/*    */   public MessageResult delFinanceDistributionParams(FinanceDistributionParams entity) {
/* 28 */     return delete(entity);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/updateFinanceDistributionParams.do"})
/*    */   @ResponseBody
/*    */   public MessageResult updateFinanceDistributionParams(FinanceDistributionParams entity) {
/* 35 */     return update(entity);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/listFinanceDistributionParams.do"})
/*    */   @ResponseBody
/*    */   public GridResult listFinanceDistributionParams(FinanceDistributionParamsSch searchEntitySch) {
/* 42 */     return query(searchEntitySch);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/listAllFinanceDistributionParams.do"})
/*    */   @ResponseBody
/*    */   public Object listAllFinanceDistributionParams(FinanceDistributionParamsSch searchEntitySch) {
/* 49 */     return queryAll(searchEntitySch);
/*    */   }
/*    */   @RequestMapping({"/getFinanceDistributionParamsById.do"})
/*    */   @ResponseBody
/*    */   public FinanceDistributionParams getFinanceDistributionParamsById(FinanceDistributionParams entity) {
/* 55 */     return (FinanceDistributionParams)((FinanceDistributionParamsService)getService()).get(entity.getId());
/*    */   }
/*    */ }

/* Location:           D:\distribution\微信上线版本\RuyTonWeb\WEB-INF\classes\
 * Qualified Name:     com.ryt.web.controller.finance.FinanceDistributionParamsController
 * JD-Core Version:    0.6.2
 */