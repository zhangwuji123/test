package com.cloud.operation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.DatacenterForm;
import com.cloud.operation.core.form.MonitorForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.ConstantUtil;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Datacenter;
import com.cloud.operation.db.entity.business.Host;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.Zone;
import com.cloud.operation.service.IDatacenterService;
import com.cloud.operation.service.IMonitorService;
import com.cloud.operation.service.IZoneService;

@Controller
@RequestMapping("/monitor")
public class MonitorAction extends BaseAction<MonitorForm, Host>  {
	
	@Resource
	private IMonitorService monitorService;
	@Resource
	private IZoneService zoneService;
	@Resource
	private IDatacenterService datacenterService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public Page<Host> pageList(@RequestBody MonitorForm f) {
		Page<Host> page = new Page<Host>();
		try {
			page = monitorService.findByPage(f);
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
	@RequestMapping(value = "/loadInstance", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public DataVO<InstanceInfo> loadInstance(@RequestBody MonitorForm f) {
		DataVO<InstanceInfo> vo = new DataVO<InstanceInfo>();
		try {
			 vo.setData(monitorService.findInstanceByHost(f));
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
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public DataVO<Object> findById(@RequestBody MonitorForm f){
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(monitorService.findById(f));
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
	 * 使用率波形图
	 * @param f
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/monitorLineImg", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public DataVO<Object> cpuMonitor(@RequestBody MonitorForm f){
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(monitorService.monitors(f));
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
	 * 使用率波形图
	 * @param f
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/monitorHistory", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public DataVO<Object> monitorHistory(@RequestBody MonitorForm f){
		logger.info("action start time : " + System.currentTimeMillis());
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(monitorService.searchDayMonitor(f));
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
		logger.info("action start end : " + System.currentTimeMillis());
		return vo;
	}
	
	
	/**
	 * 资源管理树
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/initTree", method = RequestMethod.POST)
	@RequiresPermissions("monitor:read")
	public DataVO<Map<String, Object>> initTree() {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String,Object>>();
		try {
			List<Datacenter> datacenterList = datacenterService.findList(new DatacenterForm());
			List<Map<String, Object>> tree = getDatacenterList(datacenterList);
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
	
	/***
	 * 获取数据中心树结构
	 * @param datacenter
	 * @return
	 */
	private List<Map<String,Object>> getDatacenterList(List<Datacenter> datacenters){
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		for (Datacenter datacenter : datacenters) {
			Map<String, Object> map = null;
			map = new HashMap<String, Object>();
			map.put("id", datacenter.getUuid());
			map.put("name", datacenter.getName());
			map.put("open", false);
			map.put("icon", "images/icon-datacenter.png");
			map.put("type", "datacenter");
			map.put("isParent", true);
			if (datacenter.getZones() != null && datacenter.getZones().size() > 0) {
				map.put("children", getZoneChildren(datacenter));
			}
			tree.add(map);
		}
		return tree;
	} 
	
	/***
	 * 构造树结构
	 * @param zoneList
	 * @return
	 */
	private List<Map<String,Object>> getZoneChildren(Datacenter datacenter){
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		for (Zone zone : datacenter.getZones()) {
			Map<String, Object> map = null;
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
		return tree;
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
			map.put("name", cluster.getAlias());
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
			if(host.getState() != ConstantUtil.HostState.TEMP){
				map = new HashMap<String, Object>();
				map.put("id", host.getUuid());
				map.put("name", host.getName());
				map.put("icon", "images/icon-host.png");
				map.put("type", "host");
				map.put("isParent", false);
				list.add(map);	
			}
		}
		return list;
	}
	
	@Override
	public DataVO<Host> list(@RequestBody MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Host> load(@RequestBody MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Host> save(@RequestBody MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Host> delete(@RequestBody MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Host> deletes(MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(MonitorForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
