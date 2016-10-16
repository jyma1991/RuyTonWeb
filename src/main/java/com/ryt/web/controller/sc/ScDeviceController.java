package com.ryt.web.controller.sc;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.sc.ScDevice;
import com.ryt.web.entity.sc.ScDeviceSch;
import com.ryt.web.service.sc.ScDeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScDeviceController extends
		CrudController<ScDevice, ScDeviceService> {

		//新增记录
	@RequestMapping("/addScDevice.do")
	public @ResponseBody
	MessageResult addScDevice(ScDevice entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delScDevice.do")
	public @ResponseBody
	MessageResult delScDevice(ScDevice entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateScDevice.do")
	public @ResponseBody
	MessageResult updateScDevice(ScDevice entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listScDevice.do")
	public @ResponseBody
	GridResult listScDevice(ScDeviceSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllScDevice.do")
    public @ResponseBody Object listAllScDevice(ScDeviceSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getScDeviceById.do")
    public @ResponseBody ScDevice getScDeviceById(ScDevice entity) {
        return this.getService().get(entity.getId());
    }

 	

}