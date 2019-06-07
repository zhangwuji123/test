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
import com.cloud.operation.core.form.LogForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.OperationLog;
import com.cloud.operation.service.ILogService;

@Controller
@RequestMapping("/log")
public class LogAction extends BaseAction<LogForm, OperationLog> {

	@Resource
	private ILogService logService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("log:read")
	public Page<OperationLog> pageList(@RequestBody LogForm f) {
		Page<OperationLog> page = new Page<OperationLog>();
		try {
			page = logService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	public DataVO<OperationLog> list(@RequestBody LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<OperationLog> load(@RequestBody LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<OperationLog> save(@RequestBody LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<OperationLog> delete(@RequestBody LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<OperationLog> deletes(LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
