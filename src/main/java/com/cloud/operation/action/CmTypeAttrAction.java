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
import com.cloud.operation.core.form.CmTypeAttrForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CmType;
import com.cloud.operation.db.entity.business.CmTypeAttr;
import com.cloud.operation.service.ICmTypeAttrService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/cmTypeAttr")
public class CmTypeAttrAction extends BaseAction<CmTypeAttrForm, CmTypeAttr> {

	@Resource
	private ICmTypeAttrService cmTypeAttrService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("cmTypeAttr:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "type",
					"relationType", "sort" }),
			@AllowProperty(pojo = CmType.class, name = { "name" }) })
	public Page<CmTypeAttr> pageList(@RequestBody CmTypeAttrForm f) {
		Page<CmTypeAttr> page = new Page<CmTypeAttr>();
		try {
			page = cmTypeAttrService.findPageList(f);
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
	@RequiresPermissions("cmTypeAttr:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "type",
					"sort", "relationType" }),
			@AllowProperty(pojo = CmType.class, name = { "name" }) })
	public DataVO<CmTypeAttr> list(@RequestBody CmTypeAttrForm f) {
		DataVO<CmTypeAttr> vo = new DataVO<CmTypeAttr>();
		try {
			vo.setList(cmTypeAttrService.findList(f));
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
	@RequiresPermissions("cmTypeAttr:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "type",
					"sort", "relationType" }),
			@AllowProperty(pojo = CmType.class, name = { "name" }) })
	public DataVO<CmTypeAttr> load(@RequestBody CmTypeAttrForm f) {
		DataVO<CmTypeAttr> vo = new DataVO<CmTypeAttr>();
		try {
			vo.setT(cmTypeAttrService.findById(f.getUuid()));
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
	@RequiresPermissions("cmTypeAttr:write")
	public DataVO<CmTypeAttr> save(@RequestBody CmTypeAttrForm f) {
		DataVO<CmTypeAttr> vo = new DataVO<CmTypeAttr>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				cmTypeAttrService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加配置项属性成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				cmTypeAttrService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改配置项属性成功");
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
	@RequiresPermissions("cmTypeAttr:write")
	public DataVO<CmTypeAttr> delete(@RequestBody CmTypeAttrForm f) {
		DataVO<CmTypeAttr> vo = new DataVO<CmTypeAttr>();
		try {
			cmTypeAttrService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除配置项属性成功");
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
	@RequiresPermissions("cmTypeAttr:write")
	public DataVO<CmTypeAttr> deletes(@RequestBody CmTypeAttrForm f) {
		DataVO<CmTypeAttr> vo = new DataVO<CmTypeAttr>();
		try {
			cmTypeAttrService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除配置项属性成功");
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
	public Boolean validate(CmTypeAttrForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
