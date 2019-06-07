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
import com.cloud.operation.core.form.ResourceFrameForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ResourceFrame;
import com.cloud.operation.service.IResourceFrameService;

/**
 * 资源架构
 * 
 * @author WangJc
 *
 */
@Controller
@RequestMapping("/resourceFrame")
public class ResourceFrameAction extends BaseAction<ResourceFrameForm, ResourceFrame>{
	
	@Resource
	private IResourceFrameService resourceFrameService;

	@Override
	public Page<ResourceFrame> pageList(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("resourceFrame:read")
	public DataVO<ResourceFrame> list(@RequestBody ResourceFrameForm f) {
		DataVO<ResourceFrame> vo = new DataVO<ResourceFrame>();
		try {
			vo.setList(resourceFrameService.findList(f));
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
	public DataVO<ResourceFrame> load(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ResourceFrame> save(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
	@RequiresPermissions("resourceFrame:write")
	public DataVO<ResourceFrame> batchSave(@RequestBody ResourceFrameForm f) {
		DataVO<ResourceFrame> vo = new DataVO<ResourceFrame>();
		try {
			resourceFrameService.batchInsert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("保存完成");
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
	public DataVO<ResourceFrame> delete(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<ResourceFrame> deletes(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ResourceFrameForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
