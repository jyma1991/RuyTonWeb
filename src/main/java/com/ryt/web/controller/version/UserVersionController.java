package com.ryt.web.controller.version;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.version.UserVersion;
import com.ryt.web.entity.version.UserVersionSch;
import com.ryt.web.service.version.UserVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserVersionController extends
		CrudController<UserVersion, UserVersionService> {

		//新增记录
	@RequestMapping("/addUserVersion.do")
	public @ResponseBody
	MessageResult addUserVersion(UserVersion entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delUserVersion.do")
	public @ResponseBody
	MessageResult delUserVersion(UserVersion entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateUserVersion.do")
	public @ResponseBody
	MessageResult updateUserVersion(UserVersion entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listUserVersion.do")
	public @ResponseBody
	GridResult listUserVersion(UserVersionSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllUserVersion.do")
    public @ResponseBody Object listAllUserVersion(UserVersionSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getUserVersionById.do")
    public @ResponseBody UserVersion getUserVersionById(UserVersion entity) {
        return this.getService().get(entity.getId());
    }

 	

}