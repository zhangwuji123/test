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
import com.cloud.operation.core.form.WsusUpdateForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.WsusUpdate;
import com.cloud.operation.service.IWsusUpdateService;

/**
 * 补丁控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/wsusUpdate")
public class WsusUpdateAction extends BaseAction<WsusUpdateForm, WsusUpdate> {

	@Resource
	private IWsusUpdateService wsusUpdateService;

	

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("wsusUpdate:read")
	public Page<WsusUpdate> pageList(@RequestBody WsusUpdateForm f) {
		Page<WsusUpdate> page = new Page<WsusUpdate>();
		try {
			page = wsusUpdateService.findPageList(f);
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
	public DataVO<WsusUpdate> list(WsusUpdateForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("wsusUpdate:read")
	public DataVO<WsusUpdate> load(@RequestBody WsusUpdateForm f) {
		DataVO<WsusUpdate> vo = new DataVO<WsusUpdate>();
		try{
			vo.setT(wsusUpdateService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}


	@Override
	public DataVO<WsusUpdate> save(WsusUpdateForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusUpdate> delete(WsusUpdateForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusUpdate> deletes(WsusUpdateForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean validate(WsusUpdateForm f) {
		// TODO Auto-generated method stub
		return null;
	}
}
