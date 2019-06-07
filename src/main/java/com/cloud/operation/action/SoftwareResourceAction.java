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
import com.cloud.operation.core.form.SoftwareResourceForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Software;
import com.cloud.operation.db.entity.business.SoftwareResource;
import com.cloud.operation.service.ISoftwareResourceService;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/softwareResource")
public class SoftwareResourceAction extends BaseAction<SoftwareResourceForm, SoftwareResource> {

	@Resource
	private ISoftwareResourceService softwareResourceService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("softwareResource:read")
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public Page<SoftwareResource> pageList(@RequestBody SoftwareResourceForm f) {
		Page<SoftwareResource> page = new Page<SoftwareResource>();
		try {
			page = softwareResourceService.findPageList(f);
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
	public DataVO<SoftwareResource> list(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareResource> load(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareResource> save(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareResource> delete(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<SoftwareResource> deletes(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(SoftwareResourceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
