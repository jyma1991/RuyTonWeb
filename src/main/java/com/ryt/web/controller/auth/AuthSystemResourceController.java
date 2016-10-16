package com.ryt.web.controller.auth;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.auth.AuthSystemResource;
import com.ryt.web.entity.auth.AuthSystemResourceSch;
import com.ryt.web.service.auth.AuthSystemResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class AuthSystemResourceController extends
		CrudController<AuthSystemResource, AuthSystemResourceService> {

		//新增记录
	@SuppressWarnings("unchecked")
	@RequestMapping("/addAuthSystemResource.do")
	public @ResponseBody
	MessageResult addAuthSystemResource(AuthSystemResource entity,String parentId) {
		if(entity.getSysResName().trim().length() == 0){
			return error("输入的资源名称不能为空并且不能只由空格组成！");
		}else{
			entity.setSysResName(entity.getSysResName().trim());
		}
		DefaultGridResult result = (DefaultGridResult) this.queryAll(new AuthSystemResourceSch());
		List<AuthSystemResource> list = (List<AuthSystemResource>)result.getRows();
		if(list!=null && list.size() > 0){
			for(AuthSystemResource authSystemResource : list){
				if(authSystemResource.getSysResName().equals(entity.getSysResName())){
					return error("资源的名称不能重复！请重新输入！");
				}
			}
		}
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAuthSystemResource.do")
	public @ResponseBody
	MessageResult delAuthSystemResource(AuthSystemResource entity) {
    	System.out.println("进入了delAuthSystemResource.do的action中");
		if (this.getService().hasChild(entity)) {
            return error(entity.getSysResName() + "下含有子节点,不能删除.");
        }
		System.out.println("即将删除资源...");
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAuthSystemResource.do")
	public @ResponseBody
	MessageResult updateAuthSystemResource(AuthSystemResource entity) {
		System.out.println("进入了updateAuthSystemResource.do的action中");
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAuthSystemResource.do")
	public @ResponseBody
	GridResult listAuthSystemResource(AuthSystemResourceSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAuthSystemResource.do")
    public @ResponseBody Object listAllAuthSystemResource(AuthSystemResourceSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAuthSystemResourceById.do")
    public @ResponseBody AuthSystemResource getAuthSystemResourceById(AuthSystemResource entity) {
        return this.getService().get(entity.getId());
    }

 	

}