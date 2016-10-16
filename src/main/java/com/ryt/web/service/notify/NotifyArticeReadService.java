package com.ryt.web.service.notify;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.notify.NotifyArticeReadDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.notify.NotifyArticeRead;
import com.ryt.web.entity.sys.SysClass;

import org.springframework.stereotype.Service;

@Service
public class NotifyArticeReadService extends CrudService<NotifyArticeRead, NotifyArticeReadDao> {

	/**
	 * 判断该条文章用户是否已读
	 * 
	 * @param articleBase
	 * @return
	 */
	public boolean notifyIsRead(ArticleBase articleBase) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("articleId", articleBase.getId()));
		expressions.add(new ValueExpression("userId", articleBase.getUserId()));
		query.addAll(expressions);
		List<NotifyArticeRead> list = getDao().find(query);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public NotifyArticeRead findNotifyRead(Integer userId, Integer articleId) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("articleId", articleId));
		expressions.add(new ValueExpression("userId", userId));
		query.addAll(expressions);
		List<NotifyArticeRead> list = getDao().find(query);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}