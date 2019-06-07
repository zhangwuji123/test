package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ChargeDetailDayForm;
import com.cloud.operation.core.form.ChargeDetailForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ChargeDetailDay;
import com.cloud.operation.service.IChargeDetailDayService;

@Controller
@RequestMapping("/chargeDetailDay")
public class ChargeDetailDayAction extends BaseAction<ChargeDetailDayForm, ChargeDetailDay> {

	@Resource
	private IChargeDetailDayService chargeDetailDayService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("chargeDetailDay:read")
	public Page<ChargeDetailDay> pageList(@RequestBody ChargeDetailDayForm f) {
		Page<ChargeDetailDay> page = new Page<ChargeDetailDay>();
		try {
			page = chargeDetailDayService.findPageList(f);
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
	public DataVO<ChargeDetailDay> list(@RequestBody ChargeDetailDayForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public DataVO<ChargeDetailDay> load(@RequestBody ChargeDetailDayForm f) {
		return null;
	}

	@Override
	public DataVO<ChargeDetailDay> save(@RequestBody ChargeDetailDayForm f) {
		return null;
	}

	@Override
	public DataVO<ChargeDetailDay> delete(@RequestBody ChargeDetailDayForm f) {
		return null;
	}

	@Override
	public DataVO<ChargeDetailDay> deletes(ChargeDetailDayForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ChargeDetailDayForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/sum", method = RequestMethod.POST)
	public DataVO<Double> sum(@RequestBody ChargeDetailDayForm f) {
		DataVO<Double> vo = new DataVO<Double>();
		try {
			vo.setT(chargeDetailDayService.sum(f));
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
