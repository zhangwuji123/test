package com.cloud.operation.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.constants.CmTypeConstant;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.CmDataForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CmData;
import com.cloud.operation.db.entity.business.CmDataAttr;
import com.cloud.operation.db.entity.business.CmType;
import com.cloud.operation.db.entity.business.CmTypeAttr;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.ICmDataService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/cmData")
public class CmDataAction extends BaseAction<CmDataForm, CmData> {

	@Resource
	private ICmDataService cmDataService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmData.class, name = { "cmCode", "cmName",
					"cmDesc", "team", "subclass", "status", "createUser",
					"createTime", "modifyUser", "modifyTime", "cmType",
					"cmDataAttrs" }),
			@AllowProperty(pojo = User.class, name = { "realname" }),
			@AllowProperty(pojo = CmType.class, name = { "uuid" }),
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "type" }),
			@AllowProperty(pojo = CmDataAttr.class, name = { "value",
					"cmTypeAttr", "relationData" }) })
	public Page<CmData> pageList(@RequestBody CmDataForm f) {
		Page<CmData> page = new Page<CmData>();
		try {
			page = cmDataService.findPageList(f);
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
	public DataVO<CmData> list(@RequestBody CmDataForm f) {
		DataVO<CmData> vo = new DataVO<CmData>();
		try {
			vo.setList(cmDataService.findList(f));
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
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CmData.class, name = { "cmCode", "cmName",
					"cmDesc", "team", "subclass", "status", "createUser",
					"createTime", "modifyUser", "modifyTime", "cmType",
					"cmDataAttrs" }),
			@AllowProperty(pojo = User.class, name = { "realname" }),
			@AllowProperty(pojo = CmType.class, name = { "uuid" }),
			@AllowProperty(pojo = CmTypeAttr.class, name = { "name", "type" }),
			@AllowProperty(pojo = CmDataAttr.class, name = { "value",
					"cmTypeAttr", "relationData" }) })
	public DataVO<CmData> load(@RequestBody CmDataForm f) {
		DataVO<CmData> vo = new DataVO<CmData>();
		try {
			vo.setT(cmDataService.findById(f.getUuid()));
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
	public DataVO<CmData> save(@RequestBody CmDataForm f) {
		DataVO<CmData> vo = new DataVO<CmData>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				cmDataService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加" + CmTypeConstant.getLabel(f.getType())
						+ "成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				cmDataService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改" + CmTypeConstant.getLabel(f.getType())
						+ "成功");
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
	public DataVO<CmData> delete(@RequestBody CmDataForm f) {
		DataVO<CmData> vo = new DataVO<CmData>();
		try {
			cmDataService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除" + CmTypeConstant.getLabel(f.getType())
					+ "成功");
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
	public DataVO<CmData> deletes(@RequestBody CmDataForm f) {
		DataVO<CmData> vo = new DataVO<CmData>();
		try {
			cmDataService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除成功");
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
	public Boolean validate(CmDataForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
