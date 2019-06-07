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
import com.cloud.operation.core.form.CiRelationForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Ci;
import com.cloud.operation.db.entity.business.CiApplication;
import com.cloud.operation.db.entity.business.CiCluster;
import com.cloud.operation.db.entity.business.CiDatabase;
import com.cloud.operation.db.entity.business.CiGdhk;
import com.cloud.operation.db.entity.business.CiJf;
import com.cloud.operation.db.entity.business.CiLogicServer;
import com.cloud.operation.db.entity.business.CiMiddleware;
import com.cloud.operation.db.entity.business.CiNetworkDevice;
import com.cloud.operation.db.entity.business.CiPhysicalServer;
import com.cloud.operation.db.entity.business.CiRack;
import com.cloud.operation.db.entity.business.CiRelation;
import com.cloud.operation.db.entity.business.CiStorage;
import com.cloud.operation.db.entity.business.CiTopoJf;
import com.cloud.operation.service.ICiRelationService;

@Controller
@RequestMapping("/ciRelation")
public class CiRelationAction extends BaseAction<CiRelationForm, CiRelation> {

	@Resource
	private ICiRelationService ciRelationService;
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public Page<CiRelation> pageList(@RequestBody CiRelationForm f) {
		Page<CiRelation> page = new Page<CiRelation>();
		try {
			page = ciRelationService.findPageList(f);
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
	public DataVO<CiRelation> list(CiRelationForm f) {
		return null;
	}

	@Override
	public DataVO<CiRelation> load(CiRelationForm f) {
		return null;
	}

	@Override
	public DataVO<CiRelation> save(CiRelationForm f) {
		return null;
	}

	@Override
	public DataVO<CiRelation> delete(CiRelationForm f) {
		return null;
	}

	@Override
	public DataVO<CiRelation> deletes(CiRelationForm f) {
		return null;
	}

	@Override
	public Boolean validate(CiRelationForm f) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:write")
	public DataVO<CiRelation> imports(@RequestBody CiRelationForm f) {
		DataVO<CiRelation> vo = new DataVO<CiRelation>();
		try {
			ciRelationService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("配置关系导入成功");
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
	public ResponseEntity<byte[]> exports(CiRelationForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = ciRelationService.exports(f, os);
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
	@RequiresPermissions("ciRelation:write")
	public DataVO<CiRelation> empty() {
		DataVO<CiRelation> vo = new DataVO<CiRelation>();
		try {
			ciRelationService.empty();
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("配置关系清空成功");
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
	@RequestMapping(value = "/findJfList", method = RequestMethod.POST)
	public DataVO<CiTopoJf> findJfList() {
		DataVO<CiTopoJf> vo = new DataVO<CiTopoJf>();
		try {
			vo.setList(ciRelationService.findJfList());
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
	@RequestMapping(value = "/findRackByJfUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRack> findRackByJfUuid(@RequestBody CiRelationForm f) {
		DataVO<CiRack> vo = new DataVO<CiRack>();
		try {
			vo.setList(ciRelationService.findRackByJfUuid(f.getJfUuid()));
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
	@RequestMapping(value = "/findGdhkByJfUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiGdhk> findGdhkByJfUuid(@RequestBody CiRelationForm f) {
		DataVO<CiGdhk> vo = new DataVO<CiGdhk>();
		try {
			vo.setList(ciRelationService.findGdhkByJfUuid(f.getJfUuid()));
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
	@RequestMapping(value = "/findPhysicalServerByJfUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiPhysicalServer> findPhysicalServerByJfUuid(@RequestBody CiRelationForm f) {
		DataVO<CiPhysicalServer> vo = new DataVO<CiPhysicalServer>();
		try {
			vo.setList(ciRelationService.findPhysicalServerByJfUuid(f.getJfUuid()));
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
	@RequestMapping(value = "/findNetworkDeviceByJfUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiNetworkDevice> findNetworkDeviceByJfUuid(@RequestBody CiRelationForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			vo.setList(ciRelationService.findNetworkDeviceByJfUuid(f.getJfUuid()));
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
	@RequestMapping(value = "/findStorageByJfUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiStorage> findStorageByJfUuid(@RequestBody CiRelationForm f) {
		DataVO<CiStorage> vo = new DataVO<CiStorage>();
		try {
			vo.setList(ciRelationService.findStorageByJfUuid(f.getJfUuid()));
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
	@RequestMapping(value = "/findJfByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiJf> findJfByRackUuid(@RequestBody CiRelationForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciRelationService.findJfByRackUuid(f.getRackUuid()));
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
	@RequestMapping(value = "/findPhysicalServerByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiPhysicalServer> findPhysicalServerByRackUuid(@RequestBody CiRelationForm f) {
		DataVO<CiPhysicalServer> vo = new DataVO<CiPhysicalServer>();
		try {
			vo.setList(ciRelationService.findPhysicalServerByRackUuid(f.getRackUuid()));
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
	@RequestMapping(value = "/findNetworkDeviceByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiNetworkDevice> findNetworkDeviceByRackUuid(@RequestBody CiRelationForm f) {
		DataVO<CiNetworkDevice> vo = new DataVO<CiNetworkDevice>();
		try {
			vo.setList(ciRelationService.findNetworkDeviceByRackUuid(f.getRackUuid()));
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
	@RequestMapping(value = "/findStorageByRackUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiStorage> findStorageByRackUuid(@RequestBody CiRelationForm f) {
		DataVO<CiStorage> vo = new DataVO<CiStorage>();
		try {
			vo.setList(ciRelationService.findStorageByRackUuid(f.getRackUuid()));
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
	@RequestMapping(value = "/findJfByGdhkUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiJf> findJfByGdhkUuid(@RequestBody CiRelationForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciRelationService.findJfByGdhkUuid(f.getGdhkUuid()));
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
	@RequestMapping(value = "/findJfByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiJf> findJfByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciRelationService.findJfByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findRackByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRack> findRackByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiRack> vo = new DataVO<CiRack>();
		try {
			vo.setT(ciRelationService.findRackByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findLogicServerByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiLogicServer> findLogicServerByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiLogicServer> vo = new DataVO<CiLogicServer>();
		try {
			vo.setList(ciRelationService.findLogicServerByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findApplicationByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiApplication> findApplicationByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiApplication> vo = new DataVO<CiApplication>();
		try {
			vo.setList(ciRelationService.findApplicationByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findMiddlewareByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiMiddleware> findMiddlewareByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiMiddleware> vo = new DataVO<CiMiddleware>();
		try {
			vo.setList(ciRelationService.findMiddlewareByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findDatabaseByPhysicalServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiDatabase> findDatabaseByPhysicalServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			vo.setList(ciRelationService.findDatabaseByPhysicalServerUuid(f.getPhysicalServerUuid()));
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
	@RequestMapping(value = "/findJfByNetworkDeviceUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiJf> findJfByNetworkDeviceUuid(@RequestBody CiRelationForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciRelationService.findJfByNetworkDeviceUuid(f.getNetworkDeviceUuid()));
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
	@RequestMapping(value = "/findRackByNetworkDeviceUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRack> findRackByNetworkDeviceUuid(@RequestBody CiRelationForm f) {
		DataVO<CiRack> vo = new DataVO<CiRack>();
		try {
			vo.setT(ciRelationService.findRackByNetworkDeviceUuid(f.getNetworkDeviceUuid()));
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
	@RequestMapping(value = "/findJfByStorageUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiJf> findJfByStorageUuid(@RequestBody CiRelationForm f) {
		DataVO<CiJf> vo = new DataVO<CiJf>();
		try {
			vo.setT(ciRelationService.findJfByStorageUuid(f.getStorageUuid()));
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
	@RequestMapping(value = "/findRackByStorageUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRack> findRackByStorageUuid(@RequestBody CiRelationForm f) {
		DataVO<CiRack> vo = new DataVO<CiRack>();
		try {
			vo.setT(ciRelationService.findRackByStorageUuid(f.getStorageUuid()));
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
	@RequestMapping(value = "/findApplicationByClusterUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiApplication> findApplicationByClusterUuid(@RequestBody CiRelationForm f) {
		DataVO<CiApplication> vo = new DataVO<CiApplication>();
		try {
			vo.setList(ciRelationService.findApplicationByClusterUuid(f.getClusterUuid()));
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
	@RequestMapping(value = "/findLogicServerByClusterUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiLogicServer> findLogicServerByClusterUuid(@RequestBody CiRelationForm f) {
		DataVO<CiLogicServer> vo = new DataVO<CiLogicServer>();
		try {
			vo.setList(ciRelationService.findLogicServerByClusterUuid(f.getClusterUuid()));
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
	@RequestMapping(value = "/findPhysicalServerByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiPhysicalServer> findPhysicalServerByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiPhysicalServer> vo = new DataVO<CiPhysicalServer>();
		try {
			vo.setT(ciRelationService.findPhysicalServerByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findStorageByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiStorage> findStorageByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiStorage> vo = new DataVO<CiStorage>();
		try {
			vo.setT(ciRelationService.findStorageByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findClusterByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiCluster> findClusterByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiCluster> vo = new DataVO<CiCluster>();
		try {
			vo.setT(ciRelationService.findClusterByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findApplicationByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiApplication> findApplicationByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiApplication> vo = new DataVO<CiApplication>();
		try {
			vo.setList(ciRelationService.findApplicationByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findMiddlewareByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiMiddleware> findMiddlewareByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiMiddleware> vo = new DataVO<CiMiddleware>();
		try {
			vo.setList(ciRelationService.findMiddlewareByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findDatabaseByLogicServerUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiDatabase> findDatabaseByLogicServerUuid(@RequestBody CiRelationForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			vo.setList(ciRelationService.findDatabaseByLogicServerUuid(f.getLogicServerUuid()));
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
	@RequestMapping(value = "/findPhysicalServerByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiPhysicalServer> findPhysicalServerByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiPhysicalServer> vo = new DataVO<CiPhysicalServer>();
		try {
			vo.setList(ciRelationService.findPhysicalServerByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findStorageByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiStorage> findStorageByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiStorage> vo = new DataVO<CiStorage>();
		try {
			vo.setList(ciRelationService.findStorageByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findClusterByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiCluster> findClusterByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiCluster> vo = new DataVO<CiCluster>();
		try {
			vo.setList(ciRelationService.findClusterByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findLogicServerByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiLogicServer> findLogicServerByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiLogicServer> vo = new DataVO<CiLogicServer>();
		try {
			vo.setList(ciRelationService.findLogicServerByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findMiddlewareByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiMiddleware> findMiddlewareByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiMiddleware> vo = new DataVO<CiMiddleware>();
		try {
			vo.setList(ciRelationService.findMiddlewareByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findDatabaseByApplicationUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiDatabase> findDatabaseByApplicationUuid(@RequestBody CiRelationForm f) {
		DataVO<CiDatabase> vo = new DataVO<CiDatabase>();
		try {
			vo.setList(ciRelationService.findDatabaseByApplicationUuid(f.getApplicationUuid()));
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
	@RequestMapping(value = "/findHostByMiddlewareUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<Ci> findHostByMiddlewareUuid(@RequestBody CiRelationForm f) {
		DataVO<Ci> vo = new DataVO<Ci>();
		try {
			vo.setT(ciRelationService.findHostByMiddlewareUuid(f.getMiddlewareUuid()));
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
	@RequestMapping(value = "/findClusterByMiddlewareUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiCluster> findClusterByMiddlewareUuid(@RequestBody CiRelationForm f) {
		DataVO<CiCluster> vo = new DataVO<CiCluster>();
		try {
			vo.setT(ciRelationService.findClusterByMiddlewareUuid(f.getMiddlewareUuid()));
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
	@RequestMapping(value = "/findApplicationByMiddlewareUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiApplication> findApplicationByMiddlewareUuid(@RequestBody CiRelationForm f) {
		DataVO<CiApplication> vo = new DataVO<CiApplication>();
		try {
			vo.setList(ciRelationService.findApplicationByMiddlewareUuid(f.getMiddlewareUuid()));
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
	@RequestMapping(value = "/findHostByDatabaseUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<Ci> findHostByDatabaseUuid(@RequestBody CiRelationForm f) {
		DataVO<Ci> vo = new DataVO<Ci>();
		try {
			vo.setT(ciRelationService.findHostByDatabaseUuid(f.getDatabaseUuid()));
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
	@RequestMapping(value = "/findClusterByDatabaseUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiCluster> findClusterByDatabaseUuid(@RequestBody CiRelationForm f) {
		DataVO<CiCluster> vo = new DataVO<CiCluster>();
		try {
			vo.setT(ciRelationService.findClusterByDatabaseUuid(f.getDatabaseUuid()));
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
	@RequestMapping(value = "/findApplicationByDatabaseUuid", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiApplication> findApplicationByDatabaseUuid(@RequestBody CiRelationForm f) {
		DataVO<CiApplication> vo = new DataVO<CiApplication>();
		try {
			vo.setList(ciRelationService.findApplicationByDatabaseUuid(f.getDatabaseUuid()));
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
	@RequestMapping(value = "/findRelationByCiA", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRelation> findRelationByCiA(@RequestBody CiRelationForm f) {
		DataVO<CiRelation> vo = new DataVO<CiRelation>();
		try {
			vo.setList(ciRelationService.findRelationByCiA(f.getCiA()));
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
	@RequestMapping(value = "/findRelationByCiB", method = RequestMethod.POST)
	@RequiresPermissions("ciRelation:read")
	public DataVO<CiRelation> findRelationByCiB(@RequestBody CiRelationForm f) {
		DataVO<CiRelation> vo = new DataVO<CiRelation>();
		try {
			vo.setList(ciRelationService.findRelationByCiB(f.getCiB()));
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
