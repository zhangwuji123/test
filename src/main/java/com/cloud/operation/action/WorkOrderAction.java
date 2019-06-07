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
import com.cloud.operation.core.form.WorkOrderForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.WorkOrder;
import com.cloud.operation.service.IWorkOrderService;

@Controller
@RequestMapping("/workOrder")
public class WorkOrderAction extends BaseAction<WorkOrderForm, WorkOrder> {

	@Resource
	private IWorkOrderService workOrderService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("workOrder:read")
	public Page<WorkOrder> pageList(@RequestBody WorkOrderForm f) {
		Page<WorkOrder> page = new Page<WorkOrder>();
		try {
			page = workOrderService.findPageList(f);
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
	public DataVO<WorkOrder> list(@RequestBody WorkOrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("workOrder:read")
	public DataVO<WorkOrder> load(@RequestBody WorkOrderForm f) {
		DataVO<WorkOrder> vo = new DataVO<WorkOrder>();
		try {
			vo.setT(workOrderService.findById(f.getUuid()));
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

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("workOrder:write")
	public DataVO<WorkOrder> save(@RequestBody WorkOrderForm f) {
		DataVO<WorkOrder> vo = new DataVO<WorkOrder>();
		try {
			workOrderService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("添加工单成功");
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
	public DataVO<WorkOrder> delete(@RequestBody WorkOrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<WorkOrder> deletes(WorkOrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(WorkOrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	@RequiresPermissions("workOrder:write")
	public DataVO<WorkOrder> reply(@RequestBody WorkOrderForm f) {
		DataVO<WorkOrder> vo = new DataVO<WorkOrder>();
		try {
			workOrderService.reply(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("回复工单成功");
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

	@ResponseBody
	@RequestMapping(value = "/resolve", method = RequestMethod.POST)
	@RequiresPermissions("workOrder:write")
	public DataVO<WorkOrder> resolve(@RequestBody WorkOrderForm f) {
		DataVO<WorkOrder> vo = new DataVO<WorkOrder>();
		try {
			workOrderService.resolve(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("解决工单成功");
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
