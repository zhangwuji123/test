package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.DataDiskForm;
import com.cloud.operation.core.form.DiskForm;
import com.cloud.operation.core.form.DiskTypeForm;
import com.cloud.operation.core.form.InstanceVolumeVmSnapshotForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CloudDisk;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.DataDisk;
import com.cloud.operation.db.entity.business.DiskType;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.InstanceVolumeInfo;
import com.cloud.operation.db.entity.business.InstanceVolumeVmSnapshot;
import com.cloud.operation.service.IDataDiskService;
import com.cloud.operation.service.IDiskTypeService;
import com.cloud.operation.service.IInstanceVolumeVmSnapshotService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

/***
 * 系统存储运营平台action
 */
@Controller
@RequestMapping("/dataDisk")
public class DataDiskAction extends BaseAction<DataDiskForm, DataDisk>{
	
	@Resource
	private IDataDiskService dataDiskService;
	
	@Resource
	private IDiskTypeService diskTypeService;
	
	@Resource
	private IInstanceVolumeVmSnapshotService iInstanceVolumeVmSnapshotService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("dataDisk:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name","size","state","uuid","cluster","ptype","htype"}),
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = Cluster.class, name = { "name","alias","state","uuid","hypervisorServerContainer"})
	},
	value = {
		@IgnoreProperty(pojo = DataDisk.class, name = { "diskSnapshots","taskInfos"})
	})
	public Page<DataDisk> pageList(@RequestBody DataDiskForm f) {
		Page<DataDisk> page = new Page<DataDisk>();
		try{
			page = dataDiskService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	public DataVO<DataDisk> list(DataDiskForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping("/load")
	@RequiresPermissions("dataDisk:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name","size","state","uuid","cluster","ptype","htype"}),
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = Cluster.class, name = { "name","alias","state","uuid","hypervisorServerContainer"})
	},
	value = {
		@IgnoreProperty(pojo = DataDisk.class, name = { "diskSnapshots","taskInfos"})
	})
	public DataVO<DataDisk> load(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			vo.setT(dataDiskService.findById(f.getUuid()));
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
	@RequestMapping("/save")
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> save(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		if(f.getName()!=null && f.getName().contains("<")){
			f.setName(f.getName().replaceAll("<","&lt;"));
		}
		try{
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				dataDiskService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("创建云磁盘成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())) {
				dataDiskService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改云磁盘完成");
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
	public DataVO<DataDisk> delete(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			dataDiskService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SYSTEMSTORAGE_SUCCESS);
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
	@RequestMapping("/mnt")
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> mount(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			dataDiskService.mount(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("挂载云磁盘成功");
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
	@RequestMapping("/unmnt")
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> unmount(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			dataDiskService.unmount(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除云磁盘完成");
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
	@RequestMapping("/assignUser")
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> assignUser(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			dataDiskService.assignUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("指定用户成功");
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
	@RequestMapping(value = "/extend", method = RequestMethod.POST)
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> extend(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try {
			dataDiskService.extend(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云磁盘延长使用时间完成");
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
	public DataVO<DataDisk> deletes(DataDiskForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(DataDiskForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/insPageList")
	@RequiresPermissions("dataDisk:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name","size","state","uuid","cluster","ptype","htype"}),
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid","cluster"}),
		@AllowProperty(pojo = Cluster.class, name = { "name","alias","state","uuid","hypervisorServerContainer","ptype","htype"})
	},
	value = {
		@IgnoreProperty(pojo = DataDisk.class, name = { "diskSnapshots","taskInfos"})
	})
	public Page<InstanceInfo> insPageList(@RequestBody DataDiskForm f) {
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try{
			page = dataDiskService.insPageListForMount(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/onlyUnmnt")
	@RequiresPermissions("dataDisk:write")
	public DataVO<DataDisk> onlyUnmnt(@RequestBody DataDiskForm f) {
		DataVO<DataDisk> vo = new DataVO<DataDisk>();
		try{
			dataDiskService.onlyUnmnt(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("开始执行解挂云磁盘");
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
	@RequestMapping(value = "/createDiskSnapshot", method = RequestMethod.POST)
	@RequiresPermissions("dataDisk:write")
	public DataVO<CloudDisk> createDiskSnapshot(@RequestBody DataDiskForm f) {
		DataVO<CloudDisk> vo = new DataVO<CloudDisk>();
		try {
			dataDiskService.createSnap(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("正在创建快照，请稍后");
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
	@RequestMapping(value = "/getDiskType", method = RequestMethod.POST)
	@RequiresPermissions("dataDisk:read")
	public DataVO<DiskType> getDiskType(@RequestBody DiskForm f) {
		DataVO<DiskType> vo = new DataVO<DiskType>();
		try {
			vo.setList(diskTypeService.findList(new DiskTypeForm()));
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
	@RequestMapping("/listInstanceVolumeSnapshot")
	@RequiresPermissions("dataDisk:read")
	public DataVO<InstanceVolumeVmSnapshot> listInstanceVolumeSnapshot(@RequestBody InstanceVolumeVmSnapshotForm f) {
		DataVO<InstanceVolumeVmSnapshot> vo = new DataVO<InstanceVolumeVmSnapshot>();
		try {
			vo.setList(iInstanceVolumeVmSnapshotService.selectByinstanceVolumeInfoUuid(f.getInstanceVolumeInfoUuid()));
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
