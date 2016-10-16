package com.ryt.web.controller.sc;
import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ryt.web.common.MemcacheManager;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScCourse;
import com.ryt.web.entity.sc.ScCourseSch;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScCourseService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScTeacherCourseService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScCourseController extends
		CrudController<ScCourse, ScCourseService> {
	
	@Autowired
	ScSchoolService scSchoolService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScTeacherCourseService scTeacherCourseService;
	//新增记录
	@RequestMapping("/addScCourse.do")
	public @ResponseBody
	MessageResult addScCourse(ScCourse entity) {
		//如果没有选中学校
		if(entity.getSchoolId() == null || entity.getSchoolId().equals(0)){
			int schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
			entity.setSchoolId(schoolId);
		}
		List<Integer> teachers = entity.getTeachers();
		Integer courseId = entity.getId();
		// delete the old relative
		scTeacherCourseService.delByCourseId(courseId);
		//add new relative
		for (Integer teacherId : teachers) {
			ScTeacherCourse scTeacherCourse = new ScTeacherCourse();
			scTeacherCourse.setCourseId(courseId);
			scTeacherCourse.setTeacherId(teacherId);
			try {
				scTeacherCourseService.save(scTeacherCourse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delScCourse.do")
	public @ResponseBody
	MessageResult delScCourse(ScCourse entity) {
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateScCourse.do")
	public @ResponseBody
	MessageResult updateScCourse(ScCourse entity) {
		List<Integer> teachers = entity.getTeachers();
		Integer courseId = entity.getId();
		// delete the old relative
		scTeacherCourseService.delByCourseId(courseId);
		//add new relative
		for (Integer teacherId : teachers) {
			ScTeacherCourse scTeacherCourse = new ScTeacherCourse();
			scTeacherCourse.setCourseId(courseId);
			scTeacherCourse.setTeacherId(teacherId);
			try {
				scTeacherCourseService.save(scTeacherCourse);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listScCourse.do")
	public @ResponseBody
	GridResult listScCourse(ScCourseSch searchEntitySch) {
		if(searchEntitySch.getSchoolIdSch() == null){
			int schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
			searchEntitySch.setSchoolIdSch(schoolId);
		}
		//管理员查看全部的班级
		if(searchEntitySch.getSchoolIdSch().equals(0)){
			searchEntitySch.setSchoolIdSch(null);
		}
		DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
		try {
			 for (int i = 0; i < resultGrid.getRows().size(); i++) {
			    	List<Integer> teachers = new ArrayList<Integer>();
			    	ScCourse scCourse = ((ScCourse) resultGrid.getRows().get(i));
			        int courseId = scCourse.getId();
					List< ScTeacherCourse> teacherCourse = this.getService().findScTecherCourseByTeacherId(courseId);
					if (teacherCourse != null) {
						for (int j = 0; j < teacherCourse.size(); j++) {
							teachers.add(teacherCourse.get(j).getTeacherId());
							scCourse.setTeachers(teachers);
						}

					}
			    }  
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		
		return resultGrid;
	}

	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScCourse.do")
    public @ResponseBody Object listAllScCourse(ScCourseSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScCourseJson.do")
    public @ResponseBody Object listAllScCourseJson(ScCourseSch searchEntitySch) {
		//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
		DefaultGridResult object = (DefaultGridResult)this.queryAll(searchEntitySch);
		/**
		JSONObject jsonObject = new JSONObject();
		JSONArray json = null;
		try {
			jsonObject = jsonObject.fromObject(object);
		} catch (JSONException e) {
			// TODO 鑷姩鐢熸垚鐨�catch 鍧�
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		json = JSONArray.fromObject(jsonObject.get("rows").toString());**/
		List<ScCourse> courses = (List<ScCourse>)object.getRows();
		System.out.println(JSONArray.toJSONString(courses));
		return JSONArray.toJSONString(courses);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getScCourseById.do")
    public @ResponseBody ScCourse getScCourseById(ScCourse entity) {
        return this.getService().get(entity.getId());
    }

	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScCourseJsonBySchoolId.do")
    public @ResponseBody Object listAllScCourseJson(int schoolIdSch) {
		int schoolId = 0;
		if(schoolIdSch == 0){
			schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
		}else{
			schoolId = schoolIdSch;
		}
		return this.getService().getCoursesbySchoolId(schoolId);
	}

}