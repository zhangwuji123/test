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
import com.cloud.operation.core.form.HypervisorServerContainerForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.HypervisorServerContainer;
import com.cloud.operation.service.IHypervisorServerContainerService;

@Controller
@RequestMapping("/container")
public class HypervisorServerContainerAction extends
		BaseAction<HypervisorServerContainerForm, HypervisorServerContainer> {

	@Resource
	private IHypervisorServerContainerService hypervisorServerContainerService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:read")
	public Page<HypervisorServerContainer> pageList(
			@RequestBody HypervisorServerContainerForm f) {
		Page<HypervisorServerContainer> page = new Page<HypervisorServerContainer>();
		try {
			page = hypervisorServerContainerService.findPageList(f);
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
	@RequiresPermissions("hypervisor:read")
	public DataVO<HypervisorServerContainer> list(
			@RequestBody HypervisorServerContainerForm f) {
		DataVO<HypervisorServerContainer> vo = new DataVO<HypervisorServerContainer>();
		try {
			vo.setList(hypervisorServerContainerService.findList(f));
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
	@RequiresPermissions("hypervisor:read")
	public DataVO<HypervisorServerContainer> load(
			@RequestBody HypervisorServerContainerForm f) {
		DataVO<HypervisorServerContainer> vo = new DataVO<HypervisorServerContainer>();
		try {
			vo.setT(hypervisorServerContainerService.findById(f.getUuid()));
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
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServerContainer> save(
			@RequestBody HypervisorServerContainerForm f) {
		DataVO<HypervisorServerContainer> vo = new DataVO<HypervisorServerContainer>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				hypervisorServerContainerService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_HSC_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				hypervisorServerContainerService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_HSC_SUCCESS);
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
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServerContainer> delete(
			@RequestBody HypervisorServerContainerForm f) {
		DataVO<HypervisorServerContainer> vo = new DataVO<HypervisorServerContainer>();
		try {
			hypervisorServerContainerService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HSC_SUCCESS);
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
	public DataVO<HypervisorServerContainer> deletes(
			HypervisorServerContainerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(HypervisorServerContainerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
