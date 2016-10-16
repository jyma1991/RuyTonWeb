package com.ryt.web.service.auth;

import java.util.Collections;
import java.util.List;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.auth.AuthRoleUsersDao;
import com.ryt.web.entity.auth.AuthRoleUsers;
import com.ryt.web.entity.auth.AuthUserRoleParam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class AuthRoleUsersService extends CrudService<AuthRoleUsers, AuthRoleUsersDao> {

    /**
     * �����û���ɫ
     */
    public void setUserRole(AuthUserRoleParam userRoleParam) {

        if (StringUtils.isEmpty(userRoleParam.getUserName()) || CollectionUtils.isEmpty(userRoleParam.getRoleIds())) {
            return;
        }
        //���ԭ�н�ɫ
        this.getDao().delAllUserRole(userRoleParam.getUserName());

        //�����ɫ
        AuthRoleUsers ru=null;
        for (Integer roleId : userRoleParam.getRoleIds()) {
        	//System.out.println(roleId +" "+userRoleParam.getUserName());
        	ru = new AuthRoleUsers();
        	ru.setRoleId(roleId);
        	ru.setUserName(userRoleParam.getUserName());
        	ru.setuId(userRoleParam.getuId());
        	//ru.setUuid(uuid);
        	//ru.setSortId(sortId);
            this.save(ru);
        }
    }
	
    public List<AuthRoleUsers> getUserRole(String username) {
        if (StringUtils.isEmpty(username)) {
            return Collections.emptyList();
        }
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        query.add(new ValueExpression("username", username));

        return this.find(query);
    }
	
    public List<AuthRoleUsers> getUserRole(int userId) {
        if (userId < 1) {
            return Collections.emptyList();
        }
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        query.add(new ValueExpression("uId", userId));

        return this.find(query);
    }
}