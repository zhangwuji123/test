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
import com.cloud.operation.core.form.SoftwareInstallForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Software;
import com.cloud.operation.db.entity.business.SoftwareInstall;
import com.cloud.operation.service.ISoftwareInstallService;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/softwareInstall")
public class SoftwareInstallAction extends BaseAction<SoftwareInstallForm, SoftwareInstall> {

	@Resource
	private ISoftwareInstallService softwareInstallService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("softwareInstall:read")
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public Page<SoftwareInstall> pageList(@RequestBody SoftwareInstallForm f) {
		Page<SoftwareInstall> page = new Page<SoftwareInstall>();
		try {
			page = softwareInstallService.findPageList(f);
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
	public DataVO<SoftwareInstall> list(SoftwareInstallForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareInstall> load(SoftwareInstallForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareInstall> save(SoftwareInstallForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("softwareInstall:write")
	public DataVO<SoftwareInstall> delete(@RequestBody SoftwareInstallForm f) {
		DataVO<SoftwareInstall> vo = new DataVO<SoftwareInstall>();
		try {
			softwareInstallService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除软件安装记录成功");
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
	public DataVO<SoftwareInstall> deletes(SoftwareInstallForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(SoftwareInstallForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
