package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.NetworkDeviceForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.NetworkDevice;
import com.cloud.operation.service.INetworkDeviceService;

@Controller
@RequestMapping("/networkDevice")
public class NetworkDeviceAction extends BaseAction<NetworkDeviceForm, NetworkDevice>{
	
	public static final Logger logger = LoggerFactory.getLogger(NetworkDeviceAction.class);
	
	@Resource
	private INetworkDeviceService networkDeviceService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("networkDevice:read")
	public Page<NetworkDevice> pageList(@RequestBody NetworkDeviceForm f) {
		Page<NetworkDevice> page = new Page<NetworkDevice>();
		try{
			page = networkDeviceService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("networkDevice:read")
	public DataVO<NetworkDevice> list(@RequestBody NetworkDeviceForm f) {
		DataVO<NetworkDevice> vo = new DataVO<NetworkDevice>();
		try{
			vo.setList(networkDeviceService.findList(f));
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
	@ResponseBody
	@RequestMapping("/load")
	@RequiresPermissions("networkDevice:read")
	public DataVO<NetworkDevice> load(@RequestBody NetworkDeviceForm f) {
		DataVO<NetworkDevice> vo = new DataVO<NetworkDevice>();
		try{
			vo.setT(networkDeviceService.findById(f.getUuid()));
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
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("networkDevice:write")
	public DataVO<NetworkDevice> save(@RequestBody NetworkDeviceForm f) {
		DataVO<NetworkDevice> vo = new DataVO<NetworkDevice>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				vo.setT(networkDeviceService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				//vo.setMessage(MessageUtil.ADD_NETWORKDEVICE_SUCCESS);
				vo.setMessage(MessageUtil.ADD_NETWORKDEVICE_SUCCESS_2);
			} else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				networkDeviceService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_NETWORKDEVICE_SUCCESS);
			}
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("networkDevice:write")
	public DataVO<NetworkDevice> delete(@RequestBody NetworkDeviceForm f) {
		DataVO<NetworkDevice> vo = new DataVO<NetworkDevice>();
		try{
			networkDeviceService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_NETWORKDEVICE_SUCCESS);
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	public DataVO<NetworkDevice> deletes(NetworkDeviceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(NetworkDeviceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
