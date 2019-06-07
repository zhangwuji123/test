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
import com.cloud.operation.core.form.LparInfoForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Datacenter;
import com.cloud.operation.db.entity.business.HypervisorServer;
import com.cloud.operation.db.entity.business.LparInfo;
import com.cloud.operation.db.entity.business.LparVirtualIo;
import com.cloud.operation.db.entity.business.MiniMachineInfo;
import com.cloud.operation.db.entity.business.MiniMachineIoResource;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.Zone;
import com.cloud.operation.service.ILparInfoService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/lparInfo")
public class LparInfoAction extends BaseAction<LparInfoForm, LparInfo> {
	
	@Resource
	private ILparInfoService lparInfoService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public Page<LparInfo> pageList(@RequestBody LparInfoForm f) {
		Page<LparInfo> page = new Page<LparInfo>();
		try {
			page = lparInfoService.findPageList(f);
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
	@RequiresPermissions("lparInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public DataVO<LparInfo> list(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			vo.setList(lparInfoService.findList(f));
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
	@RequiresPermissions("lparInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype","datacenter","zone","pool"}),
		@AllowProperty(pojo = Datacenter.class, name = { "name"}),
		@AllowProperty(pojo = Zone.class, name = { "name"}),
		@AllowProperty(pojo = Pool.class, name = { "name"}),
		@AllowProperty(pojo = HypervisorServer.class, name = { "ip","username","password"})
	})
	public DataVO<LparInfo> load(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			vo.setT(lparInfoService.findById(f.getUuid()));
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
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> save(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				lparInfoService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_LPAR_SUCCESS);
			}
			if (RequestAction.UPDATE.getValue().equals(f.getAction())){
				lparInfoService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.UPDATE_LPAR_SUCCESS);
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
	
	
	@ResponseBody
	@RequestMapping(value = "/modifyConfig", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> modifyConfig(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.modifyConfig(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("修改配置中，请稍后");
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
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> installConfig(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.installConfig(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("安装中，请稍后");
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> delete(@RequestBody LparInfoForm f) {
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除中，请稍后");
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
	@RequestMapping(value = "/boot", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> boot(@RequestBody LparInfoForm f){
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.bootById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("开机中，请稍后");
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
	@RequestMapping(value = "/shutdown", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> shutdown(@RequestBody LparInfoForm f){
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.shutdownById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("关机中，请稍后");
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
	@RequestMapping(value = "/reboot", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:write")
	public DataVO<LparInfo> reboot(@RequestBody LparInfoForm f){
		DataVO<LparInfo> vo = new DataVO<LparInfo>();
		try {
			lparInfoService.rebootById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("重启中，请稍后");
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
	public DataVO<LparInfo> deletes(LparInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LparInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findIoRsList", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:read")
	public DataVO<MiniMachineIoResource> findIoRsList(@RequestBody LparInfoForm f) {
		DataVO<MiniMachineIoResource> vo = new DataVO<MiniMachineIoResource>();
		try {
			vo.setList(lparInfoService.findIOResourceByLparUuid(f.getUuid()));
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
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:read")
	public DataVO<MiniMachineIoResource> setUser(@RequestBody LparInfoForm f) {
		DataVO<MiniMachineIoResource> vo = new DataVO<MiniMachineIoResource>();
		try {
			lparInfoService.setUser(f);
			vo.setMessage("指定用户完成");
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
	@RequestMapping(value = "/findMmByLPUuid", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:read")
	public DataVO<MiniMachineInfo> findMiniMachineInfoByLparUuid(@RequestBody LparInfoForm f) {
		DataVO<MiniMachineInfo> vo = new DataVO<MiniMachineInfo>();
		try {
			vo.setT(lparInfoService.findById(f.getUuid()).getMiniMachineInfo());
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
	 * 跟据lpar id 查询 虚拟io资源
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findVirtualIoList", method = RequestMethod.POST)
	@RequiresPermissions("lparInfo:read")
	public DataVO<LparVirtualIo> findVirtualIoList(@RequestBody LparInfoForm f) {
		DataVO<LparVirtualIo> vo = new DataVO<LparVirtualIo>();
		try {
			vo.setList(lparInfoService.findVirtualIoByLparUuid(f.getUuid()));
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
