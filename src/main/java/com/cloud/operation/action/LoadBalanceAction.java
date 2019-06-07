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
import com.cloud.operation.core.form.LoadBalanceForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.LoadBalance;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.service.ILoadBalanceService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/loadBalance")
public class LoadBalanceAction extends BaseAction<LoadBalanceForm, LoadBalance>{

	@Resource
	private ILoadBalanceService loadBalanceService;
	
	public LoadBalanceAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:read")
	@IgnoreProperties(
	value = {@IgnoreProperty(pojo = LoadBalance.class, name={"loadBalanceListeners", "instanceInfo"})},
	allow = {
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public Page<LoadBalance> pageList(@RequestBody LoadBalanceForm f) {
		Page<LoadBalance> page = new Page<LoadBalance>();
		try {
			page = loadBalanceService.findPageList(f);
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
	@RequiresPermissions("loadBalance:read")
	public DataVO<LoadBalance> list(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			vo.setList(loadBalanceService.findList(f));
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
	@RequiresPermissions("loadBalance:read")
	@IgnoreProperties(
	value = {@IgnoreProperty(pojo = LoadBalance.class, name={"taskInfos"})},
	allow = {
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid","vnets"})
	})
	public DataVO<LoadBalance> load(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			vo.setT(loadBalanceService.findById(f.getUuid()));
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
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> save(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡正在创建中，请稍后");
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
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> delete(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡正在删除中，请稍后");
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
	@RequestMapping(value = "/boot", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> boot(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.bootById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡正在启动中，请稍后");
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
	@RequestMapping(value = "/shutdown", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> shutdown(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.shutdownById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡正在关闭中，请稍后");
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
	@RequestMapping(value = "/restart", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> restart(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.restartById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡正在重启中，请稍后");
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
	@RequestMapping(value = "/configHa", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> configHa(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.configHa(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡高可用正在配置中，请稍后");
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
	@RequestMapping(value = "/cancelHa", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> cancelHa(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.cancelHa(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡高可用正在取消中，请稍后");
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
	@RequestMapping(value = "/resetState", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> resetState(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.resetState(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡重置状态成功");
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
	@RequestMapping(value = "/applicationUpdate", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> applicationUpdate(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.applicationUpdate(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡应用正在修改中，请稍后");
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
	@RequestMapping(value = "/extend", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> extend(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.extend(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡延长使用时间成功");
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
	public DataVO<LoadBalance> deletes(@RequestBody LoadBalanceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LoadBalanceForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalance> setUser(@RequestBody LoadBalanceForm f) {
		DataVO<LoadBalance> vo = new DataVO<LoadBalance>();
		try {
			loadBalanceService.setUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡指定用户完成");
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
