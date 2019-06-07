package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.MessageForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Message;
import com.cloud.operation.db.entity.business.TaskInfo;
import com.cloud.operation.event.EventManager;
import com.cloud.operation.service.IMessageService;
import com.cloud.operation.service.util.SystemWebSocketHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/message")
public class MessageAction extends BaseAction<MessageForm, Message>{

	private static final Logger logger = LoggerFactory
			.getLogger(MessageAction.class);
	
	@Resource
	private IMessageService messageService;

	@Bean
	public SystemWebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}

	@RequestMapping(value="/push",method=RequestMethod.POST)  
	@ResponseBody
	public DataVO<String> auditing(@RequestBody MessageForm f) {
		DataVO<String> vo = new DataVO<String>();
		try {
			logger.info("web接收talk的消息：" + new GsonBuilder().create().toJson(f));
			if(StringUtils.isNoneBlank(f.getUuid()) && !f.getUuid().equals("1")){
				TaskInfo taskInfo = messageService.setMessageToOperationUser(f);
				taskInfo.setUser(null);
				taskInfo.setTaskMsgInfos(null);
				String json = (new Gson()).toJson(taskInfo,TaskInfo.class);
				systemWebSocketHandler().sendMessageToUser(new TextMessage(json));
				vo.setState(ResponseState.SUCCESS.getValue());
			}else{
				//systemWebSocketHandler().sendMessage(new TextMessage(new Gson().toJson(f)));
			}
			
			//消息广播--sc
//			CloudEvent event = new CloudEvent(f.getResourceUuid(),CodeDefine.parse(f.getCmdCode()),f.getState()==2,f);
//			eventManager.publishEvent(event);
			
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
	
	@RequestMapping(value="/pmAlert",method=RequestMethod.GET)  
	@ResponseBody
	public DataVO<String> pmAlert(MessageForm f) {
		DataVO<String> vo = new DataVO<String>();
		try {
			String json = messageService.loadPmInfo(f.getUuid());
			systemWebSocketHandler().sendMessage(new TextMessage(json));
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
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("message:read")
	public Page<Message> pageList(@RequestBody MessageForm f) {
		Page<Message> page = new Page<Message>();
		try {
			page = messageService.findPageList(f);
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
	public DataVO<Message> list(MessageForm f) {
		return null;
	}

	@Override
	public DataVO<Message> load(MessageForm f) {
		return null;
	}

	@Override
	public DataVO<Message> save(MessageForm f) {
		return null;
	}

	@Override
	public DataVO<Message> delete(MessageForm f) {
		return null;
	}

	@Override
	public DataVO<Message> deletes(MessageForm f) {
		return null;
	}

	@Override
	public Boolean validate(MessageForm f) {
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/indexList", method = RequestMethod.POST)
	public DataVO<Message> indexList(@RequestBody MessageForm f) {
		DataVO<Message> vo = new DataVO<Message>();
		try {
			vo.setList(messageService.indexList(f));
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

}
