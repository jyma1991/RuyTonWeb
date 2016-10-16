package com.ryt.web.controller.sc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ryt.web.common.MemcacheManager;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassSchedule;
import com.ryt.web.entity.sc.ScClassScheduleSch;
import com.ryt.web.entity.sc.ScCourse;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.service.sc.ScClassScheduleService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScCourseService;
import com.ryt.web.service.sc.ScSchoolService;

import freemarker.core.ReturnInstruction.Return;

@Controller
public class ScClassScheduleController extends
		CrudController<ScClassSchedule, ScClassScheduleService> {
	
	@Autowired
	ScCourseService scCourseService;
	
	@Autowired
	ScClassService scClassService;
	
	@Autowired
	ScSchoolService scSchoolService;
	//新增记录
	@RequestMapping("/addScClassSchedule.do")
	public @ResponseBody
	MessageResult addScClassSchedule(ScClassSchedule entity) {
		if(entity.getSchoolId()== null || entity.getSchoolId().equals(0)){
			entity.setSchoolId(scSchoolService.getSchoolUserBySchoolId().getSchoolId());
		}
		ScSchool school = scSchoolService.getSchoolBySchoolId(entity.getSchoolId());
		if(school != null){
			entity.setAgentId(school.getAgentId());
		}
		MemcacheManager.delete(entity.getSchoolId()+"_"+entity.getClassId()+"_course");
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delScClassSchedule.do")
	public @ResponseBody
	MessageResult delScClassSchedule(ScClassSchedule entity) {
		ScClassSchedule classSchedule = this.getService().get(entity.getId());
		MemcacheManager.delete(classSchedule.getSchoolId()+"_"+classSchedule.getClassId()+"_course");
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateScClassSchedule.do")
	public @ResponseBody
	MessageResult updateScClassSchedule(ScClassSchedule entity) {
		MemcacheManager.delete(this.getService().get(entity.getId()).getSchoolId()+"_"+entity.getClassId()+"_course");
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listScClassSchedule.do")
	public @ResponseBody
	GridResult listScClassSchedule(ScClassScheduleSch searchEntitySch,Integer schoolId,String className,String beginDate,String endDate) {
		//DefaultGridResult result = (DefaultGridResult) this.query(searchEntitySch);
		//List<ScClassSchedule> list = (List<ScClassSchedule>) result.getRows();
		List<ScClassSchedule> list = null;
		DefaultGridResult result = null;
		if(searchEntitySch.getSchoolIdSch() != null || searchEntitySch.getClassIdSch() != null || searchEntitySch.getCreatedStartSch() != null || searchEntitySch.getCreatedEndSch() != null){
			/**if(schoolId == null){
				schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
			}
			if(schoolId > 0){
				searchEntitySch.setSchoolIdSch(schoolId);
			}else{
				searchEntitySch.setSchoolIdSch(null);
			}
			searchEntitySch.setBeginDateSch(beginDate.trim());
			searchEntitySch.setEndDateSch(endDate.trim());
			
			list = this.getService().findClassScheduleByClassName(className.trim(),beginDate.trim(),endDate.trim(),schoolId);
			//如果是根据学校来判断的话
			if(!schoolId.equals(0)){
				for(int i = 0; i<list.size();i++){
					if(list.size() >i){
						if(!list.get(i).getSchoolId().equals(schoolId)){
							list.remove(i);
							i--;
						}
					}
				}
			}**/
			//仅仅表示当幼儿园园长登录时才会发生的情况
			if(searchEntitySch.getSchoolIdSch() != null && searchEntitySch.getSchoolIdSch().equals(0)){
				schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
				searchEntitySch.setSchoolIdSch(schoolId);
			}
			result = (DefaultGridResult)this.query(searchEntitySch);
			list = (List<ScClassSchedule>) result.getRows();
		}else{
			//修改过后这段代码暂时不会被调用到
			if(searchEntitySch.getSchoolIdSch() == null){
				schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
				searchEntitySch.setSchoolIdSch(schoolId);
			}
			if(searchEntitySch.getSchoolIdSch().equals(0)){
				searchEntitySch.setSchoolIdSch(null);
			}
			result = (DefaultGridResult) this.query(searchEntitySch);
			list = (List<ScClassSchedule>) result.getRows();
		}
 		
		List<ScCourse> courses = null;
		List<ScClass> classes = null;
		if(list == null || list.size() == 0){
			result = new DefaultGridResult(list);
			return result;
		}else{
			//根据学校Id查询所有的课程信息
			courses = scCourseService.getCoursesbySchoolId(searchEntitySch.getSchoolIdSch());
			//查询某个学校下面所有的教室，69表示教室，70表示课程
			classes = scClassService.getClassListBySchoolId(searchEntitySch.getSchoolIdSch(), 69);
		}
		
		//给每个课程表设置课程
		for(int i = 0; i < list.size(); i++){
			if(courses != null){
				if(!setCourse(list.get(i),courses,list.get(i).getD1(),1)){
					list.get(i).setCourse1(new ScCourse());
					list.get(i).setD1(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD2(),2)){
					list.get(i).setCourse2(new ScCourse());
					list.get(i).setD2(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD3(),3)){
					list.get(i).setCourse3(new ScCourse());
					list.get(i).setD3(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD3(),3)){
					list.get(i).setCourse3(new ScCourse());
					list.get(i).setD3(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD4(),4)){
					list.get(i).setCourse4(new ScCourse());
					list.get(i).setD4(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD5(),5)){
					list.get(i).setCourse5(new ScCourse());
					list.get(i).setD5(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD6(),6)){
					list.get(i).setCourse6(new ScCourse());
					list.get(i).setD6(0);
				}
				if(!setCourse(list.get(i),courses,list.get(i).getD7(),7)){
					list.get(i).setCourse7(new ScCourse());
					list.get(i).setD7(0);
				}
			}
			
			/**if(list.get(i).getD1() != null && list.get(i).getD1() >0){
				list.get(i).setCourse1(scCourseService.get(list.get(i).getD1()));
			}else{
				list.get(i).setCourse1(new ScCourse());
				list.get(i).setD1(null);
			}
			if(list.get(i).getD2() != null && list.get(i).getD2() >0){
				list.get(i).setCourse2(scCourseService.get(list.get(i).getD2()));
			}else{
				list.get(i).setCourse2(new ScCourse());
				list.get(i).setD2(null);
			}
			if(list.get(i).getD3() != null && list.get(i).getD3() >0){
				list.get(i).setCourse3(scCourseService.get(list.get(i).getD3()));
			}else{
				list.get(i).setCourse3(new ScCourse());
				list.get(i).setD3(null);
			}
			if(list.get(i).getD4() != null && list.get(i).getD4() >0){
				list.get(i).setCourse4(scCourseService.get(list.get(i).getD4()));
			}else{
				list.get(i).setCourse4(new ScCourse());
				list.get(i).setD4(null);
			}
			if(list.get(i).getD5() != null && list.get(i).getD5() >0){
				list.get(i).setCourse5(scCourseService.get(list.get(i).getD5()));
			}else{
				list.get(i).setCourse5(new ScCourse());
				list.get(i).setD5(null);
			}
			if(list.get(i).getD6() != null && list.get(i).getD6() >0){
				list.get(i).setCourse6(scCourseService.get(list.get(i).getD6()));
			}else{
				list.get(i).setCourse6(new ScCourse());
				list.get(i).setD6(null);
			}
			if(list.get(i).getD7() != null && list.get(i).getD7() >0){
				list.get(i).setCourse7(scCourseService.get(list.get(i).getD7()));
			}else{
				list.get(i).setCourse7(new ScCourse());
				list.get(i).setD7(null);
			}**/
			/*if(list.get(i).getSchoolId() >0){
				list.get(i).setCourses(scCourseService.getCoursesbySchoolId(list.get(i).getSchoolId()));
			}*/
			if(classes != null){
				if(list.get(i).getClassId() > 0){
					//list.get(i).setScClass(scClassService.get(list.get(i).getClassId()));
					for(int j=0 ;j<classes.size(); j++){
						if(classes.get(j).getId().equals(list.get(i).getClassId())){
							ScClass scClass = new ScClass(classes.get(j));
							list.get(i).setScClass(scClass);
							break;
						}
					}
				}else{
					list.get(i).setScClass(new ScClass());
				}
			}
			
		}
		return result;
	}

	//获取园长管理的学校下面的所有班级（教室）
	@RequestMapping("/listSchoolClasses.do")
	public @ResponseBody Object listSchoolClasses(int placeTypeId,int schoolIdSch){
		List<ScClass> list = null;
		if(schoolIdSch ==0){
			int schoolId = scSchoolService.getSchoolUserBySchoolId().getSchoolId();
			list = scClassService.getClassListBySchoolId(schoolId,placeTypeId);
		}else{
			list = scClassService.getClassListBySchoolId(schoolIdSch,placeTypeId);
		}
		
		return list;
	}
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScClassSchedule.do")
    public @ResponseBody Object listAllScClassSchedule(ScClassScheduleSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getScClassScheduleById.do")
    public @ResponseBody ScClassSchedule getScClassScheduleById(ScClassSchedule entity) {
        return this.getService().get(entity.getId());
    }
	//条件查询课程
 	@RequestMapping("/findClassScheduleByClassName.do")
 	public @ResponseBody Object findClassScheduleByClassName(String className,String beginDate,String endDate,int schoolId){
 		Map<String, Object> map = new HashMap<String, Object>();
 		List<ScClassSchedule> list = this.getService().findClassScheduleByClassName(className.trim(),beginDate.trim(),endDate.trim(),schoolId);
 		if(list !=null && list.size()>0){
 			map.put("success", true);
 			map.put("list", list);
 		}else{
 			map.put("success", false);
 		}
 		//System.out.println(JSONObject.fromObject(map).toString());
 		System.out.println(JSONObject.toJSONString(map));
 		return JSONObject.toJSONString(map);
 	}
 	
 	//设置课程
 	public Boolean setCourse(ScClassSchedule classSchedule,List<ScCourse> courses,Integer courseId,int index){
 		boolean flag = false;
 		if(courseId != null && courseId > 0){
 			for(int i= 0; i< courses.size(); i++){
 				if(courses.get(i).getId().equals(courseId)){
 					ScCourse course = new ScCourse(courses.get(i));
 					flag = true;
 					switch (index) {
					case 1:
						classSchedule.setCourse1(course);
						break;
					case 2:
						classSchedule.setCourse2(course);
						break;	
					case 3:
						classSchedule.setCourse3(course);
						break;
					case 4:
						classSchedule.setCourse4(course);
						break;
					case 5:
						classSchedule.setCourse5(course);
						break;	
					case 6:
						classSchedule.setCourse6(course);
						break;
					case 7:
						classSchedule.setCourse7(course);
						break;
					default:
						break;
					}
 					break;
 				}
 			}
 		}
 		return flag;
 	}
}