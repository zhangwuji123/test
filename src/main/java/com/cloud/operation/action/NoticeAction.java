package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.NoticeForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Notice;
import com.cloud.operation.service.INoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeAction extends BaseAction<NoticeForm, Notice>{
	
	@Resource
	private INoticeService noticeService;

	@Override
	@ResponseBody
	@RequestMapping("/pageList")
	@RequiresPermissions("notice:read")
	public Page<Notice> pageList(@RequestBody NoticeForm f) {
		Page<Notice> page = new Page<Notice>();
		try{
			page = noticeService.findPageList(f);
			page.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			page.setState(ResponseState.FAILURE.getValue());
			page.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return page;
	}

	@Override
	public DataVO<Notice> list(NoticeForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping("/load")
	@RequiresPermissions("notice:read")
	public DataVO<Notice> load(@RequestBody NoticeForm f) {
		DataVO<Notice> vo = new DataVO<Notice>();
		try{
			vo.setT(noticeService.findById(f.getUuid()));
			vo.setState(ResponseState.SUCCESS.getValue());
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("notice:write")
	public DataVO<Notice> save(@RequestBody NoticeForm f) {
		DataVO<Notice> vo = new DataVO<Notice>();
		try{
			if(RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())){
				vo.setT(noticeService.insert(f));
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_NOTICE_SUCCESS);
			} else if(RequestAction.UPDATE.getValue().equalsIgnoreCase(f.getAction())){
				noticeService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_NOITCE_SUCCESS);
			}
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("notice:write")
	public DataVO<Notice> delete(@RequestBody NoticeForm f) {
		DataVO<Notice> vo = new DataVO<Notice>();
		try{
			noticeService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_NOITCE_SUCCESS);
		}catch(ServiceException e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			vo.setState(ResponseState.FAILURE.getValue());
			vo.setMessage(MessageUtil.SYS_EXCEPTION);
		}
		return vo;
	}

	@Override
	public DataVO<Notice> deletes(NoticeForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(NoticeForm f) {
		// TODO Auto-generated method stub
		return null;
	}

}
