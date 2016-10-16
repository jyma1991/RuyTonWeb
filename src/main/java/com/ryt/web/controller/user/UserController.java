package com.ryt.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.UserContext;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.auth.AuthRoleUsers;
import com.ryt.web.entity.finance.FinanceOrderCollection;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScAgentSch;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherSch;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.user.User;
import com.ryt.web.entity.user.UserSch;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.article.ArticleCommentService;
import com.ryt.web.service.auth.AuthRoleUsersService;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.sc.ScAgentService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.user.UserService;
import com.ryt.web.util.PasswordUtil;

@Controller
public class UserController extends
		CrudController<User, UserService> {
	
	@Autowired
	private AuthRoleUsersService authRoleUsersService;
	
	@Autowired
	private ScAgentService scAgentService;
	
	@Autowired
	private ScSchoolService scSchoolService;
	
	@Autowired
	private ScClassService scClassService;
	
	@Autowired
	private ScTeacherService scTeacherService;
	
	@Autowired
	private ScStudentService scStudentService;
	
	@Autowired
	private ScStudentParentsService scStudentParentsService;
	
	@Autowired
	private SysClassService sysClassService;
	
	@Autowired
	private ArticleBaseService articleBaseService;
	
	@Autowired
	private ArticleCommentService articleCommentService;
	
	@Autowired
	private FinanceChargeOrderService financeChargeOrderService;
	
		//閺傛澘顤冪拋鏉跨秿
	@RequestMapping("/addUser.do")
	public @ResponseBody
	MessageResult addUser(User entity) {
		UserSch userSch = new UserSch();
		userSch.setUserNameSch(entity.getUsername());
		DefaultGridResult object = (DefaultGridResult)this.queryAll(userSch);
		if(object.getTotal()>0){
			List<User> users = (List<User>)object.getRows();
			for(int i = 0; i<users.size(); i++){
				if(users.get(i).getUsername().equals(entity.getUserName().trim())){
					return error("用户名重复！");
				}
			}
		}
		//pwd加密
		String newpwd = PasswordUtil.createHash(entity.getPassword());
		entity.setUserPwd(newpwd);
		entity.setRoleProperty(64);
		return this.save(entity);
	}

	//閸掔娀娅庣拋鏉跨秿
	@RequestMapping("/delUser.do")
	public @ResponseBody
	MessageResult delUser(User entity) {
		return this.delete(entity);
	}
	
	//娣囶喗鏁肩拋鏉跨秿
	@RequestMapping("/updateUser.do")
	public @ResponseBody
	MessageResult updateUser(User entity) {
		//pwd加密
		if(entity.getPassword().length()==32){
			String newpwd = PasswordUtil.createHash(entity.getPassword());
			entity.setUserPwd(newpwd);
		}else{
			entity.setUserPwd(null);
		}
		return this.update(entity);
	}

	//閺夆�娆㈤弻銉嚄閸掑棝銆夐幙宥勭稊
	@RequestMapping("/listUser.do")
	public @ResponseBody
	GridResult listUser(UserSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//閺夆�娆㈤弻銉嚄楠炴儼绻戦崶鐐村閺堝顔囪ぐ锟�
	@RequestMapping("/listAllUser.do")
    public @ResponseBody Object listAllUser(UserSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //閼惧嘲褰囩拠锔剧矎娣団剝浼�
	@RequestMapping("/getUserById.do")
    public @ResponseBody User getUserById(User entity) {
        return this.getService().get(entity.getId());
    }
	
	//鑾峰彇鎵�湁鐢ㄦ埛鐨刯son鏁版嵁
 	@RequestMapping("/loadAllUserJson.do")
 	public @ResponseBody String getAllUserJson(UserSch searchEntitySch) {
 		DefaultGridResult object = (DefaultGridResult)this.queryAll(searchEntitySch);
		int total = object.getTotal();
		List<HashMap<String, Object>> jsonArr = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		//濡傛灉瀛樺湪璁板綍
		if(total>0){
			List<User> users = (List<User>)object.getRows();
			hashMap.put("success", "true");
			for(int i = 0; i<total; i++){
				hashMap = new HashMap<String, Object>();
				hashMap.put("id", users.get(i).getId());
				hashMap.put("userName", users.get(i).getUserName());
				jsonArr.add(hashMap);
			}
		//濡傛灉鏈嶅姟鍣ㄤ腑娌℃湁鏁版嵁	
		}else{
			hashMap.put("success", "false");
		}
 		return new JSONArray().toJSONString(jsonArr);
 	}
 	
 	/**
 	 * 根据班级编号查询家长用户
 	 * @param classId
 	 * @return
 	 */
 	@RequestMapping("/getParentsUserByClassId.do")
 	public List<User> getParentsUserByClassId(Integer classId){
 		return this.getService().getParentsUserByClassId(classId);
 	}
 	
    @RequestMapping("/resetUserPassword.do")
    public @ResponseBody Object resetUserPassword(User user) {
        String newPwsd = this.getService().resetUserPassword(user);
        return success(newPwsd);
    }
 	
 	/**
 	 * 根据不用的角色生成不同的用户统计数据
 	 * @return
 	 */
 	@RequestMapping("/getUserStatistics.do")
 	public @ResponseBody Map<Object, Object> getUserStatistics(){
 		Map<Object, Object> map = new HashMap<Object, Object>();
 		User user = UserContext.getInstance().getUser();
 		List<AuthRoleUsers> authRoleUsers = authRoleUsersService.getUserRole(user.getId());
 		int roleId = 0;
 		int adminFlag = 0;
		int agentFlag = 0;
		int schoolFlag = 0;
		//判断用户有几个角色
 		if(authRoleUsers.size() == 1){
 			roleId = authRoleUsers.get(0).getRoleId();
 		//多个角色
 		}else if(authRoleUsers.size() > 1){
 			for(int i  = 0; i<authRoleUsers.size(); i++){
 				if(authRoleUsers.get(0).getRoleId().equals(3)){
 					adminFlag = 1;
 					break;
 				}
 				if(authRoleUsers.get(0).getRoleId().equals(7)){
 					agentFlag = 1;
 					continue;
 				}
 				if(authRoleUsers.get(0).getRoleId().equals(1)){
 					schoolFlag = 1;
 				}
 			}
 		}else{
 			
 		}
 		//优先级  管理员>代理商>园长
 		if(adminFlag == 1){
 			roleId = 3;
 		}else if(agentFlag == 1){
 			roleId = 7;
 		}else if(schoolFlag == 1){
 			roleId = 1;
 		}
 		map.put("userId", user.getId());
 		//管理员角色
 		if(roleId == 3){
 			map.put("roleName", "admin");
 			ExpressionQuery query = new ExpressionQuery();
 			//查询所有的代理商
 			//List<ScAgent> agentList = scAgentService.getAgentList(scAgentSch);//scAgentService.find(query);
 			//roleProperty 0学生     1家长     2教师     4园长     8代理商     16市监管     32省监管     64平台 
 			List<User> agentList = this.getService().getUsersByRoleProperty(8);
 			Map<String, Object> agentMap = new HashMap<String, Object>(); 
 			for(int i = 0; i<agentList.size(); i++){
 	 			Integer[] deviceNum = new Integer[2];
 	 			deviceNum[0] = 0;
 	 			deviceNum[1] = 0;
 				query = new ExpressionQuery();
 	 			query.add(new ValueExpression("agentId", agentList.get(i).getId()));//agentList.get(i).getAgentId()
 	 			query.add(new ValueExpression("isDeleted", 0));
 	 			query.setQueryAll(true);
 	 			User agentUser = agentList.get(i);//this.get(agentList.get(i).getAgentId());
 	 			//查询该代理商下面所有的幼儿园数量
 	 			List<ScSchool> schools =  scSchoolService.find(query);
 	 			for(int j = 0; j<schools.size(); j++){
 	 				query = new ExpressionQuery();
 	 	 			query.add(new ValueExpression("schoolId", schools.get(j).getSchoolId()));
 	 	 			query.add(new ValueExpression("isDeleted", 0));
 	 	 			query.setQueryAll(true);
 	 	 			List<ScClass> classes = scClassService.find(query);
 	 	 			for(int x = 0; x<classes.size(); x++){
 	 	 				//placeTypeId  69表示教室   70表示公共场地
 	 	 				if(classes.get(x).getPlaceTypeId().equals(69)){
 	 	 					deviceNum[0] += 1;
 	 	 				}else if(classes.get(x).getPlaceTypeId().equals(70)){
 	 	 					deviceNum[1] += 1;
 	 	 				}
 	 	 			}
 	 			}
 	 			//如果用户没有查找到或者有时候数据错误导致用户没有姓名那么就把改用户排除在外
 	 			if(agentUser!= null && agentUser.getUsername() != null){
 	 				agentMap.put(agentUser.getTrueName(),deviceNum);
 	 			}
 	 			
 			}
 			query = new ExpressionQuery();
	 		query.add(new ValueExpression("isDeleted", 0));
 			//查询所有的代理商数量
 			int angentNum = agentList.size();
 			map.put("angentNum", angentNum);
 			//查询所有的幼儿园数量
 			int schoolNum = scSchoolService.findTotalCount(query);
 			map.put("schoolNum", schoolNum);
 			//查询所有的教师数量
 			int teacherNum = scTeacherService.findTotalCount(query);
 			map.put("teacherNum", teacherNum);
 			//查询所有的学生数量
 			int studentNum = scStudentService.findTotalCount(query);
 			map.put("studentNum", studentNum);
 			//查询所有的家长数量
 			int parentNum = scStudentParentsService.findTotalCount(query);
 			map.put("parentNum", parentNum);
 			map.put("agentDeviceMap", agentMap);
 			//查询育儿百科模块的统计数据（对应的sysClassTypeId=7,parentId=0(表示主类别)）
 			query = new ExpressionQuery();
 			query.add(new ValueExpression("sysClassTypeId", 7));
 			query.add(new ValueExpression("parentId", 0));
 			query.add(new ValueExpression("isDeleted", 0));
 			//查询所有的主类别
 			List<SysClass> sysClasses = sysClassService.find(query);
 			map.put("mainSysClassTypeNum",sysClasses.size());
 			int subSysClassesNum = 0;
 			int articlesNum = 0;
 			Map<String, Object> sybSysClassesMap = new HashMap<String, Object>(); 
 			for(int i = 0; i<sysClasses.size(); i++){
 				query = new ExpressionQuery();
 				query.add(new ValueExpression("parentId",sysClasses.get(i).getId()));
 				query.add(new ValueExpression("isDeleted", 0));
 				query.setQueryAll(true);
 				//查询某个主类别下面的所有子类别
 				List<SysClass> subSysClasses = sysClassService.find(query);
 				subSysClassesNum += subSysClasses.size();
 				for(int j = 0; j<subSysClasses.size(); j++){
 	 				query = new ExpressionQuery();
 	 				query.add(new ValueExpression("isDeleted", 0));
 	 				query.add(new ValueExpression("sysClassId",subSysClasses.get(j).getId()));
 	 				query.setQueryAll(true);
 	 				List<ArticleBase> articleBases = articleBaseService.find(query);
 	 				articlesNum+= articleBases.size();
 	 				sybSysClassesMap.put(subSysClasses.get(j).getClassName(), articleBases.size());
 				}

 			}
 			//查询子类别数量
 			map.put("subSysClassesNum",subSysClassesNum);
 			//育儿百科中的文章数量
 			map.put("articlesNum",articlesNum);
 			//育儿百科子类别统计
 			map.put("sybSysClassesMap", sybSysClassesMap);
 			//家长圈家长消息数量  sysClassId=155的时候表示家长圈，roleProperty为1的时候表示家长
 			List<ArticleBase> parentArticles = articleBaseService.getArticlesByParents(null,null,155, 1,null);
 			map.put("parentArticlesNum",parentArticles.size());
 			int parentArticleReplyNums = 0;
 			for(int i = 0; i<parentArticles.size(); i++){
 				int articleBaseId = parentArticles.get(i).getId();
 				query = new ExpressionQuery();
 	 			query.add(new ValueExpression("articleBaseId", articleBaseId));
 	 			query.add(new ValueExpression("isDeleted", 0));
 	 			query.setQueryAll(true);
 	 			List<ArticleComment> articleComments = articleCommentService.find(query);
 	 			parentArticleReplyNums += articleComments.size();
 			}
 			map.put("parentArticleReplyNums",parentArticleReplyNums);
 			//家长圈幼儿园发消息数量  sysClassId=155的时候表示家长圈，roleProperty为4的时候表园长，2表示教师
 			List<ArticleBase> schoolArticles = articleBaseService.getArticlesByParents(null,null,155, 4,null);
 			List<ArticleBase> teacherArticles = articleBaseService.getArticlesByParents(null,null,155, 2,null);
 			map.put("schoolArticlesNum",schoolArticles.size()+teacherArticles.size());
 			int schoolArticleReplyNums = 0;
 			for(int i = 0; i<schoolArticles.size(); i++){
 				int articleBaseId = schoolArticles.get(i).getId();
 				query = new ExpressionQuery();
 	 			query.add(new ValueExpression("articleBaseId", articleBaseId));
 	 			query.add(new ValueExpression("isDeleted", 0));
 	 			query.setQueryAll(true);
 	 			List<ArticleComment> articleComments = articleCommentService.find(query);
 	 			schoolArticleReplyNums += articleComments.size();
 			}
 			for(int i = 0; i<teacherArticles.size(); i++){
 				int articleBaseId = teacherArticles.get(i).getId();
 				query = new ExpressionQuery();
 	 			query.add(new ValueExpression("articleBaseId", articleBaseId));
 	 			query.add(new ValueExpression("isDeleted", 0));
 	 			query.setQueryAll(true);
 	 			List<ArticleComment> articleComments = articleCommentService.find(query);
 	 			schoolArticleReplyNums += articleComments.size();
 			}
 			map.put("schoolArticleReplyNums",schoolArticleReplyNums);
 			//代理商订单数量统计
 			List<FinanceOrderCollection> agentFinanceOrders = financeChargeOrderService.getAgentFinanceOrder("agentId","desc");
 			Map<String, Object> agentFinanceOrdersMap = new HashMap<String, Object>();
 			for(int i=0; i<agentFinanceOrders.size();i++){
 				int orderNum = agentFinanceOrders.get(i).getOrderNum();
 				agentFinanceOrdersMap.put(agentFinanceOrders.get(i).getTrueName(), orderNum);
 			}
 			map.put("agentFinanceOrdersMap", agentFinanceOrdersMap);
 			
 		//代理商角色
 		}else if(roleId == 7){
 			map.put("roleName", "agent");
 			ExpressionQuery query = new ExpressionQuery();
 			query.add(new ValueExpression("agentId", user.getId()));
 			query.add(new ValueExpression("isDeleted", 0));
 			query.setQueryAll(true);
 			//查询该代理商下面所有的幼儿园数量
 			List<ScSchool> schools =  scSchoolService.find(query);
 			//如果该代理商下面没有幼儿园
 			if(schools == null || schools.size() == 0){
 				map.put("schoolNum", 0);
 				map.put("teacherNum", 0);
 				map.put("studentNum", 0);
 				map.put("parentNum", 0);
 			}else{
 				Map<String, Object> schoolMap = new HashMap<String, Object>();
 				map.put("schoolNum", schools.size());
 				//查询教师数量
 				for(int i = 0; i<schools.size(); i++){
 					//如果数据正确
 					if(schools.get(i).getAgentId()!= null && !schools.get(i).getAgentId().equals(0)){
 						
 					}
 				}
 				query = new ExpressionQuery();
	 			query.add(new ValueExpression("agentId", user.getId()));
	 			query.add(new ValueExpression("isDeleted", 0));
	 			query.setQueryAll(true);
				List<ScTeacher> teachers = scTeacherService.find(query);
				if(teachers == null || teachers.size() == 0){
					map.put("teacherNum", 0);
				}else{
					map.put("teacherNum", teachers.size());
				}
				int studentNum = 0;
				int parentNum = 0;
				//查询学生的数量
				for(int i = 0; i<schools.size(); i++){
					query = new ExpressionQuery();
		 			query.add(new ValueExpression("schoolId", schools.get(i).getSchoolId()));
		 			query.add(new ValueExpression("isDeleted", 0));
		 			query.setQueryAll(true);
					List<ScStudent> students = scStudentService.find(query);
					studentNum += students.size();
					//查询家长数量
					if(students.size() >0){
						for(int j = 0; j<students.size(); j++){
							List<ScStudentParents> studentParents = scStudentParentsService.getStudentParentsBySId(students.get(j).getStudentId());
							parentNum += studentParents.size();
						}
					}
				}
				//查询每个学校的设备分布
				Integer[] deviceNum = new Integer[2];
				for(int j = 0; j<schools.size(); j++){
					deviceNum = new Integer[2];
					deviceNum[0] = 0;
	 	 			deviceNum[1] = 0;
 	 				query = new ExpressionQuery();
 	 	 			query.add(new ValueExpression("schoolId", schools.get(j).getSchoolId()));
 	 	 			query.add(new ValueExpression("isDeleted", 0));
 	 	 			query.setQueryAll(true);
 	 	 			List<ScClass> classes = scClassService.find(query);
 	 	 			for(int x = 0; x<classes.size(); x++){
 	 	 				//placeTypeId  69表示教室   70表示公共场地
 	 	 				if(classes.get(x).getPlaceTypeId().equals(69)){
 	 	 					deviceNum[0] += 1;
 	 	 				}else if(classes.get(x).getPlaceTypeId().equals(70)){
 	 	 					deviceNum[1] += 1;
 	 	 				}
 	 	 			}
 	 	 			schoolMap.put(schools.get(j).getSchoolName(),deviceNum);
 	 			}
				map.put("studentNum", studentNum);
				map.put("parentNum", parentNum);
				map.put("schoolDeviceMap", schoolMap);
 			}
 			
 		}else if(roleId == 1){
 			map.put("roleName", "school");
 			//查询教师数量
			ScTeacherSch teacherSch = new ScTeacherSch();
			teacherSch.setIsDeletedSch(new Byte("0"));
			teacherSch.setSchoolIdSch(user.getId());
			List<User> teachers = scTeacherService.findTeachersBySchoolId(teacherSch);
			if(teachers == null || teachers.size() == 0){
				map.put("teacherNum", 0);
			}else{
				map.put("teacherNum", teachers.size());
			}
			int studentNum = 0;
			int parentNum = 0;
			//查询学生的数量
			ExpressionQuery query = new ExpressionQuery();
 			query.add(new ValueExpression("schoolId", user.getId()));
 			query.add(new ValueExpression("isDeleted", 0));
 			query.setQueryAll(true);
			List<ScStudent> students = scStudentService.find(query);
			studentNum += students.size();
			//查询家长数量
			if(students.size() >0){
				for(int j = 0; j<students.size(); j++){
					List<ScStudentParents> studentParents = scStudentParentsService.getStudentParentsBySId(students.get(j).getStudentId());
					parentNum += studentParents.size();
				}
			}
			//查询场地的设备
			query = new ExpressionQuery();
 			query.add(new ValueExpression("schoolId", user.getId()));
 			query.add(new ValueExpression("isDeleted", 0));
 			query.setQueryAll(true);
 			List<ScClass> classes = scClassService.find(query);
 			Integer[] deviceNum = new Integer[2];
 			deviceNum[0] = 0;
	 		deviceNum[1] = 0;
 			for(int x = 0; x<classes.size(); x++){
 				//placeTypeId  69表示教室   70表示公共场地
 				if(classes.get(x).getPlaceTypeId().equals(69)){
 					deviceNum[0] += 1;
 				}else if(classes.get(x).getPlaceTypeId().equals(70)){
 					deviceNum[1] += 1;
 				}
 			}
 			map.put("classDeviceNum", deviceNum[0]);
 			map.put("publicDeviceNum", deviceNum[1]);
			map.put("studentNum", studentNum);
			map.put("parentNum", parentNum);
 		}
 		Object roleName = map.get("roleName");
 		if(roleName != null){
 			map.put("success", true);
 		}else{
 			map.put("success",false);
 		}
 		return map;
 	}
 	
    //获取详细信息
	@RequestMapping("/getScStudentUserByClassId.do")
    public @ResponseBody Object getScStudentById(Integer classId) {
        return this.getService().getStudentsByClassId(classId);
    }
}