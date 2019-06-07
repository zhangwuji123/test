package com.cloud.operation.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.constants.ClusterTypeConstant;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.IndexForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.ICitrixDesktopMachineService;
import com.cloud.operation.service.IClusterService;
import com.cloud.operation.service.IDatacenterService;
import com.cloud.operation.service.IHostService;
import com.cloud.operation.service.IIndexService;
import com.cloud.operation.service.IInstanceService;
import com.cloud.operation.service.ILparInfoService;
import com.cloud.operation.service.IMiniMachineInfoService;
import com.cloud.operation.service.IPhysicalMachineService;
import com.cloud.operation.service.IPoolService;
import com.cloud.operation.service.IZoneService;
import com.cloud.operation.service.shiro.ShiroDbRealm;

/**
 * 页面转向控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/")
public class IndexAction extends BaseAction<Object, Object> {

	@Resource
	private IIndexService indexService;
	@Resource
	private IDatacenterService datacenterService;
	@Resource
	private IZoneService zoneService;
	@Resource
	private IPoolService poolService;
	@Resource
	private IClusterService clusterService;
	@Resource
	private ILparInfoService lparInfoService;
	@Resource
	private IHostService hostService;
	@Resource
	private IPhysicalMachineService physicalMachineService;
	@Resource
	private IMiniMachineInfoService miniMachineInfoService;
	@Resource
	private IInstanceService instanceService;
	@Autowired
	private ShiroDbRealm shiroRealm;
	@Autowired
	private ICitrixDesktopMachineService citrixDesktopMachineService;

	/**
	 * 登录
	 * 
	 * @param message
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String message, HttpServletRequest request) {
		if (message != null) {
			request.setAttribute("successMessage", message);
		}
		if (shiroRealm.getCurrentUser() != null) {
			return "redirect:index";
		}
		return "index/login";
	}

	/**
	 * 登出
	 * 
	 * @param message
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public DataVO<String> logout() {
		DataVO<String> vo = new DataVO<String>();
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		vo.setState(ResponseState.SUCCESS.getValue());
		vo.setMessage("登出成功。");
		return vo;
	}

	/**
	 * 返回无权限界面处理
	 * 
	 * @param message
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Deprecated
	@RequestMapping(value = "/noPrivilege", method = RequestMethod.GET)
	public String noPrivilege(String message, HttpServletRequest request)
			throws UnsupportedEncodingException {
//		shiroRealm
//				.clearCachedAuthorizationInfo(shiroRealm.getCurrentUserName());
		if (message != null) {
			request.setAttribute("message",
					java.net.URLDecoder.decode(message, "UTF-8"));
		}
		return "index/noPrivilege";
	}

	/**
	 * 返回登录界面处理
	 * 
	 * @param message
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Deprecated
	@RequestMapping(value = "/loginTimeOut", method = RequestMethod.GET)
	public String loginTimeOut(String message,
			RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		if (message != null) {
			redirectAttributes.addFlashAttribute("message",
					java.net.URLDecoder.decode(message, "UTF-8"));
		}
		return "redirect:login";
	}

	/**
	 * 返回登录界面处理
	 * 
	 * @param message
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/noPrivilege", method = RequestMethod.POST)
	public String noPrivileges(String message, HttpServletRequest request) {
		if (message != null) {
			request.setAttribute("message", message);
		}
		return "index/login";
	}

	@ResponseBody
	@RequestMapping("/getCurrentUser")
	public DataVO<User> getCurrentUser() {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setT(shiroRealm.getCurrentUser());
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
	 * 首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "index/index";
	}

	/**
	 * 用户信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/info")
	public String info() {
		return "index/info";
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public String updatePwd() {
		return "index/updatePwd";
	}

	/**
	 * 基础架构
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/base")
	public String base() {
		// 数据中心管理
		if (shiroRealm.isPermission("datacenter:read")) {
			return "redirect:datacenter";
		}
		// 区域管理
		else if (shiroRealm.isPermission("zone:read")) {
			return "redirect:zone";
		}
		// 资源池管理
		else if (shiroRealm.isPermission("pool:read")) {
			return "redirect:pool";
		}
		// 集群管理
		else if (shiroRealm.isPermission("cluster:read")) {
			return "redirect:cluster";
		}
		// 主机
		else if (shiroRealm.isPermission("host:read")) {
			return "redirect:host";
		}
		// 存储管理
		else if (shiroRealm.isPermission("storage:read")) {
			return "redirect:storage";
		}
		// 云盘
		else if (shiroRealm.isPermission("disk:read")) {
			return "redirect:disk";
		}
		// 网络管理
		else if (shiroRealm.isPermission("network:read")) {
			return "redirect:network";
		}
		// 子网管理
		else if (shiroRealm.isPermission("subnet:read")) {
			return "redirect:subnet";
		}
		// 模板管理
		else if (shiroRealm.isPermission("image:read")) {
			return "redirect:image";
		}
		// 实例快照管理
		else if (shiroRealm.isPermission("snapshot:read")) {
			return "redirect:snapshot";
		}
		// 云盘快照管理
		else if (shiroRealm.isPermission("diskSnapshot:read")) {
			return "redirect:diskSnapshot";
		}
		// else if (shiroRealm.isPermission("iso:read")) {
		// return "redirect:iso";
		// }
		else {
			return "index/unauthorized";
		}
	}

	/**
	 * 数据中心
	 * 
	 * @return
	 */
	@RequestMapping("/datacenter")
	@RequiresPermissions("datacenter:read")
	public String datacenter() {
		return "base/datacenter";
	}

	/**
	 * 区域
	 * 
	 * @return
	 */
	@RequestMapping("/zone")
	@RequiresPermissions("zone:read")
	public String zone() {
		return "base/zone";
	}

	/**
	 * 资源池
	 * 
	 * @return
	 */
	@RequestMapping("/pool")
	@RequiresPermissions("pool:read")
	public String pool() {
		return "base/pool";
	}

	/**
	 * 集群
	 * 
	 * @return
	 */
	@RequestMapping("/cluster")
	@RequiresPermissions("cluster:read")
	public String cluster() {
		return "base/cluster";
	}

	/**
	 * 主机
	 * 
	 * @return
	 */
	@RequestMapping("/host")
	@RequiresPermissions("host:read")
	public String host() {
		return "base/host";
	}

	/**
	 * 云盘
	 * 
	 * @return
	 */
	@RequestMapping("/disk")
	@RequiresPermissions("disk:read")
	public String disk() {
		return "base/disk";
	}

	/**
	 * 云主机类型
	 * 
	 * @return
	 */
	@RequestMapping("/flavor")
	@RequiresPermissions("flavor:read")
	public String flavor() {
		return "base/flavor";
	}

	/**
	 * 快照
	 * 
	 * @return
	 */
	@RequestMapping("/snapshot")
	@RequiresPermissions("snapshot:read")
	public String snapshot() {
		return "base/snapshot";
	}

	/**
	 * 网络
	 * 
	 * @return
	 */
	@RequestMapping("/network")
	@RequiresPermissions("network:read")
	public String network() {
		return "base/network";
	}

	/**
	 * 子网
	 * 
	 * @return
	 */
	@RequestMapping("/subnet")
	@RequiresPermissions("subnet:read")
	public String subnet() {
		return "base/subnet";
	}

	/**
	 * 存储
	 * 
	 * @return
	 */
	@RequestMapping("/storage")
	@RequiresPermissions("storage:read")
	public String storage() {
		return "base/storage";
	}

	/**
	 * 模板
	 * 
	 * @return
	 */
	@RequestMapping("/image")
	@RequiresPermissions("image:read")
	public String image() {
		return "base/image";
	}

	/**
	 * ISO
	 * 
	 * @return
	 */
	@RequestMapping("/iso")
	@RequiresPermissions("iso:read")
	public String iso() {
		return "base/iso";
	}

	/**
	 * 资源管理
	 * 
	 * @return
	 */
	@RequestMapping("/resource")
	@RequiresPermissions("instance:read")
	public String resource() {
		return "resource/resource";
	}

	/**
	 * 实例
	 * 
	 * @return
	 */
	@RequestMapping("/instance")
	@RequiresPermissions("instance:read")
	public String instance() {
		return "virtual/instance";
	}

	/**
	 * 系统管理
	 * 
	 * @return
	 */
	@RequestMapping("/sys")
	public String sys() {
		// 用户管理
		if (shiroRealm.isPermission("user:read")) {
			return "redirect:user";
		}
		// 角色管理
		else if (shiroRealm.isPermission("role:read")) {
			return "redirect:role";
		}
		// 系统参数管理
		else if (shiroRealm.isPermission("sysconfig:read")) {
			return "redirect:sysconfig";
		}
		// 日志管理
		else if (shiroRealm.isPermission("log:read")) {
			return "redirect:log";
		}
		// 告警管理
		else if (shiroRealm.isPermission("alert:read")) {
			return "redirect:alert";
		}
		// 业务管理
		else if (shiroRealm.isPermission("business:read")) {
			return "redirect:business";
		}
		// 虚拟化配置
		else if (shiroRealm.isPermission("hypervisor:read")) {
			return "redirect:hypervisor";
		}
		// 无此模块的权限
		else {
			return "index/unauthorized";
		}
	}

	/**
	 * 用户
	 * 
	 * @return
	 */
	@RequestMapping("/user")
	@RequiresPermissions("user:read")
	public String user() {
		return "sys/user";
	}

	/**
	 * 角色
	 * 
	 * @return
	 */
	@RequestMapping("/role")
	@RequiresPermissions("role:read")
	public String role() {
		return "sys/role";
	}

	/**
	 * 系统参数
	 * 
	 * @return
	 */
	@RequestMapping("/sysconfig")
	@RequiresPermissions("sysconfig:read")
	public String sysconfig() {
		return "sys/sysconfig";
	}

	/**
	 * 日志
	 * 
	 * @return
	 */
	@RequestMapping("/log")
	@RequiresPermissions("log:read")
	public String log() {
		return "sys/log";
	}

	/**
	 * 告警
	 * 
	 * @return
	 */
	@RequestMapping("/alert")
	@RequiresPermissions("alert:read")
	public String alert() {
		return "sys/alert";
	}

	/**
	 * 业务
	 * 
	 * @return
	 */
	@RequestMapping("/business")
	@RequiresPermissions("business:read")
	public String business() {
		return "sys/business";
	}

	/**
	 * 虚机容器管理
	 */
	@RequestMapping("/hypervisor")
	@RequiresPermissions("hypervisor:read")
	public String hypervisor() {
		return "sys/hypervisor";
	}

	@RequestMapping("/report")
	@RequiresPermissions("report:read")
	public String cpuReport() {
		return "report/cpu";
	}

	@RequestMapping("/memoryReport")
	@RequiresPermissions("report:read")
	public String report() {
		return "report/memory";
	}

	@RequestMapping("/monitor")
	@RequiresPermissions("monitor:read")
	public String monitor() {
		return "monitor/monitor";
	}

	/**
	 * 虚机容器管理
	 */
	@RequestMapping("/container")
	@RequiresPermissions("container:read")
	public String container() {
		return "hypervisor/container";
	}

	@RequestMapping("/diskSnapshot")
	@RequiresPermissions("diskSnapshot:read")
	public String diskSnapshot() {
		return "base/diskSnapshot";
	}

	@RequestMapping("/monitorView")
	@RequiresPermissions("monitor:read")
	public String monitorView() {
		return "monitor/monitorView";
	}

	@Override
	public Page<Object> pageList(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Object> list(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Object> load(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Object> save(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Object> delete(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 统计数据中心，区域，资源池，集群，主机，实例的数量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findResourceCount")
	public DataVO<Map<String, Object>> findResourceCount() {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("dcSize", datacenterService.homeCount());
			map.put("zoneSize", zoneService.homeCount());
			map.put("poolSize", poolService.homeCount());
			map.put("pmClusterSize", clusterService.homeCount(ClusterTypeConstant.PHYSICAL_MACHINE));
			map.put("mmClusterSize", clusterService.homeCount(ClusterTypeConstant.MINI_MACHINE));
			map.put("instanceClusterSize", clusterService.homeCount(ClusterTypeConstant.INSTANCE));
//			map.put("csClusterSize", clusterService.homeCount(ClusterTypeConstant.CLOUD_STORE));
			map.put("citrixClusterSize", clusterService.homeCount(ClusterTypeConstant.CITRIX));
			map.put("lparSize", lparInfoService.homeCount());
			map.put("hostSize", hostService.homeCount());
			map.put("physicalMachineSize", physicalMachineService.homeCount());
			map.put("citrixDesktopMachineSize", citrixDesktopMachineService.homeCount());
			map.put("miniMachineSize", miniMachineInfoService.homeCount());
			map.put("instanceSize", instanceService.homeCount());
			map.putAll(datacenterService.totalCount());
			vo.setT(map);
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
	@RequestMapping(value = "/getUrl", method = RequestMethod.POST)
	public DataVO<String> getUrl(@RequestBody IndexForm f) {
		DataVO<String> vo = new DataVO<String>();
		try {
			vo.setT(indexService.getUrl(f));
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
	public DataVO<Object> deletes(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(Object f) {
		// TODO Auto-generated method stub
		return null;
	}

}
