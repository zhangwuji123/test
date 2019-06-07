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
import com.cloud.operation.core.form.AlertForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Alert;
import com.cloud.operation.db.vo.AlertVO;
import com.cloud.operation.service.IAlertService;

@Controller
@RequestMapping("/alert")
public class AlertAction extends BaseAction<AlertForm, Alert> {

	@Resource
	private IAlertService alertService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("alert:read")
	public Page<Alert> pageList(@RequestBody AlertForm f) {
		Page<Alert> page = new Page<Alert>();
		try {
			page = alertService.findPageList(f);
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
	public DataVO<Alert> list(@RequestBody AlertForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("alert:read")
	public DataVO<Alert> load(@RequestBody AlertForm f) {
		DataVO<Alert> vo = new DataVO<Alert>();
		try {
			vo.setT(alertService.findById(f.getUuid()));
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

	@Override
	public DataVO<Alert> save(@RequestBody AlertForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Alert> delete(@RequestBody AlertForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/handle", method = RequestMethod.POST)
	@RequiresPermissions("alert:write")
	public DataVO<Alert> handle(@RequestBody AlertForm f) {
		DataVO<Alert> vo = new DataVO<Alert>();
		try {
			alertService.handle(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.HANDLE_ALERT_SUCCESS);
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

	@ResponseBody
	@RequestMapping(value = "/handles", method = RequestMethod.POST)
	@RequiresPermissions("alert:write")
	public DataVO<Alert> handles(@RequestBody AlertForm f) {
		DataVO<Alert> vo = new DataVO<Alert>();
		try {
			alertService.handles(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.HANDLE_ALERT_SUCCESS);
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

	@ResponseBody
	@RequestMapping(value = "/assignUser", method = RequestMethod.POST)
	@RequiresPermissions("alert:write")
	public DataVO<Alert> assignUser(@RequestBody AlertForm f) {
		DataVO<Alert> vo = new DataVO<Alert>();
		try {
			alertService.assignUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("指派用户成功");
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

	@ResponseBody
	@RequestMapping(value = "/findTop", method = RequestMethod.POST)
	public DataVO<Alert> findTop() {
		DataVO<Alert> vo = new DataVO<Alert>();
		try {
			vo.setData(alertService.getHomeListAlert());
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
	public DataVO<Alert> deletes(AlertForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(AlertForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findLevelByType", method = RequestMethod.POST)
	public DataVO<AlertVO> findLevelByType(@RequestBody AlertForm f) {
		DataVO<AlertVO> vo = new DataVO<AlertVO>();
		try {
			vo.setList(alertService.findLevelByType(f));
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

}
