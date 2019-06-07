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
import com.cloud.operation.core.form.CiExtPropertyForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CiExtProperty;
import com.cloud.operation.service.ICiExtPropertyService;

/**
 * 配置扩展属性控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/ciExtProperty")
public class CiExtPropertyAction extends
		BaseAction<CiExtPropertyForm, CiExtProperty> {

	@Resource
	private ICiExtPropertyService ciExtPropertyService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciExtProperty:read")
	public Page<CiExtProperty> pageList(@RequestBody CiExtPropertyForm f) {
		Page<CiExtProperty> page = new Page<CiExtProperty>();
		try {
			page = ciExtPropertyService.findPageList(f);
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
	@RequiresPermissions("ciExtProperty:write")
	public DataVO<CiExtProperty> list(@RequestBody CiExtPropertyForm f) {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setList(ciExtPropertyService.findList(f));
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
	@RequiresPermissions("ciExtProperty:write")
	public DataVO<CiExtProperty> load(@RequestBody CiExtPropertyForm f) {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setT(ciExtPropertyService.findById(f.getUuid()));
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
	@RequiresPermissions("ciExtProperty:write")
	public DataVO<CiExtProperty> save(@RequestBody CiExtPropertyForm f) {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			ciExtPropertyService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("添加扩展属性成功");
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
	@RequiresPermissions("ciExtProperty:write")
	public DataVO<CiExtProperty> delete(@RequestBody CiExtPropertyForm f) {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			ciExtPropertyService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除扩展属性成功");
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
	@RequiresPermissions("ciExtProperty:write")
	public DataVO<CiExtProperty> deletes(@RequestBody CiExtPropertyForm f) {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			ciExtPropertyService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除扩展属性成功");
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
	public Boolean validate(@RequestBody CiExtPropertyForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
