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
import com.cloud.operation.core.form.ElasticScalingForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Business;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.scaling.ElasticScaling;
import com.cloud.operation.service.IElasticScalingService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

/**
 * 伸缩管理控制器
 * 
 * @author wxd
 *
 */
@Controller
@RequestMapping("/elastic")
public class ElasticScalingAction extends BaseAction<ElasticScalingForm, ElasticScaling> {

	@Resource
	private IElasticScalingService elasticScalingService;


	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public Page<ElasticScaling> pageList(@RequestBody ElasticScalingForm f) {
		Page<ElasticScaling> page = new Page<ElasticScaling>();
		try {
			page = elasticScalingService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public DataVO<ElasticScaling> list(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			vo.setList(elasticScalingService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}


	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:read")
	@IgnoreProperties(
	allow = { 
		@AllowProperty(pojo = Business.class, name = {"uuid","name"})
	})
	public DataVO<ElasticScaling> load(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			vo.setT(elasticScalingService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}



	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:write")
	public DataVO<ElasticScaling> save(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				elasticScalingService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ELASTICSCALING_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				elasticScalingService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ELASTICSCALING_SUCCESS);
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveConfig", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:write")
	public DataVO<ElasticScaling> saveConfig(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			if ("insert".equalsIgnoreCase(f.getAction())) {
				elasticScalingService.insertConfig(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ELASTICSCALING_SUCCESS);
			} else if ("update".equalsIgnoreCase(f.getAction())) {
				elasticScalingService.updateConfig(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ELASTICSCALING_SUCCESS);
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:write")
	public DataVO<ElasticScaling> delete(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			elasticScalingService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ELASTICSCALING_SUCCESS);
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:write")
	public DataVO<ElasticScaling> deletes(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			elasticScalingService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ELASTICSCALING_SUCCESS);
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(ElasticScalingForm f) {
		try {
			return elasticScalingService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/listInstances", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:read")
	public DataVO<InstanceInfo> listInstances(@RequestBody ElasticScalingForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setList(elasticScalingService.findListInstances());
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/onOff", method = RequestMethod.POST)
	@RequiresPermissions("elasticScaling:write")
	public DataVO<ElasticScaling> onOff(@RequestBody ElasticScalingForm f) {
		DataVO<ElasticScaling> vo = new DataVO<ElasticScaling>();
		try {
			elasticScalingService.onOff(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.ONOFF_ELASTICSCALING_SUCCESS);
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

}
