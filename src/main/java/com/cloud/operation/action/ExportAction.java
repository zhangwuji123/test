package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.form.ExportForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.report.Report;
import com.cloud.operation.db.vo.report.StatMetric;
import com.cloud.operation.service.IExportService;

@Controller
@RequestMapping("/export")
public class ExportAction extends BaseAction<ExportForm, Byte[]> {
	
	@Resource
	private IExportService exportService;
	
	@ResponseBody
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	private ResponseEntity<byte[]> download(ExportForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = exportService.export(f, os);
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
	public Page<Byte[]> pageList(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Byte[]> list(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Byte[]> load(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Byte[]> save(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Byte[]> delete(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataVO<Byte[]> deletes(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ExportForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
