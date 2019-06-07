package com.cloud.operation.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.AlertRuleForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.AlertRule;
import com.cloud.operation.service.IAlertRuleService;

@Controller
@RequestMapping("/AlertRule")
public class AlertRuleAction extends BaseAction<AlertRuleForm, AlertRule> {

	@Resource
	private IAlertRuleService AlertRuleService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Page<AlertRule> pageList(@RequestBody AlertRuleForm f) {
		Page<AlertRule> page = new Page<AlertRule>();
		try {
			page = AlertRuleService.findPageList(f);
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
	public DataVO<AlertRule> list(@RequestBody AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<AlertRule> load(@RequestBody AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<AlertRule> save(@RequestBody AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<AlertRule> delete(@RequestBody AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<AlertRule> deletes(AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(AlertRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
