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
import com.cloud.operation.core.form.CiNetworkDeviceForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CiExtProperty;
import com.cloud.operation.db.entity.business.CiNetworkDevice;
import com.cloud.operation.db.entity.business.CiNetworkDeviceRecord;
import com.cloud.operation.service.ICiNetworkDeviceService;

/**
 * 配置机房控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/ciNetworkDevice")
public class CiNetworkDeviceAction extends BaseAction<CiNetworkDeviceForm, CiNetworkDevice> {

	@Resource
	private ICiNetworkDeviceService ciNetworkDeviceService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciNetworkDevice:read")
	public Page<CiNetworkDevice> pageList(@RequestBody CiNetworkDeviceForm f) {
		Page<CiNetworkDevice> page = new Page<CiNetworkDevice>();
		try {
			page = ciNetworkDeviceService.findPageList(f);
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
	@RequiresPermissions("ciNetworkDevice:read")
	public DataVO<CiNetworkDevice> list(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			vo.setList(ciNetworkDeviceService.findList(f));
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
	@RequiresPermissions("ciNetworkDevice:read")
	public DataVO<CiNetworkDevice> load(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			vo.setT(ciNetworkDeviceService.findById(f.getUuid()));
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
	@RequiresPermissions("ciNetworkDevice:write")
	public DataVO<CiNetworkDevice> save(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				ciNetworkDeviceService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加网络设备成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				ciNetworkDeviceService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改网络设备成功");
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
	@RequiresPermissions("ciNetworkDevice:write")
	public DataVO<CiNetworkDevice> delete(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			ciNetworkDeviceService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除网络设备成功");
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
	@RequiresPermissions("ciNetworkDevice:write")
	public DataVO<CiNetworkDevice> deletes(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			ciNetworkDeviceService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除网络设备成功");
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
	public Boolean validate(CiNetworkDeviceForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("ciNetworkDevice:write")
	public DataVO<CiNetworkDevice> imports(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			ciNetworkDeviceService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("网络设备导入成功");
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
	public ResponseEntity<byte[]> exports(CiNetworkDeviceForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciNetworkDeviceService.exports(f, os);
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
	@RequiresPermissions("ciNetworkDevice:write")
	public DataVO<CiNetworkDevice> empty() {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			ciNetworkDeviceService.empty();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("网络设备清空成功");
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
	@RequiresPermissions("ciNetworkDevice:read")
	public DataVO<CiNetworkDeviceRecord> recordList(@RequestBody CiNetworkDeviceForm f) {
		DataVO<CiNetworkDeviceRecord> vo = new DataVO<CiNetworkDeviceRecord>();
		try {
			vo.setData(ciNetworkDeviceService.recordList(f));
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
	@RequiresPermissions("ciNetworkDevice:read")
	public Page<CiNetworkDeviceRecord> recordPageList(@RequestBody CiNetworkDeviceForm f) {
		Page<CiNetworkDeviceRecord> page = new Page<CiNetworkDeviceRecord>();
		try {
			page = ciNetworkDeviceService.recordPageList(f);
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
			String filename = ciNetworkDeviceService.recordExports(os);
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
	@RequiresPermissions("ciNetworkDevice:read")
	public DataVO<CiExtProperty> initExtProperty() {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setList(ciNetworkDeviceService.initExtProperty());
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
