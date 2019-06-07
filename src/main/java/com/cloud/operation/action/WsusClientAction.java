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
import com.cloud.operation.core.form.WsusClientForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.WsusClient;
import com.cloud.operation.service.IWsusClientService;

/**
 * 计算机控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/wsusClient")
public class WsusClientAction extends BaseAction<WsusClientForm, WsusClient> {

	@Resource
	private IWsusClientService wsusClientService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("wsusGroup:read")
	public Page<WsusClient> pageList(@RequestBody WsusClientForm f) {
		Page<WsusClient> page = new Page<WsusClient>();
		try {
			page = wsusClientService.findPageList(f);
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
	public DataVO<WsusClient> load(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusClient> save(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusClient> delete(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusClient> deletes(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean validate(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DataVO<WsusClient> list(WsusClientForm f) {
		// TODO Auto-generated method stub
		return null;
	}
}
