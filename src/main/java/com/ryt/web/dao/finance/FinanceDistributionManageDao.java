package com.ryt.web.dao.finance;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;
import com.ryt.web.entity.finance.FinanceDistributionManage;
import com.ryt.web.entity.finance.FinanceUntreatedAgentResult;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.user.User;

public interface FinanceDistributionManageDao extends BaseDao<FinanceDistributionManage> {

    //��ȡδ�����Ƽ�����Ϣ start
	 public	   List<FinanceUntreatedAgentResult>   listDeDisposeScAgent();
    //��ȡδ�����Ƽ�����Ϣ end
	
	//���δ�����Ƽ�����Ϣ start
	 public    User  findUserId(FinanceUntreatedAgentResult entity);
	 public    User  getNamePhone(FinanceUntreatedAgentResult entity);
	 public    void  agentSave(FinanceUntreatedAgentResult entity);
	 public    void  addIncomeOne(FinanceUntreatedAgentResult entity);
	 public    ScAgent  higherLevelAgent(FinanceUntreatedAgentResult entity);
	 public    void  addIncomeTwo(ScAgent scAgent);
	 public    void  updateFlag(Integer manageId);
	//���δ�����Ƽ�����Ϣ end
	 
	//��������б��ѯ start
	 public	     Integer   listAgentUpgradeMes(FinanceDistributionManage entity);
	//��������б��ѯ end
	 
	//��������ύ start
	 public      void  DoSubmitAgentUpgrade(ScAgent scAgent);
	 public      User  getMobilePhone(ScAgent scAgent);
	//��������ύ end 
	
	//��ȡ��֧��Ϣ��ѯ start
	 public      Integer  agentDisManage();
	 public 	 Integer  serviceDisManage();
	 public 	 Integer  serviceInManage();
	 public 	 Integer  marketDisManage();
	 public 	 Integer  beWithdrawManage();
	//��ȡ��֧��Ϣ��ѯ end
	 
	//������֧��Ϣ start
	 public 	 Integer  agentDisManageFilter(@Param(value="createdStartSch_String")String createdStartSch_String,@Param(value="createdEndSch_String")String createdEndSch_String);
	 public 	 Integer  serviceDisManageFilter(@Param(value="createdStartSch_String")String createdStartSch_String,@Param(value="createdEndSch_String")String createdEndSch_String);
	 public 	 Integer  serviceInManageFilter(@Param(value="createdStartSch_String")String createdStartSch_String,@Param(value="createdEndSch_String")String createdEndSch_String);
	 public 	 Integer  marketDisManageFilter(@Param(value="createdStartSch_String")String createdStartSch_String,@Param(value="createdEndSch_String")String createdEndSch_String);
	 public 	 Integer  beWithdrawManageFilter(@Param(value="createdStartSch_String")String createdStartSch_String,@Param(value="createdEndSch_String")String createdEndSch_String);
	//������֧��Ϣ end
	
	//����������Ϣ�б��ѯ start
	public List<FinanceDistributionManage> FilterListIncomeAmount(@Param(value="agentId")Integer agentId);
	//����������Ϣ�б��ѯ end
	
	 //������Ϣ�ύ start
	 public  void  submitIncomeAmount(FinanceDistributionManage entity);
	 public  User  getMessage(FinanceDistributionManage entity);
	 public  ScAgent  getCardNO(FinanceDistributionManage entity);
	 //������Ϣ�ύ end 
 
	 //������Ϣ�б��ѯ start
	 public	     List<FinanceDistributionManage>   listIncomeAmountMes();
	 //������Ϣ�б��ѯ end
	 
	  //�Ƽ������룬��������������Ϣ��ʾ start
 public      Integer  numberCount();
 public      Integer  numberCountUpgradeSmallArea();
 public      Integer  numberCountUpgradeBigArea();
 public      Integer  numberCountUpgradeGoldArea();
 public      Integer  numberCountRemainingAmt();
 //�Ƽ������룬��������������Ϣ��ʾ end
public abstract void chatAgentSubmit(FinanceUntreatedAgentResult paramFinanceUntreatedAgentResult);

  public abstract List<FinanceDistributionManage> chatAgentIncomeInf(@Param("userName") String paramString);

  public abstract List<FinanceDistributionManage> chatWithdrawInf(String paramString);
  public abstract void submitWithdraw(@Param("agentId") Integer paramInteger1, @Param("amt") Integer paramInteger2);
//20150103 微信index.html中的查询     start
public String queryTrueName(@Param("userName") String userName);
public Integer suggestRegisterCount(@Param("userName") String userName);
public Integer suggesterSchoolNum(@Param("userName") String userName);
//20150103 微信index.html中的查询     end
}