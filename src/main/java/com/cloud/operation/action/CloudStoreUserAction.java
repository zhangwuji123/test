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
import com.cloud.operation.core.form.CloudStoreUserForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CloudStoreUser;
import com.cloud.operation.service.ICloudStoreUserService;

@Controller
@RequestMapping("/cloudStoreUser")
public class CloudStoreUserAction extends BaseAction<CloudStoreUserForm, CloudStoreUser>{
	
	@Resource
	private ICloudStoreUserService cloudStoreUserService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUser:read")
	public Page<CloudStoreUser> pageList(@RequestBody CloudStoreUserForm f) {
		Page<CloudStoreUser> page = new Page<CloudStoreUser>();
		try{
			page = cloudStoreUserService.findPageList(f);
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
	@RequiresPermissions("cloudStoreUser:read")
	public DataVO<CloudStoreUser> list(@RequestBody CloudStoreUserForm f) {
		DataVO<CloudStoreUser> vo = new DataVO<CloudStoreUser>();
		try{
			vo.setList(cloudStoreUserService.findList(f));
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUser:read")
	public DataVO<CloudStoreUser> load(@RequestBody CloudStoreUserForm f) {
		DataVO<CloudStoreUser> vo = new DataVO<CloudStoreUser>();
		try{
			vo.setT(cloudStoreUserService.findById(f.getUuid()));
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
	@RequiresPermissions("cloudStoreUser:write")
	public DataVO<CloudStoreUser> save(@RequestBody CloudStoreUserForm f) {
		DataVO<CloudStoreUser> vo = new DataVO<CloudStoreUser>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				cloudStoreUserService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CLOUDSTOREUSER_SUCCESS);
			}else{
				cloudStoreUserService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CLOUDSTOREUSER_SUCCESS);
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
	@RequiresPermissions("cloudStoreUser:write")
	public DataVO<CloudStoreUser> delete(@RequestBody CloudStoreUserForm f) {
		DataVO<CloudStoreUser> vo = new DataVO<CloudStoreUser>();
		try{
			cloudStoreUserService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CLOUDSTOREUSER_SUCCESS);
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
	public DataVO<CloudStoreUser> deletes(@RequestBody CloudStoreUserForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(CloudStoreUserForm f) {
		try {
			return cloudStoreUserService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUser:write")
	public DataVO<CloudStoreUser> setUser(@RequestBody CloudStoreUserForm f) {
		DataVO<CloudStoreUser> vo = new DataVO<CloudStoreUser>();
		try{
			cloudStoreUserService.setUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云存储用户指定用户完成");
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
