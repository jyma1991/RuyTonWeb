package com.ryt.web.service.auth;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.auth.AuthSystemFunctionDao;
import com.ryt.web.entity.auth.AuthSystemFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthSystemFunctionService extends CrudService<AuthSystemFunction, AuthSystemFunctionDao> {
    @Autowired
    private AuthRolePermissionService aRolePermissionService;

    /**
     * 删除系统功能
     */
    @Override
    public void del(AuthSystemFunction sysFunction) {
        // 先删除角色
    	aRolePermissionService.delBySfId(sysFunction.getId());

        this.getDao().del(sysFunction);
    }

    public List<AuthSystemFunction> getUserSysFunction(String username) {
        return this.getDao().findUserSysFunction(username);
    }
    /**
     * 通过资源ID查找系统功能
     * @param srId
     * @return
     */
    public List<AuthSystemFunction> getBySySResId(int srId) {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        query.add(new ValueExpression("systemResourceId", srId));

        return find(query);
    }

    /**
     * 获取用户系统功能
     * @param username
     * @return
     */
    public List<AuthSystemFunction> getUseAuthSystemFunction(String username) {
        return this.getDao().findUserSysFunction(username);
    }

    public List<AuthSystemFunction> getByOperateCode(String operateCode) {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        query.add(new ValueExpression("operateCode", operateCode));

        return find(query);
    }

    /**
     * 根据资源ID删除系统功能
     * @param srId
     */
    public void delBySrId(int srId) {
        List<AuthSystemFunction> sysFuncs = getBySrId(srId);
        for (AuthSystemFunction AuthSystemFunction : sysFuncs) {
            del(AuthSystemFunction);
        }
    }

    private List<AuthSystemFunction> getBySrId(int srId) {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        query.add(new ValueExpression("systemResourceId", srId));

        return find(query);
    }

    public AuthSystemFunction getByOperateCodeSrId(String operateCode, int srId) {
        ExpressionQuery query = new ExpressionQuery();
        query.setLimit(1);
        query.add(new ValueExpression("operateCode", operateCode));
        query.add(new ValueExpression("systemResourceId", srId));

        List<AuthSystemFunction> list = find(query);

        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }

        return null;
    }

    public boolean isExistSysFun(String operateCode, int srId) {
        return this.getByOperateCodeSrId(operateCode, srId) != null;
    }
}