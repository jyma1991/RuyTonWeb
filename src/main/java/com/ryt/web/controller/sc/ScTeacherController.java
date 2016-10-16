package com.ryt.web.controller.sc;

import java.util.ArrayList;
import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.common.Utils;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScClassTeacherService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScTeacherController extends
		CrudController<ScTeacher, ScTeacherService> {
	@Autowired
	ScClassTeacherService scClassTeacherService;
	@Autowired
	UserService userService;

	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : entity {classes,User{userName}}
	* @Description    : 1. add user with userName (get teacherId=userId)
	* 					2. new classTeacher and set classId and teacherId and save
	* 					3. add teacher entity
	* @Date           : 2015年11月12日 下午1:53:10    
	******************************************************************************/
	@RequestMapping("/addScTeacher.do")
	public @ResponseBody
	MessageResult addScTeacher(ScTeacher entity) {
		if (!Utils.canntBeNull(entity,"classes,user")) {
			System.out.println("数据不全");
		}
		User user = entity.getUser();
		if(entity.getClasses()==null)
		{System.out.println("teacher's class is null userid is " + entity.getUser().getId());}
		List<User> teachers = this.getService().findUserListByUserName(entity);
		if(user!=null && entity.getClasses()!=null )
		{
			if (teachers.size() == 0) {
				user.setRoleProperty(entity.getRoleProperty());
				userService.save(user);
				
				for (int i = 0; i < entity.getClasses().size(); i++) {
					ScClassTeacher classTeacher = new ScClassTeacher();
					classTeacher.setClassId((Integer)entity.getClasses().get(i));
					classTeacher.setTeacherId(user.getId());
					scClassTeacherService.save(classTeacher);
				}
				this.getService().saveScAgentIdBySchoolId(entity);
				entity.setTeacherId(user.getId());
				return this.save(entity);
			} else {
				return error("用户名已存在");
			}
			
		}
		else {
			System.out.println("TeacherControl.adduser; user is null");
			return error("user is null");
		}
		
	}

	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : entity {id,teacherId}
	* @Description    : 1.find teacher user by teacherId and del
	* 					2.del teacher entity by id
	* @Date           : 2015年11月12日 下午2:07:29    
	******************************************************************************/
	@RequestMapping("/delScTeacher.do")
	public @ResponseBody
	MessageResult delScTeacher(ScTeacher entity) {
		if (!Utils.canntBeNull(entity,"id,teacherId")) {
			System.out.println("数据不全");
		}
		userService.del(userService.get(entity.getTeacherId()));
		List<ScClassTeacher> scClassTeachers = scClassTeacherService.findScClassTechersByTeacherId(entity.getTeacherId());
		for (ScClassTeacher scClassTeacher : scClassTeachers) {
			scClassTeacherService.del(scClassTeacher);
		}
		return this.delete(entity);
	}
	
	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : entity{id,user{id,userName}}
	* @Description    : updata teacher user and teacher entity 
	* @Date           : 2015年11月12日 下午2:01:26    
	******************************************************************************/
	@RequestMapping("/updateScTeacher.do")
	public @ResponseBody
	MessageResult updateScTeacher(ScTeacher entity) {
		if (!Utils.canntBeNull(entity,"id,user")) {
			System.out.println("数据不全");
		}
		List<User> teachers = this.getService().findUserListByUserName(entity);
		if (teachers.size()==0||(teachers.get(0).getUsername().equals(entity.getUser().getUserName() ))) {
			
			List<ScClassTeacher> scClassTeachers = scClassTeacherService.findScClassTechersByTeacherId(entity.getUser().getId());
			for (ScClassTeacher scClassTeacher : scClassTeachers) {
				try {
					scClassTeacherService.del(scClassTeacher);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			List<Integer> classes = entity.getClasses();
			for (int i = 0; i < classes.size(); i++) {
				ScClassTeacher scClassTeacher = new ScClassTeacher(); 
				scClassTeacher.setTeacherId(entity.getUser().getId());
				scClassTeacher.setClassId(classes.get(i));
				scClassTeacherService.save(scClassTeacher);
			}
			User user =entity.getUser();
			userService.update(user);
			return this.update(entity);
		} else {
			return error("用户名已存在");
		}

	}

	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : classId 
	* @Description    : find teacher users by classId
	* 					add the user and teacherId list into teacher list
	* 					add teacher list into resultGrid
	* @Date           : 2015年11月12日 下午2:11:12    
	******************************************************************************/
		@RequestMapping("/listScTeacher.do")
		public @ResponseBody
		GridResult listScTeacher(ScTeacherSch searchEntitySch) {
			if (!Utils.canntBeNull(searchEntitySch,"schoolIdSch")) {
				System.out.println("数据不全");
			}
			DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
	        for (int i = 0; i < resultGrid.getRows().size(); i++) {
	        	List<Integer> classes = new ArrayList<Integer>();
	        	ScTeacher scTeacher = ((ScTeacher) resultGrid.getRows().get(i));
	            int teacherId = scTeacher.getTeacherId();
				List< ScClassTeacher> classTeachers = scClassTeacherService.findScClassTechersByTeacherId(teacherId);
				for (int j = 0; j < classTeachers.size(); j++) {
					classes.add(classTeachers.get(j).getClassId());
					scTeacher.setClasses(classes);
				}
	            User user = userService.get(teacherId);
	            scTeacher.setUser(user);
	        }  
			
			return resultGrid;
		}
		
		//统计查询老师列表详细数据
		@RequestMapping("/listScTeacherStatistics.do")
		public @ResponseBody
		GridResult listScTeacherStatistics(ScTeacherSch searchEntitySch) {
			DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
	        for (int i = 0; i < resultGrid.getRows().size(); i++) {
	        	ScTeacher scTeacher = ((ScTeacher) resultGrid.getRows().get(i));
	            int teacherId = scTeacher.getTeacherId();
	            User user = userService.get(teacherId);
	            scTeacher.setUser(user);
	        }  
			
			return resultGrid;
		}
		
		@RequestMapping("/getScTeacherByTrueName.do")
		public @ResponseBody
		Object getScTeacherByTrueName(ScTeacherSch searchEntitySch) {
			String userNameSch = searchEntitySch.getUserNameSch();
 			if(userNameSch==null||userNameSch=="")
			{
				DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
		        for (int i = 0; i < resultGrid.getRows().size(); i++) {
		        	List<Integer> classes = new ArrayList<Integer>();
		        	ScTeacher scTeacher = ((ScTeacher) resultGrid.getRows().get(i));
		            int teacherId = scTeacher.getTeacherId();
					List< ScClassTeacher> classTeachers = scClassTeacherService.findScClassTechersByTeacherId(teacherId);
					for (int j = 0; j < classTeachers.size(); j++) {
						classes.add(classTeachers.get(j).getClassId());
						scTeacher.setClasses(classes);
					}
		            User user = userService.get(teacherId);
		            scTeacher.setUser(user);
		        }  
				
				return resultGrid;
			}
			else 
			{
				List <ScTeacher> scTeachers = this.getService().findTeacherBytrueName(userNameSch,"","");
				DefaultGridResult resultGrid = new DefaultGridResult(scTeachers);
				for (int i = 0; i < resultGrid.getRows().size(); i++) {
		        	List<Integer> classes = new ArrayList<Integer>();
		        	ScTeacher scTeacher = ((ScTeacher) resultGrid.getRows().get(i));
		            int teacherId = scTeacher.getId();
					List< ScClassTeacher> classTeachers = scClassTeacherService.findScClassTechersByTeacherId(teacherId);
					for (int j = 0; j < classTeachers.size(); j++) {
						classes.add(classTeachers.get(j).getClassId());
						scTeacher.setClasses(classes);
					}
		            User user = userService.get(teacherId);
		            scTeacher.setUser(user);
		        }  
				return resultGrid;
			}
			
		}
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScTeacher.do")
    public @ResponseBody Object listAllScTeacher(ScTeacherSch searchEntitySch) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		DefaultGridResult resultGrid = (DefaultGridResult) this.queryAll(query);
		@SuppressWarnings("unchecked")
		List<ScTeacher> rows = (List<ScTeacher>) resultGrid.getRows();
		return rows;
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getScTeacherById.do")
    public @ResponseBody ScTeacher getScTeacherById(ScTeacher entity) {
        return this.getService().get(entity.getId());
    }

	@RequestMapping("/getScTeacherBySchoolId.do")
	public @ResponseBody
	Object getScTeacherBySchoolId(ScTeacherSch searchEntitySch) {
		return this.getService().findTeachersBySchoolId(searchEntitySch);
	}

	
	@RequestMapping("/findTeacherListByCondition.do")
	public @ResponseBody
	Object findTeacherListByCondition(int agentId,int schoolId,String userTrueName ,String beginTime , String endTime) {
		if(schoolId >0){}else{schoolId = 0;}
		if(agentId >0){}else{agentId = 0;}
		List <ScTeacher> scTeachers = this.getService().findTeacherListByCondition(agentId,schoolId,userTrueName,beginTime,endTime);
		DefaultGridResult resultGrid = new DefaultGridResult(scTeachers);
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
        	ScTeacher scTeacher = ((ScTeacher) resultGrid.getRows().get(i));
            int teacherId = scTeacher.getId();
            User user = userService.get(teacherId);
            scTeacher.setUser(user);
        }  
		
		return resultGrid;
	}
	
	@RequestMapping("/findTeacherListBySchoolId.do")
	public @ResponseBody
	Object findTeacherListBySchoolId(ScTeacherSch searchEntitySch) {
		searchEntitySch.setSchoolIdSch(UserContext.getInstance().getUser().getId());
		List <User> scTeachers = this.getService().findTeachersBySchoolId(searchEntitySch);
		DefaultGridResult resultGrid = new DefaultGridResult(scTeachers);
		return resultGrid;
	}
	

}