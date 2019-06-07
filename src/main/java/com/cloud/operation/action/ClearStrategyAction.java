package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.ClearStrategyForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.ClearResourceRelation;
import com.cloud.operation.db.entity.business.ClearRule;
import com.cloud.operation.db.entity.business.ClearStrategy;
import com.cloud.operation.db.entity.business.JobParamInfo;
import com.cloud.operation.service.IClearStrategyService;

@Controller
@RequestMapping("/clearStrategy")
public class ClearStrategyAction extends BaseAction<ClearStrategyForm, ClearStrategy>{
	
	@Resource
	private IClearStrategyService clearStrateyService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:read")
	public Page<ClearStrategy> pageList(@RequestBody ClearStrategyForm f) {
		Page<ClearStrategy> page = new Page<ClearStrategy>();
		try {
			page = clearStrateyService.findPageList(f);
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
	@RequiresPermissions("clearStrategy:read")
	public DataVO<ClearStrategy> list(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			vo.setList(clearStrateyService.findList(f));
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
	@RequiresPermissions("clearStrategy:read")
	public DataVO<ClearStrategy> load(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			vo.setT(clearStrateyService.findById(f.getUuid()));
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> save(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			if(f.getAction().equalsIgnoreCase(RequestAction.INSERT.getValue())){
				clearStrateyService.insert(f);
				vo.setMessage("添加策略成功");
				vo.setState(ResponseState.SUCCESS.getValue());
			}else if(f.getAction().equalsIgnoreCase(RequestAction.UPDATE.getValue())){
				clearStrateyService.updateFromForm(f);
				vo.setMessage("修改策略成功");
				vo.setState(ResponseState.SUCCESS.getValue());
			}
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> delete(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("删除策略成功");
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
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> enable(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.enable(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("启用任务成功");
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
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> disable(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("停用任务成功");
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
	public DataVO<ClearStrategy> deletes(@RequestBody ClearStrategyForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validate(@RequestBody ClearStrategyForm f) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/paramList", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:read")
	public DataVO<JobParamInfo> paramList(@RequestBody ClearStrategyForm f) {
		DataVO<JobParamInfo> vo = new DataVO<JobParamInfo>();
		try {
			vo.setList(clearStrateyService.findJobParamList(f));
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
	@RequestMapping(value = "/addRulesParam", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> addRulesParam(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.addRulesParam(f);
			vo.setMessage("添加规则参数完成");
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
	@RequestMapping(value = "/modifyRulesParam", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> modifyRulesParam(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.modifyRulesParam(f);
			vo.setMessage("修改规则参数完成");
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
	@RequestMapping(value = "/removeRulesParam", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearStrategy> removeRulesParam(@RequestBody ClearStrategyForm f) {
		DataVO<ClearStrategy> vo = new DataVO<ClearStrategy>();
		try {
			clearStrateyService.removeRulesParam(f);
			vo.setMessage("删除规则参数完成");
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
	@RequestMapping(value = "/loadRulesParam", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:read")
	public DataVO<ClearRule> loadRulesParam(@RequestBody ClearStrategyForm f) {
		DataVO<ClearRule> vo = new DataVO<ClearRule>();
		try {
			vo.setT(clearStrateyService.loadRulesParam(f));
			vo.setMessage("获取规则参数");
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
	@RequestMapping(value = "/addServer", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearRule> addServer(@RequestBody ClearStrategyForm f) {
		DataVO<ClearRule> vo = new DataVO<ClearRule>();
		try {
			clearStrateyService.addServer(f);
			vo.setMessage("添加服务器");
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
	@RequestMapping(value = "/removeServer", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:write")
	public DataVO<ClearRule> removeServer(@RequestBody ClearStrategyForm f) {
		DataVO<ClearRule> vo = new DataVO<ClearRule>();
		try {
			clearStrateyService.removeServer(f);
			vo.setMessage("移除服务器");
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
	@RequestMapping(value = "/serverPageList", method = RequestMethod.POST)
	@RequiresPermissions("clearStrategy:read")
	public Page<ClearResourceRelation> serverPageList(@RequestBody ClearStrategyForm f) {
		Page<ClearResourceRelation> page = new Page<ClearResourceRelation>();
		try {
			page = clearStrateyService.findServerPageList(f);
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
}
