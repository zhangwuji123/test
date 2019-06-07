package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ClusterForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.DataDisk;
import com.cloud.operation.db.entity.business.Datacenter;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.entity.business.HypervisorServerContainer;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.business.Zone;
import com.cloud.operation.db.vo.CloudStatistics;
import com.cloud.operation.service.IClusterService;
import com.cloud.operation.service.IStatisticService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

/**
 * 集群控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/cluster")
public class ClusterAction extends BaseAction<ClusterForm, Cluster> {

	@Resource
	private IClusterService clusterService;

	@Resource
	private IStatisticService statisticService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Datacenter.class, name = { "name"}),
		@AllowProperty(pojo = Zone.class, name = { "name"}),
		@AllowProperty(pojo = Pool.class, name = { "name"})
	},
	value = {
		@IgnoreProperty(pojo = DataDisk.class, name = { "diskSnapshots","taskInfos"}),
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	public Page<Cluster> pageList(@RequestBody ClusterForm f) {
		Page<Cluster> page = new Page<Cluster>();
		try {
			page = clusterService.findPageList(f);
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
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Datacenter.class, name = { "name"}),
		@AllowProperty(pojo = Zone.class, name = { "name"}),
		@AllowProperty(pojo = Pool.class, name = { "name"})
	},
	value = {
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> list(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	
	

	@ResponseBody
	@RequestMapping(value = "/forVpcList", method = RequestMethod.POST)
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = Datacenter.class, name = { "name"}),
		@AllowProperty(pojo = Zone.class, name = { "name"}),
		@AllowProperty(pojo = Pool.class, name = { "name"})
	},
	value = {
		@IgnoreProperty(pojo = TaskInfo.class, name = { "taskMsgInfos","user"})
	})
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> forVpcList(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.forVpcList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	
	
	@ResponseBody
	@RequestMapping(value = "/easyList", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> easyList(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.findEasyList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "uuid", "name",
					"htype", "ptype", "alias", "state", "location", "autoSync",
					"performanceDataSync", "hypervisorServerContainer" }),
			@AllowProperty(pojo = HypervisorServerContainer.class, name = { "az" }) })
	public DataVO<Cluster> load(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setT(clusterService.findById(f.getUuid()));
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
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> save(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				clusterService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CLUSTER_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				clusterService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CLUSTER_SUCCESS);
			}
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> delete(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			clusterService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CLUSTER_SUCCESS);
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

	@ResponseBody
	@RequestMapping(value = "/findTop", method = RequestMethod.POST)
	public DataVO<CloudStatistics> findTop() {
		DataVO<CloudStatistics> vo = new DataVO<CloudStatistics>();
		try {
			vo.setData(statisticService.findClusterLimit5());
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
	@RequestMapping(value = "/hosts", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Host> hosts(@RequestBody ClusterForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setList(clusterService.findById(f.getUuid()).getHosts());
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
	
	@ResponseBody
	@RequestMapping(value = "/alloc", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Host> alloc(@RequestBody ClusterForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			clusterService.allocHosts(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("主机正在分配中");
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


	@ResponseBody
	@RequestMapping(value = "/listen", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> listen(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.findByUuids(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> deletes(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			for (String uuid : f.getUuids().split(",")) {
				clusterService.deleteById(uuid);
			}
			//clusterService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CLUSTER_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(ClusterForm f) {
		try {
			return clusterService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/storeCluster", method = RequestMethod.POST)
	public DataVO<Cluster> findListForStor(@RequestBody ClusterForm f){
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.getClustersForStor(f));
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
	
	@ResponseBody
	@RequestMapping(value = "/findClustersByHyper", method = RequestMethod.POST)
	public DataVO<Cluster> findClustersByHyper(@RequestBody ClusterForm f){
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.findClustersByHyper(f));
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
	
	@ResponseBody
	@RequestMapping(value = "/findClusterList", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> findClusterList(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setData(clusterService.findClusterList(f));
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
	@RequestMapping(value = "/listClusters", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	public DataVO<Cluster> listClusters(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setData(clusterService.findListClusters(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	
	@ResponseBody
	@RequestMapping(value = "/autoSync", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> autoSync(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			clusterService.autoSync(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("正在" + (f.getAutoSync() == 0 ? "关闭":"开启") + "自动同步，请稍后");
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
	
	
	@ResponseBody
	@RequestMapping(value = "/sync", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> sync(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			clusterService.sync(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("同步开始执行，请稍后");
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
	
	@ResponseBody
	@RequestMapping(value = "/syncMonitorData", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:write","clusterCitrix:write","clusterPhysicalServer:write","clusterCloudStorage:write","clusterMiniServer:write"})
	public DataVO<Cluster> syncMonitorData(@RequestBody ClusterForm f){
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			clusterService.syncMonitorData(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("正在" + (f.getAutoSync() == 0 ? "关闭":"开启") + "性能自动同步，请稍后");
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
	
	@ResponseBody
	@RequestMapping(value = "/forVdc", method = RequestMethod.POST)
	@RequiresPermissions(logical=Logical.OR,value={"clusterInstance:read","clusterCitrix:read","clusterPhysicalServer:read","clusterCloudStorage:read","clusterMiniServer:read"})
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "uuid", "name",
					"htype", "ptype", "alias", "state" }) })
	public DataVO<Cluster> forVdc(@RequestBody ClusterForm f) {
		DataVO<Cluster> vo = new DataVO<Cluster>();
		try {
			vo.setList(clusterService.forVdc(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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
	

}
