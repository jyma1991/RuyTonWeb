package com.ryt.web.controller.sc;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.sc.ScTeacherCourseSch;
import com.ryt.web.service.sc.ScTeacherCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScTeacherCourseController extends
		CrudController<ScTeacherCourse, ScTeacherCourseService> {

		//新增记录
	@RequestMapping("/addScTeacherCourse.do")
	public @ResponseBody
	MessageResult addScTeacherCourse(ScTeacherCourse entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScTeacherCourse.do")
	public @ResponseBody
	MessageResult delScTeacherCourse(ScTeacherCourse entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScTeacherCourse.do")
	public @ResponseBody
	MessageResult updateScTeacherCourse(ScTeacherCourse entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listScTeacherCourse.do")
	public @ResponseBody
	GridResult listScTeacherCourse(ScTeacherCourseSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScTeacherCourse.do")
    public @ResponseBody Object listAllScTeacherCourse(ScTeacherCourseSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScTeacherCourseById.do")
    public @ResponseBody ScTeacherCourse getScTeacherCourseById(ScTeacherCourse entity) {
        return this.getService().get(entity.getId());
    }

 	

}