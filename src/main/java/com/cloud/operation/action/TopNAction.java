package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.TopNForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CitrixMonitor;
import com.cloud.operation.db.entity.business.HostsGroupInfo;
import com.cloud.operation.db.entity.business.VmGroupInfo;
import com.cloud.operation.service.ITopNService;

@Controller
@RequestMapping("/topN")
public class TopNAction extends BaseAction<TopNForm, VmGroupInfo>{

	@Resource
	private ITopNService topNService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("topN:read")
	public DataVO<VmGroupInfo> list(@RequestBody TopNForm f) {
		DataVO<VmGroupInfo> vo = new DataVO<VmGroupInfo>();
		try {
			vo.setData(topNService.topN(f));
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
	@RequestMapping(value = "/cirtixlist", method = RequestMethod.POST)
	@RequiresPermissions("topN:read")
	public DataVO<CitrixMonitor> citrixlist(@RequestBody TopNForm f) {
		DataVO<CitrixMonitor> vo = new DataVO<CitrixMonitor>();
		try {
			vo.setData(topNService.citrixtopN(f));
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
	@RequestMapping(value = "/hostslist", method = RequestMethod.POST)
	@RequiresPermissions("topN:read")
	public DataVO<HostsGroupInfo> hostslist(@RequestBody TopNForm f) {
		DataVO<HostsGroupInfo> vo = new DataVO<HostsGroupInfo>();
		try {
			vo.setData(topNService.hoststopN(f));
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
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@RequiresPermissions("topN:read")
	public ResponseEntity<byte[]> export(TopNForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = topNService.export(f, os);
			headers.setContentDispositionFormData("attachment", filename);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);	
	}

	@Override
	public Page<VmGroupInfo> pageList(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> load(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> save(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> delete(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> deletes(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(TopNForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
