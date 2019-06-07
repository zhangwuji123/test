package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ApproveRuleForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ApproveRule;
import com.cloud.operation.service.IApproveRuleService;
import com.cloud.operation.service.shiro.ShiroDbRealm;
/***
 * 订单审批规则
 * 
 * @author WangJc
 *
 */
@Controller
@RequestMapping("/approveRule")
public class ApproveRuleAction extends BaseAction<ApproveRuleForm, ApproveRule>{
	
	@Resource
	private IApproveRuleService approveRuleService;
	
	@Autowired
	private ShiroDbRealm shiroRealm;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("approveRule:read")
	public Page<ApproveRule> pageList(@RequestBody ApproveRuleForm f) {
		Page<ApproveRule> page = new Page<ApproveRule>();
		try{
			page = approveRuleService.findPageList(f);
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
	public DataVO<ApproveRule> list(ApproveRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("approveRule:read")
	public DataVO<ApproveRule> load(@RequestBody ApproveRuleForm f) {
		DataVO<ApproveRule> vo = new DataVO<ApproveRule>();
		try{
			ApproveRule approveRule = approveRuleService.findById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setT(approveRule);
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
	@RequiresPermissions("approveRule:write")
	public DataVO<ApproveRule> save(@RequestBody ApproveRuleForm f) {
		f.setUserUuid(shiroRealm.getCurrentUserUuid());
		DataVO<ApproveRule> vo = new DataVO<ApproveRule>();
		try{
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				approveRuleService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_APRU_SUCCESS);
			}else{
				approveRuleService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_APRU_SUCCESS);
			}
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
	@RequiresPermissions("approveRule:write")
	public DataVO<ApproveRule> delete(@RequestBody ApproveRuleForm f) {
		DataVO<ApproveRule> vo = new DataVO<ApproveRule>();
		try{
			approveRuleService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_APRU_SUCCESS);
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
	public DataVO<ApproveRule> deletes(ApproveRuleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(ApproveRuleForm f) {
		try {
			return approveRuleService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setDefault", method = RequestMethod.POST)
	@RequiresPermissions("approveRule:write")
	public DataVO<ApproveRule> setDefault(@RequestBody ApproveRuleForm f) {
		DataVO<ApproveRule> vo = new DataVO<ApproveRule>();
		try{
			approveRuleService.setDefault(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.SETDF_APRU_SUCCESS);
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
