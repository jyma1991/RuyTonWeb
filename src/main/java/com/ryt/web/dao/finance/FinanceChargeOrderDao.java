package com.ryt.web.dao.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceOrderCollection;

public interface FinanceChargeOrderDao extends BaseDao<FinanceChargeOrder> {
	public List<FinanceOrderCollection> getAgentFinanceOrder(@Param(value="sortName")String sortname,@Param(value="sortOrder")String sortorder);
	public List<FinanceOrderCollection> getSchoolFinanceOrder(@Param(value="sortName")String sortname,@Param(value="sortOrder")String sortorder,@Param(value="agentId")Integer agentId);
	public List<FinanceOrderCollection> getClassFinanceOrder(@Param(value="sortName")String sortname,@Param(value="sortOrder")String sortorder,@Param(value="schoolId")Integer schoolId);

}