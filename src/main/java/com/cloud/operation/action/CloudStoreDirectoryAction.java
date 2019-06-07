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
import com.cloud.operation.core.form.CloudStoreDirectoryForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CloudStoreDirectory;
import com.cloud.operation.service.ICloudStoreDirectoryService;

@Controller
@RequestMapping("/cloudStoreDirectory")
public class CloudStoreDirectoryAction extends BaseAction<CloudStoreDirectoryForm, CloudStoreDirectory>{
	
	@Resource
	private ICloudStoreDirectoryService cloudStoreDirectoryService;
	
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreDirectory:read")
	public Page<CloudStoreDirectory> pageList(@RequestBody CloudStoreDirectoryForm f) {
		Page<CloudStoreDirectory> page = new Page<CloudStoreDirectory>();
		try{
			page = cloudStoreDirectoryService.findPageList(f);
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
	@RequiresPermissions("cloudStoreDirectory:read")
	public DataVO<CloudStoreDirectory> list(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			vo.setList(cloudStoreDirectoryService.findList(f));
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
	@RequestMapping(value = "/easyList", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreDirectory:read")
	public DataVO<CloudStoreDirectory> easyList(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			vo.setList(cloudStoreDirectoryService.findEasyList(f));
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
	@RequestMapping(value = "/parentList", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreDirectory:read")
	public DataVO<CloudStoreDirectory> parentList(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			vo.setList(cloudStoreDirectoryService.findParentList(f));
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
	@RequiresPermissions("cloudStoreDirectory:read")
	public DataVO<CloudStoreDirectory> load(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			vo.setT(cloudStoreDirectoryService.findById(f.getUuid()));
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
	@RequiresPermissions("cloudStoreDirectory:write")
	public DataVO<CloudStoreDirectory> save(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				cloudStoreDirectoryService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CLOUDSTOREDIRECTORY_SUCCESS);
			}else{
				cloudStoreDirectoryService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CLOUDSTOREDIRECTORY_SUCCESS);
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
	@RequiresPermissions("cloudStoreDirectory:write")
	public DataVO<CloudStoreDirectory> delete(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			cloudStoreDirectoryService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CLOUDSTOREDIRECTORY_SUCCESS);
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

	public DataVO<CloudStoreDirectory> deletes(CloudStoreDirectoryForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(CloudStoreDirectoryForm f) {
		try{
			return cloudStoreDirectoryService.validate(f);
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronous", method = RequestMethod.POST)
	@RequiresPermissions("cloudStoreDirectory:write")
	public DataVO<CloudStoreDirectory> synchronous(@RequestBody CloudStoreDirectoryForm f) {
		DataVO<CloudStoreDirectory> vo = new DataVO<CloudStoreDirectory>();
		try{
			cloudStoreDirectoryService.synchronous(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("开始同步目录");
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
