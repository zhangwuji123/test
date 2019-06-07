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
import com.cloud.operation.core.form.SingleInstanceTemplateForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Business;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Datacenter;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.SingleInstanceTemplate;
import com.cloud.operation.db.entity.business.TemplateInfo;
import com.cloud.operation.db.entity.business.Zone;
import com.cloud.operation.service.ISingleInstanceTemplateService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

/**
 * 单实例模板控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/singleInstanceTemplate")
public class SingleInstanceTemplateAction extends
		BaseAction<SingleInstanceTemplateForm, SingleInstanceTemplate> {

	@Resource
	private ISingleInstanceTemplateService singleInstanceTemplateService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name"}),
		@AllowProperty(pojo = TemplateInfo.class, name = {"uuid","name","htype","ptype","size"}),
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public Page<SingleInstanceTemplate> pageList(@RequestBody SingleInstanceTemplateForm f) {
		Page<SingleInstanceTemplate> page = new Page<SingleInstanceTemplate>();
		try {
			page = singleInstanceTemplateService.findPageList(f);
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
	@RequiresPermissions("singleInstanceTemplate:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name"}),
		@AllowProperty(pojo = TemplateInfo.class, name = {"uuid","name","htype","ptype"}),
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public DataVO<SingleInstanceTemplate> list(SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			vo.setList(singleInstanceTemplateService.findList(f));
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
	@RequiresPermissions("singleInstanceTemplate:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "uuid", "alias",
					"name", "datacenter", "zone", "pool" }),
			@AllowProperty(pojo = Datacenter.class, name = { "name" }),
			@AllowProperty(pojo = Zone.class, name = { "name" }),
			@AllowProperty(pojo = Pool.class, name = { "name" }),
			@AllowProperty(pojo = TemplateInfo.class, name = { "uuid", "name",
					"htype", "ptype" }),
			@AllowProperty(pojo = Business.class, name = { "uuid", "name" }) })
	public DataVO<SingleInstanceTemplate> load(
			@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			vo.setT(singleInstanceTemplateService.findById(f.getUuid()));
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
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> save(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				singleInstanceTemplateService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_SINGLEINSTANCETEMPLATE_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				singleInstanceTemplateService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> delete(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> deletes(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/release", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> release(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.releaseById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.RELEASE_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/releases", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> releases(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.releases(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.RELEASE_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/down", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> down(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.downById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DOWN_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/downs", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> downs(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.downs(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DOWN_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/up", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> up(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.upById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.UP_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/ups", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:write")
	public DataVO<SingleInstanceTemplate> ups(@RequestBody SingleInstanceTemplateForm f) {
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			singleInstanceTemplateService.ups(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.UP_SINGLEINSTANCETEMPLATE_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(SingleInstanceTemplateForm f) {
		try {
			return singleInstanceTemplateService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchSingleMulti", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name"}),
		@AllowProperty(pojo = TemplateInfo.class, name = {"uuid","name","htype","ptype"}),
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public DataVO<SingleInstanceTemplate> searchSingleMulti(@RequestBody SingleInstanceTemplateForm f){
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			vo = singleInstanceTemplateService.searchSingleMulti(f);
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
	@RequestMapping(value = "/findListByMulti", method = RequestMethod.POST)
	@RequiresPermissions("singleInstanceTemplate:read")
	public DataVO<SingleInstanceTemplate> findListByMulti(@RequestBody SingleInstanceTemplateForm f){
		DataVO<SingleInstanceTemplate> vo = new DataVO<SingleInstanceTemplate>();
		try {
			vo.setData(singleInstanceTemplateService.findListByMulti(f));
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
