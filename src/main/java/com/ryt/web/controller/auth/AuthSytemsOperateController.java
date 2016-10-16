package com.ryt.web.controller.auth;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthSytemsOperate;
import com.ryt.web.entity.auth.AuthSytemsOperateSch;
import com.ryt.web.service.auth.AuthSytemsOperateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthSytemsOperateController extends
		CrudController<AuthSytemsOperate, AuthSytemsOperateService> {

		//新增记录
	@RequestMapping("/addAuthSytemsOperate.do")
	public @ResponseBody
	MessageResult addAuthSytemsOperate(AuthSytemsOperate entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthSytemsOperate.do")
	public @ResponseBody
	MessageResult delAuthSytemsOperate(AuthSytemsOperate entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthSytemsOperate.do")
	public @ResponseBody
	MessageResult updateAuthSytemsOperate(AuthSytemsOperate entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthSytemsOperate.do")
	public @ResponseBody
	GridResult listAuthSytemsOperate(AuthSytemsOperateSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthSytemsOperate.do")
    public @ResponseBody Object listAllAuthSytemsOperate(AuthSytemsOperateSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthSytemsOperateById.do")
    public @ResponseBody AuthSytemsOperate getAuthSytemsOperateById(AuthSytemsOperate entity) {
        return this.getService().get(entity.getId());
    }

 	

}