package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.InstanceForm;
import com.cloud.operation.core.form.InstanceVncForm;
import com.cloud.operation.core.form.OrganizationForm;
import com.cloud.operation.core.form.ResourceProtectForm;
import com.cloud.operation.core.form.TaskInfoForm;
import com.cloud.operation.core.form.ZoneForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.utils.RestUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Business;
import com.cloud.operation.db.entity.business.CDRom;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.entity.business.HypervisorServerContainer;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.InstanceInfoVnc;
import com.cloud.operation.db.entity.business.Network;
import com.cloud.operation.db.entity.business.Organization;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.business.TaskMsgInfo;
import com.cloud.operation.db.entity.business.Vnet;
import com.cloud.operation.db.entity.business.Zone;
import com.cloud.operation.service.IInstanceService;
import com.cloud.operation.service.IProtectService;
import com.cloud.operation.service.IZoneService;
import com.cloud.operation.service.impl.OrganizationService;
import com.cloud.operation.service.impl.TaskInfoService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
/**
 * 实例控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/instance")
public class InstanceAction extends BaseAction<InstanceForm, InstanceInfo> {

	@Resource
	private IInstanceService instanceService;

	@Resource
	private IZoneService zoneService;
	
	@Resource
	private IProtectService protectService;
	
	@Resource
	private TaskInfoService taskInfoService;
	
	@Resource
	private OrganizationService organizationService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Cluster.class, name = { "uuid", "name",
					"state", "alias" }),
			@AllowProperty(pojo = Host.class, name = { "uuid", "name", "state",
					"cluster" }),
			@AllowProperty(pojo = Vnet.class, name = { "uuid", "name", "mac",
					"ip", "network", "state" }),
			@AllowProperty(pojo = Network.class, name = { "uuid", "name",
					"state" }) })
	public Page<InstanceInfo> pageList(@RequestBody InstanceForm f) {
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try {
			page = instanceService.findPageList(f);
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
	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = InstanceInfo.class, name = { "name",
					"displayName", "state", "vnets", "business" }),
			@AllowProperty(pojo = Business.class, name = { "name" }),
			@AllowProperty(pojo = Vnet.class, name = { "ip", "state", "name" }) })
	public Page<InstanceInfo> findPage(@RequestBody InstanceForm f) {
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try {
			page = instanceService.findPage(f);
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
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = InstanceInfo.class, name = { "name",
					"displayName", "state", "vnets", "business" }),
			@AllowProperty(pojo = Business.class, name = { "name" }),
			@AllowProperty(pojo = Vnet.class, name = { "ip", "state", "name" }) })
	public DataVO<InstanceInfo> list(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setList(instanceService.findList(f));
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
	@RequestMapping(value = "/tableList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public Page<InstanceInfo> tableList(@RequestBody InstanceForm f) {
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try {
			page = instanceService.findPageListSimple(f);
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
	@RequestMapping(value = "/listens", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<InstanceInfo> listens(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setList(instanceService.findList(f.getInstanceUuids()));
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
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","state","alias","hypervisorServerContainer" }),
								@AllowProperty(pojo = Host.class, name = { "uuid","name","state" ,"cluster"}),
								@AllowProperty(pojo = HypervisorServerContainer.class, name = {"uuid","alias"}),
								@AllowProperty(pojo = Vnet.class, name = {"uuid","name","mac","ip","network","state"}),
								@AllowProperty(pojo = Network.class, name = {"uuid","name","state"})
					})
	public DataVO<InstanceInfo> load(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setT(instanceService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	@IgnoreProperties(allow = { @AllowProperty(pojo = InstanceInfo.class, name = { "uuid" })
})
	public DataVO<InstanceInfo> save(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				if(f.getOpenBatchCreate() != null && f.getOpenBatchCreate() == 1){//批量创建
					instanceService.batchInsert(f);
				}else{//单独创建
					vo.setT(instanceService.insert2(f));
				}
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("云主机正在创建中，请稍后");
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				instanceService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_INSTANCE_SUCCESS);
			} else if (RequestAction.UPDATE_CPU_MEM_DISK.getValue().equals(
					f.getAction())) {
				instanceService.modifyVmConfig(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("云主机配置正在变更中，请稍后");
			}
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> delete(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.deleteById(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在删除中，请稍后");
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
	@RequestMapping(value = "/protectInstance", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> protectInstance(@RequestBody ResourceProtectForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			protectService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("锁定云主机成功");
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
	@RequestMapping(value = "/unProtectInstance", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> unProtectInstance(@RequestBody ResourceProtectForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			protectService.updateFromForm(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("解锁云主机成功");
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
	@RequestMapping(value = "/updateBusiness", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> updateBusiness(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.updateBusiness(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机业务类型正在修改");
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
	@RequestMapping(value = "/boot", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> boot(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.bootById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在开机中，请稍后");
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
	@RequestMapping(value = "/shutdown", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> shutdown(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.shutdownById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在关机中，请稍后");
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
	@RequestMapping(value = "/forcedShutdown", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> forcedShutdown(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.forcedShutdownById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在关机中，请稍后");
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
	@RequestMapping(value = "/restart", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> restart(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.restartById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在重启中，请稍后");
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
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> pause(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.pauseById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在暂停中，请稍后");
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
	@RequestMapping(value = "/recover", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> recover(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.unpauseById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在恢复中，请稍后");
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

	/**
	 * 资源管理树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/initTree", method = RequestMethod.POST)
	public DataVO<Map<String, Object>> initTree() {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();
		try {
			List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
			List<Zone> zoneList = zoneService.findList(new ZoneForm());
			Map<String, Object> map = null;
			for (Zone zone : zoneList) {
				map = new HashMap<String, Object>();
				map.put("id", zone.getUuid());
				map.put("name", zone.getName());
				map.put("open", false);
				map.put("icon", "images/icon-zone.png");
				map.put("type", "zone");
				map.put("isParent", true);
				if (zone.getPools() != null && zone.getPools().size() > 0) {
					map.put("children", getPoolChildren(zone));
				}
				tree.add(map);
			}
			vo.setList(tree);
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

	private List<Map<String, Object>> getPoolChildren(Zone zone) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (Pool pool : zone.getPools()) {
			map = new HashMap<String, Object>();
			map.put("id", pool.getUuid());
			map.put("name", pool.getName());
			map.put("icon", "images/icon-pool.png");
			map.put("type", "pool");
			map.put("isParent", true);
			if (pool.getClusters() != null && pool.getClusters().size() > 0) {
				map.put("children", getClusterChildren(pool));
			}
			list.add(map);
		}
		return list;
	}

	private List<Map<String, Object>> getClusterChildren(Pool pool) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (Cluster cluster : pool.getClusters()) {
			map = new HashMap<String, Object>();
			map.put("id", cluster.getUuid());
			map.put("name", cluster.getName());
			map.put("icon", "images/icon-cluster.png");
			map.put("type", "cluster");
			map.put("isParent", true);
			if (cluster.getHosts() != null && cluster.getHosts().size() > 0) {
				map.put("children", getHostChildren(cluster));
			}
			list.add(map);
		}
		return list;
	}

	private List<Map<String, Object>> getHostChildren(Cluster cluster) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (Host host : cluster.getHosts()) {
			map = new HashMap<String, Object>();
			map.put("id", host.getUuid());
			map.put("name", host.getName());
			map.put("icon", "images/icon-host.png");
			map.put("type", "host");
			map.put("isParent", true);
			if (host.getInstanceInfos() != null && host.getInstanceInfos().size() > 0) {
				map.put("children", getInstanceChildren(host));
			}
			list.add(map);
		}
		return list;
	}

	private List<Map<String, Object>> getInstanceChildren(Host host) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (InstanceInfo instance : host.getInstanceInfos()) {
			map = new HashMap<String, Object>();
			map.put("id", instance.getUuid());
			map.put("name", instance.getName());
			map.put("icon", "images/icon-vm.png");
			map.put("type", "instance");
			map.put("isParent", false);
			list.add(map);
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/migrate", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> migrate(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.migrate(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在迁移中，请稍后");
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
	@RequestMapping(value = "/snapshotRecover", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> snapshotRecover(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.snapshotRecover(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在从快照恢复中，请稍后");
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
	@RequestMapping(value = "/sendVnc", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<InstanceInfo> sendVnc(String tenantId, String serverId,
			String tokenId, HttpServletRequest request, HttpServletResponse response) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			StringBuffer url = new StringBuffer();
			url.append("http://160.17.5.104:8774/v2/");
			url.append(tenantId);
			url.append("/servers/");
			url.append(serverId);
			url.append("/action");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", "novnc");
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("os-getVNCConsole", paramMap);
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("X-Auth-Token", tokenId);
			Map<String, String> resMap = new HashMap<String, String>();
			resMap.put("Access-Control-Allow-Origin", "*");
			resMap.put("Access-Control-Allow-Headers", "X-Requested-With");
			vo.setMessage(RestUtil.sendPost(url.toString(), paramsMap, reqMap, resMap));
			vo.setState(ResponseState.SUCCESS.getValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	/**
	 * vmwareVnc
	 */
	@RequestMapping(value = "/vncVmware", method = RequestMethod.GET)
	public String vncVmware(@RequestBody String uuid, HttpServletRequest request) {
		request.setAttribute("uuid", uuid);
		return "resource/vnc";
	}

	@ResponseBody
	@RequestMapping(value = "/existByName", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<Boolean> existByName(@RequestBody InstanceForm f) {
		DataVO<Boolean> vo = new DataVO<Boolean>();
		try {
			vo.setT(instanceService.existThisName(f.getName()));
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

	/**
	 * 根据虚机UUID获取openstackVnc
	 * 
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVnc", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","state","alias","hypervisorServerContainer" }),
			@AllowProperty(pojo = Host.class, name = { "uuid","name","state" ,"cluster"}),
			@AllowProperty(pojo = HypervisorServerContainer.class, name = {"uuid","alias"}),
			@AllowProperty(pojo = Vnet.class, name = {"uuid","name","mac","ip","network","state"}),
			@AllowProperty(pojo = Network.class, name = {"uuid","name","state"})
	})
	public DataVO<InstanceInfoVnc> getVnc(@RequestBody InstanceForm f) {
		DataVO<InstanceInfoVnc> vo = new DataVO<InstanceInfoVnc>();
		try {
			vo.setT(instanceService.getConsoleUrlById(f.getUuid()));
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

	/**
	 * 根据ID获取ＶＮＣ的信息
	 * 
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkVnc", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","state","alias","hypervisorServerContainer" }),
			@AllowProperty(pojo = Host.class, name = { "uuid","name","state" ,"cluster"}),
			@AllowProperty(pojo = HypervisorServerContainer.class, name = {"uuid","alias"}),
			@AllowProperty(pojo = Vnet.class, name = {"uuid","name","mac","ip","network","state"}),
			@AllowProperty(pojo = Network.class, name = {"uuid","name","state"})
	})
	public DataVO<InstanceInfoVnc> checkVnc(@RequestBody InstanceVncForm f) {
		DataVO<InstanceInfoVnc> vo = new DataVO<InstanceInfoVnc>();
		try {
			vo.setT(instanceService.checkVnc(f.getUuid()));
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

	/**
	 * 根据UUID获取vmwareVnc信息
	 * 
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVmwareVnc", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<Object> getVmwareVnc(@RequestBody String uuid) {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(instanceService.getVncOfVMW(uuid));
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

	@RequestMapping(value = "/forwardVnc", method = RequestMethod.GET)
	public String forwardVnc(String uuid, HttpServletRequest request) {
		request.setAttribute("uuid", uuid);
		return "resource/forwardVnc";
	}

	@Override
	public DataVO<InstanceInfo> deletes(InstanceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(InstanceForm f) {
		try {
			return instanceService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/listWithUser", method = RequestMethod.POST)
	public DataVO<InstanceInfo> findListWithUser(@RequestBody InstanceForm f){
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setList(instanceService.findListWithUser(f));
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
	@RequestMapping(value = "/toTemplate", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> transferTemplate(@RequestBody InstanceForm f){
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.transferTemplate(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在生成模板，请稍后");
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
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> setUser(@RequestBody InstanceForm f){
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.setUser(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("指定用户成功");
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
	@RequestMapping(value = "/setState", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> setState(@RequestBody InstanceForm f){
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.setState(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("修改状态完成");
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
	@RequestMapping(value = "/extend", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> extend(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.extend(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机延长使用时间成功");
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
	@RequestMapping(value = "/createNic", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> createNic(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.createNic(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在添加网卡，请稍后");
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
	@RequestMapping(value = "/deleteNic", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> deleteNic(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.deleteNic(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在删除网卡，请稍后");
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
	@RequestMapping(value = "/createVolume", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> createVolume(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.createVolume(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在添加磁盘，请稍后");
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
	@RequestMapping(value = "/deleteVolume", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> deleteVolume(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.deleteVolume(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("云主机正在删除磁盘，请稍后");
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
	@RequestMapping(value = "/findInstanceList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<InstanceInfo> findInstanceList(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setData(instanceService.findInstanceList(f));
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
	@RequestMapping(value = "/searchPageList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(allow = { 
		@AllowProperty(pojo = InstanceInfo.class, name = {"uuid","displayName","name","state","foreignRef"})
	})
	public Page<InstanceInfo> searchPageList(@RequestBody InstanceForm f) {
		Page<InstanceInfo> page = new Page<InstanceInfo>();
		try {
			page = instanceService.searchPageList(f);
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
	@RequestMapping(value = "/queryBuildingSnapshotByID", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<Boolean> queryBuildingSnapshotByID(@RequestBody InstanceForm f) {
		DataVO<Boolean> vo = new DataVO<Boolean>();
		try {
			vo.setT(instanceService.queryBuildingSnapshotByID(f.getUuid()));
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
	@RequestMapping(value = "/queryByDisplayName", method = RequestMethod.POST)
	public Boolean queryByDisplayName(@RequestBody InstanceForm f) {
		try {
			return instanceService.queryByDisplayName(f.getDisplayName());
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/isoList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<CDRom> isoList(@RequestBody InstanceForm f) {
		DataVO<CDRom> vo = new DataVO<CDRom>();
		try {
			vo.setData(instanceService.findInstanceCDRoms(f));
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
	@RequestMapping(value = "/isoMnt", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> isoMnt(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.mntIso(f);
			vo.setMessage("镜像挂载中，请稍后");
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
	@RequestMapping(value = "/removeIso", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<InstanceInfo> removeIso(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			instanceService.removeIso(f);
			vo.setMessage("镜像移除中，请稍后");
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
	@RequestMapping(value = "/exports", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exports(InstanceForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = instanceService.exports(f, os);
			headers.setContentDispositionFormData("attachment", new String(
					filename.getBytes(), "iso-8859-1"));
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findESInstanceList", method = RequestMethod.POST)
	public DataVO<InstanceInfo> findElasticScalingInstanceList(@RequestBody InstanceForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			vo.setList(instanceService.findESInstanceList(f));
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
	@RequestMapping(value = "/taskRepeatSending", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(
			allow = {
				@AllowProperty(pojo = TaskInfo.class, name = {"uuid","taskName",
					"resourceName","resourceType","state"})
			})
	public Page<TaskInfo> taskRepeatSending(@RequestBody TaskInfoForm f){
		Page<TaskInfo> page = new Page<TaskInfo>();
		try{
			page = instanceService.findTaskListByInstanceId(f);
			page.setStart(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			page.setStart(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			page.setStart(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}
		return page;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendTask", method = RequestMethod.POST)
	@RequiresPermissions("instance:write")
	public DataVO<TaskInfo> sendTask(@RequestBody TaskInfoForm f){
		DataVO<TaskInfo> vo = new DataVO<TaskInfo>();
		try{
			TaskMsgInfo TaskMsgInfo = instanceService.findTaskMsgInfoUuid(f.getUuid());
			instanceService.send(TaskMsgInfo.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("消息正在发送中！");
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/instanceInfoList", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	@IgnoreProperties(
			allow = {
				@AllowProperty(pojo = InstanceInfo.class, name = {"uuid","name","state","vnets"}),
				@AllowProperty(pojo = Vnet.class, name = {"ip"})
			})
	public DataVO<InstanceInfo> instanceInfoList(@RequestBody InstanceForm f){
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try{
			vo.setList(instanceService.findInstanceListByUuids(f));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadOrganationSelect", method = RequestMethod.POST)
	@RequiresPermissions("instance:read")
	public DataVO<Organization> loadOrganationSelect(@RequestBody OrganizationForm f){
		DataVO<Organization> vo = new DataVO<Organization>();
		try{
			vo.setList(organizationService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
}

