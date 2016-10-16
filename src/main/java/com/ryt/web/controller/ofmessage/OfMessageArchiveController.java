package com.ryt.web.controller.ofmessage;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.ofmessage.OfMessageArchive;
import com.ryt.web.entity.ofmessage.OfMessageArchiveSch;
import com.ryt.web.service.ofmessage.OfMessageArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OfMessageArchiveController extends
		CrudController<OfMessageArchive, OfMessageArchiveService> {

		//新增记录
	@RequestMapping("/addOfMessageArchive.do")
	public @ResponseBody
	MessageResult addOfMessageArchive(OfMessageArchive entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delOfMessageArchive.do")
	public @ResponseBody
	MessageResult delOfMessageArchive(OfMessageArchive entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateOfMessageArchive.do")
	public @ResponseBody
	MessageResult updateOfMessageArchive(OfMessageArchive entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listOfMessageArchive.do")
	public @ResponseBody
	GridResult listOfMessageArchive(OfMessageArchiveSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllOfMessageArchive.do")
    public @ResponseBody Object listAllOfMessageArchive(OfMessageArchiveSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

  

 	

}