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
import com.cloud.operation.core.action.IAction.RequestAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.CitrixDesktopGroupForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.Network;
import com.cloud.operation.db.entity.business.citrix.CitrixDesktopGroup;
import com.cloud.operation.service.ICitrixDesktopGroupService;
import com.cloud.operation.service.impl.CitrixDesktopGroupService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/citrix/desktopGroup")
public class CitrixDesktopGroupAction extends BaseAction<CitrixDesktopGroupForm, CitrixDesktopGroup> {

	@Resource
	private ICitrixDesktopGroupService citrixDesktopGroupService;
	@Autowired
	private CitrixDesktopGroupService cdgService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
//	@RequiresPermissions("deliveryGroup:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CitrixDesktopGroup.class, name = { "name", "cluster", "state", "description", "createTime", "deliveryType","vmCount","desktopKind","inmaintenancemode","tags","enabled"}),
			@AllowProperty(pojo = Cluster.class, name = { "name" }) })
	public Page<CitrixDesktopGroup> pageList(@RequestBody CitrixDesktopGroupForm f) {
		Page<CitrixDesktopGroup> page = new Page<CitrixDesktopGroup>();
		try {
			page = citrixDesktopGroupService.findPageList(f);
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
	public DataVO<CitrixDesktopGroup> list(CitrixDesktopGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value="/load", method = RequestMethod.POST)
	@RequiresPermissions("deliveryGroup:read")
	public DataVO<CitrixDesktopGroup> load(@RequestBody CitrixDesktopGroupForm f) {
		DataVO<CitrixDesktopGroup> vo = new DataVO<CitrixDesktopGroup>();
		try{
			vo.setT(citrixDesktopGroupService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
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
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@RequiresPermissions("deliveryGroup:write")
	public DataVO<CitrixDesktopGroup> save(@RequestBody CitrixDesktopGroupForm f) {
		DataVO<CitrixDesktopGroup> vo = new DataVO<CitrixDesktopGroup>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				vo.setT(cdgService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CITRIX_DESKTOP_GROUP_SUCCESS);
			} else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				citrixDesktopGroupService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CITRIX_DESKTOP_GROUP_SUCCESS);
			}
			/*vo.setT(cdgService.insert(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.ADD_CITRIX_DESKTOP_GROUP_SUCCESS);*/
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
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@RequiresPermissions("deliveryGroup:write")
	public DataVO<CitrixDesktopGroup> delete(@RequestBody CitrixDesktopGroupForm f) {
		DataVO<CitrixDesktopGroup> vo = new DataVO<CitrixDesktopGroup>();
		try{
			citrixDesktopGroupService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CITRIX_DESKTOP_GROUP_SUCCESS);
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
	public DataVO<CitrixDesktopGroup> deletes(CitrixDesktopGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(CitrixDesktopGroupForm f) {
		try {
			return citrixDesktopGroupService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
