package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.BaseForm;
import com.cloud.operation.core.form.SysconfigForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.SystemServiceType;
import com.cloud.operation.service.ISystemServiceService;

@Controller
@RequestMapping("/systemService")
public class SystemServiceAction extends BaseAction<BaseForm, SystemServiceType> {
	
	public static final Logger logger = LoggerFactory.getLogger(SystemServiceAction.class);
	
	@Resource
	private ISystemServiceService systemServiceService;
	
	
	


	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("systemService:read")
	public DataVO<SystemServiceType> list(BaseForm f) {
		DataVO<SystemServiceType> vo = new DataVO<SystemServiceType>();
		try {
			vo.setData(systemServiceService.findList(f));
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
	public Boolean validate(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SystemServiceType> pageList(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SystemServiceType> load(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SystemServiceType> save(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SystemServiceType> delete(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SystemServiceType> deletes(BaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}
}
