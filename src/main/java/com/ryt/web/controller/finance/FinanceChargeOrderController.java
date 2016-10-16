package com.ryt.web.controller.finance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.finance.FinanceOrderCollection;
import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceChargeOrderBak;
import com.ryt.web.entity.finance.FinanceChargeOrderSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.finance.FinanceChargeProductService;
import com.ryt.web.service.user.UserService;

import com.ryt.web.entity.finance.FinanceChargeOrderBak;
import com.ryt.web.entity.finance.FinanceChargeOrderSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.finance.FinanceChargeProductService;
import com.ryt.web.service.user.UserService;

@Controller
public class FinanceChargeOrderController extends CrudController<FinanceChargeOrder, FinanceChargeOrderService> {

	@Autowired
	private UserService userService;
	@Autowired
	private FinanceChargeProductService financeChargeProductService;

	// 新增记录
	@RequestMapping("/addFinanceChargeOrder.do")
	public @ResponseBody MessageResult addFinanceChargeOrder(FinanceChargeOrder entity) {
		// 判断新增用户是否在用户表中存在
		User user = userService.getUserByUserName(entity.getChargeAccountId());
		if (user != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			entity.setPayTime(format.format(calendar.getTime()));
			return this.save(entity);
		}
		return error("该用户不存在，请重新输入充值账号！！！");

	}

	// 删除记录
	@RequestMapping("/delFinanceChargeOrder.do")
	public @ResponseBody MessageResult delFinanceChargeOrder(FinanceChargeOrder entity) {
		return this.delete(entity);
	}

	// 修改记录
	@RequestMapping("/updateFinanceChargeOrder.do")
	public @ResponseBody MessageResult updateFinanceChargeOrder(FinanceChargeOrder entity) {
		return this.update(entity);
	}

	// 条件查询分页操作
	@RequestMapping("/listFinanceChargeOrder.do")
	public @ResponseBody GridResult listFinanceChargeOrder(FinanceChargeOrderSch searchEntitySch) {
		DefaultGridResult result = (DefaultGridResult) this.query(searchEntitySch);
		List<FinanceChargeOrderBak> financeChargeOrders = (List<FinanceChargeOrderBak>) result.getRows();
		for (int i = 0; i < financeChargeOrders.size(); i++) {
			FinanceChargeOrderBak financeChargeOrder = financeChargeOrders.get(i);
			financeChargeOrder.setProduct(financeChargeProductService.get(financeChargeOrder.getProductId()));
			financeChargeOrder.setStudent(userService.get(financeChargeOrder.getStudentId()));
			financeChargeOrder.setComfirmUser(userService.get(financeChargeOrder.getConfirmUserId()));
		}
		return new DefaultGridResult(financeChargeOrders);
	}

	// 条件查询并返回所有记录
	@RequestMapping("/listAllFinanceChargeOrder.do")
	public @ResponseBody Object listAllFinanceChargeOrder(FinanceChargeOrderSch searchEntitySch) {
		return this.queryAll(searchEntitySch);
	}

	// 获取详细信息
	@RequestMapping("/getFinanceChargeOrderById.do")
	public @ResponseBody FinanceChargeOrder getFinanceChargeOrderById(FinanceChargeOrder entity) {
		return this.getService().get(entity.getId());
	}

	

	// 条件查询代理商订单并返回记录
	@RequestMapping("/listAgentFinanceOrder.do")
	public @ResponseBody GridResult listAgentFinanceOrder(FinanceChargeOrderSch searchEntitySch) {
		List<FinanceOrderCollection> agentFinanceOrders = this.getService()
				.getAgentFinanceOrder(searchEntitySch.getSortname(), searchEntitySch.getSortorder());
		DefaultGridResult result = new DefaultGridResult(agentFinanceOrders);
		result.setPageIndex(searchEntitySch.getPageIndex());
		result.setPageSize(searchEntitySch.getPageSize());
		result.setStart(searchEntitySch.getStart());
		return result;
	}

	// 根据代理商Id查询学校订单详细列表
	@RequestMapping("/listSchoolFinanceOrder.do")
	public @ResponseBody GridResult listSchoolFinanceOrder(FinanceChargeOrderSch searchEntitySch) {
		List<FinanceOrderCollection> schoolFinanceOrders = this.getService().getSchoolFinanceOrder(
				searchEntitySch.getSortname(), searchEntitySch.getSortorder(), searchEntitySch.getAgentIdSch());
		DefaultGridResult result = new DefaultGridResult(schoolFinanceOrders);
		result.setPageIndex(searchEntitySch.getPageIndex());
		result.setPageSize(searchEntitySch.getPageSize());
		result.setStart(searchEntitySch.getStart());
		return result;
	}

	// 根据学校Id查询班级订单详细列表
	@RequestMapping("/listClassFinanceOrder.do")
	public @ResponseBody GridResult listClassFinanceOrder(FinanceChargeOrderSch searchEntitySch){
		List<FinanceOrderCollection> classFinanceOrders = this.getService().getClassFinanceOrder(searchEntitySch.getSortname(),searchEntitySch.getSortorder(),searchEntitySch.getSchoolIdSch());
		DefaultGridResult result = new DefaultGridResult(classFinanceOrders);
		result.setPageIndex(searchEntitySch.getPageIndex());
		result.setPageSize(searchEntitySch.getPageSize());
		result.setStart(searchEntitySch.getStart());
		return result;
	}

	
}