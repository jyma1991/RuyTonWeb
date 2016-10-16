package com.ryt.web.controller.auth;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.controller.SearchController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.auth.AuthSystemFunction;
import com.ryt.web.entity.auth.AuthSystemResource;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.auth.AuthSystemFunctionService;
import com.ryt.web.service.auth.AuthSystemResourceService;
import com.ryt.web.util.TreeUtil;

@Controller
public class MenuController extends SearchController<AuthSystemResource, AuthSystemResourceService> {

    @Autowired
    private AuthSystemFunctionService aSystemFunctionService;

    /**
     * 加载用户菜单
     * @return
     */
    @RequestMapping("listUserMenu.do")
    public @ResponseBody Object listUserMenu() {
        User user = UserContext.getInstance().getUser();

        if (user == null) {
        	user = new User();
        	user.setUserId(0);
        	user.setUserName("admin");
        	user.setId(0);
        	user.setOperaterId(0);
            //return error("当前用户不存在");
        }

        List<AuthSystemResource> menuList = this.getService().getUserMenu(user.getUserName());
        return menuList;
    }

    // 获取所有菜单
    @RequestMapping("/listAllMenu.do")
    public @ResponseBody Object listAllMenu() {
        ExpressionQuery query = ExpressionQuery.buildQueryAll();
        
        DefaultGridResult resultGrid = (DefaultGridResult) this.queryAll(query);
        @SuppressWarnings("unchecked")
        List<AuthSystemResource> rows = (List<AuthSystemResource>) resultGrid.getRows();
        List<AuthSystemFunction> sysFuns = null;
        for (AuthSystemResource AuthSystemResource : rows) {
            sysFuns = aSystemFunctionService.getBySySResId(AuthSystemResource.getId());
            AuthSystemResource.setaSystemFunctions(sysFuns);
        }
        
        return TreeUtil.buildTreeData(rows);
    }



}
