package com.ryt.web.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysClassSch;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.sys.SysClassService;

@Controller
public class SysClassController extends
		CrudController<SysClass, SysClassService> {
	@Autowired
	ArticleBaseService articleBaseService;
	
		//鏂板璁板綍
	@RequestMapping("/addSysClass.do")
	public @ResponseBody
	MessageResult addSysClass(SysClass entity) {
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delSysClass.do")
	public @ResponseBody
	MessageResult delSysClass(SysClass entity) {
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateSysClass.do")
	public @ResponseBody
	MessageResult updateSysClass(SysClass entity) {
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listSysClass.do")
	public @ResponseBody
	Object listSysClass(SysClassSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
		return this.getService().getListSysClassAndArticleCount(searchEntitySch);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
		@RequestMapping("/listScClassType.do")
		public @ResponseBody
		Object listScClassType() {
			SysClassSch searchEntitySch = new SysClassSch();
			searchEntitySch.setIsDeletedSch(new Byte("0"));
			searchEntitySch.setIdSch(69);
			List<SysClass> classes1 = this.getService().getListSysClassAndArticleCount(searchEntitySch);
			searchEntitySch.setIdSch(70);
			List<SysClass> classes2 = this.getService().getListSysClassAndArticleCount(searchEntitySch);
			List<SysClass> list = new ArrayList<SysClass>();
			list.addAll(classes1);
			list.addAll(classes2);
			return list;
		}
	
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllSysClass.do")
    public @ResponseBody Object listAllSysClass(SysClassSch searchEntitySch) {
		searchEntitySch.setIsDeletedSch(new Byte("0"));
		return this.getService().getListSysClassAndArticleCount(searchEntitySch);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getSysClassById.do")
    public @ResponseBody SysClass getSysClassById(SysClass entity) {
        return this.getService().get(entity.getId());
    }

	@RequestMapping("/getForumClass.do")
	public @ResponseBody Map<String, Object> getForumClass(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			SysClassSch sysClassSch = new SysClassSch();
			sysClassSch.setSysClassTypeIdSch(RuyTonAppContants.SYSCLASSTYPEID_FORUM);
			List<SysClass> list=this.getService().getListSysClassAndArticleCount(sysClassSch);
			resultMap.put("success", true);
			resultMap.put("info", list);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", e.getMessage());
		}
		resultMap.put("token", request.getAttribute("token"));
		return resultMap;
	}

}