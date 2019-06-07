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
import com.cloud.operation.core.form.MiniMachineInfoForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Business;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.MiniMachineInfo;
import com.cloud.operation.service.IMiniMachineInfoService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;
import com.cloud.operation.service.util.IgnoreProperty;

/***
 * Lpar 小机
 * @author WangJc
 */
@Controller
@RequestMapping("/miniMachineInfo")
public class MiniMachineInfoAction extends BaseAction<MiniMachineInfoForm, MiniMachineInfo> {
	
	@Resource
	private IMiniMachineInfoService miniMachineInfoService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("miniMachineInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public Page<MiniMachineInfo> pageList(@RequestBody MiniMachineInfoForm f) {
		Page<MiniMachineInfo> page = new Page<MiniMachineInfo>();
		try {
			page = miniMachineInfoService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("miniMachineInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public DataVO<MiniMachineInfo> list(@RequestBody MiniMachineInfoForm f) {
		DataVO<MiniMachineInfo> vo = new DataVO<MiniMachineInfo>();
		try {
			vo.setList(miniMachineInfoService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("miniMachineInfo:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = Cluster.class, name = {"uuid","alias","name","htype","ptype"})
	})
	public DataVO<MiniMachineInfo> load(@RequestBody MiniMachineInfoForm f) {
		DataVO<MiniMachineInfo> vo = new DataVO<MiniMachineInfo>();
		try {
			vo.setT(miniMachineInfoService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
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
	public DataVO<MiniMachineInfo> save(MiniMachineInfoForm f) {
		return null;
	}

	@Override
	public DataVO<MiniMachineInfo> delete(MiniMachineInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<MiniMachineInfo> deletes(MiniMachineInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(MiniMachineInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setRemark", method = RequestMethod.POST)
	@RequiresPermissions("miniMachineInfo:write")
	public DataVO<MiniMachineInfo> setRemark(@RequestBody MiniMachineInfoForm f){
		DataVO<MiniMachineInfo> vo = new DataVO<MiniMachineInfo>();
		try {
			miniMachineInfoService.setRemark(f);
			vo.setMessage("添加备注完成");
			vo.setState(ResponseState.SUCCESS.getValue());
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

}
