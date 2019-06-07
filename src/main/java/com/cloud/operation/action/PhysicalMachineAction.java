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
import com.cloud.operation.core.form.PhysicalMachineForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.PhysicalMachine;
import com.cloud.operation.service.IPhysicalMachineService;

@Controller
@RequestMapping("/physicalMachine")
public class PhysicalMachineAction extends BaseAction<PhysicalMachineForm, PhysicalMachine>{
	
	@Resource
	private IPhysicalMachineService physicalMachineService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:read")
	public Page<PhysicalMachine> pageList(@RequestBody PhysicalMachineForm f) {
		Page<PhysicalMachine> page = null;
		try{
			page = physicalMachineService.findPageList(f);
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

	@ResponseBody
	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:read")
	public Page<PhysicalMachine> findPage(@RequestBody PhysicalMachineForm f) {
		Page<PhysicalMachine> page = new Page<PhysicalMachine>();
		try {
			page = physicalMachineService.findPage(f);
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
	@RequiresPermissions("physicalMachine:read")
	public DataVO<PhysicalMachine> list(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			vo.setList(physicalMachineService.findList(f));
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
	@RequiresPermissions("physicalMachine:read")
	public DataVO<PhysicalMachine> load(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			vo.setT(physicalMachineService.findById(f.getUuid()));
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
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> save(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vo.setT(physicalMachineService.insert(f));
				vo.setMessage(MessageUtil.ADD_PHYSICALMACHINE_SUCCESS);
				vo.setState(ResponseState.SUCCESS.getValue());
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				physicalMachineService.updateFromForm(f);
				vo.setMessage(MessageUtil.EDIT_PHYSICALMACHINE_SUCCESS);
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
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> delete(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.deleteById(f.getUuid());
			vo.setMessage(MessageUtil.DEL_PHYSICALMACHINE_SUCCESS);
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
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> deploy(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.deploy(f);
			vo.setMessage("物理机部署成功");
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
	@RequestMapping(value = "/listByConfig", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:read")
	public DataVO<PhysicalMachine> listByConfig(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			vo.setList(physicalMachineService.findListByConfig(f));
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
	public DataVO<PhysicalMachine> deletes(PhysicalMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(PhysicalMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/boot", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> boot(@RequestBody PhysicalMachineForm f){
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.boot(f.getUuid());
			vo.setMessage("物理机开始启动");
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
	@RequestMapping(value = "/shutdown", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> shutdown(@RequestBody PhysicalMachineForm f){
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.shutdown(f.getUuid());
			vo.setMessage("物理机开始关机");
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
	@RequestMapping(value = "/restart", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> restart(@RequestBody PhysicalMachineForm f){
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.restart(f.getUuid());
			vo.setMessage("物理机开始重启");
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
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> setUser(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try{
			physicalMachineService.setUser(f);
			vo.setMessage("指定所属用户完成");
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
	@RequestMapping(value = "/extend", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> extend(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			physicalMachineService.extend(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("物理机延长使用时间成功");
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
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> imports(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			physicalMachineService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("物理机正在批量导入中");
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
	@RequestMapping(value = "/exports", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:read")
	public DataVO<PhysicalMachine> exports(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			vo.setList(physicalMachineService.exports(f));
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
	@RequestMapping(value = "/sync", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> sync(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			physicalMachineService.sync(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("物理机同步中，请稍后");
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
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	@RequiresPermissions("physicalMachine:write")
	public DataVO<PhysicalMachine> install(@RequestBody PhysicalMachineForm f) {
		DataVO<PhysicalMachine> vo = new DataVO<PhysicalMachine>();
		try {
			physicalMachineService.install(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("物理机安装软件中，请稍后");
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
