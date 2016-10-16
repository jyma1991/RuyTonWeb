package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthDataPermission;
import com.ryt.web.entity.auth.AuthDataPermissionSch;
import com.ryt.web.service.auth.AuthDataPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthDataPermissionController extends
		CrudController<AuthDataPermission, AuthDataPermissionService> {

		//新增记录
	@RequestMapping("/addAuthDataPermission.do")
	public @ResponseBody
	MessageResult addAuthDataPermission(AuthDataPermission entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthDataPermission.do")
	public @ResponseBody
	MessageResult delAuthDataPermission(AuthDataPermission entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthDataPermission.do")
	public @ResponseBody
	MessageResult updateAuthDataPermission(AuthDataPermission entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthDataPermission.do")
	public @ResponseBody
	GridResult listAuthDataPermission(AuthDataPermissionSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthDataPermission.do")
    public @ResponseBody Object listAllAuthDataPermission(AuthDataPermissionSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthDataPermissionById.do")
    public @ResponseBody AuthDataPermission getAuthDataPermissionById(AuthDataPermission entity) {
        return this.getService().get(entity.getId());
    }

 	

}