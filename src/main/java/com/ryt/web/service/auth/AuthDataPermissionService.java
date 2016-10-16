package com.ryt.web.service.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.durcframework.core.expression.Expression;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.InnerJoinExpression;
import org.durcframework.core.expression.subexpression.ListExpression;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.auth.AuthDataPermissionDao;
import com.ryt.web.entity.auth.AuthDataPermission;
import org.springframework.stereotype.Service;

@Service
public class AuthDataPermissionService extends CrudService<AuthDataPermission, AuthDataPermissionDao> {
	public List<Expression> buildDataExpresstions(List<Integer> roleIds, int sfId) {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();

        query.add(new InnerJoinExpression("r_data_permission_role", "t2", "dp_id", "dp_id"));

        query.add(new ValueExpression("sr_id", sfId));
        query.add(new ListExpression("t2.role_id", roleIds));

        List<AuthDataPermission> list = this.find(query);

        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        List<Expression> expressions = new ArrayList<Expression>(list.size());

        for (AuthDataPermission dataPermission : list) {
            expressions.add(ExpressionFactory.build(dataPermission));
        }

        return expressions;
    }
}