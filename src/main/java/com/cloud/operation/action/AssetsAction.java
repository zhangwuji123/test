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
import com.cloud.operation.core.form.AssetsForm;
import com.cloud.operation.core.form.ResourceDisplayForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ResourceDisplay;
import com.cloud.operation.db.entity.business.assets.AssetsInfo;
import com.cloud.operation.service.IAssetsService;
import com.cloud.operation.service.IResourceDisplayService;
import com.cloud.operation.service.vo.ResourceDetail;

/**
 *设备管理控制器
 * 
 * @author wxd
 *
 */
@Controller
@RequestMapping("/assets")
public class AssetsAction extends BaseAction<AssetsForm, AssetsInfo> {

	@Resource
	private IAssetsService assetsService;

	@Resource
	private IResourceDisplayService resourceDisplayService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public Page<AssetsInfo> pageList(@RequestBody AssetsForm f) {
		Page<AssetsInfo> page = new Page<AssetsInfo>();
		try {
			page = assetsService.findPageList(f);
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
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<AssetsInfo> list(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			vo.setList(assetsService.findList(f));
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
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<AssetsInfo> load(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			vo.setT(assetsService.findById(f.getUuid()));
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
	@RequiresPermissions("assets:write")
	public DataVO<AssetsInfo> save(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				assetsService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ASSETS_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				assetsService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ASSETS_SUCCESS);
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("assets:write")
	public DataVO<AssetsInfo> delete(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			assetsService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ASSETS_SUCCESS);
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("assets:write")
	public DataVO<AssetsInfo> deletes(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			assetsService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ASSETS_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(AssetsForm f) {
		try {
			return assetsService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAssetsByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<AssetsInfo> findAssetsByRackUuid(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			vo.setList(assetsService.findAssetsByRackUuid(f.getRack()));
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
	
	@ResponseBody
	@RequestMapping(value = "/findAssetsByBladeCenterUuid", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<AssetsInfo> findAssetsByBladeCenterUuid(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			vo.setList(assetsService.findAssetsByBladeCenterUuid(f.getBladecenter()));
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
	
	@ResponseBody
	@RequestMapping(value = "/findAssetList", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<AssetsInfo> findAssetList(@RequestBody AssetsForm f) {
		DataVO<AssetsInfo> vo = new DataVO<AssetsInfo>();
		try {
			vo.setData(assetsService.findAssetList(f));
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
	
	@ResponseBody
	@RequestMapping(value = "/findDetailList", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<ResourceDetail> findDetailList(@RequestBody AssetsForm f) {
		DataVO<ResourceDetail> vo = new DataVO<ResourceDetail>();
		try {
			vo.setData(assetsService.findDetailList());
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
	
	@ResponseBody
	@RequestMapping(value = "/findResourceDisplayList", method = RequestMethod.POST)
	@RequiresPermissions("assets:read")
	public DataVO<ResourceDisplay> findResourceDisplayList(@RequestBody ResourceDisplayForm f) {
		DataVO<ResourceDisplay> vo = new DataVO<ResourceDisplay>();
		try {
			vo.setData(resourceDisplayService.findList(f));
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
