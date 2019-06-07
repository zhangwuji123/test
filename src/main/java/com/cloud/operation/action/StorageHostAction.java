package com.cloud.operation.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.controller.BaseController;
import com.cloud.operation.core.controller.IController.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.StorageHostForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.entity.business.Storage;
import com.cloud.operation.db.entity.business.StorageHostRelation;
import com.cloud.operation.service.IStorageHostRelationService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/storageHost")
public class StorageHostAction extends BaseController<StorageHostForm, StorageHostRelation> {

	@Resource
	private IStorageHostRelationService storageHostRelationService;
	
	@Override
	public Page<StorageHostRelation> pageList(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<StorageHostRelation> list(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<StorageHostRelation> load(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<StorageHostRelation> save(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<StorageHostRelation> delete(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<StorageHostRelation> deletes(StorageHostForm f,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/hostPageList", method = RequestMethod.POST)
	public Page<Host> hostPageList(StorageHostForm f){
		Page<Host> page = new Page<Host>();
		try {
			page = storageHostRelationService.findPageListByStorageUuid(f);
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
	@RequestMapping(value = "/storageByHost", method = RequestMethod.POST)
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","htype","ptype","alias","state"})
	})
	public DataVO<Storage> findStorageByHostUuid(@RequestBody StorageHostForm f){
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			vo.setList(storageHostRelationService.findByHostUuid(f.getHostUuid()));
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
	
	@ResponseBody
	@RequestMapping(value = "/easystorageByHost", method = RequestMethod.POST)
	public DataVO<Storage> easyFindStorageByHostUuid(@RequestBody StorageHostForm f){
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			vo.setList(storageHostRelationService.easyFindByHostUuid(f.getHostUuid()));
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



}
