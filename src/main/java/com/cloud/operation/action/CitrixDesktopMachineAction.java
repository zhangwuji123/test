package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.CitrixChangeUserForm;
import com.cloud.operation.core.form.CitrixDesktopMachineForm;
import com.cloud.operation.core.form.CitrixDesktopPasswordForm;
import com.cloud.operation.core.form.TaskInfoForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.business.TaskMsgInfo;
import com.cloud.operation.db.entity.business.citrix.CitrixDesktopGroup;
import com.cloud.operation.db.entity.business.citrix.CitrixDesktopMachine;
import com.cloud.operation.db.entity.business.citrix.CitrixMachineCatalog;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.ICitrixDesktopMachineService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/citrix/desktopMachine")
public class CitrixDesktopMachineAction extends BaseAction<CitrixDesktopMachineForm, CitrixDesktopMachine> {

	@Resource
	private ICitrixDesktopMachineService citrixDesktopMachineService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
//	@RequiresPermissions("desktopCitrix:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CitrixDesktopMachine.class, name = { "name", "citrixDesktopGroup", "machineName", "associatedUserNames", "state", "description", "createTime", "user", "citrixMachineCatalog","os","cpu","memory","rootdisksize","datadisksize","desktopType","finishTime","serialNumber","expirePierod","citrixUserPassword","alert","changeUserState"}),
			@AllowProperty(pojo = User.class, name = { "realname", "username" }),
			@AllowProperty(pojo = CitrixDesktopGroup.class, name = { "name" }),
			@AllowProperty(pojo = CitrixMachineCatalog.class, name = { "name" }) })
	public Page<CitrixDesktopMachine> pageList(@RequestBody CitrixDesktopMachineForm f) {
		Page<CitrixDesktopMachine> page = new Page<CitrixDesktopMachine>();
		try {
			page = citrixDesktopMachineService.findPageList(f);
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
	
	@ResponseBody
	@RequestMapping(value = "/taskList", method = RequestMethod.POST)
	public Page<TaskInfo> taskList(@RequestBody CitrixDesktopMachineForm f) {
		Page<TaskInfo> page = new Page<TaskInfo>();
		try {
			page = citrixDesktopMachineService.findTaskPageList(f);
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
	
	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	//@RequiresPermissions("task:write")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = User.class, name = {"uuid","realname","username"})
	})
	public DataVO<TaskMsgInfo> send(@RequestBody TaskInfoForm f){
		DataVO<TaskMsgInfo> vo = new DataVO<TaskMsgInfo>();
		try {
			citrixDesktopMachineService.send(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("消息正在发送中。");
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	@RequiresPermissions("vpc:write")
	public DataVO<CitrixDesktopMachine> save(@RequestBody CitrixDesktopMachineForm f) {
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try {
			if(StringUtil.isEmpty(f.getUserUuid())){
				throw new ServiceException("用户不能为空");
			}
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				citrixDesktopMachineService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("Citrix桌面添加中......");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				citrixDesktopMachineService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("Citrix桌面修改中......");
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

	@ResponseBody
	@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> batchCreate(@RequestBody CitrixDesktopMachineForm f) {
		int count=0;
	
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try {
			if(f.getCount()!=null && f.getCount()!=0) {
				count=f.getCount();
			}else{
				vo.setState(ResponseState.FAILURE.getValue());
				vo.setMessage("批量创建云桌面的数量不能为0");
			}
			citrixDesktopMachineService.batchCreate(f, count);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("批量创建云桌面："+count+"台正在创建中---");
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
	public DataVO<CitrixDesktopMachine> list(CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping("/load")
	@RequiresPermissions("desktopCitrix:read")
	@Override
	public DataVO<CitrixDesktopMachine> load(@RequestBody CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			vo.setT(citrixDesktopMachineService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
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
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> delete(@RequestBody CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面删除中......");
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
	@ResponseBody
	@RequestMapping(value = "/setState", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> setState(@RequestBody CitrixDesktopMachineForm f){
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try {
			citrixDesktopMachineService.setState(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("修改状态完成");
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
	public DataVO<CitrixDesktopMachine> deletes(CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/resetPassword", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> resetPassword(@RequestBody CitrixDesktopPasswordForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.resetPassword(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面密码正在修改中.......请稍后重试新密码");
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
	
	/**
	 * citrix云桌面关系解除
	 * @Date 2018-06-04
	 * @author czl
	 */
	@ResponseBody
	@RequestMapping(value="/relationRemove", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> relationRemove(@RequestBody CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.relationRemove(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面关系解除成功");
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
	
	/**
	 * citrix云桌面关系建立
	 * @Date 2018-06-04
	 * @author czl
	 */
	@ResponseBody
	@RequestMapping(value="/relationBuild", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> relationBuild(@RequestBody CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.relationBuild(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面关系建立成功");
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
	
	/**
	 * citrix云桌面更改配置
	 * @Date 2018-06-05
	 * @author czl
	 */
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> update(@RequestBody CitrixDesktopMachineForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.updateById(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面配置变更成功");
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
	/**
	 * citrix云桌变更用户
	 * @Date 2018-06-06
	 * @author xuw
	 */
	@ResponseBody
	@RequestMapping(value="/change", method = RequestMethod.POST)
	@RequiresPermissions("desktopCitrix:write")
	public DataVO<CitrixDesktopMachine> changeUser(@RequestBody CitrixChangeUserForm f) {
		// TODO Auto-generated method stub
		DataVO<CitrixDesktopMachine> vo = new DataVO<CitrixDesktopMachine>();
		try{
			citrixDesktopMachineService.changeUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云桌面用户变更中......");
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
}
