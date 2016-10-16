package com.ryt.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.durcframework.core.SpringContext;
import org.durcframework.core.UserContext;
import org.durcframework.core.WebContext;
import org.durcframework.core.expression.Expression;
//import org.durcframework.rms.entity.AuthSystemFunction;
import com.ryt.web.entity.auth.AuthSystemFunction;
import com.ryt.web.entity.user.User;
//import org.durcframework.rms.entity.RUserRole;
import com.ryt.web.entity.auth.AuthRoleUsers;

import com.ryt.web.service.auth.AuthDataPermissionRoleService;
import com.ryt.web.service.auth.AuthDataPermissionService;
//import org.durcframework.rms.service.AuthSystemFunctionService;
import com.ryt.web.service.auth.AuthSystemFunctionService;
//import org.durcframework.rms.service.RUserRoleService;
import com.ryt.web.service.auth.AuthRoleUsersService;
import org.springframework.util.StringUtils;

public enum RMSContext {
    INS;

    private static final String USER_ROLE_IDS = "user_role_ids";
    private static Map<String, List<AuthSystemFunction>> useAuthSystemFunctionMap = new HashMap<String, List<AuthSystemFunction>>();

    public static RMSContext getInstance() {
        return INS;
    }


    /**
     * 获取当前用户系统功能
     * @return
     */
    public List<AuthSystemFunction> getCurrentUseAuthSystemFunction() {
        User user = UserContext.getInstance().getUser();
        return useAuthSystemFunctionMap.get(user.getUsername());
    }


    /**
     * 刷新保存用权限数�?.(系统功能=菜单+操作�?)
     */
    public void refreshUserRightData(String username) {
        AuthSystemFunctionService sysFunctionService = SpringContext.getBean(AuthSystemFunctionService.class);

        List<AuthSystemFunction> userSysFuns = sysFunctionService.getUserSysFunction(username);

        useAuthSystemFunctionMap.put(username, userSysFuns);

        this.saveUserRoleIds(username);
    }

    /**
     * 获取用户数据权限条件
     * @return
     */
    public List<Expression> getUserDataExpressions() {
        String srId = WebContext.getInstance().getRequest().getParameter("srId");

        if (srId == null) {
            return Collections.emptyList();
        }
        List<Integer> roleIds = getCurrentUserRoleIds();
        AuthDataPermissionService dataPermissionService = SpringContext.getBean(AuthDataPermissionRoleService.class);

        return dataPermissionService.buildDataExpresstions(roleIds, Integer.valueOf(srId));
    }

    /**
     * 保存用户角色ID
     * @param username
     */
    public void saveUserRoleIds(String username) {
        AuthRoleUsersService userRoleService = SpringContext.getBean(AuthRoleUsersService.class);
        List<AuthRoleUsers> userRoles = userRoleService.getUserRole(username);

        if (CollectionUtils.isNotEmpty(userRoles)) {
            List<Integer> roleIds = new ArrayList<Integer>(userRoles.size());
            for (AuthRoleUsers userRole : userRoles) {
                roleIds.add(userRole.getRoleId());
            }
            WebContext.getInstance().setAttr(USER_ROLE_IDS, roleIds);
        }
    }

    /**
     * 获取当前用户角色ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getCurrentUserRoleIds() {
        Object roleIds = WebContext.getInstance().getAttr(USER_ROLE_IDS);

        if (roleIds == null) {
            return Collections.emptyList();
        }

        return (List<Integer>) roleIds;
    }

    /**
     * 刷新�?有用户的系统功能
     */
    public void refreshAllUserRightData() {
        Set<String> usernameSet = useAuthSystemFunctionMap.keySet();
        for (String username : usernameSet) {
            this.refreshUserRightData(username);
        }
    }

    /**
     * 移除用户权限数据,在用户注�?或session失效可以用到
     * @param username
     */
    public void clearUserRightData(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        useAuthSystemFunctionMap.remove(username);
    }

    /**
     * 移除当前用户权限数据
     */
    public void clearCurrentUserRightData() {
        User user = UserContext.getInstance().getUser();
        if (user != null) {
            this.clearUserRightData(user.getUsername());
        }
    }
}
