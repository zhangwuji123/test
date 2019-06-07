package com.cloud.operation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cloud.operation.core.constants.SysconfigParamNameConstant;
import com.cloud.operation.core.constants.UploadFileTypeConstant;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.SystemSkinForm;
import com.cloud.operation.core.utils.ConfigUtil;
import com.cloud.operation.core.utils.DateUtils;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.utils.ConstantUtil.ResponseState;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.service.ISysconfigService;
import com.google.gson.Gson;

/**
 * 文件上传的控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadAction {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadAction.class);
	
	@Autowired
	private HttpServletRequest request;

	@Resource
	private ISysconfigService sysconfigService;

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartFile multipartFile, Integer action,
			String name) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		DataVO<Object> vo = new DataVO<Object>();
		// 判断文件大小
		if (multipartFile.getSize() > 1024 * 1024) {
			vo.setState(ResponseState.FAILURE);
			vo.setMessage("sizeError");
			return new Gson().toJson(vo);
		}
		int i = multipartFile.getOriginalFilename().lastIndexOf(".");
		String extName = multipartFile.getOriginalFilename().substring(i + 1);
		//上传图片文件
		if (action == null
				|| action.intValue() == UploadFileTypeConstant.IMAGE_FILE) {
			if (!("jpg".equalsIgnoreCase(extName)
					|| "jpeg".equalsIgnoreCase(extName)
					|| "png".equalsIgnoreCase(extName) || "gif"
						.equalsIgnoreCase(extName))) {
				vo.setState(ResponseState.FAILURE);
				vo.setMessage("formatError");
				return new Gson().toJson(vo);
			}
		}
		//上传excel文件
		else if (action.intValue() == UploadFileTypeConstant.XLS_FILE) {
			if (!("xlsx".equalsIgnoreCase(extName) || "xls"
					.equalsIgnoreCase(extName))) {
				vo.setState(ResponseState.FAILURE);
				vo.setMessage("formatError");
				return new Gson().toJson(vo);
			}
		}
		//上传logo ico文件
		else if (action.intValue() == UploadFileTypeConstant.LOGO_ICO_FILE) {
			if (!"ico".equalsIgnoreCase(extName)) {
				vo.setState(ResponseState.FAILURE);
				vo.setMessage("formatError");
				return new Gson().toJson(vo);
			}
		}
		//上传logo png文件
		else if (action.intValue() == UploadFileTypeConstant.LOGO_PNG_FILE) {
			if (!"png".equalsIgnoreCase(extName)) {
				vo.setState(ResponseState.FAILURE);
				vo.setMessage("formatError");
				return new Gson().toJson(vo);
			}
		} 
		//上传自服务logo png文件
		else if (action.intValue() == UploadFileTypeConstant.CONSOLE_LOGO_PNG_FILE) {
			if (!"png".equalsIgnoreCase(extName)) {
				vo.setState(ResponseState.FAILURE);
				vo.setMessage("formatError");
				return new Gson().toJson(vo);
			}
		}
		// 上传脚本文件
		else if (action.intValue() == UploadFileTypeConstant.SCRIPT_FILE) {

		}
		// 文件路径不存在就创建新的路径
		File filePath = new File(ConfigUtil.getFileUploadPath());
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File xlsPath = new File(ConfigUtil.getFileUploadPath() + "/xls");
		if (!xlsPath.exists()) {
			xlsPath.mkdir();
		}
		File logoPath = new File(ConfigUtil.getFileUploadPath() + "/logo");
		if (!logoPath.exists()) {
			logoPath.mkdir();
		}
		File scriptPath = new File(ConfigUtil.getFileUploadPath() + "/script");
		if (!scriptPath.exists()) {
			scriptPath.mkdir();
		}
		if (action != null) {
			if (action.intValue() == UploadFileTypeConstant.XLS_FILE) {
				// 生成随机的文件名避免重复
				String fileName = "cloud_" + sdf.format(DateUtils.getCurrentDate())
						+ '.' + extName;
				// + "_" + multipartFile.getOriginalFilename();
				// 上传文件
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/xls/" + fileName));
				vo.setMessage(fileName);
			} else if (action.intValue() == UploadFileTypeConstant.LOGO_ICO_FILE) {
				// 上传文件
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/logo/logo.ico"));
			} else if (action.intValue() == UploadFileTypeConstant.LOGO_PNG_FILE) {
				// 上传文件
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/logo/logo.png"));
			} else if (action.intValue() == UploadFileTypeConstant.CONSOLE_LOGO_PNG_FILE) {
				// 上传文件
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/logo/console_logo.png"));
			} else if (action.intValue() == UploadFileTypeConstant.SCRIPT_FILE) {
				String fileName = "";
				if (StringUtils.isNotBlank(name)) {
					fileName = name + "_" + sdf.format(DateUtils.getCurrentDate()) + '.'
							+ extName;
				} else {
					fileName = "cloud_" + sdf.format(DateUtils.getCurrentDate()) + '.'
							+ extName;
				}
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/script/" + fileName));
				vo.setMessage(fileName);
			} else {
				// 上传文件
				String fileName = "FILE_" + sdf.format(DateUtils.getCurrentDate())
						+ java.util.UUID.randomUUID() + '.' + extName;
				// "_"
				// + multipartFile.getOriginalFilename();
				vo.setMessage(fileName);
				multipartFile.transferTo(new File(filePath.getAbsolutePath()
						+ "/" + fileName));
			}
		} else {
			// 上传文件
			String fileName = "FILE_" + sdf.format(DateUtils.getCurrentDate()) + '.' +  extName;
			vo.setMessage(fileName);
			multipartFile.transferTo(new File(filePath.getAbsolutePath() + "/"
					+ fileName));
		}
		vo.setState(ResponseState.SUCCESS);
		return new Gson().toJson(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/relationUpload", method = RequestMethod.POST)
	public String relationUpload(MultipartFile relationImageFile)
			throws Exception {
		DataVO<Object> vo = new DataVO<Object>();
		// 判断文件大小
		if (relationImageFile.getSize() > 1024 * 1024) {
			vo.setState(ResponseState.FAILURE);
			vo.setMessage("sizeError");
			return new Gson().toJson(vo);
		}
		// 生成随机的文件名避免重复
		int i = relationImageFile.getOriginalFilename().lastIndexOf(".");
		String extName = relationImageFile.getOriginalFilename().substring(i + 1);
		String fileName = "FILE_" + DateUtils.getCurTimeMillis() + "_"
				+  java.util.UUID.randomUUID()+ "." + extName;
		
		
		vo.setMessage(fileName);
		// 判断文件扩展名
		if (!("jpg".equalsIgnoreCase(extName)
				|| "jpeg".equalsIgnoreCase(extName)
				|| "png".equalsIgnoreCase(extName) || "gif"
					.equalsIgnoreCase(extName))) {
			vo.setState(ResponseState.FAILURE);
			vo.setMessage("formatError");
			return new Gson().toJson(vo);
		}
		// 文件路径不存在就创建新的路径
		File filePath = new File(ConfigUtil.getFileUploadPath());
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		// 上传文件
		relationImageFile.transferTo(new File(filePath.getAbsolutePath() + "/"
				+ fileName ));
		vo.setState(ResponseState.SUCCESS);
		return new Gson().toJson(vo);
	}

	@ResponseBody
	@RequestMapping(value = "/uploads", method = RequestMethod.POST)
	public String upload(CommonsMultipartFile[] files) throws Exception {
		System.out.println(files.toString());
		DataVO<Object> vo = new DataVO<Object>();
		vo.setState(ResponseState.SUCCESS);
		return new Gson().toJson(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateSkin", method = RequestMethod.POST)
	public DataVO<Object> updateSkin(@RequestBody SystemSkinForm f) {
		DataVO<Object> vo = new DataVO<Object>();
		try {
			String imagePath = sysconfigService.findByParamName(
					SysconfigParamNameConstant.FILE_UPLOAD_ABSOLUTE_PATH)
					.getValue();
			String adminFile = null;
			String mainFile = null;
			String viewFile = null;
			if ("admin".equals(f.getType()) && "blue".equals(f.getColor())) {
				adminFile = imagePath + "css/skin/blue/admin.css";
			} else if ("admin".equals(f.getType()) && "red".equals(f.getColor())) {
				adminFile = imagePath + "css/skin/red/admin.css";
			} else if ("self".equals(f.getType()) && "blue".equals(f.getColor())) {
				mainFile = imagePath + "css/skin/blue/main.css";
				viewFile = imagePath + "css/skin/blue/view.css";
			} else if ("self".equals(f.getType()) && "red".equals(f.getColor())) {
				mainFile = imagePath + "css/skin/red/main.css";
				viewFile = imagePath + "css/skin/red/view.css";
			} else {
				throw new ServiceException("皮肤设置错误");
			}
			if (StringUtils.isNotBlank(adminFile)) {
				copyFile(adminFile, imagePath + "css/admin.css");
			}
			if (StringUtils.isNotBlank(mainFile)) {
				copyFile(mainFile, imagePath + "css/main.css");
			}
			if (StringUtils.isNotBlank(viewFile)) {
				copyFile(viewFile, imagePath + "css/view.css");
			}
			vo.setState(ResponseState.SUCCESS);
			vo.setMessage("皮肤设置成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE);
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE);
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}
	
	private void copyFile(String file1, String file2) throws ServiceException {
		logger.info("源文件：" + file1);
		logger.info("目标文件：" + file2);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel ic = null;
		FileChannel oc = null;
		try {
			File f1 = new File(file1);
			File f2 = new File(file2);
			if (!f2.exists()) {
				f2.createNewFile();
			}
			fis = new FileInputStream(f1);
			fos = new FileOutputStream(f2);
			ic = fis.getChannel();
			oc = fos.getChannel();
			ByteBuffer b = ByteBuffer.allocate((int) ic.size());
			ic.read(b);
			b.flip();
			oc.write(b);
			oc.force(false);
		} catch (Exception e) {
			throw new ServiceException("文件复制错误");
		} finally {
			try {
				if (oc != null) {
					oc.close();
				}
				if (ic != null) {
					ic.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				throw new ServiceException("文件复制错误");
			}
		}
	}

}
