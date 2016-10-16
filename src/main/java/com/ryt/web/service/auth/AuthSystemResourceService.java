package com.ryt.web.service.auth;

import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;


import com.ryt.web.dao.auth.AuthSystemResourceDao;
import com.ryt.web.entity.auth.AuthSystemResource;
import com.ryt.web.util.TreeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthSystemResourceService extends CrudService<AuthSystemResource, AuthSystemResourceDao> {
    @Autowired
    private AuthSystemFunctionService aSystemFunctionService;

    /**
     * 判断是否有子节点
     * @param aSystemResource
     * @return
     */
    public boolean hasChild(AuthSystemResource aSystemResource) {
    	System.out.println("id:"+" "+aSystemResource.getId());
        ExpressionQuery query = new ExpressionQuery();
        query.add(new ValueExpression("parentId", aSystemResource.getId()));
        int count = this.findTotalCount(query);
        return count > 0;
    }

    /**
     * 根据用户名获取菜单
     * @param username 用户名
     * @return 返回用户菜单列表
     */
    public List<AuthSystemResource> getUserMenu(String username) {

        List<AuthSystemResource> list = this.getDao().findUserMenu(username);

        list = TreeUtil.buildTreeData(list);

        return list;
    }

    /**
     * 获取所有资源
     * @return
     */
    public List<AuthSystemResource> getAllSysRes() {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        List<AuthSystemResource> list = this.find(query);

        list = TreeUtil.buildTreeData(list);

        return list;
    }

    /**
     * 删除资源 首先删除对应的系统功能,在删除自身
     */
    @Override
    public void del(AuthSystemResource entity) {
    	aSystemFunctionService.delBySrId(entity.getId());
        this.getDao().del(entity);
    }

}