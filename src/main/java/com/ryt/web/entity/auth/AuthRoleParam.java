package com.ryt.web.entity.auth;

import java.util.List;

/**
 * �����û�Ȩ�޲�����
 * @author Jyma
 *
 */
public class AuthRoleParam {
	private Integer id;
	private Integer[] sysFuncId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer[] getSysFuncId() {
		return sysFuncId;
	}
	public void setSysFuncId(Integer[] sysFuncId) {
		this.sysFuncId = sysFuncId;
	}
	
	
}
