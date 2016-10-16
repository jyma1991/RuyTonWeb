package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.auth.AuthRoleUsers;
import com.ryt.web.entity.auth.AuthRoleUsersSch;
import com.ryt.web.entity.auth.AuthUserRoleParam;
import com.ryt.web.service.auth.AuthRoleUsersService;
@Controller
public class AuthRoleUsersController extends
		CrudController<AuthRoleUsers, AuthRoleUsersService> {

		//新增记录
	@RequestMapping("/addAuthRoleUsers.do")
	public @ResponseBody
	MessageResult addAuthRoleUsers(AuthRoleUsers entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthRoleUsers.do")
	public @ResponseBody
	MessageResult delAuthRoleUsers(AuthRoleUsers entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthRoleUsers.do")
	public @ResponseBody
	MessageResult updateAuthRoleUsers(AuthRoleUsers entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthRoleUsers.do")
	public @ResponseBody
	GridResult listAuthRoleUsers(AuthRoleUsersSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthRoleUsers.do")
    public @ResponseBody Object listAllAuthRoleUsers(AuthRoleUsersSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthRoleUsersById.do")
    public @ResponseBody AuthRoleUsers getAuthRoleUsersById(AuthRoleUsers entity) {
        return this.getService().get(entity.getId());
    }
    @RequestMapping("/setUserRole.do")
    public @ResponseBody Object setUserRole(AuthUserRoleParam userRoleParam) {
    	if("admin".equals(userRoleParam.getUserName()))
    		return error("不能为admin设置角色！");
        this.getService().setUserRole(userRoleParam);
        return success();
    }
 	

}