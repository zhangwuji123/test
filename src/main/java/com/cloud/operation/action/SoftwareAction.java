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
import com.cloud.operation.core.form.SoftwareForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Software;
import com.cloud.operation.db.entity.business.SoftwareRelation;
import com.cloud.operation.service.ISoftwareService;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

/**
 * 软件控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/software")
public class SoftwareAction extends BaseAction<SoftwareForm, Software> {

	@Resource
	private ISoftwareService softwareService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("software:read")
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public Page<Software> pageList(@RequestBody SoftwareForm f) {
		Page<Software> page = new Page<Software>();
		try {
			page = softwareService.findPageList(f);
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
	@RequiresPermissions("software:read")
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public DataVO<Software> list(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			vo.setList(softwareService.findList(f));
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
	@RequiresPermissions("software:read")
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public DataVO<Software> load(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			vo.setT(softwareService.findById(f.getUuid()));
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
	@RequiresPermissions("software:write")
	public DataVO<Software> save(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				softwareService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加软件成功");
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				softwareService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改软件成功");
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
	@RequiresPermissions("software:write")
	public DataVO<Software> delete(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			softwareService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除软件成功");
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
	@RequiresPermissions("software:write")
	public DataVO<Software> deletes(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			softwareService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除软件成功");
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
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	@RequiresPermissions("software:write")
	public DataVO<Software> install(@RequestBody SoftwareForm f) {
		DataVO<Software> vo = new DataVO<Software>();
		try {
			softwareService.install(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("软件正在安装中");
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
	public Boolean validate(SoftwareForm f) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findSoftwareRelationByResourceUuid", method = RequestMethod.POST)
	@IgnoreProperties(
			value = {
				@IgnoreProperty(pojo = Software.class, name = { "softwareRelations"})
			})
	public DataVO<SoftwareRelation> findSoftwareRelationByResourceUuid(@RequestBody SoftwareForm f) {
		DataVO<SoftwareRelation> vo = new DataVO<SoftwareRelation>();
		try {
			vo.setList(softwareService.findSoftwareRelationByResourceUuid(f.getResourceUuid()));
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

}
