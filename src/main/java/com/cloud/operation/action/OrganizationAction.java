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
import com.cloud.operation.core.form.OrganizationForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Organization;
import com.cloud.operation.service.IOrganizationService;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/organization")
public class OrganizationAction extends BaseAction<OrganizationForm, Organization> {

	@Resource
	private IOrganizationService organizationService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	//@RequiresPermissions("organization:read")
	@IgnoreProperties(value = { @IgnoreProperty(pojo = Organization.class, name = { "organizationUserRelations" }) })
	public Page<Organization> pageList(@RequestBody OrganizationForm f) {
		Page<Organization> page = new Page<Organization>();
		try {
			page = organizationService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage("系统异常");
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	//@RequiresPermissions("organization:read")
	@IgnoreProperties(value = { @IgnoreProperty(pojo = Organization.class, name = { "organizationUserRelations" }) })
	public DataVO<Organization> list(@RequestBody OrganizationForm f) {
		DataVO<Organization> vo = new DataVO<Organization>();
		try {
			vo.setList(organizationService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage("系统异常");
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	//@RequiresPermissions("organization:read")
	@IgnoreProperties(value = { @IgnoreProperty(pojo = Organization.class, name = { "organizationUserRelations" }) })
	public DataVO<Organization> load(@RequestBody OrganizationForm f) {
		DataVO<Organization> vo = new DataVO<Organization>();
		try {
			vo.setT(organizationService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage("系统异常");
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	//@RequiresPermissions("organization:write")
	public DataVO<Organization> save(@RequestBody OrganizationForm f) {
		DataVO<Organization> vo = new DataVO<Organization>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vo.setT(organizationService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加组织机构成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				organizationService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改组织机构成功");
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
	//@RequiresPermissions("organization:write")
	public DataVO<Organization> delete(@RequestBody OrganizationForm f) {
		DataVO<Organization> vo = new DataVO<Organization>();
		try {
			organizationService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除组织机构成功");
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
	public DataVO<Organization> deletes(OrganizationForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(OrganizationForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
