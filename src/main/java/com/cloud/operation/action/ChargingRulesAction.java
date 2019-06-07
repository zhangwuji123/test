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
import com.cloud.operation.core.form.ChargingRulesForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ChargingRules;
import com.cloud.operation.service.IChargingRulesService;

/**
 * 计费规则控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/chargingRules")
public class ChargingRulesAction extends
		BaseAction<ChargingRulesForm, ChargingRules> {

	@Resource
	private IChargingRulesService chargingRulesService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("chargingRules:read")
	public Page<ChargingRules> pageList(@RequestBody ChargingRulesForm f) {
		Page<ChargingRules> page = new Page<ChargingRules>();
		try {
			page = chargingRulesService.findPageList(f);
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
	@RequiresPermissions("chargingRules:read")
	public DataVO<ChargingRules> list(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			vo.setList(chargingRulesService.findList(f));
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
	@RequiresPermissions("chargingRules:read")
	public DataVO<ChargingRules> load(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			vo.setT(chargingRulesService.findById(f.getUuid()));
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
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("chargingRules:write")
	public DataVO<ChargingRules> save(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				chargingRulesService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CHARGINGRULES_SUCCESS);
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				chargingRulesService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CHARGINGRULES_SUCCESS);
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
	@RequiresPermissions("chargingRules:write")
	public DataVO<ChargingRules> delete(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			chargingRulesService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CHARGINGRULES_SUCCESS);
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
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	@RequiresPermissions("chargingRules:write")
	public DataVO<ChargingRules> enable(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			chargingRulesService.enable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("计费规则启用成功");
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
	@RequiresPermissions("chargingRules:write")
	public DataVO<ChargingRules> disable(@RequestBody ChargingRulesForm f) {
		DataVO<ChargingRules> vo = new DataVO<ChargingRules>();
		try {
			chargingRulesService.disable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("计费规则废弃成功");
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
	public DataVO<ChargingRules> deletes(ChargingRulesForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ChargingRulesForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
