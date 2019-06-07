package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.BandwidthForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Bandwidth;
import com.cloud.operation.service.IBandwidthService;

@Controller
@RequestMapping("/bandwidth")
public class BandwidthAction extends BaseAction<BandwidthForm, Bandwidth>{
	
	@Resource
	private IBandwidthService bandwidthService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("bandwidth:read")
	public Page<Bandwidth> pageList(@RequestBody BandwidthForm f) {
		Page<Bandwidth> page = new Page<Bandwidth>();
		try{
			page = bandwidthService.findPageList(f);
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
	public DataVO<Bandwidth> list(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Bandwidth> load(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Bandwidth> save(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Bandwidth> delete(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Bandwidth> deletes(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(BandwidthForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
