package com.cloud.operation.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.LBForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.LB;
import com.cloud.operation.service.ILBService;

/***
 * 负载均衡
 * @author WangJc
 */
@Controller
@RequestMapping("/lb")
public class LBAction extends BaseAction<LBForm, LB> {
	
	@Resource
	public ILBService lbService;

	@Override
	public Page<LB> pageList(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LB> list(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LB> load(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LB> save(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LB> delete(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<LB> deletes(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(LBForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/lbMonitor", method = RequestMethod.POST)
	public DataVO<String> lbMonitor(@RequestBody LBForm f){
		DataVO<String> vo = new DataVO<String>();
		try {
			vo.setT(lbService.getLBMonitor(f));
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
