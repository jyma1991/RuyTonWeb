package com.ryt.web.service.finance;

import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.dao.finance.FinanceChargeProductDao;
import com.ryt.web.entity.finance.FinanceChargeProduct;
import org.springframework.stereotype.Service;

@Service
public class FinanceChargeProductService extends CrudService<FinanceChargeProduct, FinanceChargeProductDao> {

	public List<FinanceChargeProduct> getAllProductList() {
		ExpressionQuery query = new ExpressionQuery();
		query.addParam("isDeleted", RuyTonAppContants.ARTICLE_DELETE_FALSE);
		query.addSort("id","asc");
		return getDao().find(query);
	}

}