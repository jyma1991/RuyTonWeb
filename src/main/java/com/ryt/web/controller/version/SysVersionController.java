package com.ryt.web.controller.version;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.version.SysVersion;
import com.ryt.web.entity.version.SysVersionSch;
import com.ryt.web.service.version.SysVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysVersionController extends
		CrudController<SysVersion, SysVersionService> {

		//新增记录
	@RequestMapping("/addSysVersion.do")
	public @ResponseBody
	MessageResult addSysVersion(SysVersion entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delSysVersion.do")
	public @ResponseBody
	MessageResult delSysVersion(SysVersion entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateSysVersion.do")
	public @ResponseBody
	MessageResult updateSysVersion(SysVersion entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listSysVersion.do")
	public @ResponseBody
	GridResult listSysVersion(SysVersionSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllSysVersion.do")
    public @ResponseBody Object listAllSysVersion(SysVersionSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getSysVersionById.do")
    public @ResponseBody SysVersion getSysVersionById(SysVersion entity) {
        return this.getService().get(entity.getId());
    }

 	

}