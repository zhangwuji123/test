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
import com.cloud.operation.core.form.VmSnapshotForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.DataDisk;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.InstanceVolumeInfo;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.business.VmSnapshot;
import com.cloud.operation.service.IVmSnapshotService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

/**
 * 快照控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/snapshot")
public class SnapshotAction extends BaseAction<VmSnapshotForm, VmSnapshot> {

	@Resource
	private IVmSnapshotService vmSnapshotService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("snapshot:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public Page<VmSnapshot> pageList(@RequestBody VmSnapshotForm f) {
		Page<VmSnapshot> page = new Page<VmSnapshot>();
		try {
			page = vmSnapshotService.findPageList(f);
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
	@RequiresPermissions("snapshot:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public DataVO<VmSnapshot> list(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			vo.setData(vmSnapshotService.findList(f));
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
	@RequiresPermissions("snapshot:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = InstanceInfo.class, name = { "name","displayName","state","uuid"}),
		@AllowProperty(pojo = TaskInfo.class, name = { "taskName","state","createdAt"})
	})
	public DataVO<VmSnapshot> load(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			vo.setT(vmSnapshotService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage("系统异常");
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("snapshot:write")
	public DataVO<VmSnapshot> save(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vmSnapshotService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_SNAPSHOT_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				vmSnapshotService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_SNAPSHOT_SUCCESS);
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
	@RequiresPermissions("snapshot:write")
	public DataVO<VmSnapshot> delete(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			vmSnapshotService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SNAPSHOT_SUCCESS);
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
	@RequestMapping(value = "/countByNameAndId", method = RequestMethod.POST)
	public Boolean countByNameAndId(VmSnapshotForm f) {
		try {
			return vmSnapshotService.countByNameAndId(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("snapshot:write")
	public DataVO<VmSnapshot> deletes(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			// TODO Auto-generated method stub
			String[] uuidList = f.getUuids().split(",");
			for (String uuid : uuidList) {
				vmSnapshotService.deleteById(uuid);
			}
			//vmSnapshotService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_SNAPSHOT_SUCCESS);
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
	public Boolean validate(VmSnapshotForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/snapshotAll", method = RequestMethod.POST)
	@RequiresPermissions("snapshot:read")
	public DataVO<VmSnapshot> getSnapshotCountByVmUuid(@RequestBody VmSnapshotForm f) {
		DataVO<VmSnapshot> vo = new DataVO<VmSnapshot>();
		try {
			vo.setData(vmSnapshotService.findAll(f.getVmUuid()));
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
