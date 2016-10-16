package com.ryt.web.controller.finance;

import java.util.Date;
import java.util.HashMap;
//import java.util.Date;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;

import com.ryt.web.entity.auth.AuthRoleUsers;
import com.ryt.web.entity.finance.FinanceDistributionManage;
import com.ryt.web.entity.finance.FinanceDistributionManageSch;
import com.ryt.web.entity.finance.FinanceUntreatedAgentResult;
import com.ryt.web.entity.sc.ScAgent;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.auth.AuthRoleUsersService;
import com.ryt.web.service.finance.FinanceDistributionManageService;
import com.ryt.web.service.sc.ScAgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ryt.web.service.user.UserService;
import com.ryt.app.util.SendSmsUtil;

@Controller
public class FinanceDistributionManageController
		extends CrudController<FinanceDistributionManage, FinanceDistributionManageService> {
	@Autowired
	UserService userService;
	@Autowired
	ScAgentService scAgentService;
	@Autowired
	FinanceDistributionManageService FinanceDistributionManageService;
	@Autowired
	AuthRoleUsersService authRoleUsersService;

	// 获取未处理推荐人信息 start
	@RequestMapping("/listDeScAgent.do")
	public @ResponseBody List<FinanceUntreatedAgentResult> listDeScAgent() {
		return this.getService().listDeScAgent();
	}
	// 获取未处理推荐人信息 end

	// 2015.12.21 请马哥帮忙看一下
	// 添加未处理推荐人信息 start
	@RequestMapping("/agreeScAgent.do")
	public @ResponseBody MessageResult agreeScAgent(FinanceUntreatedAgentResult entity) {

		// get data from weixin begin 20151218160000
		String trueName = entity.getName();// new agent's trueName
		Integer suggestAgentId = entity.getAgentId();// agentId of suggester
		String identityCardNo = entity.getIdentityCardNo();// new agent's
															// identityCardNo
		String phone = entity.getPhone();// new agent's phone and be added as
											// his userName
		String cardNo = entity.getCardNo();// new agent's bank card
		// get data from weixin end 20151218160000
		// 上面的是原始信，没错就行，下面是组装的信息

		// packaging data of user table begin 20151218160000
		List<User> list = userService.listAllUserByUserName(phone);
		if (list != null && list.size() >0) {
				return error("该用户已存在");
		}
		User user = new User();
		user.setTrueName(trueName);
		user.setRoleProperty(8);
		user.setUserPwd(
				"1000:a74d48c86b6f3ed426993bc615f90960554f79a355df9145:1c98e04dff5d044b1e561eb1be36fec79225259ea27a7f8b");
		user.setUserName(phone);
		user.setMobilePhone(phone);
		userService.save(user);
		// packaging data of user table end 20151218160000

		// packaging data of agent table begin 20151218
		ScAgent scAgent = new ScAgent();
		scAgent.setAgentId(user.getId());// agentId is id of user table
		scAgent.setCardNo(cardNo); // cardNo of scAgent
		scAgent.setLevelId(1);// levelId of scAgent
		scAgent.setUserName(phone); // userName of scAgentId
		scAgent.setIdentityCardNo(identityCardNo);// identityCardNo of scAgentId
		scAgent.setCommandUserId(suggestAgentId);// commandUserId of this new
													// agent
		scAgentService.save(scAgent);
		// packaging data of agent table end 20151218
		
		AuthRoleUsers authRoleUsers = new AuthRoleUsers();

		authRoleUsers.setuId(user.getId());
		authRoleUsers.setRoleId(7); // 代理商默认为7
		authRoleUsers.setUserName(user.getUserName());
		authRoleUsersService.save(authRoleUsers);
		


		// get message of suggester begin
		User user_suggester = FinanceDistributionManageService.getMessage(entity);
		// get message of suggester end

		// 这个方法执行一些修改状态的操作，无反回值
		this.getService().agreeScAgent(entity);
		// 这个方法执行一些修改状态的操作，无反回值

		// send SMS begin
		SendSmsUtil sendSmsUtilAgent = new SendSmsUtil();
		sendSmsUtilAgent.sendSmsUtilAgent(String.valueOf(suggestAgentId), user_suggester.getTrueName(), trueName,
				String.valueOf(scAgent.getAgentId()), phone);
		// send SMS end

		return success();
	}
	// 添加未处理推荐人信息 end
	// 2015.12.21 请马哥帮忙看一下

	// 升级代理商列表查询 start
	@RequestMapping("/listAgentUpgrade.do")
	public @ResponseBody   Integer listAgentUpgrade(FinanceDistributionManage entity) {
		Integer count = this.getService().listAgentUpgrade(entity);
		if(count==1){
		ScAgent scAgent1 =	scAgentService.queryAgentByAgentId(entity.getAgentId());
		FinanceDistributionManageService.submitAgentUpgrade(scAgent1);
		}
		return count;
	}
	// 升级代理商列表查询 end

	// 升级代理商提交 start
	@RequestMapping("/submitAgentUpgrade.do")
	public @ResponseBody String submitAgentUpgrade(ScAgent scAgent) {
		this.getService().submitAgentUpgrade(scAgent);
		return "success";
	}
	// 升级代理商提交 end

	// 按ID查询 start
	@RequestMapping("/getFinanceDistributionManageById.do")
	public @ResponseBody FinanceDistributionManage getFinanceDistributionManageById(FinanceDistributionManage entity) {
		return this.getService().get(entity.getId());
	}
	// 按ID查询 end

	// 集合查询 start
	@RequestMapping("/listFinanceDistributionManage.do")
	public @ResponseBody GridResult listFinanceDistributionManage(FinanceDistributionManageSch searchEntitySch) {
		DefaultGridResult gridResult =(DefaultGridResult) this.query(searchEntitySch);
		gridResult.getTotal();
		List list = gridResult.getRows();
		gridResult.setTotal(list.size());
		return gridResult;
	}
	// 集合查询 end

	// 修改记录 start
	@RequestMapping("/updateFinanceDistributionManage.do")
	public @ResponseBody MessageResult updateFinanceDistributionManage(FinanceDistributionManage entity) {
		return this.update(entity);
	}
	// 修改记录 end

	// 新增记录 start
	@RequestMapping("/addFinanceDistributionManage.do")
	public @ResponseBody MessageResult addFinanceDistributionManage(FinanceDistributionManage entity) {
		return this.save(entity);
	}
	// 新增记录 end

	// 删除记录 start
	@RequestMapping("/delFinanceDistributionManage.do")
	public @ResponseBody MessageResult delFinanceDistributionManage(FinanceDistributionManage entity) {
		return this.delete(entity);
	}
	// 删除记录 end

	// 获取收支信息查询 start
	@RequestMapping("/financePayments.do")
	public @ResponseBody List<Map<String, Integer>> financePayments() {
		return this.getService().financePayments();

	}
	// 获取收支信息查询 end

	// 过滤收支信息 start
	@RequestMapping("/financePaymentsFilter.do")
	public @ResponseBody List<Map<String, Integer>> financePaymentsFilter(Date createdStartSch, Date createdEndSch) {
		return this.getService().financePaymentsFilter(createdStartSch, createdEndSch);
	}

	// 过滤收支信息 end
	// 推荐人申请，代理商升级，提现信息提示 start
	@RequestMapping("/messagePrompt.do")
	public @ResponseBody List<Integer> messagePrompt() {
		return this.getService().messagePrompt();
	}
	// 推荐人申请，代理商升级，提现信息提示 end

	// <!--提现信息列表查询 start -->
	@RequestMapping("/listIncomeAmount.do")
	public @ResponseBody List<FinanceDistributionManage> listIncomeAmount() {
		return this.getService().listIncomeAmount();
	}

	// <!--提现信息列表查询 end -->
	// <!--提现信息提交 start -->
	@RequestMapping("/updateIncomeAmount.do")
	public @ResponseBody String updateIncomeAmount(FinanceDistributionManage entity) {
		this.getService().updateIncomeAmount(entity);
		return "success";
	}
	// <!--提现信息提交 end -->

	// 过滤提现信息列表查询 start
	@RequestMapping("/filterListIncomeAmount.do")
	public @ResponseBody List<FinanceDistributionManage> FilterListIncomeAmount(Integer agentId) {

		return this.getService().FilterListIncomeAmount(agentId);
	}
	// 过滤提现信息列表查询 end

	// 微信部分 start
	@RequestMapping({ "/chatAgentSubmit.do" })
	@ResponseBody
	public String chatAgentSubmit(Integer agentId, Integer levelId, String name, String cardNo, String identityCardNo,
			String phone) {
		FinanceDistributionManage financeDistributionManage = new FinanceDistributionManage();
		financeDistributionManage.setAgentId(agentId);
		financeDistributionManage.setLevelId(levelId);
		financeDistributionManage.setName(name);
		financeDistributionManage.setCardNo(cardNo);
		financeDistributionManage.setIdentityCardNo(identityCardNo);
		financeDistributionManage.setPhone(phone);
		financeDistributionManage.setSaleType(1);
		this.save(financeDistributionManage);
		return "success";
	}

	@RequestMapping({ "/chatAgentIncomeInf.do" })
	@ResponseBody
	public List<FinanceDistributionManage> chatAgentIncomeInf(String userName) {
		return this.getService().chatAgentIncomeInf(userName);
	}

	@RequestMapping({ "/chatWithdrawInf.do" })
	@ResponseBody
	public List<FinanceDistributionManage> chatWithdrawInf(String userName) {
		return this.getService().chatWithdrawInf(userName);
	}

	@RequestMapping({ "/submitWithdraw.do" })
	@ResponseBody
	public String submitWithdraw(Integer agentId, Integer amt) {
		this.getService().submitWithdraw(agentId, amt);
		return "success";
	}
	// 微信部分 end
	//20150103 微信index.html中的查询     start
	@RequestMapping({ "/chatIndexQuery.do" })
	@ResponseBody
	public Map<String,Object> chatIndexQuery(String userName) {
		ScAgent scAgent = new ScAgent();
		scAgent = scAgentService.getDao().chatScAgentInf(userName);
		Integer levelId = scAgent.getLevelId();
		Map<String,Object> map = new HashMap<String, Object>();
		map=this.getService().chatIndexQuery(userName);
		map.put("levelId", levelId);
		return map;
	}
	//20150103 微信index.html中的查询     end
	
	//20150104 微信通过agentId来查询这个代理商的信息 start
	@RequestMapping({ "/chatGetMessage.do" })
	@ResponseBody
	public  User  chatGetMessage(Integer agentId){
		FinanceUntreatedAgentResult entity  = new FinanceUntreatedAgentResult();
		entity.setAgentId(agentId);
	    User user_suggester = FinanceDistributionManageService.getMessage(entity);
	    return user_suggester;
	}
	//20150104 微信通过agentId来查询这个代理商的信息 end
}