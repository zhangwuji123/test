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
import com.cloud.operation.core.form.LoadBalanceListenerForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.LoadBalanceListener;
import com.cloud.operation.service.ILoadBalanceListenerService;


/**
 * 负载均衡监听控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/loadBalanceListener")
public class LoadBalanceListenerAction extends
		BaseAction<LoadBalanceListenerForm, LoadBalanceListener> {

	@Resource
	private ILoadBalanceListenerService loadBalanceListenerService;

	@Override
	public Page<LoadBalanceListener> pageList(LoadBalanceListenerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("loadBalance:read")
	public DataVO<LoadBalanceListener> list(
			@RequestBody LoadBalanceListenerForm f) {
		DataVO<LoadBalanceListener> vo = new DataVO<LoadBalanceListener>();
		try {
			vo.setList(loadBalanceListenerService.findList(f));
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
	public DataVO<LoadBalanceListener> load(
			@RequestBody LoadBalanceListenerForm f) {
		DataVO<LoadBalanceListener> vo = new DataVO<LoadBalanceListener>();
		try {
			vo.setT(loadBalanceListenerService.findById(f.getUuid()));
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
	public DataVO<LoadBalanceListener> save(
			@RequestBody LoadBalanceListenerForm f) {
		DataVO<LoadBalanceListener> vo = new DataVO<LoadBalanceListener>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				loadBalanceListenerService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("负载均衡监听创建成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				loadBalanceListenerService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("负载均衡监听修改成功");
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
	public DataVO<LoadBalanceListener> delete(
			@RequestBody LoadBalanceListenerForm f) {
		DataVO<LoadBalanceListener> vo = new DataVO<LoadBalanceListener>();
		try {
			loadBalanceListenerService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("负载均衡监听删除成功");
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
	public DataVO<LoadBalanceListener> deletes(LoadBalanceListenerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LoadBalanceListenerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
