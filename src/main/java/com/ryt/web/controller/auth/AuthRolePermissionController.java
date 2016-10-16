package com.ryt.web.controller.auth;

import java.util.List;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthRolePermission;
import com.ryt.web.entity.auth.AuthRolePermissionSch;
import com.ryt.web.service.auth.AuthRolePermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthRolePermissionController extends
		CrudController<AuthRolePermission, AuthRolePermissionService> {

		//新增记录
	@RequestMapping("/addAuthRolePermission.do")
	public @ResponseBody
	MessageResult addAuthRolePermission(AuthRolePermission entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthRolePermission.do")
	public @ResponseBody
	MessageResult delAuthRolePermission(AuthRolePermission entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthRolePermission.do")
	public @ResponseBody
	MessageResult updateAuthRolePermission(AuthRolePermission entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthRolePermission.do")
	public @ResponseBody
	GridResult listAuthRolePermission(AuthRolePermissionSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthRolePermission.do")
    public @ResponseBody Object listAllAuthRolePermission(AuthRolePermissionSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthRolePermissionById.do")
    public @ResponseBody AuthRolePermission getAuthRolePermissionById(AuthRolePermission entity) {
        return this.getService().get(entity.getId());
    }

	@RequestMapping("/getlistSystemFunctionIds.do")
    public @ResponseBody List<AuthRolePermission> getlistSystemFunctionIds(Integer id) {
 	
        return this.getService().getRolePermissionByRole(id);
    }

}