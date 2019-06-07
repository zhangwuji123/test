package com.cloud.operation.action;

import java.util.List;

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
import com.cloud.operation.core.form.ApproveHistoryForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ApproveHistory;
import com.cloud.operation.service.IApproveHistoryService;
import com.cloud.operation.service.shiro.ShiroDbRealm;

@Controller
@RequestMapping("/approve")
public class ApproveHistoryAction extends BaseAction<ApproveHistoryForm, ApproveHistory>{
	
	@Resource
	private IApproveHistoryService approveHistoryService;
	
	@Autowired
	private ShiroDbRealm shiroRealm;

	@Override
	public Page<ApproveHistory> pageList(ApproveHistoryForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("approve:read")
	public DataVO<ApproveHistory> list(@RequestBody ApproveHistoryForm f) {
		DataVO<ApproveHistory> vo = new DataVO<ApproveHistory>();
		try{
			List<ApproveHistory> list = approveHistoryService.findList(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setList(list);
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("approve:read")
	public DataVO<ApproveHistory> load(@RequestBody ApproveHistoryForm f) {
		DataVO<ApproveHistory> vo = new DataVO<ApproveHistory>();
		try{
			ApproveHistory approveHistory = approveHistoryService.findById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setT(approveHistory);
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
	@RequiresPermissions("approve:write")
	public DataVO<ApproveHistory> save(@RequestBody ApproveHistoryForm f) {
		f.setUserUuid(shiroRealm.getCurrentUserUuid());
		DataVO<ApproveHistory> vo = new DataVO<ApproveHistory>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				approveHistoryService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_APHT_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
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
	public DataVO<ApproveHistory> delete(ApproveHistoryForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ApproveHistory> deletes(ApproveHistoryForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ApproveHistoryForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	public IApproveHistoryService getApproveHistoryService() {
		return approveHistoryService;
	}

	public void setApproveHistoryService(
			IApproveHistoryService approveHistoryService) {
		this.approveHistoryService = approveHistoryService;
	}

	public ShiroDbRealm getShiroRealm() {
		return shiroRealm;
	}

	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}

}
