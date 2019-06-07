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
import com.cloud.operation.core.form.CitrixMachineCatalogForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.citrix.CitrixDesktopGroup;
import com.cloud.operation.db.entity.business.citrix.CitrixMachineCatalog;
import com.cloud.operation.service.ICitrixMachineCatalogService;
import com.cloud.operation.service.impl.CitrixMachineCatalogService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/citrix/machineCatalog")
public class CitrixMachineCatalogAction extends BaseAction<CitrixMachineCatalogForm, CitrixMachineCatalog> {

	@Resource
	private ICitrixMachineCatalogService citrixMachineCatalogService;
	@Autowired
	private CitrixMachineCatalogService cmcService;
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
//	@RequiresPermissions("computerCatalog:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = CitrixMachineCatalog.class, name = { "name", "cluster", "state", "description", "createTime", "allocationType" ,"provisioningType","persistUserChanges","vmCount","plannedDesktops","usedDesktops","totalCpu", "allocatedCpu","totalMemory","allocatedMemory","totalDisk","allocatedDisk"}),
			@AllowProperty(pojo = Cluster.class, name = { "name" }) })
	public Page<CitrixMachineCatalog> pageList(@RequestBody CitrixMachineCatalogForm f) {
		Page<CitrixMachineCatalog> page = new Page<CitrixMachineCatalog>();
		try {
			page = citrixMachineCatalogService.findPageList(f);
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
	public DataVO<CitrixMachineCatalog> list(CitrixMachineCatalogForm f) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@ResponseBody
	@RequestMapping(value="/load", method=RequestMethod.POST)
	@RequiresPermissions("computerCatalog:read")
	public DataVO<CitrixMachineCatalog> load(@RequestBody CitrixMachineCatalogForm f) {
	 	DataVO<CitrixMachineCatalog> vo = new DataVO<CitrixMachineCatalog>();
		try{
			vo.setT(citrixMachineCatalogService.findById(f.getUuid()));
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
	@RequiresPermissions("computerCatalog:write")
	public DataVO<CitrixMachineCatalog> save(@RequestBody CitrixMachineCatalogForm f) {
	 	DataVO<CitrixMachineCatalog> vo = new DataVO<CitrixMachineCatalog>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				vo.setT(cmcService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_CITRIX_MACHINE_CATALOG_SUCCESS);
			} else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				citrixMachineCatalogService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_CITRIX_MACHINE_CATALOG_SUCCESS);
			}
			/*vo.setT(cmcService.insert(f));
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.ADD_CITRIX_MACHINE_CATALOG_SUCCESS);*/
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
	public DataVO<CitrixMachineCatalog> delete(@RequestBody CitrixMachineCatalogForm f) {
		DataVO<CitrixMachineCatalog> vo = new DataVO<CitrixMachineCatalog>();
		try{
			citrixMachineCatalogService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_CITRIX_MACHINE_CATALOG_SUCCESS);
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
	public DataVO<CitrixMachineCatalog> deletes(CitrixMachineCatalogForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(CitrixMachineCatalogForm f) {
		try {
			return citrixMachineCatalogService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
