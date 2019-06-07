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
import com.cloud.operation.core.form.CiDatabaseForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.CiDatabase;
import com.cloud.operation.db.entity.business.CiDatabaseRecord;
import com.cloud.operation.db.entity.business.CiExtProperty;
import com.cloud.operation.service.ICiDatabaseService;

/**
 * 配置存储控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/ciDatabase")
public class CiDatabaseAction extends BaseAction<CiDatabaseForm, CiDatabase> {

	@Resource
	private ICiDatabaseService ciDatabaseService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciDatabase:read")
	public Page<CiDatabase> pageList(@RequestBody CiDatabaseForm f) {
		Page<CiDatabase> page = new Page<CiDatabase>();
		try {
			page = ciDatabaseService.findPageList(f);
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
	@RequiresPermissions("ciDatabase:read")
	public DataVO<CiDatabase> list(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			vo.setList(ciDatabaseService.findList(f));
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
	@RequiresPermissions("ciDatabase:read")
	public DataVO<CiDatabase> load(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			vo.setT(ciDatabaseService.findById(f.getUuid()));
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
	@RequiresPermissions("ciDatabase:write")
	public DataVO<CiDatabase> save(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				ciDatabaseService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("添加数据库成功");
			} else if (RequestAction.UPDATE.getValue().equalsIgnoreCase(
					f.getAction())) {
				ciDatabaseService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage("修改数据库成功");
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
	@RequiresPermissions("ciDatabase:write")
	public DataVO<CiDatabase> delete(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			ciDatabaseService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除数据库成功");
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
	@RequiresPermissions("ciDatabase:write")
	public DataVO<CiDatabase> deletes(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			ciDatabaseService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除数据库成功");
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
	public Boolean validate(CiDatabaseForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("ciDatabase:write")
	public DataVO<CiDatabase> imports(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			ciDatabaseService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("数据库导入成功");
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
	public ResponseEntity<byte[]> exports(CiDatabaseForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciDatabaseService.exports(f, os);
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
	@RequiresPermissions("ciDatabase:write")
	public DataVO<CiDatabase> empty() {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			ciDatabaseService.empty();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("数据库清空成功");
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
	@RequiresPermissions("ciDatabase:read")
	public DataVO<CiDatabaseRecord> recordList(@RequestBody CiDatabaseForm f) {
		DataVO<CiDatabaseRecord> vo = new DataVO<CiDatabaseRecord>();
		try {
			vo.setData(ciDatabaseService.recordList(f));
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
	@RequiresPermissions("ciDatabase:read")
	public Page<CiDatabaseRecord> recordPageList(@RequestBody CiDatabaseForm f) {
		Page<CiDatabaseRecord> page = new Page<CiDatabaseRecord>();
		try {
			page = ciDatabaseService.recordPageList(f);
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
			String filename = ciDatabaseService.recordExports(os);
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
	@RequiresPermissions("ciDatabase:read")
	public DataVO<CiExtProperty> initExtProperty() {
		DataVO<CiExtProperty> vo = new DataVO<CiExtProperty>();
		try {
			vo.setList(ciDatabaseService.initExtProperty());
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
