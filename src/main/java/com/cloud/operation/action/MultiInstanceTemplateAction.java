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
import com.cloud.operation.core.form.MultiInstanceTemplateForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.MultiInstanceTemplate;
import com.cloud.operation.service.IMultiInstanceTemplateService;

/**
 * 多实例模板控制器
 * @author syd
 *
 */
@Controller
@RequestMapping("/multiInstanceTemplate")
public class MultiInstanceTemplateAction extends BaseAction<MultiInstanceTemplateForm, MultiInstanceTemplate> {

	@Resource
	private IMultiInstanceTemplateService multiInstanceTemplateService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("multiInstanceTemplate:read")
	public Page<MultiInstanceTemplate> pageList(@RequestBody MultiInstanceTemplateForm f) {
		Page<MultiInstanceTemplate> page = new Page<MultiInstanceTemplate>();
		try {
			page = multiInstanceTemplateService.findPageList(f);
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
	@RequiresPermissions("multiInstanceTemplate:read")
	public DataVO<MultiInstanceTemplate> list(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			vo.setList(multiInstanceTemplateService.findList(f));
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
	@RequiresPermissions("multiInstanceTemplate:read")
	public DataVO<MultiInstanceTemplate> load(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			vo.setT(multiInstanceTemplateService.findById(f.getUuid()));
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> save(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				multiInstanceTemplateService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_MULTIINSTANCETEMPLATE_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				multiInstanceTemplateService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> delete(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> deletes(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> release(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.releaseById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.RELEASE_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> releases(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.releases(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.RELEASE_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> down(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.downById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DOWN_MULTIINSTANCETEMPLATE_SUCCESS);
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
	@RequiresPermissions("multiInstanceTemplate:write")
	public DataVO<MultiInstanceTemplate> downs(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.downs(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DOWN_MULTIINSTANCETEMPLATE_SUCCESS);
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
	public DataVO<MultiInstanceTemplate> up(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.upById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.UP_MULTIINSTANCETEMPLATE_SUCCESS);
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
	public DataVO<MultiInstanceTemplate> ups(@RequestBody MultiInstanceTemplateForm f) {
		DataVO<MultiInstanceTemplate> vo = new DataVO<MultiInstanceTemplate>();
		try {
			multiInstanceTemplateService.ups(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.UP_MULTIINSTANCETEMPLATE_SUCCESS);
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
	public Boolean validate(MultiInstanceTemplateForm f) {
		try {
			return multiInstanceTemplateService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
