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
import com.cloud.operation.core.form.VdcForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Organization;
import com.cloud.operation.db.entity.business.OrganizationUserRelation;
import com.cloud.operation.db.entity.business.Vdc;
import com.cloud.operation.db.entity.business.VdcResource;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.IVdcService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/vdc")
public class VdcAction extends BaseAction<VdcForm, Vdc> {

	@Resource
	private IVdcService vdcService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("vdc:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Vdc.class, name = { "name", "isolationMode",
					"state", "description", "createTime","organizationUuid" }),
			@AllowProperty(pojo = User.class, name = { "realname",
					"organizationUserRelation" }),
			@AllowProperty(pojo = OrganizationUserRelation.class, name = "organization"),
			@AllowProperty(pojo = Organization.class, name = "name") })
	public Page<Vdc> pageList(@RequestBody VdcForm f) {
		Page<Vdc> page = new Page<Vdc>();
		try {
			page = vdcService.findPageList(f);
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
	@RequiresPermissions("vdc:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Vdc.class, name = { "name", "isolationMode",
					"state", "description", "createTime","organizationUuid" }),
			@AllowProperty(pojo = User.class, name = { "realname",
					"organizationUserRelation" }),
			@AllowProperty(pojo = OrganizationUserRelation.class, name = "organization"),
			@AllowProperty(pojo = Organization.class, name = "name") })
	public DataVO<Vdc> list(VdcForm f) {
		DataVO<Vdc> vo = new DataVO<Vdc>();
		try {
			vo.setList(vdcService.findList(f));
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("vdc:read")
	public DataVO<Vdc> load(@RequestBody VdcForm f) {
		DataVO<Vdc> vo = new DataVO<Vdc>();
		try {
			vo.setT(vdcService.findById(f.getUuid()));
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
	@RequiresPermissions("vdc:write")
	public DataVO<Vdc> save(@RequestBody VdcForm f) {
		DataVO<Vdc> vo = new DataVO<Vdc>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vdcService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加VDC成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				vdcService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改VDC成功");
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
	@RequiresPermissions("vdc:write")
	public DataVO<Vdc> delete(@RequestBody VdcForm f) {
		DataVO<Vdc> vo = new DataVO<Vdc>();
		try {
			vdcService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除VDC成功");
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
	public DataVO<Vdc> deletes(VdcForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(VdcForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findVdcQuota", method = RequestMethod.POST)
	@RequiresPermissions("vdc:read")
	public DataVO<Map<String,Object>> findVdcQuota(@RequestBody VdcForm f) {
		DataVO<Map<String,Object>> vo = new DataVO<Map<String,Object>>();
		try {
			vo.setT(vdcService.findVdcQuota(f));
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
	@RequestMapping(value = "/findVdcResource", method = RequestMethod.POST)
	@RequiresPermissions("vdc:read")
	public Page<VdcResource> findVdcResource(@RequestBody VdcForm f) {
		Page<VdcResource> page = new Page<VdcResource>();
		try {
			page = vdcService.findVdcResource(f);
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
	@RequestMapping(value = "/changeManager", method = RequestMethod.POST)
	@RequiresPermissions("vdc:write")
	public DataVO<Vdc> changeManager(@RequestBody VdcForm f) {
		DataVO<Vdc> vo = new DataVO<Vdc>();
		try {
			vdcService.changeManager(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("VDC指定管理员成功");
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
