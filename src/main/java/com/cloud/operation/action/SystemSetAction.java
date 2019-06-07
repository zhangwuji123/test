package com.cloud.operation.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.SystemSetForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.service.ISystemSetService;

@Controller
@RequestMapping("/systemSet")
public class SystemSetAction extends BaseAction<SystemSetForm, Map<String,Object>> {

	@Resource
	private ISystemSetService systemSetService;

	@Override
	public Page<Map<String, Object>> pageList(SystemSetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Map<String, Object>> list(SystemSetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Map<String, Object>> load(SystemSetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("systemSet:write")
	public DataVO<Map<String, Object>> save(@RequestBody SystemSetForm f) {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();
		try {
			systemSetService.updateFromForm(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("系统设置完成");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	public DataVO<Map<String, Object>> delete(SystemSetForm f) {
		return null;
	}

	@Override
	public DataVO<Map<String, Object>> deletes(SystemSetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(SystemSetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findChargingSwitch", method = RequestMethod.POST)
	public DataVO<Integer> findChargingSwitch() {
		DataVO<Integer> vo = new DataVO<Integer>();
		try {
			vo.setT(systemSetService.getChargingSwitch());
			vo.setState(ResponseState.SUCCESS.getValue());
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

}
