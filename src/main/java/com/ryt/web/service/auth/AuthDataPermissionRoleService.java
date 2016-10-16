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

import com.ryt.web.dao.auth.AuthDataPermissionRoleDao;
import com.ryt.web.entity.auth.AuthDataPermission;
import com.ryt.web.entity.auth.AuthDataPermissionRole;
import org.springframework.stereotype.Service;

@Service
public class AuthDataPermissionRoleService extends CrudService<AuthDataPermissionRole, AuthDataPermissionRoleDao> {

	
	
	
	
}