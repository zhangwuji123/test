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
import com.cloud.operation.core.form.ScriptExecuteForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ScriptExceute;
import com.cloud.operation.service.IScriptExecuteService;

@Controller
@RequestMapping("/scriptExecute")
public class ScriptExecuteAction extends BaseAction<ScriptExecuteForm, ScriptExceute> {

	@Resource
	private IScriptExecuteService scriptExecuteService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("scriptExecute:read")
	public Page<ScriptExceute> pageList(@RequestBody ScriptExecuteForm f) {
		Page<ScriptExceute> page = new Page<ScriptExceute>();
		try {
			page = scriptExecuteService.findPageList(f);
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
	public DataVO<ScriptExceute> list(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ScriptExceute> load(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ScriptExceute> save(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ScriptExceute> delete(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ScriptExceute> deletes(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ScriptExecuteForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
