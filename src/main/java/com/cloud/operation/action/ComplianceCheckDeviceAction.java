package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
import com.cloud.operation.core.action.IAction.RequestAction;
import com.cloud.operation.core.action.IAction.ResponseState;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ComplianceCheckClassifyForm;
import com.cloud.operation.core.form.ComplianceCheckDeviceForm;
import com.cloud.operation.core.form.OrderForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.dao.business.IComplianceCheckClassifyDao;
import com.cloud.operation.db.entity.business.ComplianceCheckClassify;
import com.cloud.operation.db.entity.business.ComplianceCheckDevice;
import com.cloud.operation.db.entity.business.ComplianceCheckResult;
import com.cloud.operation.db.entity.business.MiniMachine;
import com.cloud.operation.service.IComplianceCheckClassifyService;
import com.cloud.operation.service.IComplianceCheckDeviceService;

/**
 * 健康检查设备控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/complianceCheckDevice")
public class ComplianceCheckDeviceAction extends BaseAction<ComplianceCheckDeviceForm, ComplianceCheckDevice> {

	@Resource
	private IComplianceCheckDeviceService complianceCheckDeviceService;
	
	
	@Resource
	private IComplianceCheckClassifyService complianceCheckClassifyService;
	
	

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:read")
	public Page<ComplianceCheckDevice> pageList(@RequestBody ComplianceCheckDeviceForm f) {
		Page<ComplianceCheckDevice> page = new Page<ComplianceCheckDevice>();
		try {
			page = complianceCheckDeviceService.findPageList(f);
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
	public DataVO<ComplianceCheckDevice> list(ComplianceCheckDeviceForm f) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:read")
	public DataVO<ComplianceCheckDevice> load(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try{
			vo.setT(complianceCheckDeviceService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}


	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:write")
	public DataVO<ComplianceCheckDevice> save(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				complianceCheckDeviceService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ComplianceCheckDevice_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				complianceCheckDeviceService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ComplianceCheckDevice_SUCCESS);
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
	@RequiresPermissions("complianceCheckDevice:write")
	public DataVO<ComplianceCheckDevice> delete(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try {
			//complianceCheckDeviceService.deleteById(f.getUuid());
			complianceCheckDeviceService.deleteByForm(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ComplianceCheckDevice_SUCCESS);
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
	@RequiresPermissions("complianceCheckDevice:write")
	public DataVO<ComplianceCheckDevice> deletes(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try {
			//complianceCheckDeviceService.deleteById(f.getUuid());
			complianceCheckDeviceService.deleteByuuidList(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ComplianceCheckDevice_SUCCESS);
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
	public Boolean validate(ComplianceCheckDeviceForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 执行按钮
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkComplianceCheckDevice", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:write")
	public DataVO<ComplianceCheckDevice> checkComplianceCheckDevice(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try {
			complianceCheckDeviceService.checkComplianceCheckDevice(f);
			vo.setMessage(MessageUtil.CHECK_ComplianceCheckDevice_SUCCESS);
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 查询ComplianceCheckResult列表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/complianceResultList", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:read")
	public DataVO<ComplianceCheckResult> checkComplianceCheckDeviceList(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckResult> vo = new DataVO<ComplianceCheckResult>();
		try{
			vo.setList(complianceCheckDeviceService.findCheckComplianceCheckDeviceList(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 查询单个ComplianceCheckResult
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/complianceResult", method = RequestMethod.POST)
	@RequiresPermissions("ComplianceCheckDevice:read")
	public DataVO<ComplianceCheckResult> queryCheckComplianceCheckDevice(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckResult> vo = new DataVO<ComplianceCheckResult>();
		try{
			vo.setT(complianceCheckDeviceService.findCheckComplianceCheckDevice(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	/**
	 * 查询分类表
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/classifyList", method = RequestMethod.POST)
	@RequiresPermissions("complianceCheckDevice:read")
	public DataVO<ComplianceCheckClassify> ClassifyList(@RequestBody ComplianceCheckClassifyForm f) {
		
		DataVO<ComplianceCheckClassify> vo = new DataVO<ComplianceCheckClassify>();
		try {
			vo.setList(complianceCheckClassifyService.findList(f));
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
	
	/**
	 * 导出
	 * @param f
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/exports", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exports(ComplianceCheckDeviceForm f) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			String filename = complianceCheckDeviceService.exports(f,os);
			headers.setContentDispositionFormData("attachment", new String(
					filename.getBytes(), "iso-8859-1"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	public DataVO<ComplianceCheckDevice> imports(@RequestBody ComplianceCheckDeviceForm f) {
		DataVO<ComplianceCheckDevice> vo = new DataVO<ComplianceCheckDevice>();
		try {
			complianceCheckDeviceService.imports(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("检查设备导入成功");
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
