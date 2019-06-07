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
import com.cloud.operation.core.form.TaskInfoForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.db.entity.business.TaskMsgInfo;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.ITaskInfoService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/task")
public class TaskAction extends BaseAction<TaskInfoForm, TaskInfo> {

	@Resource
	private ITaskInfoService taskInfoService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("task:read")
	@IgnoreProperties(
		allow = {
			@AllowProperty(pojo = User.class, name = {"uuid","realname","username"})
		})
	public Page<TaskInfo> pageList(@RequestBody TaskInfoForm f) {
		Page<TaskInfo> page = new Page<TaskInfo>();
		try {
			page = taskInfoService.findPageList(f);
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
	public DataVO<TaskInfo> list(TaskInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("task:read")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = User.class, name = {"uuid","realname","username"})
	})
	public DataVO<TaskInfo> load(@RequestBody TaskInfoForm f) {
		DataVO<TaskInfo> vo = new DataVO<TaskInfo>();
		try {
			vo.setT(taskInfoService.findById(f.getUuid()));
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

	@Override
	public DataVO<TaskInfo> save(TaskInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<TaskInfo> delete(TaskInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<TaskInfo> deletes(TaskInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(TaskInfoForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@RequiresPermissions("task:write")
	@IgnoreProperties(
	allow = {
		@AllowProperty(pojo = User.class, name = {"uuid","realname","username"})
	})
	public DataVO<TaskMsgInfo> send(@RequestBody TaskInfoForm f){
		DataVO<TaskMsgInfo> vo = new DataVO<TaskMsgInfo>();
		try {
			taskInfoService.send(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("消息正在发送中。");
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

}
