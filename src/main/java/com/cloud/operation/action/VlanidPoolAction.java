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
import com.cloud.operation.core.form.VlanidPoolForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.NetworkDevice;
import com.cloud.operation.db.entity.business.VlanidPool;
import com.cloud.operation.service.IVlanidPoolService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;


@Controller
@RequestMapping("/vlanidPool")
public class VlanidPoolAction extends BaseAction<VlanidPoolForm, VlanidPool>{
	
	@Resource
	private IVlanidPoolService vlanidPoolService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("vlanidPool:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = NetworkDevice.class,
					name = { "name" })})
	public Page<VlanidPool> pageList(@RequestBody VlanidPoolForm f) {
		Page<VlanidPool> page = new Page<VlanidPool>();
		try {
			page = vlanidPoolService.findPageList(f);
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
	@RequiresPermissions("vlanidPool:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = NetworkDevice.class,
					name = { "name" })})
	public DataVO<VlanidPool> list(@RequestBody VlanidPoolForm f) {
		DataVO<VlanidPool> vo = new DataVO<VlanidPool>();
		try {
			vo.setList(vlanidPoolService.findList(f));
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
	@RequiresPermissions("vlanidPool:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = NetworkDevice.class,
					name = { "name" })})
	public DataVO<VlanidPool> load(@RequestBody VlanidPoolForm f) {
		DataVO<VlanidPool> vo = new DataVO<VlanidPool>();
		try {
			vo.setT(vlanidPoolService.findById(f.getUuid()));
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
	@RequiresPermissions("vlanidPool:write")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = NetworkDevice.class,
					name = { "name" })})
	public DataVO<VlanidPool> save(@RequestBody VlanidPoolForm f) {
		DataVO<VlanidPool> vo = new DataVO<VlanidPool>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vlanidPoolService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加VLAN ID池成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				vlanidPoolService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改VLAN ID池成功");
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
	@RequiresPermissions("vlanidPool:write")
	public DataVO<VlanidPool> delete(@RequestBody VlanidPoolForm f) {
		DataVO<VlanidPool> vo = new DataVO<VlanidPool>();
		try {
			vlanidPoolService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除VLAN ID池成功");
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
	public DataVO<VlanidPool> deletes(VlanidPoolForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(VlanidPoolForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
