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
import com.cloud.operation.core.form.DistributedForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.PerformanceDistribution;
import com.cloud.operation.db.entity.business.VmGroupInfo;
import com.cloud.operation.service.IDistributedService;
import com.cloud.operation.service.IPerformanceDistributionReportService;

@Controller
@RequestMapping("/distributed")
public class DistributedAction extends BaseAction<DistributedForm, VmGroupInfo>{
	
	@Resource
	private IDistributedService distributedService;
	
	@Resource
	private IPerformanceDistributionReportService performanceDistributionReportService;
	
	@Override
	public DataVO<VmGroupInfo> list(@RequestBody DistributedForm f) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@RequiresPermissions("distributed:read")
	public DataVO<PerformanceDistribution> dayList(@RequestBody DistributedForm f) {
		DataVO<PerformanceDistribution> vo = new DataVO<PerformanceDistribution>();
		try {
			vo.setData(performanceDistributionReportService.dayReport(f));
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
	@RequestMapping(value = "/timesList", method = RequestMethod.POST)
	@RequiresPermissions("timesDistributed:read")
	public DataVO<PerformanceDistribution> timesList(@RequestBody DistributedForm f) {
		DataVO<PerformanceDistribution> vo = new DataVO<PerformanceDistribution>();
		try {
			vo.setData(performanceDistributionReportService.timeSlotReport(f));
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
	@RequestMapping(value = "/exportDayReport", method = RequestMethod.POST)
	@RequiresPermissions("distributed:read")
	public ResponseEntity<byte[]> export(DistributedForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = performanceDistributionReportService.exportDayReport(f, os);
			headers.setContentDispositionFormData("attachment", filename);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);	
	}
	
	@ResponseBody
	@RequestMapping(value = "/timeSlotReport", method = RequestMethod.POST)
	@RequiresPermissions("distributed:read")
	public ResponseEntity<byte[]> timeSlotReport(DistributedForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = performanceDistributionReportService.timeSlotReport(f, os);
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
	public Page<VmGroupInfo> pageList(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> load(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> save(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> delete(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<VmGroupInfo> deletes(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(DistributedForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
