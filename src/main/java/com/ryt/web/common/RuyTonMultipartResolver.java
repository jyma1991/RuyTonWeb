package com.ryt.web.common;

/**
 * @author Jyma
 *  Spring MultipartResolver 和 ServletFileUpload 冲突，重写 isMultipart xml配置文件中添加排除文件url
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class RuyTonMultipartResolver extends CommonsMultipartResolver {
	private String excludeUrls;
	private String[] excludeUrlArray;

	public String getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(String excludeUrls) {
		this.excludeUrls = excludeUrls;
		this.excludeUrlArray = excludeUrls.split(",");
	}

	/**
	 * 重写该方法，防止fileUpload.do 的servlet 上传方法被提交处理
	 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		for (String url : excludeUrlArray) {
			// 处理跳过的url
			if (request.getRequestURI().contains("fileUpload.do")) {
				return false;
			}
		}

		return super.isMultipart(request);
	}
}