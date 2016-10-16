package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthDataTypeResource;
import com.ryt.web.entity.auth.AuthDataTypeResourceSch;
import com.ryt.web.service.auth.AuthDataTypeResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthDataTypeResourceController extends
		CrudController<AuthDataTypeResource, AuthDataTypeResourceService> {

		//新增记录
	@RequestMapping("/addAuthDataTypeResource.do")
	public @ResponseBody
	MessageResult addAuthDataTypeResource(AuthDataTypeResource entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthDataTypeResource.do")
	public @ResponseBody
	MessageResult delAuthDataTypeResource(AuthDataTypeResource entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthDataTypeResource.do")
	public @ResponseBody
	MessageResult updateAuthDataTypeResource(AuthDataTypeResource entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthDataTypeResource.do")
	public @ResponseBody
	GridResult listAuthDataTypeResource(AuthDataTypeResourceSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthDataTypeResource.do")
    public @ResponseBody Object listAllAuthDataTypeResource(AuthDataTypeResourceSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthDataTypeResourceById.do")
    public @ResponseBody AuthDataTypeResource getAuthDataTypeResourceById(AuthDataTypeResource entity) {
        return this.getService().get(entity.getId());
    }

 	

}