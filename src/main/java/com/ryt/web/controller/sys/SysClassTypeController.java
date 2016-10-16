package com.ryt.web.controller.sys;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysClassType;
import com.ryt.web.entity.sys.SysClassTypeSch;
import com.ryt.web.service.sys.SysClassTypeService;

@Controller
public class SysClassTypeController extends
		CrudController<SysClassType, SysClassTypeService> {

		//新增记录
	@RequestMapping("/addSysClassType.do")
	public @ResponseBody
	MessageResult addSysClassType(SysClassType entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delSysClassType.do")
	public @ResponseBody
	MessageResult delSysClassType(SysClassType entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateSysClassType.do")
	public @ResponseBody
	MessageResult updateSysClassType(SysClassType entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listSysClassType.do")
	public @ResponseBody
	GridResult listSysClassType(SysClassTypeSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllSysClassType.do")
    public @ResponseBody Object listAllSysClassType(SysClassTypeSch searchEntitySch) {
		ExpressionQuery query = ExpressionQuery.buildQueryAll();
		DefaultGridResult resultGrid = (DefaultGridResult) this.queryAll(query);
		@SuppressWarnings("unchecked")
		List<SysClass> rows = (List<SysClass>) resultGrid.getRows();
		return rows;
    }

    //获取详细信息
	@RequestMapping("/getSysClassTypeById.do")
    public @ResponseBody SysClassType getSysClassTypeById(SysClassType entity) {
        return this.getService().get(entity.getId());
    }

 	

}