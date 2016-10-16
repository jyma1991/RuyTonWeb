package com.ryt.web.service.sc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.sc.ScAgentDao;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScAgentSch;

@Service
public class ScAgentService extends CrudService<ScAgent, ScAgentDao> {

	public List<ScAgent> getAgentList(ScAgentSch searchEntitySch) {
		ExpressionQuery expressionQuery = new ExpressionQuery();
		expressionQuery.addAnnotionExpression(searchEntitySch).addPaginationInfo(searchEntitySch);
		expressionQuery.setQueryAll(true);
		return this.find(expressionQuery);
	}

	// ������������Ϣ start
	public List<Map<String, Integer>> agentManage() {
		Integer agentOneLevel = this.getDao().agentOneLevel();
		Integer agentTwoLevel = this.getDao().agentTwoLevel();
		Integer agentThreeLevel = this.getDao().agentThreeLevel();
		Integer agentFourLevel = this.getDao().agentFourLevel();
		Integer totalAgent = agentOneLevel + agentTwoLevel + agentThreeLevel + agentFourLevel;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("agentOneLevel", agentOneLevel);
		map.put("agentTwoLevel", agentTwoLevel);
		map.put("agentThreeLevel", agentThreeLevel);
		map.put("agentFourLevel", agentFourLevel);
		map.put("totalAgent", totalAgent);
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(map);
		return list;
	}

	// ������������Ϣ end
	// ������������Ϣ���� start
	public List<Map<String, Integer>> agentManageFilter(Date createdStartSch, Date createdEndSch) {
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
		Integer agentOneLevelFilter = this.getDao().agentOneLevelFilter(createdStartSch_String, createdEndSch_String);
		Integer agentTwoLevelFilter = this.getDao().agentTwoLevelFilter(createdStartSch_String, createdEndSch_String);
		Integer agentThreeLevelFilter = this.getDao().agentThreeLevelFilter(createdStartSch_String,
				createdEndSch_String);
		Integer agentFourLevelFilter = this.getDao().agentFourLevelFilter(createdStartSch_String, createdEndSch_String);
		Integer totalAgentFilter = agentOneLevelFilter + agentTwoLevelFilter + agentThreeLevelFilter
				+ agentFourLevelFilter;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("agentOneLevelFilter", agentOneLevelFilter);
		map.put("agentTwoLevelFilter", agentTwoLevelFilter);
		map.put("agentThreeLevelFilter", agentThreeLevelFilter);
		map.put("agentFourLevelFilter", agentFourLevelFilter);
		map.put("totalAgentFilter", totalAgentFilter);
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		list.add(map);
		return list;
	}

	// ������������Ϣ���� end

	public ScAgent chatScAgentInf(String userName) {
		return this.getDao().chatScAgentInf(userName);
	}

	public List<ScAgent> chatScAgentLowerInf(String userName) {

		return this.getDao().chatScAgentLowerInf(userName);
	}
	public ScAgent queryAgentByAgentId(Integer agentId) {

		return this.getDao().queryAgentByAgentId(agentId);
	}
}