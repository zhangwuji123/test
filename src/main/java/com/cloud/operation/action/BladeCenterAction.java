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
import com.cloud.operation.core.form.BladeCenterForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.assets.BladeCenterInfo;
import com.cloud.operation.service.IBladeCenterService;

/**
 * 刀箱管理控制器
 * 
 * @author wxd
 *
 */
@Controller
@RequestMapping("/bladeCenter")
public class BladeCenterAction extends BaseAction<BladeCenterForm, BladeCenterInfo> {

	@Resource
	private IBladeCenterService bladeCenterService;


	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("bladeCenter:read")
	public Page<BladeCenterInfo> pageList(@RequestBody BladeCenterForm f) {
		Page<BladeCenterInfo> page = new Page<BladeCenterInfo>();
		try {
			page = bladeCenterService.findPageList(f);
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
	@RequiresPermissions("bladeCenter:read")
	public DataVO<BladeCenterInfo> list(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			vo.setList(bladeCenterService.findList(f));
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
	@RequestMapping(value = "/listByRackId", method = RequestMethod.POST)
	@RequiresPermissions("rack:read")
	public DataVO<BladeCenterInfo> listByRackId(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			vo.setList(bladeCenterService.findListByRackId(f.getRack()));
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
	@RequiresPermissions("bladeCenter:read")
	public DataVO<BladeCenterInfo> load(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			vo.setT(bladeCenterService.findById(f.getUuid()));
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
	@RequiresPermissions("bladeCenter:write")
	public DataVO<BladeCenterInfo> save(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				bladeCenterService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_BLADECENTER_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				bladeCenterService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_BLADECENTER_SUCCESS);
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
	@RequiresPermissions("bladeCenter:write")
	public DataVO<BladeCenterInfo> delete(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			bladeCenterService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_BLADECENTER_SUCCESS);
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("bladeCenter:write")
	public DataVO<BladeCenterInfo> deletes(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			bladeCenterService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_BLADECENTER_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(BladeCenterForm f) {
		try {
			return bladeCenterService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findBladeCentersByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("bladeCenter:read")
	public DataVO<BladeCenterInfo> findBladeCentersByRackUuid(@RequestBody BladeCenterForm f) {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			vo.setList(bladeCenterService.findListByRackId(f.getRack()));
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
	@RequestMapping(value = "/findBladeCenterList", method = RequestMethod.POST)
	@RequiresPermissions("bladeCenter:read")
	public DataVO<BladeCenterInfo> findBladeCenterList() {
		DataVO<BladeCenterInfo> vo = new DataVO<BladeCenterInfo>();
		try {
			vo.setData(bladeCenterService.findBladeCenterList());
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
