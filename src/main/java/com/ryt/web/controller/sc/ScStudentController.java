package com.ryt.web.controller.sc;

import java.text.SimpleDateFormat;
import java.util.List;
import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScStudentController extends
		CrudController<ScStudent, ScStudentService> {
	
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	UserService userService;
	@Autowired
	ScStudentParentsService studentParentsService;
	@Autowired
	ScClassService scClassService;
	
	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : ScStudent 
	* @Description    : 1.create an user of student
	* 					2.add studentId in student table
	* 					3.attention: before every new student user add to the database
	* 					, the button of "添加" must be triggered , Otherwise the exception 
	* 					of "Duplicate entry '116' for key 'PRIMARY'" will be thrown  			   
	* @Date           : 2015年11月9日 上午11:25:05    
	******************************************************************************/
		//新增记录
	@RequestMapping("/addScStudent.do")
	public @ResponseBody
	MessageResult addScStudent(ScStudent entity) {
			

			if(entity.getUser()!=null)
			{
					entity.getUser().setId(null);
					userService.save(entity.getUser());
					int studentId = entity.getUser().getId();
					
					ScClass scClass =scClassService.get(entity.getClassId());
					if(scClass!=null) //设置班主任
						entity.setTeacherId(scClass.getManagerId());
					
					entity.setStudentId(studentId);
					this.save(entity);
					return success();
			}
			else
			{
					System.out.println("usercontrol.adduser can not get user");
					return error("can not get user");
			} 

	}

	//删除记录
	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : ScStudent
	* @Description    : 0.del user of student
	* 					1.find StudentParents associate table by studentId
	* 					2.find parentId list from the StudentParents associate table
	* 					3.find user list by parentId list
	* 					4.del users in user list(parents)
	* 					5.del entity of student 
	* @Date           : 2015年11月9日 上午11:58:29    
	******************************************************************************/
	@RequestMapping("/delScStudent.do")
	public @ResponseBody
	MessageResult delScStudent(ScStudent entity) {
		 List<ScStudentParents> scStudentParents = scStudentParentsService.getStudentParentsBySId(entity.getStudentId());
		 
		 User user = null;
		 try {
			 userService.del(userService.get(entity.getStudentId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 for (int i = 0; i < scStudentParents.size(); i++) {
			 try {
				 if(scStudentParents.get(i).getParentId()!=null)
				 {
					 user = userService.get(scStudentParents.get(i).getParentId());
					 userService.del(user);
				 }
				 scStudentParentsService.del(scStudentParents.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScStudent.do")
	public @ResponseBody
	MessageResult updateScStudent(ScStudent entity) {
		
		userService.update(entity.getUser());
		
		//设置班主任
		ScClass scClass =scClassService.get(entity.getClassId());
		if(scClass!=null) //设置班主任
			entity.setTeacherId(scClass.getManagerId());
		
		return this.update(entity);
	}

	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : classIdSch 
	* @Description    : 1.find student list by classIdSch 
	* 					2.add student user in student 
	* @Date           : 2015年11月12日 下午2:44:36    
	******************************************************************************/
	@RequestMapping("/listScStudent.do")
	public @ResponseBody
	GridResult listScStudent(ScStudentSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
		DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
        for (int i = 0; i < resultGrid.getRows().size(); i++) {
            int studentId = ((ScStudent) resultGrid.getRows().get(i)).getStudentId();
            User user = userService.get(studentId);
            if (user!= null) {
                ((ScStudent) resultGrid.getRows().get(i)).setUser(user);

			} else {
				System.out.println("student user is null  studentId:"+ studentId);
			}
            
        }
		return resultGrid;
	}

	@RequestMapping("/listScStudentStatistic.do")
	public @ResponseBody
	GridResult listScStudentStatistic(ScStudentSch searchEntitySch,Integer agentIdSch) {
		if(agentIdSch == null){
			agentIdSch = 0;
		}
		if(searchEntitySch.getSchoolIdSch() == null){
			searchEntitySch.setSchoolIdSch(0);
		}
		if(searchEntitySch.getClassIdSch() == null){
			searchEntitySch.setClassIdSch(0);
		}
		String beginDate = null;
		String endDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(searchEntitySch.getCreatedStartSch() != null){
			beginDate = format.format(searchEntitySch.getCreatedStartSch());
		}
		if(searchEntitySch.getCreatedEndSch() != null){
			endDate = format.format(searchEntitySch.getCreatedEndSch());
		}
		List<ScStudent> list = this.getService().getStudentsByAgentId(agentIdSch, searchEntitySch.getSchoolIdSch(), searchEntitySch.getClassIdSch(), searchEntitySch.getStudentCodeSch(), beginDate, endDate);
		for (int i = 0; i < list.size(); i++) {
            int studentId = (list.get(i)).getStudentId();
            User user = userService.get(studentId);
            if (user!= null) {
            	list.get(i).setUser(user);
			}
            
        }
		return new DefaultGridResult(list);
	}
	
	//条件查询分页操作
	@RequestMapping("/listScParents.do")
	public @ResponseBody
	GridResult listScParents(ScStudentSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
		DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
        for (int i = 0; i < resultGrid.getRows().size(); i++) {
            int studentId = ((ScStudent) resultGrid.getRows().get(i)).getStudentId();
            ScStudentParents scStudentParents = scStudentParentsService.get(studentId);
            Integer parentId = scStudentParents.getParentId();
            User user = userService.get(parentId);
            if(user!= null)
            {((ScStudent) resultGrid.getRows().get(i)).setUser(user);}
            else
            {System.out.println("parent user is null parentId:" + parentId);}
        }
		return resultGrid;
	}
	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScStudent.do")
    public @ResponseBody Object listAllScStudent(ScStudentSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScStudentById.do")
    public @ResponseBody ScStudent getScStudentById(ScStudent entity) {
        return this.getService().get(entity.getId());
    }
	
}