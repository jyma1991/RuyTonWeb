package com.ryt.web.dao.sc;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScAgentSch;

public interface ScAgentDao extends BaseDao<ScAgent> {

	List<Object> getAgentList(ScAgentSch searchEntitySch);

	// ������������Ϣ start
	public Integer agentOneLevel();

	public Integer agentTwoLevel();

	public Integer agentThreeLevel();

	public Integer agentFourLevel();

	// ������������Ϣ start
	public Integer agentOneLevelFilter(@Param(value = "createdStartSch_String") String createdStartSch_String,
			@Param(value = "createdEndSch_String") String createdEndSch_String);

	public Integer agentTwoLevelFilter(@Param(value = "createdStartSch_String") String createdStartSch_String,
			@Param(value = "createdEndSch_String") String createdEndSch_String);

	public Integer agentThreeLevelFilter(@Param(value = "createdStartSch_String") String createdStartSch_String,
			@Param(value = "createdEndSch_String") String createdEndSch_String);

	public Integer agentFourLevelFilter(@Param(value = "createdStartSch_String") String createdStartSch_String,
			@Param(value = "createdEndSch_String") String createdEndSch_String);

	// ������������Ϣ end
	public abstract ScAgent chatScAgentInf(@Param("userName") String paramString);

	public abstract List<ScAgent> chatScAgentLowerInf(@Param("userName") String paramString);
	
	public  ScAgent queryAgentByAgentId(@Param("agentId") Integer agentId);
}