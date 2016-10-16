package com.ryt.web.common;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import com.ryt.web.util.RightUtil;

/**
 * 权限检查标签
 * @author hc.tang
 *
 */
public class RmsRoleTag extends ConditionalTagSupport {

	private static final String SYS_RES_ID = "systemResourceId";
	// 操作代码
	private String operateCode;

	@Override
	protected boolean condition() throws JspTagException {
		return RightUtil.check(
				this.pageContext.getRequest().getParameter(SYS_RES_ID),
				operateCode);
		//return true;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

}
