
package com.ryt.web.controller.sc;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.sc.ScAgentSch;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.auth.AuthRoleUsersService;
import com.ryt.web.service.sc.ScAgentService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.user.UserService;
import com.ryt.web.util.PasswordUtil;

@Controller
public class ScAgentController extends CrudController<ScAgent, ScAgentService> {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthRoleUsersService authRoleUsersService;

	@Autowired
	private ScSchoolService scSchoolService;

	// 鏂板璁板綍
	@RequestMapping("/addScAgent.do")
	public @ResponseBody MessageResult addScAgent(ScAgent entity) {
		List<User> list = userService.listAllUserByUserName(entity.getUser().getUserName().trim());
		if (list != null && list.size() == 1) {
			return success("该用户已存在");
		}
		// pwd加密
		String newpwd = PasswordUtil.createHash(entity.getUser().getPassword());
		entity.getUser().setUserPwd(newpwd);

		User user = entity.getUser();
		user.setId(0);
		user.setRoleProperty(8);// 代理商默认为8
		userService.save(user);
		entity.setAgentId(user.getId());
		AuthRoleUsers authRoleUsers = new AuthRoleUsers();

		authRoleUsers.setuId(entity.getUser().getId());
		authRoleUsers.setRoleId(7); // 代理商默认为7
		authRoleUsers.setUserName(entity.getUser().getUserName());
		authRoleUsersService.save(authRoleUsers);
		return this.save(entity);
	}

	// 鍒犻櫎璁板綍
	@RequestMapping("/delScAgent.do")
	public @ResponseBody MessageResult delScAgent(ScAgent entity) {
		ScAgent agent = this.get(entity.getId());
		User user = userService.get(agent.getAgentId());
		MessageResult messageResult = this.delete(entity);
		userService.del(user);
		return messageResult;
	}

	// 淇敼璁板綍
	@RequestMapping("/updateScAgent.do")
	public @ResponseBody MessageResult updateScAgent(ScAgent entity) {
		// System.out.println(JSONObject.fromObject(entity).toString());
		List<User> list = userService.listAllUserByUserName(entity.getUser().getUserName().trim());
		if (list != null && list.size() == 1) {
			if (!entity.getUser().getId().equals(list.get(0).getId()))
				return error("该用户已存在");
		}else if(list != null && list.size() >= 1){
			return error("该用户已存在");
		}
		// 表示修改过密码
		if (entity.getUser().getPassword().length() <= 32) {
			// pwd加密
			String newpwd = PasswordUtil.createHash(entity.getUser().getPassword());
			entity.getUser().setUserPwd(newpwd);
		}
		userService.update(entity.getUser());
		return this.update(entity);
	}

