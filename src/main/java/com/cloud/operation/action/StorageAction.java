package com.cloud.operation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.controller.BaseController;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.StorageForm;
import com.cloud.operation.core.form.StorageHostForm;
import com.cloud.operation.core.form.StorageInstanceForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.ConstantUtil;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.Storage;
import com.cloud.operation.service.IStorageHostRelationService;
import com.cloud.operation.service.IStorageInstanceRelationService;
import com.cloud.operation.service.IStorageService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/storage")
public class StorageAction extends BaseController<StorageForm, Storage> {

	@Resource
	private IStorageService storageService;
	
	@Resource
	private IStorageHostRelationService storageHostRelationService;
	
	@Resource
	private IStorageInstanceRelationService storageInstanceRelationService;

	@Override
	public Page<Storage> pageList(StorageForm f, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public DataVO<Storage> list(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			vo.setList(storageService.findList(f));
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
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public DataVO<Storage> load(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			vo.setT(storageService.findById(f.getUuid()));
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
	public DataVO<Storage> save(StorageForm f, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Storage> delete(StorageForm f, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Storage> deletes(StorageForm f, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/initTree", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	public DataVO<Map<String, Object>> initTree(@RequestBody StorageForm f) {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();
		try {
			List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
			List<Storage> storageList = storageService.findList(f);
			Map<String, Object> temp = new HashMap<String, Object>();
			for (Storage storage : storageList) {
				if(storage.getStype() != ConstantUtil.StorageState.LOCAL.getValue() && storage.getStype() != ConstantUtil.StorageState.SHARE.getValue()){
					continue;
				}
				Map<String, Object> map = null;
				map = new HashMap<String, Object>();
				Cluster cluster = storage.getCluster();
				if(cluster != null ){
					if(temp.get(cluster.getUuid()) == null){
						Map<String, Object> tmp = new HashMap<String, Object>();
						tmp.put("id", cluster.getUuid());
						tmp.put("name", cluster.getAlias());
						tmp.put("title", cluster.getAlias());
						tmp.put("icon", "images/icon-zone.png");
						tmp.put("isParent", true);
						tmp.put("open", false);
						temp.put(cluster.getUuid(), tmp);
						Map<String, Object> share = new HashMap<String, Object>();
						share.put("id", cluster.getUuid() + "_Share");
						share.put("name", "Share");
						share.put("title", "Share");
						share.put("isParent", true);
						share.put("open", false);
						share.put("parentId", cluster.getUuid());
						share.put("icon", "images/icon-vm.png");
						temp.put(cluster.getUuid() + "_Share", share);
						
						Map<String, Object> local = new HashMap<String, Object>();
						local.put("id", cluster.getUuid() + "_Local");
						local.put("name", "Local");
						local.put("title", "Local");
						local.put("isParent", true);
						local.put("open", false);
						local.put("parentId", cluster.getUuid());
						local.put("icon", "images/icon-host.png");
						temp.put(cluster.getUuid() + "_Local", local);
					}
					if(storage.getStype() == ConstantUtil.StorageState.SHARE.getValue()){
						map.put("parentId", cluster.getUuid() + "_Share");
					}else if(storage.getStype() == ConstantUtil.StorageState.LOCAL.getValue()){
						map.put("parentId", cluster.getUuid() + "_Local");
					}
				}
				map.put("id", storage.getUuid());
				if(storage.getStype() == ConstantUtil.StorageState.SHARE.getValue()){
					map.put("name", storage.getName());
					map.put("title", storage.getName());
				}else if(storage.getStype() == ConstantUtil.StorageState.LOCAL.getValue()){
					if(storage.getStorageHostRelations().size() > 0){
						map.put("name", storage.getStorageHostRelations().get(0).getHost().getIp()+"("+storage.getName()+")");
						map.put("title", storage.getStorageHostRelations().get(0).getHost().getIp()+"("+storage.getName()+")");
					}else{
						map.put("name", storage.getName());
						map.put("title", storage.getName());
					}
				}
				map.put("icon", "images/icon-cluster.png");
				map.put("isParent", false);
				tree.add(map);
			}
			//把cluster装到list中
			for(String key : temp.keySet()){
				tree.add((HashMap<String, Object>)temp.get(key));
			}
			vo.setList(tree);
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/instances", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"}),
		@AllowProperty(pojo = Host.class, name = {"uuid","name","htype","ptype"})
	},
	value = {
		@IgnoreProperty(pojo = InstanceInfo.class, name={"vnets","instanceVolumes","instanceInfoDetail"})
	})
	public Page<InstanceInfo> instances(@RequestBody StorageInstanceForm f){
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try {
			page = storageInstanceRelationService.findPageByStorageUuid(f);
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
	@RequestMapping(value = "/hosts", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Host.class, name = {"uuid","name","htype","ptype","cluster","ip","state"}),
		@AllowProperty(pojo = Cluster.class, name = {"uuid","name","alias"})
	})
	public Page<Host> hosts(@RequestBody StorageHostForm f){
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
	@RequestMapping(value = "/findStroageById", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	public DataVO<Storage> findStroageById(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			Storage tmp = storageService.findById(f.getUuid());
			Storage storage = new Storage();
			if (tmp!=null) {
				storage.setUuid(tmp.getUuid());
				storage.setStype(tmp.getStype());
				storage.setState(tmp.getState());
				storage.setPtype(tmp.getPtype());
				storage.setName(tmp.getName());
				storage.setIqn(tmp.getIqn());
				storage.setHtype(tmp.getHtype());
				storage.setForeignRef(tmp.getForeignRef());
				storage.setFileSystem(tmp.getFileSystem());
				storage.setDiskUsed(tmp.getDiskUsed());
				storage.setDiskTotal(tmp.getDiskTotal());
				storage.setDiskAlloc(tmp.getDiskAlloc());
			}
			vo.setT(storage);
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
	@RequestMapping(value = "/findShareStroages", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	public DataVO<Storage> findBussinessStroages(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			List<Storage> data = storageService.findShareStroages();
			vo.setData(data);
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
	@RequestMapping(value = "/findHyperVShareStorages", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Storage.class, name = {"uuid","name"})
	})
	public DataVO<Storage> findHyperVShareStorages(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			List<Storage> data = storageService.findHyperVShareStorages();
			vo.setData(data);
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
	@RequestMapping(value = "/findSurplusStorages", method = RequestMethod.POST)
	@RequiresPermissions("storage:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Storage.class, name = {"uuid","diskTotal","diskUsed"})
	})
	public DataVO<Storage> findSurplusStorages(@RequestBody StorageForm f, HttpServletRequest request) {
		DataVO<Storage> vo = new DataVO<Storage>();
		try {
			vo.setT(storageService.findSurplusStorages(f.getUuid()));
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
