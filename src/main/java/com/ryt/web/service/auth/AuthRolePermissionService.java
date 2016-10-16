package com.ryt.web.service.auth;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.auth.AuthRolePermissionDao;
import com.ryt.web.entity.auth.AuthRole;
import com.ryt.web.entity.auth.AuthRolePermission;
import com.ryt.web.entity.auth.AuthSystemFunction;
import org.springframework.stereotype.Service;

@Service
public class AuthRolePermissionService extends CrudService<AuthRolePermission, AuthRolePermissionDao> {

	public List<AuthRolePermission> getRolePermissionByFunction(AuthSystemFunction function) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("sysFuncId", function.getId()));
		query.setQueryAll(true);
		return this.find(query);
	}

	public List<AuthRolePermission> getRolePermissionByRole(int roleId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("roleId", roleId));
		query.setQueryAll(true);
		return this.find(query);
	}

	/**
	 * 设置role权限
	 * 全量更新模式，先清除现有权限，后重新写入
	 * @author Jyma
	 * @since 2015-10-28 11:19:29
	 * @param authRole
	 */
	public void setRoleFunction(AuthRole authRole) {

		int roleId = authRole.getId();
		Integer[] listFuncIds = authRole.getSysFuncId();

		if (authRole.getId() <= 0)
			return;
		delByRoleId(roleId);//清除现有权限

		AuthRolePermission rp = null;
		for (Integer sfId : listFuncIds) {
			if (sfId == 0)
				continue;
			rp = new AuthRolePermission();
			rp.setRoleId(roleId);
			rp.setSysFuncId(sfId);
			this.save(rp);
		}
	}

	public void setSysFunctionRole(List<Integer> sfIds, int roleId) {
		delByRoleId(roleId);
		if (CollectionUtils.isNotEmpty(sfIds)) {
			AuthRolePermission rp = null;
			for (Integer sfId : sfIds) {
				rp = new AuthRolePermission();
				rp.setRoleId(roleId);
				rp.setSysFuncId(sfId);
				this.save(rp);
			}
		}
	}

	public void delBySfId(int sfId) {
		this.getDao().delBySfId(sfId);
	}

	// 根绝角色ID删除权限
	public void delByRoleId(int roleId) {
		this.getDao().delByRoleId(roleId);
	}
}