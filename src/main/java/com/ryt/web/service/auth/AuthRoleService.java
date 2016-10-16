package com.ryt.web.service.auth;

import java.util.List;

import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.auth.AuthRoleDao;
import com.ryt.web.entity.auth.AuthRole;
import com.ryt.web.entity.auth.AuthSystemFunction;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRoleService extends CrudService<AuthRole, AuthRoleDao> {

	@Autowired
	private AuthRolePermissionService authRolePermissionService;

	/**
	 * 根绝功能获取用户
	 * 
	 * @param sysFunction
	 * @return
	 */
	public List<AuthRole> getRolesBySysFunction(AuthSystemFunction sysFunction) {
		return this.getDao().findRoleByFunction(sysFunction.getId());
	}

	/**
	 * 调用用户权限服务设置权限
	 * 
	 * @param authRole
	 *            角色信息，内包含了权限id
	 */
	public void SetRolePermission(AuthRole authRole) {
		authRolePermissionService.setRoleFunction(authRole);
	}

	/**
	 * 根据用户id 删除其全部功能权限
	 * 
	 * @param authRole
	 */
	public void delRolePermission(AuthRole authRole) {
		authRolePermissionService.delByRoleId(authRole.getId());
	}

}