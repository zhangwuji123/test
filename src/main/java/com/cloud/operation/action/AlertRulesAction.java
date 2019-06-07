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
import com.cloud.operation.core.form.AlertRulesForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.AlertRules;
import com.cloud.operation.service.IAlertRulesService;

@Controller
@RequestMapping("/storageAlertRules")
public class AlertRulesAction extends BaseAction<AlertRulesForm, AlertRules> {

	@Resource
	private IAlertRulesService alertRulesService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:read")
	public Page<AlertRules> pageList(@RequestBody AlertRulesForm f) {
		Page<AlertRules> page = new Page<AlertRules>();
		try {
			page = alertRulesService.findPageList(f);
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
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:read")
	public DataVO<AlertRules> list(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			vo.setList(alertRulesService.findList(f));
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
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:read")
	public DataVO<AlertRules> load(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			vo.setT(alertRulesService.findById(f.getUuid()));
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
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:write")
	public DataVO<AlertRules> save(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				alertRulesService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加告警规则成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				alertRulesService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改告警规则成功");
			}
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
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:write")
	public DataVO<AlertRules> delete(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			alertRulesService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除告警规则成功");
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
	public DataVO<AlertRules> deletes(AlertRulesForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(AlertRulesForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:write")
	public DataVO<AlertRules> enable(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			alertRulesService.enable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("启用告警规则成功");
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
	
	
	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	@RequiresPermissions("storageAlertRules:write")
	public DataVO<AlertRules> disable(@RequestBody AlertRulesForm f) {
		DataVO<AlertRules> vo = new DataVO<AlertRules>();
		try {
			alertRulesService.disable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("停用告警规则成功");
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
