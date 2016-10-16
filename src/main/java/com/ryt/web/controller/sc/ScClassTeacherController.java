package com.ryt.web.controller.sc;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScClassTeacherSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScClassTeacherService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScClassTeacherController extends
		CrudController<ScClassTeacher, ScClassTeacherService> {
	@Autowired
	UserService userService;
		//新增记录
	@RequestMapping("/addScClassTeacher.do")
	public @ResponseBody
	MessageResult addScClassTeacher(ScClassTeacher entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScClassTeacher.do")
	public @ResponseBody
	MessageResult delScClassTeacher(ScClassTeacher entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScClassTeacher.do")
	public @ResponseBody
	MessageResult updateScClassTeacher(ScClassTeacher entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listScClassTeacher.do")
	public @ResponseBody
	GridResult listScClassTeacher(ScClassTeacherSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScClassTeacher.do")
    public @ResponseBody Object listAllScClassTeacher(ScClassTeacherSch searchEntitySch) {
		DefaultGridResult gridResult = (DefaultGridResult)this.queryAll(searchEntitySch);
		for (int i = 0; i < gridResult.getRows().size(); i++) {
            int teacherId = ((ScClassTeacher) gridResult.getRows().get(i)).getTeacherId();
            User user = userService.get(teacherId);
            if (user!= null) {
                ((ScClassTeacher) gridResult.getRows().get(i)).setTeacherName(user.getTrueName());

			}
        }
		return gridResult;
    }
	

    //获取详细信息
	@RequestMapping("/getScClassTeacherById.do")
    public @ResponseBody ScClassTeacher getScClassTeacherById(ScClassTeacher entity) {
        return this.getService().get(entity.getId());
    }

 	

}