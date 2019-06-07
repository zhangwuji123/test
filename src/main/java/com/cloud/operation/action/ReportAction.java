package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
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
import com.cloud.operation.core.form.ReportDownloadSetForm;
import com.cloud.operation.core.form.ReportForm;
import com.cloud.operation.core.form.UpLoadFileForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Business;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.Vnet;
import com.cloud.operation.db.entity.business.report.Report;
import com.cloud.operation.db.vo.report.StatMetric;
import com.cloud.operation.db.vo.report.StatPoint;
import com.cloud.operation.service.IReportService;
import com.cloud.operation.service.IZoneService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/report")
public class ReportAction extends BaseAction<ReportForm, Report> {
	@Resource
	private IReportService reportService;

	@Resource
	private IZoneService zoneService;

	@Override
	@RequestMapping(value = "/pageList")
	@ResponseBody
	@RequiresPermissions(logical=Logical.OR,value={"cpureport:read","memreport:read"})
	public Page<Report> pageList(@RequestBody ReportForm f) {
		Page<Report> page = new Page<Report>();
		try {
			page = reportService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
			return page;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	@RequestMapping(value = "/load")
	@ResponseBody
	@RequiresPermissions("report:read")
	public DataVO<Report> load(@RequestBody ReportForm f) {
		DataVO<Report> vo = new DataVO<Report>();
		try {
			vo.setT(reportService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("report:write")
	public DataVO<Report> save(@RequestBody ReportForm f) {
		DataVO<Report> vo = new DataVO<Report>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				reportService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_REPORT_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				reportService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_REPORT_SUCCESS);
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
	public DataVO<Report> delete(@RequestBody ReportForm f) {
		return null;
	}

	@Override
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions("report:read")
	public DataVO<Report> list(@RequestBody ReportForm f) {
		DataVO<Report> vo = new DataVO<Report>();
		try {
			vo.setList(reportService.findList(f));
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

	@RequestMapping("/download")
	public ResponseEntity<byte[]> export2Excel(ReportDownloadSetForm f)
			throws IOException {
		logger.info("导出报表...");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		Report r = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			r = reportService.findById(f.getDlUuid());
			String filename = r.getName();
			filename = new String(filename.getBytes("GB2312"), "ISO_8859_1");
			// response.setContentType("application/vnd.ms-excel");
			// response.setHeader("Content-disposition","attachment;filename="+filename+".xls");
			headers.setContentDispositionFormData("attachment", filename + ".xls");
			// 取得主机的所有虚拟机

			List<StatMetric> vmInfos = reportService.getExitsReport(r);
			reportService.writeExcel(vmInfos, os, f);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);

	}

	@RequestMapping("/upload")
	@ResponseBody
	public DataVO<Object> SaveDataByExcel(@RequestBody UpLoadFileForm f)
			throws IOException {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			int r = reportService.readExcel(f.getFile());
			vo.setMessage("" + r);
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

	@RequestMapping("/show")
	@ResponseBody
	@RequiresPermissions("report:read")
	public DataVO<Object> show(@RequestBody ReportDownloadSetForm f)
			throws IOException {
		DataVO<Object> vo = new DataVO<Object>();
		Report r = null;
		try {
			r = reportService.findById(f.getDlUuid());
			List<StatMetric> vmInfos = this.reportService.getExitsReport(r);
			ArrayList<Object> result = new ArrayList<Object>();

			if (vmInfos == null) {
				vo.setData(result);
				vo.setState(ResponseState.SUCCESS.getValue());
				return vo;
			}

			for (StatMetric sm : vmInfos) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ip", sm.getIp());
				map.put("hostname", sm.getHostname());
				map.put("desc", sm.getDesc());

				for (StatPoint sp : sm.getStatMetric()) {
					HashMap<String, String> content = new HashMap<String, String>();
					content.put("average", sp.getAverage() + "");
					content.put("max", sp.getMax() + "");
					content.put("min", sp.getMin() + "");
					map.put(sp.getDate(), content);
				}
				result.add(map);
			}

			vo.setData(result);
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
	@RequestMapping(value = "/countByNameAndId", method = RequestMethod.POST)
	public Boolean countByNameAndId(ReportForm f) {
		try {
			return reportService.countByAliasAndId(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public DataVO<Report> deletes(ReportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ReportForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findHostAndInstance", method = RequestMethod.POST)
	@RequiresPermissions("businessreport:read")
	@IgnoreProperties(
	allow = {
	    @AllowProperty(pojo = InstanceInfo.class, name = { "business","name","memSize","displayName","sysDiskSize","userDiskSize","cpuNum","uuid","vnets"}),
	    @AllowProperty(pojo = Business.class, name = { "name","uuid","colorpicker"}),
	    @AllowProperty(pojo = Vnet.class, name = { "uuid","name","ip","state"})
	})
	public DataVO<Object> findHostAndInstance(@RequestBody ReportForm f){
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(reportService.findInstanceByClusterUuid(f));
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
	@RequestMapping(value = "/margin", method = RequestMethod.POST)
	@RequiresPermissions("marginreport:read")
	public DataVO<Object> margin(@RequestBody ReportForm f) {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			vo.setT(reportService.findMargin(f));
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
	@RequestMapping(value = "/exportBusinessReport", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportBusinessReport( ReportForm f){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = reportService.exportBusinessReport(f, os);
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
	@RequestMapping(value = "/exportMarginReport", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportMarginReport( ReportForm f){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = reportService.exportMarginReport(f, os);
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
	@RequestMapping(value = "/instanceEveryDay", method = RequestMethod.POST)
	@RequiresPermissions("instanceEveryDayReport:read")
	public DataVO<Map<String, Object>> instanceEveryDay(
			@RequestBody ReportForm f) {
		DataVO<Map<String, Object>> vo = new DataVO<Map<String, Object>>();
		try {
			vo.setList(reportService.findInstanceCount(f));
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
	@RequestMapping(value = "/exportInstanceEveryDayReport", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportInstanceEveryDayReport( ReportForm f){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = reportService.exportInstanceCountReport(f, os);
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
	@RequestMapping(value = "/instanceResource", method = RequestMethod.POST)
	@RequiresPermissions("instanceResourceReport:read")
	public DataVO<Map<String, Map<String, Map<String, Object>>>> instanceResource(
			@RequestBody ReportForm f) {
		DataVO<Map<String, Map<String, Map<String, Object>>>> vo = new DataVO<Map<String, Map<String, Map<String, Object>>>>();
		try {
			vo.setT(reportService.findInstanceResource(f));
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
	@RequestMapping(value = "/exportConsumeReport", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportConsumeReport(ReportForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = reportService.exportConsumeReport(f, os);
			headers.setContentDispositionFormData("attachment", new String(
					filename.getBytes(), "iso-8859-1"));
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/resourceCount", method = RequestMethod.POST)
	@RequiresPermissions("marginreport:read")
	public DataVO count(@RequestBody ReportForm f) {
		DataVO vo = new DataVO();
		try {
			if(f.getCountTag() == 1){
				vo.setList(reportService.countByCluster(f));
			}else{
				vo.setList(reportService.countByAppSystem(f));
			}
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
	
}
