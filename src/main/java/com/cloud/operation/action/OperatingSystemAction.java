package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.OperatingSystemForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.OperatingSystem;
import com.cloud.operation.service.IOperatingSystemService;

@Controller
@RequestMapping("/operatingSystem")
public class OperatingSystemAction extends BaseAction<OperatingSystemForm, OperatingSystem>{
	
	@Resource
	private IOperatingSystemService operatingSystemService;
	
	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("operatingSystem:read")
	public Page<OperatingSystem> pageList(@RequestBody OperatingSystemForm f) {
		Page<OperatingSystem> page = new Page<OperatingSystem>();
		try{
			page = operatingSystemService.findPageList(f);
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
	@RequestMapping("/lists")
	@RequiresPermissions("operatingSystem:read")
	public DataVO<OperatingSystem> list(@RequestBody OperatingSystemForm f) {
		DataVO<OperatingSystem> vo = new DataVO<OperatingSystem>();
		try{
			vo.setList(operatingSystemService.findList(f));
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
	@RequiresPermissions("operatingSystem:read")
	public DataVO<OperatingSystem> load(@RequestBody OperatingSystemForm f) {
		DataVO<OperatingSystem> vo = new DataVO<OperatingSystem>();
		try{
			vo.setT(operatingSystemService.findById(f.getUuid()));
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
	@RequestMapping("/save")
	@RequiresPermissions("operatingSystem:write")
	public DataVO<OperatingSystem> save(@RequestBody OperatingSystemForm f) {
		DataVO<OperatingSystem> vo = new DataVO<OperatingSystem>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				operatingSystemService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加操作系统完成");
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				operatingSystemService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改操作系统完成");
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
	@RequiresPermissions("operatingSystem:write")
	public DataVO<OperatingSystem> delete(@RequestBody OperatingSystemForm f) {
		DataVO<OperatingSystem> vo = new DataVO<OperatingSystem>();
		try {
			operatingSystemService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除操作系统完成");
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
	public DataVO<OperatingSystem> deletes(OperatingSystemForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(OperatingSystemForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
