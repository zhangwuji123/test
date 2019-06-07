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
import com.cloud.operation.core.form.DiskSnapshotForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.DataDisk;
import com.cloud.operation.db.entity.business.DiskSnapshot;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.InstanceVolumeInfo;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.service.IDiskSnapshotService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

@Controller
@RequestMapping("/diskSnapshot")
public class DiskSnapshotAction  extends BaseAction<DiskSnapshotForm, DiskSnapshot>{
	
	@Resource
	private IDiskSnapshotService diskSnapshotService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name","size","state","uuid","cluster","ptype","htype"}),
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = Cluster.class, name = { "name","alias","state","uuid","hypervisorServerContainer"}),
		@AllowProperty(pojo = DataDisk.class, name = { "name","state","uuid","instanceInfo"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public Page<DiskSnapshot> pageList(@RequestBody DiskSnapshotForm f) {
		Page<DiskSnapshot> page = new Page<DiskSnapshot>();
		try {
			page = diskSnapshotService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}
		return page;
	}

	@Override
	public DataVO<DiskSnapshot> list(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name","size","state","uuid","cluster","ptype","htype"}),
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = Cluster.class, name = { "name","alias","state","uuid","hypervisorServerContainer"}),
		@AllowProperty(pojo = DataDisk.class, name = { "name","state","uuid","instanceInfo"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public DataVO<DiskSnapshot> load(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		try {
			vo.setT(diskSnapshotService.findById(f.getUuid()));
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:write")
	public DataVO<DiskSnapshot> save(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				diskSnapshotService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_DISKSNAPSHOT_SUCCESS);
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:write")
	public DataVO<DiskSnapshot> delete(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		try {
			diskSnapshotService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_DISKSNAPSHOT_SUCCESS);
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
	@RequestMapping(value = "/createDisk", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:write")
	public DataVO<DiskSnapshot> createDisk(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		try {
			diskSnapshotService.createFromSnap(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("通过快照创建云磁盘成功");
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("diskSnapshot:write")
	public DataVO<DiskSnapshot> deletes(@RequestBody DiskSnapshotForm f) {
		DataVO<DiskSnapshot> vo = new DataVO<DiskSnapshot>();
		try {
			String[] uuidList = f.getUuids().split(",");
			for(String uuid : uuidList){
				diskSnapshotService.deleteById(uuid);
			}
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_DISKSNAPSHOT_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(DiskSnapshotForm f) {
		try {
			return diskSnapshotService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
}
