package com.ryt.web.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.SpringContext;
import org.springframework.stereotype.Component;

import com.ryt.log.entity.SysLog;
import com.ryt.log.service.SysLogService;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.user.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SimpleCORSFilter  implements Filter {
	// 30秒验证时间
	long between = 1 * 1000 * 30;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type, Authorization,Origin,Accept");

		SysLogService sysLogService = (SysLogService) SpringContext.getBean(SysLogService.class);
		
		SimpleDateFormat simpleDateFormat = new   SimpleDateFormat("yyyyMMdd");
		SysLog sysLog = new SysLog();
		Date   now   =   new   Date();
		
		if (request.getRequestURI().contains(".do")||request.getRequestURI().contains(".jsp")|| request.getRequestURI().contains(".html")){
			sysLog.setRequestURL(""+request.getRequestURI());
			sysLog.setQueryString(request.getQueryString());
			sysLog.setIp(request.getRemoteAddr());
			sysLog.setHeader(request.getHeader("referer"));
			sysLog.setTableName("sysLog"+simpleDateFormat.format(now));
			sysLogService.saveLog( sysLog);
		}
		String newToken = "";
		// 一些api接口访问需要token验证
		if (request.getRequestURI().contains("/api")) {
			String authHeader = request.getHeader("Authorization");
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				final String token = authHeader.substring(7); // The part after
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();

				// 周期性检查 token有效期
				if (new Date().getTime() - claims.getIssuedAt().getTime() > between) {
					// 获取该用户的手机号码
					UserService userService = (UserService) SpringContext.getBean(UserService.class);
					User user = userService.get(claims.getSubject());

					// 如果用户的tokens,存在，则返回新的token
					if (user!=null && !StringUtils.isEmpty(user.getToken())) {
						newToken = Jwts.builder().setSubject(claims.getSubject()).claim("qq", claims.get("qq"))
								.claim("weixin", claims.get("weixin")).setIssuedAt(new Date())
								.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
						//更新数据库中的token
						user.setToken(newToken);
						userService.update(user);
					}
					request.setAttribute("token", newToken);
								
				} else {
					request.setAttribute("token", token);
				}

				request.setAttribute("claims", claims);
				chain.doFilter(request, response);
			} 

		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
