package com.ryt.web.common;



import java.util.List;

/**
 * 添加操作点参数
 * 
 * @author hc.tang
 * 
 */
public class AddOperateParam {
	private String operateCode; // 操作类型
	private int id; // 资源
	private List<Integer> roleId; // 角色

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

}
