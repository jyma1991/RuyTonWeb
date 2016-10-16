package com.ryt.web.controller.sc;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.stream.StStream;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.stream.StStreamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScClassController extends CrudController<ScClass, ScClassService> {

	@Autowired
	private StStreamService stStreamService;

	/* 新增一条班级记录，绑定一条可用的流 ********************************************/
	/*
	 * @author:lihui
	 ***************************************************************/
	/*
	 * 2015.11.10 createtime
	 *******************************************************/
	@RequestMapping("/addScClass.do")
	public @ResponseBody MessageResult addScClass(ScClass scClass) {
		MessageResult ret = this.save(scClass);
		System.out.println("添加后的主键ID:" + scClass.getId());
		StStream s = stStreamService.get(scClass.getStreamId());
		s.setAgentId(scClass.getAgentId());
		s.setClassId(scClass.getId());
		s.setSchoolId(scClass.getSchoolId());
		stStreamService.update(s);
		return ret;
	}

	/* 删除一条班级记录，将与这个班级绑定的流释放为可用的流 ***************************/
	/*
	 * @author:lihui
	 ***************************************************************/
	/*
	 * 2015.11.9 createtime
	 ********************************************************/
	@RequestMapping("/delScClass.do")
	public @ResponseBody MessageResult delScClass(ScClass entity, StStream stream) {
		StStream old = stStreamService.getStStreamByClassId(entity.getId());
		if (old != null) {
			stStreamService.cancelStStreamLink(old.getClassId());
		}
		return this.delete(entity);
	}

	/* 修改一条班级记录，将与这个班级绑定的流释放为可用的流并重新绑定一条可用的流 *******/
	/*
	 * @author:lihui
	 ***************************************************************/
	/*
	 * 2015.11.9 createtime
	 ********************************************************/
	@RequestMapping("/updateScClass.do")
	public @ResponseBody MessageResult updateScClass(ScClass entity, StStream stream) {
		//班级存在学生时不能更改为公共场地
		if(RuyTonAppContants.CLASS_TYPE_PUBLIC==entity.getPlaceTypeId()&&this.getService().getScStudentCountByClassId(entity.getId())>0){
			return error("该班级中存在学生，无法更改班级类型！");
		}
		StStream old = stStreamService.getStStreamByClassId(entity.getId());
		if (old != null) {
			stStreamService.cancelStStreamLink(old.getClassId());
		}
		StStream s = stStreamService.get(entity.getStreamId());
		if (s != null) {
			s.setAgentId(entity.getAgentId());
			s.setClassId(entity.getId());
			s.setSchoolId(entity.getSchoolId());
			stStreamService.update(s);
		}
		this.getService().updateStudentTeacherId(entity);
		return this.update(entity);
	}

	// 条件查询分页操作
	@RequestMapping("/listScClass.do")
	public @ResponseBody GridResult listScClass(ScClassSch searchEntitySch) {
		return this.query(searchEntitySch);
	}
	
	// 条件查询分页操作
	@RequestMapping("/listStatisticScClass.do")
	public @ResponseBody GridResult listStatisticScClass(ScClassSch searchEntitySch) {
		DefaultGridResult result =  (DefaultGridResult)this.query(searchEntitySch);
		List<ScClass> list = (List<ScClass>)result.getRows();
		for(int i = 0; i<list.size(); i++){
			int streamId = list.get(i).getStreamId();
			StStream stream = stStreamService.get(streamId);
			list.get(i).setStream(stream);
		}
		return result;
		
	}

	// 鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllScClass.do")
	public @ResponseBody Object listAllScClass(ScClassSch searchEntitySch) {
		return this.queryAll(searchEntitySch);
	}

	// 鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getScClassById.do")
	public @ResponseBody ScClass getScClassById(ScClass entity) {
		return this.getService().get(entity.getId());
	}

}