package com.ryt.web.controller.area;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.area.AreaProvince;
import com.ryt.web.entity.area.AreaProvinceSch;
import com.ryt.web.service.area.AreaProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AreaProvinceController extends
		CrudController<AreaProvince, AreaProvinceService> {

		//新增记录
	@RequestMapping("/addAreaProvince.do")
	public @ResponseBody
	MessageResult addAreaProvince(AreaProvince entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAreaProvince.do")
	public @ResponseBody
	MessageResult delAreaProvince(AreaProvince entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAreaProvince.do")
	public @ResponseBody
	MessageResult updateAreaProvince(AreaProvince entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAreaProvince.do")
	public @ResponseBody
	GridResult listAreaProvince(AreaProvinceSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAreaProvince.do")
    public @ResponseBody Object listAllAreaProvince(AreaProvinceSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAreaProvinceById.do")
    public @ResponseBody AreaProvince getAreaProvinceById(AreaProvince entity) {
        return this.getService().get(entity.getId());
    }

 	

}