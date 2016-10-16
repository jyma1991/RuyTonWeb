package com.ryt.web.controller.area;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.area.AreaDistrict;
import com.ryt.web.entity.area.AreaDistrictSch;
import com.ryt.web.service.area.AreaDistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AreaDistrictController extends
		CrudController<AreaDistrict, AreaDistrictService> {

		//新增记录
	@RequestMapping("/addAreaDistrict.do")
	public @ResponseBody
	MessageResult addAreaDistrict(AreaDistrict entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAreaDistrict.do")
	public @ResponseBody
	MessageResult delAreaDistrict(AreaDistrict entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAreaDistrict.do")
	public @ResponseBody
	MessageResult updateAreaDistrict(AreaDistrict entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAreaDistrict.do")
	public @ResponseBody
	GridResult listAreaDistrict(AreaDistrictSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAreaDistrict.do")
    public @ResponseBody Object listAllAreaDistrict(AreaDistrictSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAreaDistrictById.do")
    public @ResponseBody AreaDistrict getAreaDistrictById(AreaDistrict entity) {
        return this.getService().get(entity.getId());
    }

 	

}