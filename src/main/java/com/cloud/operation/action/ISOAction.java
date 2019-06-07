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
import com.cloud.operation.core.form.ISOSForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.ISO;
import com.cloud.operation.service.IISOService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/iso")
public class ISOAction extends BaseAction<ISOSForm, ISO> {
	
	@Resource
	private IISOService isoService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("iso:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","htype","ptype","alias","state"})
	})
	public Page<ISO> pageList(@RequestBody ISOSForm f) {
		Page<ISO> page = new Page<ISO>();
		try {
			page = isoService.findPageList(f);
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
	@RequiresPermissions("iso:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","htype","ptype","alias","state"})
	})
	public DataVO<ISO> list(@RequestBody ISOSForm f) {
		DataVO<ISO> vo = new DataVO<ISO>();
		try {
			vo.setList(isoService.findList(f));
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("iso:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = Cluster.class, name = { "uuid","name","htype","ptype","alias","state"})
	})
	public DataVO<ISO> load(@RequestBody ISOSForm f) {
		DataVO<ISO> vo = new DataVO<ISO>();
		try {
			vo.setT(isoService.findById(f.getUuid()));
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
	public DataVO<ISO> save(ISOSForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ISO> delete(ISOSForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ISO> deletes(ISOSForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ISOSForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setType", method = RequestMethod.POST)
	@RequiresPermissions("iso:write")
	public DataVO<ISO> setType(@RequestBody ISOSForm f) {
		DataVO<ISO> vo = new DataVO<ISO>();
		try {
			isoService.setType(f);
			vo.setMessage("设置ISO类型完成");
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
	@RequestMapping(value = "/batchSetType", method = RequestMethod.POST)
	@RequiresPermissions("iso:write")
	public DataVO<ISO> batchSetType(@RequestBody ISOSForm f) {
		DataVO<ISO> vo = new DataVO<ISO>();
		try {
			isoService.batchSetType(f);
			vo.setMessage("设置ISO类型完成");
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

}
