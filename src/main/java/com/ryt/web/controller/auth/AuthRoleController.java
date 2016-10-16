package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthRole;
import com.ryt.web.entity.auth.AuthRoleSch;
import com.ryt.web.service.auth.AuthRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthRoleController extends
		CrudController<AuthRole, AuthRoleService> {

		//新增记录
	@RequestMapping("/addAuthRole.do")
	public @ResponseBody
	MessageResult addAuthRole(AuthRole entity) {
		//添加用户的权限 edited by jyma 2015-10-28 11:09:31
		this.getService().SetRolePermission(entity);
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthRole.do")
	public @ResponseBody
	MessageResult delAuthRole(AuthRole entity) {
		//删除用户权限  edited by jyma 2015-10-28 11:09:31
		this.getService().delRolePermission(entity);
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthRole.do")
	public @ResponseBody
	MessageResult updateAuthRole(AuthRole entity) {
		//更新用户权限  edited by jyma 2015-10-28 11:09:31
		this.getService().SetRolePermission(entity);
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthRole.do")
	public @ResponseBody
	GridResult listAuthRole(AuthRoleSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthRole.do")
    public @ResponseBody Object listAllAuthRole(AuthRoleSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthRoleById.do")
    public @ResponseBody AuthRole getAuthRoleById(AuthRole entity) {
        return this.getService().get(entity.getId());
    }


}