package com.ryt.web.controller.area;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.area.AreaCity;
import com.ryt.web.entity.area.AreaCitySch;
import com.ryt.web.service.area.AreaCityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AreaCityController extends
		CrudController<AreaCity, AreaCityService> {

		//新增记录
	@RequestMapping("/addAreaCity.do")
	public @ResponseBody
	MessageResult addAreaCity(AreaCity entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delAreaCity.do")
	public @ResponseBody
	MessageResult delAreaCity(AreaCity entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateAreaCity.do")
	public @ResponseBody
	MessageResult updateAreaCity(AreaCity entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listAreaCity.do")
	public @ResponseBody
	GridResult listAreaCity(AreaCitySch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllAreaCity.do")
    public @ResponseBody Object listAllAreaCity(AreaCitySch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getAreaCityById.do")
    public @ResponseBody AreaCity getAreaCityById(AreaCity entity) {
        return this.getService().get(entity.getId());
    }

 	

}