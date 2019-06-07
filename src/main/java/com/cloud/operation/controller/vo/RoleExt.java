package com.cloud.operation.controller.vo;

import com.cloud.operation.db.entity.main.Role;

/**
 * 扩展Role 分配角色复选框时使用
 * 
 * @author syd
 *
 */
@SuppressWarnings("serial")
public class RoleExt extends Role {

	private Boolean check = false;

	public RoleExt() {
		// TODO Auto-generated constructor stub
	}

	public RoleExt(Role r) {
		this(r, false);
	}

	public RoleExt(Role r, Boolean check) {
		this.setUuid(r.getUuid());
		this.setName(r.getName());
		this.setCheck(check);
		this.setDescription(r.getDescription());
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

}
