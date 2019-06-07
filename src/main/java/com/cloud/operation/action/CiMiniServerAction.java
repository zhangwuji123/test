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
import com.cloud.operation.core.form.CiMiniServerForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CiExtProperty;
import com.cloud.operation.db.entity.business.CiMiniServer;
import com.cloud.operation.db.entity.business.CiMiniServerRecord;
import com.cloud.operation.service.ICiMiniServerService;

/**
 * 配置存储控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/ciMiniServer")
public class CiMiniServerAction extends
		BaseAction<CiMiniServerForm, CiMiniServer> {

	@Resource
	private ICiMiniServerService ciMiniServerService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciMiniServer:read")
	public Page<CiMiniServer> pageList(@RequestBody CiMiniServerForm f) {
		Page<CiMiniServer> page = new Page<CiMiniServer>();
		try {
			page = ciMiniServerService.findPageList(f);
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
	@RequiresPermissions("ciMiniServer:read")
	public DataVO<CiMiniServer> list(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			vo.setList(ciMiniServerService.findList(f));
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
	@RequiresPermissions("ciMiniServer:read")
	public DataVO<CiMiniServer> load(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			vo.setT(ciMiniServerService.findById(f.getUuid()));
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
	@RequiresPermissions("ciMiniServer:write")
	public DataVO<CiMiniServer> save(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				ciMiniServerService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加小型机成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				ciMiniServerService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改小型机成功");
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
	@RequiresPermissions("ciMiniServer:write")
	public DataVO<CiMiniServer> delete(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			ciMiniServerService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除小型机成功");
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
	@RequiresPermissions("ciMiniServer:write")
	public DataVO<CiMiniServer> deletes(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			ciMiniServerService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除小型机成功");
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
	public Boolean validate(CiMiniServerForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("ciMiniServer:write")
	public DataVO<CiMiniServer> imports(@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			ciMiniServerService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("小型机导入成功");
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
	public ResponseEntity<byte[]> exports(
			CiMiniServerForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciMiniServerService.exports(f, os);
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
	@RequiresPermissions("ciMiniServer:write")
	public DataVO<CiMiniServer> empty() {
		DataVO<CiMiniServer> vo = new DataVO<CiMiniServer>();
		try {
			ciMiniServerService.empty();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("小型机清空成功");
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
	@RequiresPermissions("ciMiniServer:read")
	public DataVO<CiMiniServerRecord> recordList(
			@RequestBody CiMiniServerForm f) {
		DataVO<CiMiniServerRecord> vo = new DataVO<CiMiniServerRecord>();
		try {
			vo.setData(ciMiniServerService.recordList(f));
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
	@RequiresPermissions("ciMiniServer:read")
	public Page<CiMiniServerRecord> recordPageList(@RequestBody CiMiniServerForm f) {
		Page<CiMiniServerRecord> page = new Page<CiMiniServerRecord>();
		try {
			page = ciMiniServerService.recordPageList(f);
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
			String filename = ciMiniServerService.recordExports(os);
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
	@RequiresPermissions("ciMiniServer:read")
	public DataVO<CiExtProperty> initExtProperty() {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setList(ciMiniServerService.initExtProperty());
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
