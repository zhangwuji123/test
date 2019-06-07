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
import com.cloud.operation.core.form.CloudStoreUserStoreForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CloudStoreAuthClient;
import com.cloud.operation.db.entity.business.CloudStoreDirectory;
import com.cloud.operation.db.entity.business.CloudStoreUserStore;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.ICloudStoreUserStoreService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/cloudStoreUserStore")
public class CloudStoreUserStoreAction extends BaseAction<CloudStoreUserStoreForm, CloudStoreUserStore>{
	
	@Resource
	private ICloudStoreUserStoreService cloudStoreUserStoreService;
	
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUserStore:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Cluster.class, name = { "name","alias","uuid"}),
	    @AllowProperty(pojo = User.class, name = { "username","realname","uuid"}),
	    @AllowProperty(pojo = CloudStoreDirectory.class, name = { "name","path","state","uuid"}),
	    @AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"})
	},
	value = {
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	public Page<CloudStoreUserStore> pageList(@RequestBody CloudStoreUserStoreForm f) {
		Page<CloudStoreUserStore> page = new Page<CloudStoreUserStore>();
		try{
			page = cloudStoreUserStoreService.findPageList(f);
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
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Cluster.class, name = { "name","alais"}),
	    @AllowProperty(pojo = User.class, name = { "username","realname","uuid"}),
	    @AllowProperty(pojo = CloudStoreDirectory.class, name = { "name","path","state","uuid"}),
	    @AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"})
	},
	value = {
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	public DataVO<CloudStoreUserStore> list(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try {
			vo.setData(cloudStoreUserStoreService.findListUserStore(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage("系统异常");
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUserStore:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Cluster.class, name = { "name","alais"}),
	    @AllowProperty(pojo = User.class, name = { "username","realname","uuid"}),
	    @AllowProperty(pojo = CloudStoreDirectory.class, name = { "name","path","state","uuid"}),
	    @AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"})
	},
	value = {
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	public DataVO<CloudStoreUserStore> load(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try{
			vo.setT(cloudStoreUserStoreService.findById(f.getUuid()));
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
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreUserStore> save(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				cloudStoreUserStoreService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CLOUDSTOREUSERSTORE_SUCCESS);
			}else{
				cloudStoreUserStoreService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CLOUDSTOREUSERSTORE_SUCCESS);
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
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreUserStore> delete(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try{
			cloudStoreUserStoreService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CLOUDSTOREUSERSTORE_SUCCESS);
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
	public DataVO<CloudStoreUserStore> deletes(CloudStoreUserStoreForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(CloudStoreUserStoreForm f) {
		try {
			return cloudStoreUserStoreService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreAuthClient> auth(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreAuthClient> vo = new DataVO<CloudStoreAuthClient>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				cloudStoreUserStoreService.auth(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_AUTH_SUCCESS);
			}else{
				cloudStoreUserStoreService.modifyAuth(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_AUTH_SUCCESS);
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
	
	@ResponseBody
	@RequestMapping(value = "/cancelAuth", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreAuthClient> cancelAuth(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreAuthClient> vo = new DataVO<CloudStoreAuthClient>();
		try{
			cloudStoreUserStoreService.cancelAuth(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_AUTH_SUCCESS);
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
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreUserStore> setUser(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try{
			cloudStoreUserStoreService.setUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("指定用户完成");
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
	@RequiresPermissions("cloudStoreUserStore:write")
	public DataVO<CloudStoreUserStore> extend(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreUserStore> vo = new DataVO<CloudStoreUserStore>();
		try {
			cloudStoreUserStoreService.extend(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云存储延长使用时间成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/loadAuth", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreUserStore:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceInfo.class, name = { "name","user","displayName","state","uuid"}),
	    @AllowProperty(pojo = User.class, name = { "uuid","username","realname","state"})
	})
	public DataVO<CloudStoreAuthClient> loadAuth(@RequestBody CloudStoreUserStoreForm f) {
		DataVO<CloudStoreAuthClient> vo = new DataVO<CloudStoreAuthClient>();
		try{
			vo.setT(cloudStoreUserStoreService.findAuthById(f.getUuid()));
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
