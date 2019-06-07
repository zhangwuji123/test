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
import com.cloud.operation.core.form.HypervisorServerForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.HypervisorServer;
import com.cloud.operation.service.IHypervisorServerService;

@Controller
@RequestMapping("/hypervisor")
public class HypervisorServerAction extends
		BaseAction<HypervisorServerForm, HypervisorServer> {

	@Resource
	private IHypervisorServerService hypervisorServerService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:read")
	public Page<HypervisorServer> pageList(@RequestBody HypervisorServerForm f) {
		Page<HypervisorServer> page = new Page<HypervisorServer>();
		try {
			page = hypervisorServerService.findPageList(f);
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
	public DataVO<HypervisorServer> list(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			vo.setList(hypervisorServerService.findList(f));
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
	public DataVO<HypervisorServer> load(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			vo.setT(hypervisorServerService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces="application/json")
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServer> save(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				hypervisorServerService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_HYPERSERVER_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				hypervisorServerService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_HYPERSERVER_SUCCESS);
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
	public DataVO<HypervisorServer> delete(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			hypervisorServerService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HYPERSERVER_SUCCESS);
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
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServer> resetPassword(
			@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			hypervisorServerService.resetPassword(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HYPERSERVER_SUCCESS);
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
	@RequestMapping(value = "/findVnc", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:read")
	public DataVO<HypervisorServer> findVnc(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			vo.setT(hypervisorServerService.findVnc(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HYPERSERVER_SUCCESS);
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServer> deletes(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			hypervisorServerService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HYPERSERVER_SUCCESS);
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
	public Boolean validate(HypervisorServerForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkConfig", method = RequestMethod.POST)
	@RequiresPermissions("hypervisor:write")
	public DataVO<HypervisorServer> checkConfig(@RequestBody HypervisorServerForm f) {
		DataVO<HypervisorServer> vo = new DataVO<HypervisorServer>();
		try {
			hypervisorServerService.checkConfig(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("开始验证链接配置，请稍后");
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
