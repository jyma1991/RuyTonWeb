package com.ryt.web.common;

import javax.servlet.http.HttpServletRequest;

import org.durcframework.core.UserContext;
import org.durcframework.core.UserFilter;

public class AppUserFilter extends UserFilter {

	/**
	 * 用户是否登录,如果没有登录则被拦截,跳转到getNeedLoginPage()页面
	 * 
	 * @param request
	 * @return true,已登录
	 */
	protected boolean isLogin(HttpServletRequest request) {
		// 获取请求路径,如果符合排除路径则只返回true
		String path = request.getServletPath();
		for (String str : NEED_NOT_LOGGIN) {
			if (path.indexOf(str) > -1 && str.length()>1) {
				return true;
			}
		}

		return UserContext.getInstance().getUser() != null;
	}

}
