package com.ryt.web.controller.auth;

import java.util.List;
import java.util.Map;
import org.durcframework.core.GridResult;
import org.durcframework.core.JsonObjProcessor;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.common.AddOperateParam;
import com.ryt.web.entity.auth.AuthRole;
import com.ryt.web.entity.auth.AuthSystemFunction;
import com.ryt.web.entity.auth.AuthSystemFunctionSch;
import com.ryt.web.entity.auth.AuthSystemResource;
import com.ryt.web.entity.auth.AuthSytemsOperate;
import com.ryt.web.service.auth.AuthRoleService;
import com.ryt.web.service.auth.AuthSystemFunctionService;
import com.ryt.web.service.auth.AuthSystemResourceService;
import com.ryt.web.service.auth.AuthSytemsOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthSystemFunctionController extends
		CrudController<AuthSystemFunction, AuthSystemFunctionService> {

    @Autowired
    private AuthSytemsOperateService aSystemsOperateService;
    @Autowired
    private AuthRoleService roleService;
    @Autowired
    private AuthSystemResourceService aSystemsResourceService;
		//新增记录
	@RequestMapping("/addAuthSystemFunction.do")
    public @ResponseBody Object addRSysFunction(AddOperateParam entity) {
    	System.out.println(entity.getOperateCode()+" "+entity.getId());
    	AuthSytemsOperate operate = aSystemsOperateService.getByOperateCode(entity.getOperateCode());
        if (operate == null) {
            return error("操作类型不存在");
	}
        AuthSystemResource res = aSystemsResourceService.get(entity.getId());
        if (res == null) {
            return error("资源不存在");
        }

        if (this.getService().isExistSysFun(entity.getOperateCode(), entity.getId())) {
        	System.out.println("操作点已添加");
            return error("操作点已添加");
        }
        aSystemsOperateService.add(res, operate);
        return success();
    }
	//删除记录
	@RequestMapping("/delAuthSystemFunction.do")
	public @ResponseBody
	MessageResult delAuthSystemFunction(AuthSystemFunction entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthSystemFunction.do")
	public @ResponseBody
	MessageResult updateAuthSystemFunction(AuthSystemFunction entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthSystemFunction.do")
	public @ResponseBody
	GridResult listAuthSystemFunction(AuthSystemFunctionSch searchEntitySch) {
		return this.query(searchEntitySch);
	}
	@RequestMapping("/listAuthSystemFunctionBySearch.do")
	 public @ResponseBody Object listRSysFunction(AuthSystemFunctionSch searchEntitySch) {

        return this.queryWithProcessor(searchEntitySch, new JsonObjProcessor<AuthSystemFunction>() {
            public void process(AuthSystemFunction entity, Map<String, Object> jsonObject) {
            	AuthSytemsOperate operate = aSystemsOperateService.getByOperateCode(entity.getOperateCode());//get(entity.getOperateCode());
                jsonObject.put("operateName", operate.getOperateName());
                jsonObject.put("operateCode", operate.getOperateCode());
                List<AuthRole> roles = roleService.getRolesBySysFunction(entity);
                jsonObject.put("roles", roles);
            }
        });
    }
	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthSystemFunction.do")
    public @ResponseBody Object listAllAuthSystemFunction(AuthSystemFunctionSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthSystemFunctionById.do")
    public @ResponseBody AuthSystemFunction getAuthSystemFunctionById(AuthSystemFunction entity) {
        return this.getService().get(entity.getId());
    }

 	

}