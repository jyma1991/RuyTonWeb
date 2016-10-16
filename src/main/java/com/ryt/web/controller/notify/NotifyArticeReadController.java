package com.ryt.web.controller.notify;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.notify.NotifyArticeRead;
import com.ryt.web.entity.notify.NotifyArticeReadSch;
import com.ryt.web.service.notify.NotifyArticeReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotifyArticeReadController extends
		CrudController<NotifyArticeRead, NotifyArticeReadService> {

		//新增记录
	@RequestMapping("/addNotifyArticeRead.do")
	public @ResponseBody
	MessageResult addNotifyArticeRead(NotifyArticeRead entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delNotifyArticeRead.do")
	public @ResponseBody
	MessageResult delNotifyArticeRead(NotifyArticeRead entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateNotifyArticeRead.do")
	public @ResponseBody
	MessageResult updateNotifyArticeRead(NotifyArticeRead entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listNotifyArticeRead.do")
	public @ResponseBody
	GridResult listNotifyArticeRead(NotifyArticeReadSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllNotifyArticeRead.do")
    public @ResponseBody Object listAllNotifyArticeRead(NotifyArticeReadSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getNotifyArticeReadById.do")
    public @ResponseBody NotifyArticeRead getNotifyArticeReadById(NotifyArticeRead entity) {
        return this.getService().get(entity.getId());
    }

 	

}