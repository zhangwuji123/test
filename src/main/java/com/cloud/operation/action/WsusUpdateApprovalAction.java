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
import com.cloud.operation.core.form.WsusUpdateApprovalForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.WsusUpdateApproval;
import com.cloud.operation.service.IWsusUpdateApprovalService;

/**
 * 补丁与组控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/wsusUpdateApproval")
public class WsusUpdateApprovalAction extends BaseAction<WsusUpdateApprovalForm, WsusUpdateApproval> {

	@Resource
	private IWsusUpdateApprovalService wsusUpdateApprovalService;

	

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("wsusUpdate:read")
	public Page<WsusUpdateApproval> pageList(@RequestBody WsusUpdateApprovalForm f) {
		Page<WsusUpdateApproval> page = new Page<WsusUpdateApproval>();
		try {
			page = wsusUpdateApprovalService.findPageList(f);
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
	public DataVO<WsusUpdateApproval> list(WsusUpdateApprovalForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusUpdateApproval> load(WsusUpdateApprovalForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("wsusUpdate:write")
	public DataVO<WsusUpdateApproval> save(@RequestBody WsusUpdateApprovalForm f) {
		DataVO<WsusUpdateApproval> vo = new DataVO<WsusUpdateApproval>();
		try {
			WsusUpdateApproval wsusUa =  wsusUpdateApprovalService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			if(wsusUa!=null){				
				vo.setMessage(MessageUtil.ADD_WsusUpdateApproval_SUCCESS);
			}else{
				vo.setMessage(MessageUtil.ADD_WsusUpdateApproval_EMPTY);
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
	public DataVO<WsusUpdateApproval> delete(WsusUpdateApprovalForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusUpdateApproval> deletes(WsusUpdateApprovalForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean validate(WsusUpdateApprovalForm f) {
		// TODO Auto-generated method stub
		return null;
	}
}
