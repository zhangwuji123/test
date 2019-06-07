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
import com.cloud.operation.core.form.MiniMachineForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.MiniMachine;
import com.cloud.operation.db.entity.business.SecurityDetectionResult;
import com.cloud.operation.service.IMiniMachineService;

@Controller
@RequestMapping("/miniMachine")
public class MiniMachineAction extends BaseAction<MiniMachineForm, MiniMachine>{
	
	@Resource
	private IMiniMachineService miniMachineService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:read")
	public Page<MiniMachine> pageList(@RequestBody MiniMachineForm f) {
		Page<MiniMachine> page = null;
		try{
			page = miniMachineService.findPageList(f);
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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:read")
	public DataVO<MiniMachine> list(@RequestBody MiniMachineForm f) {
		DataVO<MiniMachine> vo = new DataVO<MiniMachine>();
		try {
			vo.setList(miniMachineService.findList(f));
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
	@RequiresPermissions("miniMachine:read")
	public DataVO<MiniMachine> load(@RequestBody MiniMachineForm f) {
		DataVO<MiniMachine> vo = new DataVO<MiniMachine>();
		try{
			vo.setT(miniMachineService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:write")
	public DataVO<MiniMachine> save(@RequestBody MiniMachineForm f) {
		DataVO<MiniMachine> vo = new DataVO<MiniMachine>();
		try{
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vo.setT(miniMachineService.insert(f));
				vo.setMessage(MessageUtil.ADD_MINIMACHINE_SUCCESS);
				vo.setState(ResponseState.SUCCESS.getValue());
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				miniMachineService.updateFromForm(f);
				vo.setMessage(MessageUtil.EDIT_MINIMACHINE_SUCCESS);
				vo.setState(ResponseState.SUCCESS.getValue());
			}
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:write")
	public DataVO<MiniMachine> delete(@RequestBody MiniMachineForm f) {
		DataVO<MiniMachine> vo = new DataVO<MiniMachine>();
		try{
			miniMachineService.deleteById(f.getUuid());
			vo.setMessage(MessageUtil.DEL_MINIMACHINE_SUCCESS);
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
	public DataVO<MiniMachine> deletes(MiniMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(MiniMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/checkMiniMachine", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:write")
	public DataVO<MiniMachine> checkMiniMachine(@RequestBody MiniMachineForm f) {
		DataVO<MiniMachine> vo = new DataVO<MiniMachine>();
		try {
			miniMachineService.checkMiniMachine(f);
			vo.setMessage(MessageUtil.CHECK_MINIMACHINE_SUCCESS);
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

	@ResponseBody
	@RequestMapping(value = "/checkMiniMachineList", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:read")
	public DataVO<SecurityDetectionResult> checkMiniMachineList(@RequestBody MiniMachineForm f) {
		DataVO<SecurityDetectionResult> vo = new DataVO<SecurityDetectionResult>();
		try{
			vo.setList(miniMachineService.findCheckMiniMachineList(f.getUuid()));
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
	
	@ResponseBody
	@RequestMapping(value = "/queryCheckMiniMachine", method = RequestMethod.POST)
	@RequiresPermissions("miniMachine:read")
	public DataVO<SecurityDetectionResult> queryCheckMiniMachine(@RequestBody MiniMachineForm f) {
		DataVO<SecurityDetectionResult> vo = new DataVO<SecurityDetectionResult>();
		try{
			vo.setT(miniMachineService.findCheckMiniMachine(f.getUuid()));
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
}
