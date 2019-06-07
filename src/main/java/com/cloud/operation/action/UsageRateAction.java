package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.TopNForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.HostsGroupInfo;
import com.cloud.operation.db.entity.business.VmGroupInfo;
import com.cloud.operation.service.ITopNService;

@Controller
@RequestMapping("/usageRate")
public class UsageRateAction extends BaseAction<TopNForm, VmGroupInfo>{
	
	@Resource
	private ITopNService topNService;
	
	@Override
	public Page<VmGroupInfo> pageList(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
//	@ResponseBody
//	@RequestMapping(value = "/list", method = RequestMethod.POST)
//	@RequiresPermissions("usageRate:read")
	public DataVO<VmGroupInfo> list(@RequestBody TopNForm f) {
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("usageRate:read")
	public DataVO<Object> report(@RequestBody TopNForm f) {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setList(topNService.searchUsageRate(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	public DataVO<VmGroupInfo> load(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> save(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> delete(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> deletes(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
