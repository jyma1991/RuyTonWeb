package com.ryt.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class OperateIntercepter extends HandlerInterceptorAdapter {

	public String[] allowUrls;// 杩樻病鍙戠幇鍙互鐩存帴閰嶇疆涓嶆嫤鎴殑璧勬簮锛屾墍浠ュ湪浠ｇ爜閲岄潰鏉ユ帓闄�

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return super.preHandle(request, response, handler) & checkReferer(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);

	}

	// 防止直接访问.do
	private boolean checkReferer(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!request.getRequestURI().startsWith("/RuyTonWeb/app") && !request.getRequestURI().startsWith("/app")
				&& request.getHeader("referer") == null) {
			return true;
		}
		return true;
	}
}
