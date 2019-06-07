package com.cloud.operation.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.form.UtilForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.AES;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;

@Controller
@RequestMapping("/util")
public class UtilAction extends BaseAction<UtilForm, String> {
	
	@ResponseBody
	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
	@RequiresPermissions("util:read")
	public DataVO<String> decrypt(@RequestBody UtilForm f) {
		DataVO<String> vo = new DataVO<String>();
		try{
			vo.setT(AES.decrypt(f.getPassword()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage("转明文失败，可能原因是密文有误");
		}
		return vo;
	}
	

	@Override
	public Page<String> pageList(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<String> list(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<String> load(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<String> save(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<String> delete(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<String> deletes(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(UtilForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
