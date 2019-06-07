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
import com.cloud.operation.core.form.CmTypeForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CmType;
import com.cloud.operation.db.entity.business.CmTypeAttr;
import com.cloud.operation.service.ICmTypeService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/cmType")
public class CmTypeAction extends BaseAction<CmTypeForm, CmType> {

	@Resource
	private ICmTypeService cmTypeService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("cmType:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = CmType.class, name = {
			"name", "code", "sort" }) })
	public Page<CmType> pageList(@RequestBody CmTypeForm f) {
		Page<CmType> page = new Page<CmType>();
		try {
			page = cmTypeService.findPageList(f);
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
	@RequiresPermissions("cmType:read")
	@IgnoreProperties(allow = { @AllowProperty(pojo = CmType.class, name = {
		"name", "code", "sort" }) })
	public DataVO<CmType> list(@RequestBody CmTypeForm f) {
		DataVO<CmType> vo = new DataVO<CmType>();
		try {
			vo.setList(cmTypeService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			return vo;
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("cmType:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmType.class, name = { "name", "code",
					"sort", "typeAttrs" }),
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "sort" }) })
	public DataVO<CmType> load(@RequestBody CmTypeForm f) {
		DataVO<CmType> vo = new DataVO<CmType>();
		try {
			vo.setT(cmTypeService.findById(f.getUuid()));
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("cmType:write")
	public DataVO<CmType> save(@RequestBody CmTypeForm f) {
		DataVO<CmType> vo = new DataVO<CmType>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				cmTypeService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加配置项类型成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				cmTypeService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改配置项类型成功");
			}
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("cmType:write")
	public DataVO<CmType> delete(@RequestBody CmTypeForm f) {
		DataVO<CmType> vo = new DataVO<CmType>();
		try {
			cmTypeService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除配置项类型成功");
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("cmType:write")
	public DataVO<CmType> deletes(@RequestBody CmTypeForm f) {
		DataVO<CmType> vo = new DataVO<CmType>();
		try {
			cmTypeService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除配置项类型成功");
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

	@Override
	public Boolean validate(CmTypeForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
