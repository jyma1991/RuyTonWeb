package com.ryt.web.controller.sc;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.auth.AuthRoleUsers;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScSchoolSch;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.auth.AuthRoleUsersService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.user.UserService;
import com.ryt.web.util.PasswordUtil;

@Controller
public class ScSchoolController extends
		CrudController<ScSchool, ScSchoolService> {
	@Autowired
	UserService userService;
	@Autowired
	AuthRoleUsersService authRoleUsersService;
	@Autowired
	ScTeacherService scTeacherService;
	@Autowired
	ScStudentService scStudentService;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	
	/*新增一条学校记录，同时新增一条用户记录****************************************/
	/*@author:lihui**************************************************************/
	/*2015.11.5 createtime*******************************************************/
	@RequestMapping("/addScSchool.do")
	public @ResponseBody
	MessageResult addScSchool(ScSchool entity) {
		String newpwd = PasswordUtil.createHash(entity.getUser().getPassword());
		entity.getUser().setUserPwd(newpwd);
		userService.save(entity.getUser());
		entity.setSchoolId(entity.getUser().getId());
		AuthRoleUsers authRoleUsers = new AuthRoleUsers();
		authRoleUsers.setRoleId(1); // 幼儿园角色ID 为1
		authRoleUsers.setUserName(entity.getUser().getUserName());
		authRoleUsers.setuId(entity.getUser().getId());
		authRoleUsersService.save(authRoleUsers);
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScSchool.do")
	public @ResponseBody
	MessageResult delScSchool(ScSchool entity) {
		ScSchool school = this.get(entity.getId());
		User user = userService.get(school.getSchoolId());
		MessageResult messageResult = this.delete(entity);
		userService.del(user);
		return messageResult;
	}
	
	/*修改一条学校记录，并且修改相应用户数据*****************************************/
	/*@author:lihui***************************************************************/
	/*2015.11.6 createtime********************************************************/
	@RequestMapping("/updateScSchool.do")
	public @ResponseBody
	MessageResult updateScSchool(ScSchool entity) {
		if(entity.getUser().getPassword().length()==32){
			String newpwd = PasswordUtil.createHash(entity.getUser().getPassword());
			entity.getUser().setUserPwd(newpwd);
		}else{
			entity.getUser().setUserPwd(null);
		}
		userService.update(entity.getUser());
		return this.update(entity);
	}
	
	/*修改一条学校记录，并且修改相应用户数据*****************************************/
	/*@author:wyp***************************************************************/
	/*2015.11.12 createtime********************************************************/
	@RequestMapping("/getSchoolUserBySchoolId.do")
	public @ResponseBody
	String getSchoolUserBySchoolId() {	
		return this.getService().getSchoolUserBySchoolId().toString();
	}

	//条件查询分页操作
	@RequestMapping("/listScSchool.do")
	public @ResponseBody
	GridResult listScSchool(ScSchoolSch searchEntitySch) {
			/*List<Object> list = this.getService().getDao().getAgentList(searchEntitySch);
			DefaultGridResult result = new DefaultGridResult(list);*/
			DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
	        for (int i = 0; i < resultGrid.getRows().size(); i++) {
	            int schoolId = ((ScSchool) resultGrid.getRows().get(i)).getSchoolId();
	            User user = userService.get(schoolId);
	            ((ScSchool) resultGrid.getRows().get(i)).setUser(user);
	        }  
		return resultGrid;
	}
	
	//条件查询分页操作,幼儿园详细列表包括学生和教师数量
		@RequestMapping("/listScSchoolAndStudentTeacher.do")
		public @ResponseBody
		GridResult listScSchoolAndStudentTeacher(ScSchoolSch searchEntitySch) {
				/*List<Object> list = this.getService().getDao().getAgentList(searchEntitySch);
				DefaultGridResult result = new DefaultGridResult(list);*/
				DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
		        for (int i = 0; i < resultGrid.getRows().size(); i++) {
		            int schoolId = ((ScSchool) resultGrid.getRows().get(i)).getSchoolId();
		            User user = userService.get(schoolId);
		            if(user != null){
		            	((ScSchool) resultGrid.getRows().get(i)).setUser(user);
		            	//查询教师数量
		            	ExpressionQuery query = new ExpressionQuery();
		            	query.add(new ValueExpression("schoolId", schoolId));
		            	query.add(new ValueExpression("isDeleted", 0));
			 			query.setQueryAll(true);
						List<ScTeacher> teachers = scTeacherService.find(query);
						((ScSchool) resultGrid.getRows().get(i)).setTeacherNum(teachers.size());
						//查询学生的数量
						query = new ExpressionQuery();
			 			query.add(new ValueExpression("schoolId", schoolId));
			 			query.add(new ValueExpression("isDeleted", 0));
			 			query.setQueryAll(true);
						List<ScStudent> students = scStudentService.find(query);
						((ScSchool) resultGrid.getRows().get(i)).setStudentNum(students.size());
						//查询家长的数量
						int parentNum = 0;
						//查询家长数量
						if(students.size() >0){
							for(int j = 0; j<students.size(); j++){
								List<ScStudentParents> studentParents = scStudentParentsService.getStudentParentsBySId(students.get(j).getStudentId());
								//如果存在数据
								if(studentParents.size() >0 && studentParents.get(0).getSchoolId() != null){
									if(!studentParents.get(0).getSchoolId().equals(0)){
										//根据schoolId来查询某个学校的所有家长数量。
										query = new ExpressionQuery();
							 			query.add(new ValueExpression("schoolId", schoolId));
							 			query.add(new ValueExpression("isDeleted", 0));
							 			query.setQueryAll(true);
							 			List<ScStudentParents> scStudentParents = scStudentParentsService.find(query);
							 			parentNum = scStudentParents.size();
							 			break;
									}
								}
								//如果家长的schoolId没有被设置的话
								for(int z = 0; z<studentParents.size(); z++){
									studentParents.get(z).setSchoolId(schoolId);
									scStudentParentsService.update(studentParents.get(z));
								}
								
								
							}
						}
						((ScSchool) resultGrid.getRows().get(i)).setParentNum(parentNum);
		            }
		            
		        }  
			return resultGrid;
		}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScSchool.do")
    public @ResponseBody Object listAllScSchool(ScSchoolSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScSchoolById.do")
    public @ResponseBody ScSchool getScSchoolById(ScSchool entity) {
        return this.getService().get(entity.getId());
    }
	
    //获取详细信息通过schoolID
	@RequestMapping("/getScSchoolBySchoolIdId.do")
    public @ResponseBody ScSchool getScSchoolBySchoolId(ScSchool entity) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("schoolId", entity.getSchoolId()));
        return this.getService().find(query).get(0);
    }
 	
	 @RequestMapping({"/chatScSchoolInf.do"})
   public @ResponseBody List<ScSchool> chatScSchoolInf(String userName) {
     return this.getService().chatScSchoolInf(userName);
   }

}