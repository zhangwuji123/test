package com.cloud.operation.controller.vo;

import com.cloud.operation.db.entity.main.Module;

/**
 * 扩展Module 分配权限复选框时使用
 * 
 * @author syd
 *
 */
@SuppressWarnings("serial")
public class ModuleExt extends Module {

	private Boolean readCheck = false;

	private Boolean writeCheck = false;

	public ModuleExt() {
		// TODO Auto-generated constructor stub
	}

	public ModuleExt(Module m) {
		this(m, false, false);
	}

	public ModuleExt(Module m, Boolean readCheck, Boolean writeCheck) {
		this.setUuid(m.getUuid());
		this.setSn(m.getSn());
		this.setName(m.getName());
		this.setReadCheck(readCheck);
		this.setWriteCheck(writeCheck);
	}

	public Boolean getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(Boolean readCheck) {
		this.readCheck = readCheck;
	}

	public Boolean getWriteCheck() {
		return writeCheck;
	}

	public void setWriteCheck(Boolean writeCheck) {
		this.writeCheck = writeCheck;
	}

}
