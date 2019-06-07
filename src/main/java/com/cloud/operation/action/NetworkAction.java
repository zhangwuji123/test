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
import com.cloud.operation.core.form.NetworkForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.IpPool;
import com.cloud.operation.db.entity.business.Network;
import com.cloud.operation.db.entity.business.NetworkScop;
import com.cloud.operation.db.entity.business.Networks;
import com.cloud.operation.service.INetworkService;

@Controller
@RequestMapping("/networks")
public class NetworkAction extends BaseAction<NetworkForm, Network>{
	
	@Resource
	private INetworkService networkService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("networks:read")
	public Page<Network> pageList(@RequestBody NetworkForm f) {
		Page<Network> page = new Page<Network>();
		try{
			page = networkService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("networks:read")
	public DataVO<Network> list(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			vo.setList(networkService.findList(f));
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
	@RequestMapping("/load")
	@RequiresPermissions("networks:read")
	public DataVO<Network> load(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			vo.setT(networkService.findById(f.getUuid()));
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
	
	@ResponseBody
	@RequestMapping("/loadNetworkScop")
	@RequiresPermissions("networks:read")
	public DataVO<NetworkScop> loadNetworkScop(@RequestBody NetworkForm f) {
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try{
			vo.setT(networkService.loadNetworkScop(f.getUuid()));
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
	@RequestMapping("/save")
	@RequiresPermissions("networks:write")
	public DataVO<Network> save(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				vo.setT(networkService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_NETWORK_SUCCESS);
			} else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				networkService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_NETWORK_SUCCESS);
			}
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
	@RequestMapping("/delete")
	@RequiresPermissions("networks:write")
	public DataVO<Network> delete(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			networkService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_NETWORK_SUCCESS);
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
	public DataVO<Network> deletes(NetworkForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/modifyState", method = RequestMethod.POST)
	@RequiresPermissions("networks:write")
	public DataVO<Network> modifyState(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try {
			networkService.modifyState(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("网络修改状态成功");
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
	public Boolean validate(NetworkForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/networkScopPageList")
	@RequiresPermissions("networks:read")
	public Page<NetworkScop> networkScopPageList(@RequestBody NetworkForm f) {
		Page<NetworkScop> page = new Page<NetworkScop>();
		try{
			page = networkService.networkScopPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/ipPoolPageList")
	@RequiresPermissions("networks:read")
	public Page<IpPool> ipPoolPageList(@RequestBody NetworkForm f) {
		Page<IpPool> page = new Page<IpPool>();
		try{
			page = networkService.ipPoolPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/upLevel")
	@RequiresPermissions("networks:write")
	public DataVO<Network> upLevel(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			networkService.upLevel(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("网络升级完成");
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
	
	
	@ResponseBody
	@RequestMapping("/downLevel")
	@RequiresPermissions("networks:write")
	public DataVO<Network> downLevel(@RequestBody NetworkForm f) {
		DataVO<Network> vo = new DataVO<Network>();
		try{
			networkService.downLevel(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("网络降级完成");
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
	
	@ResponseBody
	@RequestMapping("/normalList")
	@RequiresPermissions("networks:read")
	public DataVO<NetworkScop> normalList(@RequestBody NetworkForm f) {
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try{
			vo.setList(networkService.findByCluster(f));
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
	
	@ResponseBody
	@RequestMapping(value = "/findNetworks", method = RequestMethod.POST)
	public DataVO<NetworkScop> findNetworks(@RequestBody String instanceUuid) {
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try {
			vo.setList(networkService.findByInstanceUuid(instanceUuid));
			vo.setData(networkService.findNotByInstanceUuid(instanceUuid));
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
	@RequestMapping(value = "/ipList", method = RequestMethod.POST)
	@RequiresPermissions("networks:read")
	public DataVO<IpPool> ipList(@RequestBody NetworkForm f) {
		DataVO<IpPool> vo = new DataVO<IpPool>();
		try {
			vo.setList(networkService.findEasyList(f));
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
	@RequestMapping(value = "/findNetworksByUuids", method = RequestMethod.POST)
	@RequiresPermissions("networks:read")
	public DataVO<Network> findNetworks(@RequestBody NetworkForm f){
		DataVO<Network> vo = new DataVO<Network>();
		try {
			vo.setList(networkService.findNetworksByUuids(f.getUuid()));
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
	@RequestMapping(value = "/findNetworksByCluster", method = RequestMethod.POST)
	public DataVO<NetworkScop> findNetworksByCluster(@RequestBody NetworkForm f) {
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try {
			vo.setList(networkService.findNetwrokScopByCluster(f));
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
	@RequestMapping(value = "/mayUpLevelNetworksPageList", method = RequestMethod.POST)
	@RequiresPermissions("networks:read")
	public Page<Networks> mayUpLevelNetworksPageList(@RequestBody NetworkForm f){
		Page<Networks> page = new Page<Networks>();
		try {
			page = networkService.mayUpLevelNetworksPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
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
	@RequestMapping(value = "/addNetworkScop", method = RequestMethod.POST)
	@RequiresPermissions("networks:write")
	public DataVO<Networks> addNetworkScop(@RequestBody NetworkForm f){
		DataVO<Networks> vo = new DataVO<Networks>();
		try {
			networkService.addNetworkScop(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("添加适配中，请稍后");
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
	@RequestMapping(value = "/removeNetworkScop", method = RequestMethod.POST)
	@RequiresPermissions("networks:write")
	public DataVO<Networks> removeNetworkScop(@RequestBody NetworkForm f){
		DataVO<Networks> vo = new DataVO<Networks>();
		try {
			networkService.removeNetworkScop(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除适配中，请稍后");
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
	@RequestMapping(value = "/modifyNetworkScop", method = RequestMethod.POST)
	@RequiresPermissions("networks:write")
	public DataVO<Networks> modifyNetworkScop(@RequestBody NetworkForm f){
		DataVO<Networks> vo = new DataVO<Networks>();
		try {
			networkService.modifyNetworkScop(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("修改适配中，请稍后");
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
	@RequestMapping(value = "/outsideNetworkList", method = RequestMethod.POST)
	public DataVO<NetworkScop> outsideNetworkList(@RequestBody NetworkForm f) {
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try {
			vo.setList(networkService.findOutSideNetwork(f));
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
	@RequestMapping("/oldNetworkLoad")
	@RequiresPermissions("networks:read")
	public DataVO<Networks> oldNetworkLoad(@RequestBody NetworkForm f) {
		DataVO<Networks> vo = new DataVO<Networks>();
		try{
			vo.setT(networkService.oldNetworkLoad(f.getUuid()));
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
	
	
}
