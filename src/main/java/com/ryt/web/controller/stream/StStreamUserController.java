package com.ryt.web.controller.stream;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.stream.StStreamUser;
import com.ryt.web.entity.stream.StStreamUserSch;
import com.ryt.web.service.stream.StStreamUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StStreamUserController extends
		CrudController<StStreamUser, StStreamUserService> {

		//新增记录
	@RequestMapping("/addStStreamUser.do")
	public @ResponseBody
	MessageResult addStStreamUser(StStreamUser entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delStStreamUser.do")
	public @ResponseBody
	MessageResult delStStreamUser(StStreamUser entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateStStreamUser.do")
	public @ResponseBody
	MessageResult updateStStreamUser(StStreamUser entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listStStreamUser.do")
	public @ResponseBody
	GridResult listStStreamUser(StStreamUserSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllStStreamUser.do")
    public @ResponseBody Object listAllStStreamUser(StStreamUserSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getStStreamUserById.do")
    public @ResponseBody StStreamUser getStStreamUserById(StStreamUser entity) {
        return this.getService().get(entity.getId());
    }

 	

}