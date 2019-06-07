package com.cloud.operation.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ColumnsForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Columns;
import com.cloud.operation.service.IColumnsService;

/**
 * 自定义列控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/columns")
public class ColumnsAction extends BaseAction<ColumnsForm, Columns> {

	@Resource
	private IColumnsService columnsService;

	@Override
	public Page<Columns> pageList(ColumnsForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public DataVO<Columns> list(@RequestBody ColumnsForm f) {
		DataVO<Columns> vo = new DataVO<Columns>();
		try {
			vo.setList(columnsService.findList(f));
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
	public DataVO<Columns> load(@RequestBody ColumnsForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public DataVO<Columns> save(@RequestBody ColumnsForm f) {
		DataVO<Columns> vo = new DataVO<Columns>();
		try {
			columnsService.updateFromForm(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("列表正在调整，请稍后");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	public DataVO<Columns> delete(@RequestBody ColumnsForm f) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/findSort", method = RequestMethod.POST)
	public DataVO<Columns> findSort() {
		DataVO<Columns> vo = new DataVO<Columns>();
		try {
			vo.setList(columnsService.findSort());
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
	public DataVO<Columns> deletes(ColumnsForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(ColumnsForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
