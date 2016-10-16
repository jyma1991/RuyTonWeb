package com.ryt.log.controller;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.log.entity.SysLog;
import com.ryt.log.entity.SysLogSch;
import com.ryt.log.service.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysLogController extends CrudController<SysLog, SysLogService> {

		//新增记录
	@RequestMapping("/addSysLog.do")
	public @ResponseBody
	MessageResult addSysLog(SysLog entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delSysLog.do")
	public @ResponseBody
	MessageResult delSysLog(SysLog entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateSysLog.do")
	public @ResponseBody
	MessageResult updateSysLog(SysLog entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listSysLog.do")
	public @ResponseBody
	GridResult listSysLog(SysLogSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllSysLog.do")
    public @ResponseBody Object listAllSysLog(SysLogSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getSysLogById.do")
    public @ResponseBody SysLog getSysLogById(SysLog entity) {
        return this.getService().get(entity.getId());
    }

 	

}