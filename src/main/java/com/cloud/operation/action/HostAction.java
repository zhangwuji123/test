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
import com.cloud.operation.core.form.HostForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.vo.CloudStatistics;
import com.cloud.operation.service.IClusterService;
import com.cloud.operation.service.IHostService;
import com.cloud.operation.service.IStatisticService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

/**
 * 主机控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/host")
public class HostAction extends BaseAction<HostForm, Host> {

	@Resource
	private IHostService hostService;

	@Resource
	private IStatisticService statisticService;

	@Resource
	private IClusterService clusterService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "name","htype","ptype","alias","state","uuid"})
		},
	value = { @IgnoreProperty(pojo = Host.class, name = { "datacenter","pool","storageHostRelation","zone","instanceInfos"})
	})
	public Page<Host> pageList(@RequestBody HostForm f) {
		Page<Host> page = new Page<Host>();
		try {
			page = hostService.findPageList(f);
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
	@RequiresPermissions("host:read")
	@IgnoreProperties(value = { @IgnoreProperty(pojo = Host.class, name = { "datacenter","pool","cluster","storageHostRelation","zone","instanceInfos"})
	})
	public DataVO<Host> list(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setList(hostService.findList(f));
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
	@RequestMapping(value = "/forVdcList", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "name","htype","ptype","alias","state","uuid"})
		},
	value = { @IgnoreProperty(pojo = Host.class, name = { "datacenter","pool","storageHostRelation","zone","instanceInfos"})
	})
	public DataVO<Host> forVdcList(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setList(hostService.forVdcList(f));
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
	@RequestMapping(value = "/easyList", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public DataVO<Host> easyList(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setList(hostService.findListByCluster(f));
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
	@RequiresPermissions("host:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "name","htype","ptype","alias","state","uuid"})
		},
	value = { @IgnoreProperty(pojo = Host.class, name = { "datacenter","pool","zone","instanceInfos"})
	})
	public DataVO<Host> load(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setT(hostService.findById(f.getUuid()));
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
	@RequestMapping(value = "/loadEasyInfo", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public DataVO<Host> loadEasyInfo(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setT(hostService.findEasyInfoById(f.getUuid()));
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
	@RequiresPermissions("host:write")
	public DataVO<Host> save(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				hostService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_HOST_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				hostService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_HOST_SUCCESS);
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
	@RequiresPermissions("host:write")
	public DataVO<Host> delete(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			hostService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_HOST_SUCCESS);
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
	@RequestMapping(value = "/findTop", method = RequestMethod.POST)
	public DataVO<CloudStatistics> findTop() {
		DataVO<CloudStatistics> vo = new DataVO<CloudStatistics>();
		try {
			vo.setData(statisticService.findHostLimit5());
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
	@RequestMapping(value = "/findHostNoCluster", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public DataVO<Host> findHostNoCluster(@RequestBody HostForm f){
		DataVO<Host> vo = new DataVO<Host>();
		try {
			// 查询集群的资源平台类型与虚拟化类型
			Cluster cluster = clusterService.findById(f.getClusterUuid());
			f.setPtype(cluster.getPtype());
			f.setHtype(cluster.getHtype());
			// 根据资源平台类型与虚拟化类型查询所有集群未分配的主机
			vo.setList(hostService.findHostNoCluster(f));
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
	@RequestMapping(value = "/findHostByCluster", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public DataVO<Host> findHostByCluster(@RequestBody HostForm f){
		DataVO<Host> vo = new DataVO<Host>();
		try {
			vo.setList(clusterService.findById(f.getClusterUuid()).getHosts());
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
	@RequestMapping(value = "/listNoCluster", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public DataVO<Host> listNoCluster(@RequestBody HostForm f) {
		DataVO<Host> vo = new DataVO<Host>();
		try {
			// 查询集群的资源平台类型与虚拟化类型
			Cluster cluster = clusterService.findById(f.getClusterUuid());
			f.setPtype(cluster.getPtype());
			f.setHtype(cluster.getHtype());
			// 根据资源平台类型与虚拟化类型查询所有集群未分配的主机
			vo.setList(hostService.findHostNoCluster(f));
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
	@RequestMapping(value = "/findHosts", method = RequestMethod.POST)
	@RequiresPermissions("host:read")
	public Page<Host> findHosts(@RequestBody HostForm f) {
		Page<Host> page = new Page<Host>();
		try {
			page = hostService.findHostPageByInstanceUuid(f);
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
	public DataVO<Host> deletes(HostForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(HostForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
