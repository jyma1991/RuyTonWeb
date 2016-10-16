package com.ryt.web.controller.sc;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScSignInOutSch;
import com.ryt.web.service.sc.ScSignInOutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScSignInOutController extends
		CrudController<ScSignInOut, ScSignInOutService> {

		//新增记录
	@RequestMapping("/addScSignInOut.do")
	public @ResponseBody
	MessageResult addScSignInOut(ScSignInOut entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScSignInOut.do")
	public @ResponseBody
	MessageResult delScSignInOut(ScSignInOut entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScSignInOut.do")
	public @ResponseBody
	MessageResult updateScSignInOut(ScSignInOut entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listScSignInOut.do")
	public @ResponseBody
	GridResult listScSignInOut(ScSignInOutSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScSignInOut.do")
    public @ResponseBody Object listAllScSignInOut(ScSignInOutSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScSignInOutById.do")
    public @ResponseBody ScSignInOut getScSignInOutById(ScSignInOut entity) {
        return this.getService().get(entity.getId());
    }

	@RequestMapping("/getScSignInOut4Student.do")
    public @ResponseBody Object getScSignInOut(ScSignInOut entity) {
        return this.getService().getSignInfoStudent4Student(entity);
    }
	@RequestMapping("/getSignInfo4Teacher.do")
	public @ResponseBody Object getSignInfo4Teacher(ScClassSch scClassSch){
		return this.getService().getSignInfo4Teacher(scClassSch);
	}
}