package com.cloud.operation.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.VpcForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Cluster;
import com.cloud.operation.db.entity.business.HypervisorServerContainer;
import com.cloud.operation.db.entity.business.NetworkScop;
import com.cloud.operation.db.entity.business.OrderInfo;
import com.cloud.operation.db.entity.business.Vdc;
import com.cloud.operation.db.entity.business.Vpc;
import com.cloud.operation.db.entity.business.VpcClusterRelation;
import com.cloud.operation.db.entity.business.VpcResource;
import com.cloud.operation.service.IVpcService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/vpc")
public class VpcAction extends BaseAction<VpcForm, Vpc> {

	@Resource
	private IVpcService vpcService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Vpc.class, name = { "alias", "isolationMode",
					"state", "description", "createTime", "vdc" ,"vpcClusterRelations","clusterOrgCode","source"}),
			@AllowProperty(pojo = Vdc.class, name = { "name" }) })
	public Page<Vpc> pageList(@RequestBody VpcForm f) {
		Page<Vpc> page = new Page<Vpc>();
		try {
			page = vpcService.findPageList(f);
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
	@RequiresPermissions("vpc:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Vpc.class, name = { "alias", "isolationMode",
					"state", "description", "createTime", "vdc" ,"vpcClusterRelations","clusterOrgCode","source"}),
			@AllowProperty(pojo = Vdc.class, name = { "name" }) })
	public DataVO<Vpc> list(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			vo.setList(vpcService.findList(f));
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
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	public DataVO<Vpc> load(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			vo.setT(vpcService.findById(f.getUuid()));
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
	@RequiresPermissions("vpc:write")
	public DataVO<Vpc> save(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vpcService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加VPC成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				vpcService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改VPC成功");
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
	@RequiresPermissions("vpc:write")
	public DataVO<Vpc> delete(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			vpcService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除VPC成功");
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
	public DataVO<Vpc> deletes(VpcForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(VpcForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findVpcQuota", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	public DataVO<Map<String,Object>> findVpcQuota(@RequestBody VpcForm f) {
		DataVO<Map<String,Object>> vo = new DataVO<Map<String,Object>>();
		try {
			vo.setT(vpcService.findVpcQuota(f));
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
	@RequestMapping(value = "/findVpcResource", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	public Page<VpcResource> findVdcResource(@RequestBody VpcForm f) {
		Page<VpcResource> page = new Page<VpcResource>();
		try {
			page = vpcService.findVpcResource(f);
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
	@RequiresPermissions("vpc:write") 
	@RequestMapping(value = "/modifyQuota", method = RequestMethod.POST)
	public DataVO<Vpc> modifyQuota(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			vpcService.modifyQuota(f);
			vo.setMessage("VPC配额修改中");
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
	@RequestMapping(value = "/findVpcByCatalog", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Vpc.class, name = { "alias","name", "isolationMode",
					"state", "description", "createTime", "vdc", "vpcClusterRelations" }),
			@AllowProperty(pojo = Vdc.class, name = { "name" }) ,
			@AllowProperty(pojo = VpcClusterRelation.class, name = { "cluster" }),
			@AllowProperty(pojo = Cluster.class, name = { "hypervisorServerContainer" }),
			@AllowProperty(pojo = HypervisorServerContainer.class, name = { "uuid" }) })
	public DataVO<Vpc> findVpcByCatalog(@RequestBody VpcForm f) {
		DataVO<Vpc> vo = new DataVO<Vpc>();
		try {
			vo.setList(vpcService.findVpcByCatalog(f));
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
	@RequestMapping(value = "/findNetworkList", method = RequestMethod.POST)
	@RequiresPermissions("self:read")
	public DataVO<NetworkScop> findNetworkList(@RequestBody VpcForm f){
		DataVO<NetworkScop> vo = new DataVO<NetworkScop>();
		try{
			vo.setList(vpcService.findNetworkByVpc(f));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	@ResponseBody
	@RequestMapping(value = "/loadOrder", method = RequestMethod.POST)
	@RequiresPermissions("vpc:read")
	@IgnoreProperties(allow = {@AllowProperty(pojo = OrderInfo.class, name = {"state" }) })
	public DataVO<OrderInfo> loadOrder(@RequestBody VpcForm f){
		DataVO<OrderInfo> vo = new DataVO<OrderInfo>();
		try{
			vo.setList(vpcService.findOrderByVpc(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
}
