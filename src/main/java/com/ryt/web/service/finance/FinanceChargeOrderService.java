package com.ryt.web.service.finance;

import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.finance.FinanceChargeOrderDao;
import com.ryt.web.entity.finance.FinanceOrderCollection;
import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceChargeOrderSch;

import org.springframework.stereotype.Service;

@Service
public class FinanceChargeOrderService extends CrudService<FinanceChargeOrder, FinanceChargeOrderDao> {

	public List<FinanceChargeOrder> getMyOrderList(FinanceChargeOrderSch searchEntitySch) {
		ExpressionQuery query = new ExpressionQuery();
		query.addValueExpression(new ValueExpression("userId", searchEntitySch.getUserIdSch()));
		query.setQueryAll(true);
		List<FinanceChargeOrder> list = this.find(query);
		return list;
	}

	public List<FinanceOrderCollection> getAgentFinanceOrder(String sortName, String sortOrder) {
		return this.getDao().getAgentFinanceOrder(sortName, sortOrder);
	}

	public List<FinanceOrderCollection> getSchoolFinanceOrder(String sortName, String sortOrder, Integer agentId) {
		return this.getDao().getSchoolFinanceOrder(sortName, sortOrder, agentId);
	}

	public List<FinanceOrderCollection> getClassFinanceOrder(String sortName, String sortOrder, Integer schoolId) {
		return this.getDao().getClassFinanceOrder(sortName, sortOrder, schoolId);
	}

	public FinanceChargeOrder getByTradeNo(String out_trade_no) {
		ExpressionQuery query = new ExpressionQuery();
		query.addValueExpression(new ValueExpression("tradeNo", out_trade_no));
		List<FinanceChargeOrder> list = this.find(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}