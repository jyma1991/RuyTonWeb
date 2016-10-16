package com.ryt.web.service.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.finance.FinanceDistributionManageDao;
import com.ryt.web.entity.finance.FinanceDistributionManage;
import com.ryt.web.entity.finance.FinanceUntreatedAgentResult;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.user.User;
import com.ryt.app.util.SendSmsUtil;
import org.springframework.stereotype.Service;

@Service
public class FinanceDistributionManageService
		extends CrudService<FinanceDistributionManage, FinanceDistributionManageDao> {
	// 推荐人申请，代理商升级，提现信息提示 start
	public List<Integer> messagePrompt() {
		Integer number_apply = this.getDao().numberCount();
		Integer numberSmallArea = this.getDao().numberCountUpgradeSmallArea();
		Integer numberBigArea = this.getDao().numberCountUpgradeBigArea();
		Integer numberGoldArea = this.getDao().numberCountUpgradeGoldArea();
		Integer number_upgrade = numberSmallArea + numberBigArea + numberGoldArea;
		Integer number_withdraw = this.getDao().numberCountRemainingAmt();
		List<Integer> list = new ArrayList<Integer>();
		list.add(number_apply);
		list.add(number_upgrade);
		list.add(number_withdraw);
		return list;
	}
	// 推荐人申请，代理商升级，提现信息提示 end

	// 获取未处理推荐人信息 start
	public List<FinanceUntreatedAgentResult> listDeScAgent() {
		return this.getDao().listDeDisposeScAgent();
	}
	// 获取未处理推荐人信息 end

	// findUserId begin
	public User findUserId(FinanceUntreatedAgentResult entity) {
		return this.getDao().findUserId(entity);
	}

	// find end
	// 添加未处理推荐人信息 start
	public void agreeScAgent(FinanceUntreatedAgentResult entity) {
		Integer manageId = entity.getId();
		this.getDao().addIncomeOne(entity);
		ScAgent scAgent = this.getDao().higherLevelAgent(entity);
		if(scAgent !=null){
		if (scAgent.getLevelId() != 1) {
			scAgent.setAgentCode(entity.getName());
			scAgent.setAgentId(scAgent.getAgentId());
			this.getDao().addIncomeTwo(scAgent);
		}
		}
		this.getDao().updateFlag(manageId);

	}
	// 添加未处理推荐人信息 end

	// <!--提现信息列表查询 start -->
	public List<FinanceDistributionManage> listIncomeAmount() {
		return this.getDao().listIncomeAmountMes();
	}

	// <!--提现信息列表查询 end -->
	// <!--提现信息确认 start -->
	public void updateIncomeAmount(FinanceDistributionManage entity) {
		Integer withdrawAmt = entity.getWithdrawAmt();
		String string_withdrawAmt = String.valueOf(withdrawAmt * 0.01);
		User user = this.getDao().getMessage(entity);
		String trueName = user.getTrueName();
		String mobilePhone = user.getMobilePhone();
		ScAgent scAgent = this.getDao().getCardNO(entity);
		String cardNo = scAgent.getCardNo();
		SendSmsUtil sendSmsUtil = new SendSmsUtil();
		sendSmsUtil.sendSmsToWithdraw(trueName, string_withdrawAmt, cardNo, "400-115-1313", mobilePhone);
		this.getDao().submitIncomeAmount(entity);
	}

	// <!--提现信息确认 end -->
	public User getMessage(FinanceUntreatedAgentResult entity) {
		FinanceDistributionManage financeDistributionManage = new FinanceDistributionManage();
		financeDistributionManage.setAgentId(entity.getAgentId());
		return this.getDao().getMessage(financeDistributionManage);
	}

	// 升级代理商列表查询 start
	public Integer listAgentUpgrade(FinanceDistributionManage entity) {
		return this.getDao().listAgentUpgradeMes(entity);
	}
	// 升级代理商列表查询 end

	// 升级代理商提交 start
	public void submitAgentUpgrade(ScAgent scAgent) {
		this.getDao().DoSubmitAgentUpgrade(scAgent);
		Integer levelId = scAgent.getLevelId();
		String String_levelId = String.valueOf(levelId + 1);
		if ("2".equals(String_levelId)) {
			String_levelId = "小区";
		}
		if ("3".equals(String_levelId)) {
			String_levelId = "大区";
		}
		if ("4".equals(String_levelId)) {
			String_levelId = "金牌";
		}
		String userName = scAgent.getUserName();
		User user = this.getDao().getMobilePhone(scAgent);
		String mobilePhone = user.getMobilePhone();
		//SendSmsUtil sendSmsUtilUpgrade = new SendSmsUtil();
		//sendSmsUtilUpgrade.sendSmsUtilUpgrade(userName, String_levelId, mobilePhone);
	}
	// �����������ύ end

	// ��ȡ��֧��Ϣ��ѯ start
	public List<Map<String, Integer>> financePayments() {
		Integer agentDis = this.getDao().agentDisManage();
		Integer serviceDis = this.getDao().serviceDisManage();
		Integer serviceIn = this.getDao().serviceInManage();
		Integer marketDis = this.getDao().marketDisManage();
		Integer beWithdraw = this.getDao().beWithdrawManage();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("agentDis", agentDis / 100);
		map.put("serviceDis", serviceDis / 100);
		map.put("serviceIn", serviceIn / 100);
		map.put("marketDis", marketDis / 100);
		map.put("totalIn", (agentDis + serviceDis + marketDis) / 100);
		map.put("beWithdraw", (beWithdraw) / 100);
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(map);
		return list;
	}
	// 获取收支信息查询 end

	// 过滤收支信息 start
	public List<Map<String, Integer>> financePaymentsFilter(Date createdStartSch, Date createdEndSch) {
		String createdStartSch_String = null;
		String createdEndSch_String = null;
		if (createdStartSch == null) {
			createdStartSch_String = "19491001";
		} else {
			createdStartSch_String = new SimpleDateFormat("yyyyMMdd").format(createdStartSch);
		}
		if (createdEndSch == null) {
			createdEndSch_String = "25001001";
		} else {
			createdEndSch_String = new SimpleDateFormat("yyyyMMdd").format(createdEndSch);
		}
		Integer agentDis = this.getDao().agentDisManageFilter(createdStartSch_String, createdEndSch_String);
		Integer serviceDis = this.getDao().serviceDisManageFilter(createdStartSch_String, createdEndSch_String);
		Integer serviceIn = this.getDao().serviceInManageFilter(createdStartSch_String, createdEndSch_String);
		Integer marketDis = this.getDao().marketDisManageFilter(createdStartSch_String, createdEndSch_String);
		Integer beWithdraw = this.getDao().beWithdrawManageFilter(createdStartSch_String, createdEndSch_String);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("agentDis", agentDis / 100);
		map.put("serviceDis", serviceDis / 100);
		map.put("serviceIn", serviceIn / 100);
		map.put("marketDis", marketDis / 100);
		map.put("totalIn", (agentDis + serviceDis + serviceIn + marketDis) / 100);
		map.put("beWithdraw", (beWithdraw) / 100);
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(map);
		return list;
	}
	// 过滤收支信息 start

	// 过滤提现信息列表查询 start
	public List<FinanceDistributionManage> FilterListIncomeAmount(Integer agentId) {
		return this.getDao().FilterListIncomeAmount(agentId);
	}
	// 过滤提现信息列表查询 end

	public void chatAgentSubmit(FinanceUntreatedAgentResult financeUntreatedAgentResult) {
		this.getDao().chatAgentSubmit(financeUntreatedAgentResult);
	}

	public List<FinanceDistributionManage> chatAgentIncomeInf(String userName) {
		return this. getDao().chatAgentIncomeInf(userName);
	}

	public List<FinanceDistributionManage> chatWithdrawInf(String userName) {
		return this. getDao().chatWithdrawInf(userName);
	}

	public void submitWithdraw(Integer agentId, Integer amt) {
		this. getDao().submitWithdraw(agentId, amt);
	}
	//20150103 微信index.html中的查询     start
	public Map<String,Object> chatIndexQuery(String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
	    String trueName =this.getDao().queryTrueName(userName);
	    Integer suggestRegisterCount = this.getDao().suggestRegisterCount(userName);
	    Integer suggesterSchoolNum  = this.getDao().suggesterSchoolNum(userName);
	    map.put("trueName", trueName);
	    map.put("suggestRegisterCount", suggestRegisterCount);
	    map.put("suggesterSchoolNum", suggesterSchoolNum);
		return map;
	}
	//20150103 微信index.html中的查询     end

}