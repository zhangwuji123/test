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
import com.cloud.operation.core.form.LicenceInfoForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.LicenceConfig;
import com.cloud.operation.db.entity.business.LicenceInfo;
import com.cloud.operation.service.ILicenceInfoService;
import com.cloud.operation.service.ISystemServiceService;

@Controller
@RequestMapping("/licence")
public class LicenseAction  extends BaseAction<LicenceInfoForm, LicenceInfo>{
	
	@Resource
	private ILicenceInfoService licenceInfoService;
	
	@Resource
	private ISystemServiceService systemServiceService;

	@Override
	public Page<LicenceInfo> pageList(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LicenceInfo> list(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LicenceInfo> load(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("licence:write")
	public DataVO<LicenceInfo> save(@RequestBody LicenceInfoForm f) {
		DataVO<LicenceInfo> vo = new DataVO<LicenceInfo>();
		try {
			licenceInfoService.insert(f);
			systemServiceService.initServiceData();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("注册Licence成功");
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
	public DataVO<LicenceInfo> delete(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LicenceInfo> deletes(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LicenceInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public DataVO<LicenceConfig> config(@RequestBody LicenceInfoForm f){
		DataVO<LicenceConfig> vo = new DataVO<LicenceConfig>();
		try {
			vo.setT(licenceInfoService.getLicenceConfig());
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
