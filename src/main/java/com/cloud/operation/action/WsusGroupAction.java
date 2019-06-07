package com.cloud.operation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.WsusGroupForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.WsusGroup;
import com.cloud.operation.service.IWsusGroupService;

/**
 * 计算机组控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/wsusGroup")
public class WsusGroupAction extends BaseAction<WsusGroupForm, WsusGroup> {

	@Resource
	private IWsusGroupService wsusGroupService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("wsusGroup:read")
	public Page<WsusGroup> pageList(@RequestBody WsusGroupForm f) {
		Page<WsusGroup> page = new Page<WsusGroup>();
		try {
			page = wsusGroupService.findPageList(f);
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
	
	@ResponseBody
	@RequestMapping(value = "/findPageListByApproval", method = RequestMethod.POST)
	@RequiresPermissions("wsusGroup:read")
	public DataVO<Map<String,Object>> findPageListByApproval(@RequestBody WsusGroupForm f) {
		DataVO<Map<String,Object>> vo = new DataVO<Map<String,Object>>();
		try {
			vo.setList(wsusGroupService.findApproalList(f));
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
	
	
	@ResponseBody
	@RequestMapping(value = "/initTree", method = RequestMethod.POST)
	@RequiresPermissions("wsusGroup:read")
	public DataVO<Map<String, Object>> initTree(WsusGroupForm f) {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();	
		try {
				List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
				/*Map rootMap = new HashMap();
				rootMap.put("id", 0);
				rootMap.put("parentId",null);
				rootMap.put("name", "计算机管理");
				rootMap.put("isParent", true);
				rootMap.put("icon", "images/icon-pool.png");
				tree.add(rootMap);*/
				
				List<WsusGroup> wsusGroupList = wsusGroupService.findList(f);
				if(wsusGroupList!=null&&wsusGroupList.size()>0){
					for(WsusGroup wsusGroup : wsusGroupList){
						Map<String, Object> childMap = new HashMap<String, Object>();
						childMap.put("id", wsusGroup.getUuid());
						if(wsusGroup.getUuid().equals("1")){
							childMap.put("parentId", null);
							childMap.put("isParent", true);
						}else{
							childMap.put("parentId","1");
							childMap.put("isParent", false);
						}
						
						childMap.put("name", wsusGroup.getName());
						childMap.put("icon", "images/icon-vm.png");
						tree.add(childMap);
					}
				}
				vo.setList(tree);
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
	public DataVO<WsusGroup> load(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusGroup> save(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusGroup> delete(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataVO<WsusGroup> deletes(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean validate(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DataVO<WsusGroup> list(WsusGroupForm f) {
		// TODO Auto-generated method stub
		return null;
	}
}
