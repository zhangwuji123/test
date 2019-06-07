package com.cloud.operation.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.InstanceForm;
import com.cloud.operation.core.form.VnetForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.InstanceInfo;
import com.cloud.operation.db.entity.business.Vnet;
import com.cloud.operation.service.IInstanceService;
import com.cloud.operation.service.IVnetService;

/**
 * 
 * @ClassName: VnetController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangg
 * @date 2015年5月19日 下午4:38:44
 *
 */
@Controller
@RequestMapping("/vnet")
public class VnetAction extends BaseAction<VnetForm, Vnet> {

	@Resource
	private IVnetService vnetService;

	@Resource
	private IInstanceService instanceService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public Page<Vnet> pageList(@RequestBody VnetForm f) {
		Page<Vnet> page = new Page<Vnet>();
		try {
			page = vnetService.findPageList(f);
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
	public DataVO<Vnet> list(@RequestBody VnetForm f) {
		DataVO<Vnet> vo = new DataVO<Vnet>();
		try {
			vo.setList(vnetService.findList(f));
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
	public DataVO<Vnet> load(@RequestBody VnetForm f) {
		DataVO<Vnet> vo = new DataVO<Vnet>();
		try {
			vo.setT(vnetService.findById(f.getUuid()));
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
	public DataVO<Vnet> save(@RequestBody VnetForm f) {
		DataVO<Vnet> vo = new DataVO<Vnet>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				vnetService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ZONE_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				vnetService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ZONE_SUCCESS);
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
	public DataVO<Vnet> delete(@RequestBody VnetForm f) {
		DataVO<Vnet> vo = new DataVO<Vnet>();
		try {
			vnetService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ZONE_SUCCESS);
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

	@RequestMapping("/getTemplateWithVnet")
	public ResponseEntity<byte[]> getTemplateWithVnet(InstanceForm f)
			throws IOException {
		logger.info("导出报表...");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		List<InstanceInfo> ins = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			headers.setContentDispositionFormData("attachment", "VNET_TEMPLATE.xls");
			ins = instanceService.getInstanceByVnetTemplate(f);
			vnetService.createTemplateWithVnet(ins, os);
			return new ResponseEntity<byte[]>(os.toByteArray(), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
				HttpStatus.CREATED);

	}

	@RequestMapping("/saveDataByExcel")
	public String SaveDataByExcel(
			@RequestParam(value = "file", required = false) MultipartFile[] file)
			throws IOException {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			int cnt = vnetService.readExcel(file[0]);
			vo.setMessage("" + cnt);
			vo.setState(ResponseState.SUCCESS.getValue());
			return "redirect:../resource";
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return "redirect:../resource";
	}

	@Override
	public DataVO<Vnet> deletes(VnetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(VnetForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
