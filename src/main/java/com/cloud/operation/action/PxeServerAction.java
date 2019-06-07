package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.action.IAction.RequestAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.PxeServerForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Pool;
import com.cloud.operation.db.entity.business.PxeServer;
import com.cloud.operation.service.IPxeServerService;

@Controller
@RequestMapping("/pxe")
public class PxeServerAction extends BaseAction<PxeServerForm, PxeServer> {
	
	@Resource
	private IPxeServerService pxeServerService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("pxe:read")
	public Page<PxeServer> pageList(@RequestBody PxeServerForm f) {
		Page<PxeServer> page = new Page<PxeServer>();
		try{
			page = pxeServerService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("pxe:read")
	public DataVO<PxeServer> list(@RequestBody PxeServerForm f) {
		DataVO<PxeServer> vo = new DataVO<PxeServer>();
		try{
			vo.setList(pxeServerService.findList(f));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping("/load")
	@RequiresPermissions("pxe:read")
	public DataVO<PxeServer> load(@RequestBody PxeServerForm f) {
		DataVO<PxeServer> vo = new DataVO<PxeServer>();
		try {
			vo.setT(pxeServerService.findById(f.getUuid()));
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
	@RequestMapping("/save")
	@RequiresPermissions("pxe:write")
	public DataVO<PxeServer> save(@RequestBody PxeServerForm f) {
		DataVO<PxeServer> vo = new DataVO<PxeServer>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				pxeServerService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加PXE服务器完成");
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				pxeServerService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改PXE服务器完成");
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
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("pxe:write")
	public DataVO<PxeServer> delete(@RequestBody PxeServerForm f) {
		DataVO<PxeServer> vo = new DataVO<PxeServer>();
		try {
			pxeServerService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除PXE服务器完成");
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
	public DataVO<PxeServer> deletes(PxeServerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(PxeServerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
