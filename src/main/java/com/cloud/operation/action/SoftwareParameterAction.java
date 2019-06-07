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
import com.cloud.operation.core.form.SoftwareFlowParameterRelationForm;
import com.cloud.operation.core.form.SoftwareParameterForm;
import com.cloud.operation.core.form.SoftwareParameterRelationForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.SoftwareFlowParameterRelation;
import com.cloud.operation.db.entity.business.SoftwareParameter;
import com.cloud.operation.db.entity.business.SoftwareParameterRelation;
import com.cloud.operation.service.ISoftwareFlowParameterRelationService;
import com.cloud.operation.service.ISoftwareParameterRelationService;
import com.cloud.operation.service.ISoftwareParameterService;

@Controller
@RequestMapping("/softwareParameter")
public class SoftwareParameterAction extends BaseAction<SoftwareParameterForm,SoftwareParameter> {
	
	@Resource
	private ISoftwareParameterService softwareParameterService;
	
	@Resource
	private ISoftwareParameterRelationService softwareParameterRelationService;
	
	@Resource
	private ISoftwareFlowParameterRelationService softwareFlowParameterRelationService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:read")
	public Page<SoftwareParameter> pageList(@RequestBody SoftwareParameterForm f) {
		Page<SoftwareParameter> page = new Page<SoftwareParameter>();
		try{
			page = softwareParameterService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:read")
	public DataVO<SoftwareParameter> load(@RequestBody SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try{
			vo.setT(softwareParameterService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameter> save(@RequestBody SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try{
			softwareParameterService.insert(f);
			vo.setMessage("添加软件参数完成");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameter> update(@RequestBody SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try{
			softwareParameterService.updateFromForm(f);
			vo.setMessage("修改软件参数完成");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameter> delete(@RequestBody SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try{
			softwareParameterService.deleteById(f.getUuid());
			vo.setMessage("删除软件参数完成");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameter> deletes(@RequestBody SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try {
			//softwareParameterService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除软件参数完成");
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
	@RequiresPermissions("softwareParameter:read")
	public Boolean validate(@RequestBody SoftwareParameterForm f) {
		try {
			return softwareParameterService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:read")
	public DataVO<SoftwareParameter> list(SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try {
			vo.setList(softwareParameterService.findList(f));
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
	 * 根据软件id查询软件参数列表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listBySoftwareUuid", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:read")
	public DataVO<SoftwareParameter> listBySoftwareUuid(SoftwareParameterForm f) {
		DataVO<SoftwareParameter> vo = new DataVO<SoftwareParameter>();
		try {
			vo.setList(softwareParameterService.findListBySoftwareUuid(f));
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
	 * 添加软件参数中间表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveSoftwareParameterRelation", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameterRelation> saveSoftwareParameterRelation(@RequestBody SoftwareParameterRelationForm f) {
		DataVO<SoftwareParameterRelation> vo = new DataVO<SoftwareParameterRelation>();
		try{
			softwareParameterRelationService.inserts(f);
			vo.setMessage("设置软件参数成功");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 添加软件流参数中间表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveSoftwareFlowParameterRelation", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareFlowParameterRelation> saveSoftwareFlowParameterRelation(@RequestBody SoftwareFlowParameterRelationForm f) {
		DataVO<SoftwareFlowParameterRelation> vo = new DataVO<SoftwareFlowParameterRelation>();
		try{
			softwareFlowParameterRelationService.inserts(f);
			vo.setMessage("设置软件参数成功");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 删除软件参数中间表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSoftwareParameterRelation", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareParameterRelation> deleteSoftwareParameterRelation(@RequestBody SoftwareParameterRelationForm f) {
		DataVO<SoftwareParameterRelation> vo = new DataVO<SoftwareParameterRelation>();
		try{
			softwareParameterRelationService.deleteById(f.getUuid());
			vo.setMessage("删除软件参数成功");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 删除软件流参数中间表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSoftwareFlowParameterRelation", method = RequestMethod.POST)
	@RequiresPermissions("softwareParameter:write")
	public DataVO<SoftwareFlowParameterRelation> deleteSoftwareFlowParameterRelation(@RequestBody SoftwareFlowParameterRelationForm f) {
		DataVO<SoftwareFlowParameterRelation> vo = new DataVO<SoftwareFlowParameterRelation>();
		try{
			softwareFlowParameterRelationService.deleteById(f.getUuid());
			vo.setMessage("删除软件参数成功");
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
}