	// 鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listScAgent.do")
	public @ResponseBody GridResult listScAgent(ScAgentSch searchEntitySch) {
		String userName = null;
		DefaultGridResult resultGrid = null;
		// 判断是否有搜索条件
		if (searchEntitySch != null && searchEntitySch.getUserNameSch() != null
				&& !searchEntitySch.getUserNameSch().trim().equals("")) {
			userName = searchEntitySch.getUserNameSch().trim();
			searchEntitySch.setUserNameSch(null);
			resultGrid = (DefaultGridResult) this.queryAll(searchEntitySch);
		} else {
			resultGrid = (DefaultGridResult) this.query(searchEntitySch);
		}
		int num[] = null;
		if (resultGrid.getRows().size() > 0) {
			num = new int[resultGrid.getRows().size() + 1];
		}
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			int agentId = ((ScAgent) resultGrid.getRows().get(i)).getAgentId();
			User user = userService.get(agentId);

			// 判断搜索的用户名是否为空
			if (userName != null) {
				if (user == null || user.getUserName() == null || user.getUserName().indexOf(userName) == -1) {
					// 标记此位置需要删除数据
					num[i + 1] = i + 1;
				} else {
					num[i + 1] = 0;
					((ScAgent) resultGrid.getRows().get(i)).setUser(user);
				}
			} else {
				((ScAgent) resultGrid.getRows().get(i)).setUser(user);
			}
		}
		int count = 0;
		// 依次删除
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			if (num[i + 1 + count] > 0) {
				resultGrid.getRows().remove(i);
				i--;
				count++;
			}
		}
		return resultGrid;
	}

	// 查看代理商详细列表，并带有各代理商管理学校数量
	@RequestMapping("/listScAgentAndSchool.do")
	public @ResponseBody GridResult listScAgentAndSchool(ScAgentSch searchEntitySch) {
		String userName = null;
		DefaultGridResult resultGrid = null;
		// 判断是否有搜索条件
		if (searchEntitySch != null && searchEntitySch.getUserNameSch() != null
				&& !searchEntitySch.getUserNameSch().trim().equals("")) {
			userName = searchEntitySch.getUserNameSch().trim();
			searchEntitySch.setUserNameSch(null);
			resultGrid = (DefaultGridResult) this.queryAll(searchEntitySch);
		} else {
			resultGrid = (DefaultGridResult) this.query(searchEntitySch);
		}
		int num[] = null;
		if (resultGrid.getRows().size() > 0) {
			num = new int[resultGrid.getRows().size() + 1];
		}
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			int agentId = ((ScAgent) resultGrid.getRows().get(i)).getAgentId();
			User user = userService.get(agentId);
			ExpressionQuery query = new ExpressionQuery();
			// 判断搜索的用户名是否为空
			if (userName != null) {
				if (user == null || user.getUserName() == null || user.getUserName().indexOf(userName) == -1) {
					// 标记此位置需要删除数据
					num[i + 1] = i + 1;
				} else {
					query.add(new ValueExpression("agentId", agentId));
					query.add(new ValueExpression("isDeleted", 0));
					query.setQueryAll(true);
					List<ScSchool> schools = scSchoolService.find(query);
					((ScAgent) resultGrid.getRows().get(i)).setSchoolNum(schools.size());
					num[i + 1] = 0;
					((ScAgent) resultGrid.getRows().get(i)).setUser(user);
				}
			} else {
				query.add(new ValueExpression("agentId", agentId));
				query.add(new ValueExpression("isDeleted", 0));
				query.setQueryAll(true);
				List<ScSchool> schools = scSchoolService.find(query);
				((ScAgent) resultGrid.getRows().get(i)).setSchoolNum(schools.size());
				((ScAgent) resultGrid.getRows().get(i)).setUser(user);
			}
		}
		int count = 0;
		// 依次删除
		for (int i = 0; i < resultGrid.getRows().size(); i++) {
			if (num[i + 1 + count] > 0) {
				resultGrid.getRows().remove(i);
				i--;
				count++;
			}
		}
		return resultGrid;
	}

	// 鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScAgent.do")
	public @ResponseBody Object listAllScAgent(ScAgentSch searchEntitySch) {
		DefaultGridResult result = (DefaultGridResult) this.queryAll(searchEntitySch);
		List<ScAgent> list = (List<ScAgent>) result.getRows();
		for (int i = 0; i < list.size(); i++) {
			User user = userService.get(list.get(i).getAgentId());
			if (user != null) {
				list.get(i).setTrueName(user.getTrueName());
			} else {
				list.remove(i);
				i--;
			}

		}
		return result;
	}

	// 鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getScAgentById.do")
	public @ResponseBody ScAgent getScAgentById(ScAgent entity) {
		return this.getService().get(entity.getId());
	}

	// 代理商人数信息 start
	@RequestMapping("/agentManage.do")
	public @ResponseBody List<Map<String, Integer>> agentManage() {
		return this.getService().agentManage();
	}
	// 代理商人数信息 end

	// 代理商人数信息过滤 start
	@RequestMapping("/agentManageFilter.do")
	public @ResponseBody List<Map<String, Integer>> agentManageFilter(Date createdStartSch, Date createdEndSch) {
		return this.getService().agentManageFilter(createdStartSch, createdEndSch);
	}
	// 代理商人数信息过滤 end

	@RequestMapping({ "/chatScAgentInf.do" })
	public @ResponseBody ScAgent chatScAgentInf(String userName) {
		return this.getService().chatScAgentInf(userName);
	}

	@RequestMapping({ "/chatScAgentLowerInf.do" })
	public @ResponseBody List<ScAgent> chatScAgentLowerInf(String userName) {
		return this.getService().chatScAgentLowerInf(userName);
	}

}