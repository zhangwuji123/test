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
import com.cloud.operation.core.form.SysconfigForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Sysconfig;
import com.cloud.operation.service.ISysconfigService;

/**
 * 系统参数控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/sysconfig")
public class SysconfigAction extends BaseAction<SysconfigForm, Sysconfig> {

	@Resource
	private ISysconfigService sysconfigService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("sysconfig:read")
	public Page<Sysconfig> pageList(@RequestBody SysconfigForm f) {
		Page<Sysconfig> page = new Page<Sysconfig>();
		try {
			page = sysconfigService.findPageList(f);
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
	public DataVO<Sysconfig> list(@RequestBody SysconfigForm f) {
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("sysconfig:read")
	public DataVO<Sysconfig> load(@RequestBody SysconfigForm f) {
		DataVO<Sysconfig> vo = new DataVO<Sysconfig>();
		try {
			Sysconfig sysconfig = sysconfigService.findById(f.getUuid());
			vo.setT(sysconfig);
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

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("sysconfig:write")
	public DataVO<Sysconfig> save(@RequestBody SysconfigForm f) {
		DataVO<Sysconfig> vo = new DataVO<Sysconfig>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				sysconfigService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_SYSCONFIG_SUCCESS);
			} else {
				sysconfigService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_SYSCONFIG_SUCCESS);
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

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("sysconfig:delete")
	public DataVO<Sysconfig> delete(@RequestBody SysconfigForm f) {
		DataVO<Sysconfig> vo = new DataVO<Sysconfig>();
		try {
			sysconfigService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SYSCONFIG_SUCCESS);
			throw new ServiceException();
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
	@RequestMapping(value = "/findByParamName", method = RequestMethod.POST)
	public DataVO<Sysconfig> findByParamName(@RequestBody SysconfigForm f) {
		DataVO<Sysconfig> vo = new DataVO<Sysconfig>();
		try {
			Sysconfig sysconfig = sysconfigService.findByParamName(f.getParamName());
			vo.setT(sysconfig);
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

	@ResponseBody
	@RequestMapping(value = "/updateByParamName", method = RequestMethod.POST)
	public DataVO<Sysconfig> updateByParamName(@RequestBody SysconfigForm f) {
		DataVO<Sysconfig> vo = new DataVO<Sysconfig>();
		try {
			sysconfigService.updateByParamName(f);
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

	@ResponseBody
	@RequestMapping(value = "/findPhone", method = RequestMethod.POST)
	public DataVO<Sysconfig> findPhone() {
		SysconfigForm f = new SysconfigForm();
		f.setParamName("phone");
		return findByParamName(f);
	}

	@ResponseBody
	@RequestMapping(value = "/findCopyright", method = RequestMethod.POST)
	public DataVO<Sysconfig> findCopyright() {
		SysconfigForm f = new SysconfigForm();
		f.setParamName("copyright");
		return findByParamName(f);
	}

	@ResponseBody
	@RequestMapping(value = "/findVersion", method = RequestMethod.POST)
	public DataVO<Sysconfig> findVersion() {
		SysconfigForm f = new SysconfigForm();
		f.setParamName("version");
		return findByParamName(f);
	}

	@ResponseBody
	@RequestMapping(value = "/findSystemName", method = RequestMethod.POST)
	public DataVO<Sysconfig> findSystemName() {
		SysconfigForm f = new SysconfigForm();
		f.setParamName("system_name");
		return findByParamName(f);
	}

	@ResponseBody
	@RequestMapping(value = "/findSystemSkin", method = RequestMethod.POST)
	public DataVO<Sysconfig> findSystemSkin() {
		SysconfigForm f = new SysconfigForm();
		f.setParamName("admin_skin_color");
		return findByParamName(f);
	}

	@Override
	public DataVO<Sysconfig> deletes(SysconfigForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(SysconfigForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
