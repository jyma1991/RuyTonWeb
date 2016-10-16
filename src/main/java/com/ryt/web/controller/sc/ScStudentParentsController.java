package com.ryt.web.controller.sc;

import java.util.ArrayList;
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

import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScStudentParentsSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScStudentParentsController extends
		CrudController<ScStudentParents, ScStudentParentsService> {
	
	@Autowired
	UserService userService;
	@Autowired
	ScStudentParentsService studentParentsService;
	@Autowired
	ScClassService scClassService;
	@Autowired
	ScStudentService scStudentService;
	
		//新增记录
	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : User studentId
	* @Description    : set studentId and create an parent User and set parentId					 
	* @Date           : 2015年11月10日 上午10:31:11    
	******************************************************************************/
	@RequestMapping("/addScStudentParents.do")
	public @ResponseBody
	MessageResult addScStudentParents(ScStudentParents entity) {
		//是否包含用户名称
		List<User> parents = this.getService().findUserListByuserName(entity);
		if (entity.getUser().getUserPwd().substring(0, 4).equals("1000")) 
		{	
			//if  No such user in the usertable 
			if (parents.size()==0 && (!this.getService().IsHaveCardNo(entity))) 
			{
				//find schoolId and save into entity
				Integer studentId = entity.getStudentId();
				ExpressionQuery query = new ExpressionQuery();
				query.add(new ValueExpression("studentId", studentId));
				List <ScStudent> listStudent = scStudentService.find(query);
				Integer classId = listStudent.get(0).getClassId();
				ExpressionQuery query1 = new ExpressionQuery();
				query1.add(new ValueExpression("id", classId));
				List <ScClass> listClass = scClassService.find(query1);
				Integer schoolId = listClass.get(0).getSchoolId();
				entity.setSchoolId(schoolId);
				//find schoolId and save into entity
				
				User user = entity.getUser();
				user.setRoleProperty(entity.getRoleProperty());
				userService.save(user);
				entity.setParentId(entity.getUser().getId());
				return  this.save(entity);

			} 
			else if( (!this.getService().IsHaveCardNo(entity)))
			{
				//find schoolId and save into entity
				Integer studentId = entity.getStudentId();
				ExpressionQuery query = new ExpressionQuery();
				query.add(new ValueExpression("studentId", studentId));
				List <ScStudent> listStudent = scStudentService.find(query);
				Integer classId = listStudent.get(0).getClassId();
				ExpressionQuery query1 = new ExpressionQuery();
				query1.add(new ValueExpression("id", classId));
				List <ScClass> listClass = scClassService.find(query1);
				Integer schoolId = listClass.get(0).getSchoolId();
				entity.setSchoolId(schoolId);
				//find schoolId and save into entity
				
				entity.setParentId(parents.get(0).getId());
				return  this.save(entity);
			}
			else 
			{
				return  error("卡号已存在！");
			}
		}
		else 
		{
			System.out.println("密码错误！");
			return error("密码有误！");
		}
		
		
	}

	/*****************************************************************************
	* @author         :xiaoren 
	* @version        : V1.0
	* @param          : studentId
	* @Description    : delete parent user by pid
	* 					delete parent and student associate 
	* @Date           : 2015年11月9日 下午4:10:50    
	******************************************************************************/
	//删除记录
	@RequestMapping("/delScStudentParents.do")
	public @ResponseBody
	MessageResult delScStudentParents(ScStudentParents entity ) {
			ScStudentParents scStudentParent =this.get(entity.getId());
			User parentUser = userService.get(scStudentParent.getParentId());
			//是否已经关联
			List<ScStudentParents> studentParents = 
					this.getService().findStudentParentsListByuserName(scStudentParent);
			if(studentParents.size()>=2)					  //当已经有关联
			{
				return this.delete(entity);
			}
			else if(parentUser.getRoleProperty().equals(1))   //只删除角色为1即家长
			{
				userService.del(parentUser);
				return this.delete(entity);
			}else 
			{
				return this.delete(entity);
			}
	}
	
	//修改记录
	@RequestMapping("/updateScStudentParents.do")
	public @ResponseBody
	MessageResult updateScStudentParents(ScStudentParents entity) {
		//是否包含用户名称
		List<User> parents = this.getService().findUserListByuserName(entity);
		ScStudentParents scStudentParent = this.getService().get(entity.getId());
		
		if (entity.getUser().getUserPwd().substring(0, 4).equals("1000")) 
		{	//user unduplicated check or self-renewing  
			if (parents.size()==0||(parents.get(0).getUsername().equals(entity.getUser().getUserName()))) 
			{	//cardNo already exsit
				if(entity.getCardNo().equals("")||entity.getCardNo().equals(scStudentParent.getCardNo())) 
				{
					entity.setCardNo(null);
					userService.update(entity.getUser());
					return this.update(entity);
				}
				//cardNo do not exsit
				else if(!this.getService().IsHaveCardNo(entity))
				{
					userService.update(entity.getUser());
					return this.update(entity);
				}
				else 
				{
					return error("卡号已存在");
				}
			}
			else 
			{
				return error("用户名存在");
			}
		}
		else {
			System.out.println("密码错误");
			return error("密码错误！");
		}
		
	}
/*****************************************************************************
* @author         :xiaoren 
* @version        : V1.0
* @param          : the case to find 
* @Description    : 1.find the parentIds by studentId 
* 					2.find the users of parents by parentsId list
* @Date           : 2015年11月9日 下午2:23:54    
******************************************************************************/
	//条件查询分页操作
	@RequestMapping("/listScStudentParents.do")
	public @ResponseBody
	GridResult listScStudentParents(ScStudentParentsSch searchEntitySch) {
		DefaultGridResult resultGrid =(DefaultGridResult) this.query(searchEntitySch);
		ScStudentParents studentParents = new ScStudentParents();
		List<ScStudentParents>  studentParentslist = (List<ScStudentParents>)resultGrid.getRows();
		studentParents.setStudentId(searchEntitySch.getStudentIdSch());
		List<User> userList = new ArrayList<User>();
		User user = null;
		for (int i = 0; i < studentParentslist.size(); i++) {
			user = userService.get(studentParentslist.get(i).getParentId());
			if (user!= null) {
				studentParentslist.get(i).setUser(user);
			} else {
				System.out.println("studentParents user is null  ParentId:"+ studentParentslist.get(i).getParentId());
				studentParentslist.remove(i);
			}
			
		}
		resultGrid.setList(studentParentslist);
		return resultGrid;
	}
	
	//条件查询统计数据分页操作
		@RequestMapping("/listScStudentParentsStatistic.do")
		public @ResponseBody
		GridResult listScStudentParentsStatistic(ScStudentParentsSch searchEntitySch,Integer agentIdSch) {
			List<ScStudentParents>  studentParentslist = this.getService().getParentsByAgentId(agentIdSch);
			List<User> userList = new ArrayList<User>();
			User user = null;
			for (int i = 0; i < studentParentslist.size(); i++) {
				user = userService.get(studentParentslist.get(i).getParentId());
				if (user!= null) {
					studentParentslist.get(i).setUser(user);
				}/* else {
					System.out.println("studentParents user is null  ParentId:"+ studentParentslist.get(i).getParentId());
					studentParentslist.remove(i);
				}*/
				
			}
			return new DefaultGridResult(studentParentslist);
		}

	//条件查询分页操作
	@RequestMapping("/getParentsByCondition.do")
	public @ResponseBody
	GridResult getParentsByCondition(ScStudentParentsSch searchEntitySch) {
		List<ScStudentParents>  studentParentslist = this.getService().getParentsByCondition(searchEntitySch);
		User user = null;
		for (int i = 0; i < studentParentslist.size(); i++) {
			user = userService.get(studentParentslist.get(i).getParentId());
			if (user!= null) {
				studentParentslist.get(i).setUser(user);
			}/* else {
				studentParentslist.remove(i);
			}*/
			
		}
		return new DefaultGridResult(studentParentslist);
	}
	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScStudentParents.do")
    public @ResponseBody Object listAllScStudentParents(ScStudentParentsSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScStudentParentsById.do")
    public @ResponseBody ScStudentParents getScStudentParentsById(ScStudentParents entity) {
        return this.getService().get(entity.getId());
    }

    //获取详细信息
	@RequestMapping("/getdefaultPassword.do")
    public @ResponseBody String getdefaultPassword() {
        return "1000:a74d48c86b6f3ed426993bc615f90960554f79a355df9145:1c98e04dff5d044b1e561eb1be36fec79225259ea27a7f8b";
    }
	
	//批量修改之前没有填写schoolId的家长数据
	@RequestMapping("/modifyParentSchoolId.do")
	public String modifyParentSchoolId(){
		ExpressionQuery query = new ExpressionQuery();
		query.setQueryAll(true);
		List<ScStudentParents> parents = this.getService().find(query);
		int count = 0;
		for(int i= 0; i<parents.size(); i++){
			if(parents.get(i).getSchoolId() == null || parents.get(i).getSchoolId() <1){
				int studentId = parents.get(i).getStudentId();
				ScStudent scStudent = scStudentService.getSctudentByUserId(studentId);
				if(scStudent != null){
					parents.get(i).setSchoolId(scStudent.getSchoolId());
					count ++;
					this.update(parents.get(i));
				}
				
			}
		}
		return "修改了"+count+"条数据";
	}
}