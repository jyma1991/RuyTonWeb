package com.ryt.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.controller.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.entity.Location;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.CoordinateConversion;
import com.ryt.app.util.GetLocationUtil;
import com.ryt.app.util.Point;
import com.ryt.web.entity.user.User;




@Controller
@RequestMapping("app/api")
public class ApiController extends CrudController<User, AppUserService> {

	@RequestMapping(value = "role.do")
	@ResponseBody
	public Map<String, Object> getRole(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// getService().get( claims.getSubject());
		final String token = (String) request.getAttribute("token");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("token", token);

		return result;

	}

	/**
	 * 获取地理信息
	 * 
	 * @param location
	 * @return
	 */
	@RequestMapping("/getLocation.do")
	@ResponseBody
	public Map<String, Object> getLocation(String platform,String location, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(RuyTonAppContants.PLATFORM_IOS.equals(platform)){
				if(!StringUtils.isEmpty(location)){
					Point point = CoordinateConversion.google_bd_encrypt(Double.parseDouble(location.split(",")[0]),Double.parseDouble(location.split(",")[1]));
					location = point.getLat()+","+point.getLng();
				}	
			}
			GetLocationUtil getLocationUtil = new GetLocationUtil();
			Location locationInfo = getLocationUtil.GetLocationInfo(location);
			result.put("success", true);
			result.put("info", locationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("info", e.getMessage());
		}
		result.put("token", (String) request.getAttribute("token"));
		return result;

	}
	
	

}
