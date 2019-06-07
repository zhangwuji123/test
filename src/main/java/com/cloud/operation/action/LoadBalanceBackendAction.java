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
import com.cloud.operation.core.form.LoadBalanceBackendForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.LoadBalanceBackend;
import com.cloud.operation.service.ILoadBalanceBackendService;

/**
 * 负载均衡后端主机控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/loadBalanceBackend")
public class LoadBalanceBackendAction extends
		BaseAction<LoadBalanceBackendForm, LoadBalanceBackend> {

	@Resource
	private ILoadBalanceBackendService loadBalanceBackendService;

	public LoadBalanceBackendAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Page<LoadBalanceBackend> pageList(
			@RequestBody LoadBalanceBackendForm f) {
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:read")
	public DataVO<LoadBalanceBackend> list(@RequestBody LoadBalanceBackendForm f) {
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			vo.setList(loadBalanceBackendService.findList(f));
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
	public DataVO<LoadBalanceBackend> load(@RequestBody LoadBalanceBackendForm f) {
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			vo.setT(loadBalanceBackendService.findById(f.getUuid()));
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
	public DataVO<LoadBalanceBackend> save(@RequestBody LoadBalanceBackendForm f) {
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				loadBalanceBackendService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("负载均衡后端主机创建成功");
			}else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				loadBalanceBackendService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("负载均衡后端主机修改成功");
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
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalanceBackend> delete(
			@RequestBody LoadBalanceBackendForm f) {
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			loadBalanceBackendService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡后端主机删除成功");
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
	public DataVO<LoadBalanceBackend> deletes(
			@RequestBody LoadBalanceBackendForm f) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalanceBackend> disable(@RequestBody LoadBalanceBackendForm f){
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			loadBalanceBackendService.disable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡后端主机禁用成功");
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
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:write")
	public DataVO<LoadBalanceBackend> enable(@RequestBody LoadBalanceBackendForm f){
		DataVO<LoadBalanceBackend> vo = new DataVO<LoadBalanceBackend>();
		try {
			loadBalanceBackendService.enable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡后端主机启用成功");
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
	public Boolean validate(LoadBalanceBackendForm f) {
		return null;
	}
	
}
