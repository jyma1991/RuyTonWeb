package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthDataPermissionRole;
import com.ryt.web.entity.auth.AuthDataPermissionRoleSch;
import com.ryt.web.service.auth.AuthDataPermissionRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthDataPermissionRoleController extends
		CrudController<AuthDataPermissionRole, AuthDataPermissionRoleService> {

		//新增记录
	@RequestMapping("/addAuthDataPermissionRole.do")
	public @ResponseBody
	MessageResult addAuthDataPermissionRole(AuthDataPermissionRole entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthDataPermissionRole.do")
	public @ResponseBody
	MessageResult delAuthDataPermissionRole(AuthDataPermissionRole entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthDataPermissionRole.do")
	public @ResponseBody
	MessageResult updateAuthDataPermissionRole(AuthDataPermissionRole entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthDataPermissionRole.do")
	public @ResponseBody
	GridResult listAuthDataPermissionRole(AuthDataPermissionRoleSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthDataPermissionRole.do")
    public @ResponseBody Object listAllAuthDataPermissionRole(AuthDataPermissionRoleSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthDataPermissionRoleById.do")
    public @ResponseBody AuthDataPermissionRole getAuthDataPermissionRoleById(AuthDataPermissionRole entity) {
        return this.getService().get(entity.getId());
    }

 	

}