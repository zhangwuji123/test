package com.cloud.operation.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.OrderForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CloudStoreUserStore;
import com.cloud.operation.db.entity.business.DataDisk;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.InstanceVolumeInfo;
import com.cloud.operation.db.entity.business.LoadBalance;
import com.cloud.operation.db.entity.business.OrderInfo;
import com.cloud.operation.db.entity.business.PhysicalMachine;
import com.cloud.operation.db.entity.business.Software;
import com.cloud.operation.db.entity.business.SoftwareResource;
import com.cloud.operation.service.IOrderService;
import com.cloud.operation.service.shiro.ShiroDbRealm;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/order")
public class OrderAction extends BaseAction<OrderForm, OrderInfo>{
	
	@Resource
	private IOrderService orderService;
	
	@Autowired
	private ShiroDbRealm shiroRealm;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("order:read")
	public Page<OrderInfo> pageList(@RequestBody OrderForm f) {
		Page<OrderInfo> page = new Page<OrderInfo>();
		try{
			page = orderService.findPageList(f);
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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("order:read")
	public DataVO<OrderInfo> list(@RequestBody OrderForm f) {
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			vo.setList(orderService.findList(f));
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("order:read")
	public DataVO<OrderInfo> load(@RequestBody OrderForm f) {
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			vo.setT(orderService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("order:write")
	public DataVO<OrderInfo> save(@RequestBody OrderForm f) {
		
		//设置当前创建订单的用户
		f.setUserUuid(shiroRealm.getCurrentUserUuid());
		
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				orderService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ORDER_SUCCESS);
			}else{
				orderService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ORDER_SUCCESS);
			}
			
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("order:write")
	public DataVO<OrderInfo> delete(@RequestBody OrderForm f) {
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			orderService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ORDER_SUCCESS);
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataVO<OrderInfo> deletes(OrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(OrderForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/resourceList", method = RequestMethod.POST)
	@RequiresPermissions("order:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = InstanceInfo.class, name = { "name",
					"displayName", "state" }),
			@AllowProperty(pojo = CloudStoreUserStore.class, name = { "name",
					"state" }),
			@AllowProperty(pojo = LoadBalance.class, name = { "name", "state" }),
			@AllowProperty(pojo = PhysicalMachine.class, name = { "name",
					"state" }),
			@AllowProperty(pojo = SoftwareResource.class, name = { "name",
			"state","software" }),
			@AllowProperty(pojo = Software.class, name = { "name"}),
			@AllowProperty(pojo = DataDisk.class, name = { "name",
			"state","instanceInfo","instanceVolumeInfo" }),
			@AllowProperty(pojo = InstanceVolumeInfo.class, name = { "name",
				"state" })})
	public DataVO<HashMap<String,Object>> resourceList(@RequestBody OrderForm f) {
		DataVO<HashMap<String,Object>> vo = new DataVO<HashMap<String,Object>>();
		try{
			vo.setData(orderService.findResourceList(f));
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
	@RequestMapping(value = "/resourceSubmit", method = RequestMethod.POST)
	@RequiresPermissions("order:write")
	public DataVO<OrderInfo> rsourceSubmit(@RequestBody OrderForm f) {
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			orderService.resourceSubmit(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("执行交付资源完成");
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
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
	@RequestMapping(value = "/canApprove", method = RequestMethod.POST)
	@RequiresPermissions("order:read")
	public DataVO<Long> canApprove(@RequestBody OrderForm f) {
		DataVO<Long> vo = new DataVO<Long>();
		try{
			vo.setT(orderService.canApproveCount(f));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public ShiroDbRealm getShiroRealm() {
		return shiroRealm;
	}

	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}

}
