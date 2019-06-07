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
import com.cloud.operation.core.form.CiJfForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CiExtProperty;
import com.cloud.operation.db.entity.business.CiJf;
import com.cloud.operation.db.entity.business.CiJfRecord;
import com.cloud.operation.service.ICiJfService;

/**
 * 配置机房控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/ciJf")
public class CiJfAction extends BaseAction<CiJfForm, CiJf> {

	@Resource
	private ICiJfService ciJfService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:read")
	public Page<CiJf> pageList(@RequestBody CiJfForm f) {
		Page<CiJf> page = new Page<CiJf>();
		try {
			page = ciJfService.findPageList(f);
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
	@RequiresPermissions("ciJf:read")
	public DataVO<CiJf> list(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setList(ciJfService.findList(f));
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
	@RequiresPermissions("ciJf:read")
	public DataVO<CiJf> load(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciJfService.findById(f.getUuid()));
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
	@RequiresPermissions("ciJf:write")
	public DataVO<CiJf> save(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				ciJfService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加机房成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				ciJfService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改机房成功");
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
	@RequiresPermissions("ciJf:write")
	public DataVO<CiJf> delete(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			ciJfService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除机房成功");
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
	@RequiresPermissions("ciJf:write")
	public DataVO<CiJf> deletes(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			ciJfService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除机房成功");
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
	public Boolean validate(CiJfForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:write")
	public DataVO<CiJf> imports(@RequestBody CiJfForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			ciJfService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("机房导入成功");
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
	@RequestMapping(value = "/exports", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exports(CiJfForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciJfService.exports(f, os);
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
	@RequestMapping(value = "/empty", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:write")
	public DataVO<CiJf> empty() {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			ciJfService.empty();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("机房清空成功");
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
	@RequestMapping(value = "/recordList", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:read")
	public DataVO<CiJfRecord> recordList(@RequestBody CiJfForm f) {
		DataVO<CiJfRecord> vo = new DataVO<CiJfRecord>();
		try {
			vo.setData(ciJfService.recordList(f));
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
	@RequestMapping(value = "/recordPageList", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:read")
	public Page<CiJfRecord> recordPageList(@RequestBody CiJfForm f) {
		Page<CiJfRecord> page = new Page<CiJfRecord>();
		try {
			page = ciJfService.recordPageList(f);
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
	@RequestMapping(value = "/recordExports", method = RequestMethod.POST)
	public ResponseEntity<byte[]> recordExports() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciJfService.recordExports(os);
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
	@RequestMapping(value = "/initExtProperty", method = RequestMethod.POST)
	@RequiresPermissions("ciJf:read")
	public DataVO<CiExtProperty> initExtProperty() {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setList(ciJfService.initExtProperty());
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
