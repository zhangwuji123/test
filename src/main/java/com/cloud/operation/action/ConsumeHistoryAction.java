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
import com.cloud.operation.core.form.ChargeDetailForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ChargeDetail;
import com.cloud.operation.service.IChargeDetailService;

@Controller
@RequestMapping("/consume")
public class ConsumeHistoryAction extends BaseAction<ChargeDetailForm, ChargeDetail>{
	
	@Resource
	private IChargeDetailService chargeDetailService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("consume:read")
	public Page<ChargeDetail> pageList(@RequestBody ChargeDetailForm f) {
		Page<ChargeDetail> page = new Page<ChargeDetail>();
		try {
			page = chargeDetailService.findPageList(f);
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

	
	
	@ResponseBody
	@RequestMapping(value = "/conumeSum", method = RequestMethod.POST)
	public DataVO<Map<String,Object>> conumeSum(@RequestBody ChargeDetailForm f) {
		DataVO<Map<String,Object>> vo = new DataVO<Map<String,Object>>();
		try {
			vo.setT(chargeDetailService.consumeTotal(f));
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
	public DataVO<ChargeDetail> list(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<ChargeDetail> load(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<ChargeDetail> save(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<ChargeDetail> delete(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<ChargeDetail> deletes(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean validate(ChargeDetailForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
