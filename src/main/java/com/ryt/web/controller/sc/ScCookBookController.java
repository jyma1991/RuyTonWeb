package com.ryt.web.controller.sc;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.common.MemcacheManager;
import com.ryt.web.entity.sc.ScCookBook;
import com.ryt.web.entity.sc.ScCookBookSch;
import com.ryt.web.service.sc.ScCookBookService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.user.UserService;

@Controller
public class ScCookBookController extends CrudController<ScCookBook, ScCookBookService> {

	@Autowired
	private UserService userService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	SysClassService sysClassService;

	// 新增记录
	@RequestMapping("/addScCookBook.do")
	public @ResponseBody MessageResult addScCookBook(ScCookBook entity) {
		MemcacheManager.delete(entity.getSchoolId() + "_cook");
		return this.save(entity);
	}

	// 删除记录
	@RequestMapping("/delScCookBook.do")
	public @ResponseBody MessageResult delScCookBook(ScCookBook entity) {
		MemcacheManager.delete(entity.getSchoolId() + "_cook");
		return this.delete(entity);
	}

	// 修改记录
	@RequestMapping("/updateScCookBook.do")
	public @ResponseBody MessageResult updateScCookBook(ScCookBook entity) {
		/*
		 * if(entity.getFoodPicId()==0){ entity.setFoodPicId(0); }
		 */
		MemcacheManager.delete(entity.getSchoolId() + "_cook");

		return this.update(entity);
	}

	// 条件查询分页操作
	@RequestMapping("/listScCookBook.do")
	public @ResponseBody GridResult listScCookBook(ScCookBookSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	// 条件查询并返回所有记录
	@RequestMapping("/listAllScCookBook.do")
	public @ResponseBody Object listAllScCookBook(ScCookBookSch searchEntitySch) {
		return this.queryAll(searchEntitySch);
	}

	// 获取详细信息
	@RequestMapping("/getScCookBookById.do")
	public @ResponseBody ScCookBook getScCookBookById(ScCookBook entity) {
		return this.getService().get(entity.getId());
	}
	//
	/*
	 * @RequestMapping("/listAllScCookBook.do") public @ResponseBody Object
	 * listAdminScCookBook(ScCookBookSch searchEntitySch) { int agentId =
	 * searchEntitySch.getAgentIdSch(); User user = userService.get(agentId);
	 * return this.queryAll(searchEntitySch); }
	 */

}