package com.cloud.operation.action;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.UserForm;
import com.cloud.operation.core.form.UserRoleForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.service.IUserRoleService;
import com.cloud.operation.service.IUserService;
import com.cloud.operation.service.shiro.ShiroDbRealm;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction<UserForm, User> {

	@Resource(name="userService")
	private IUserService userService;

	@Resource
	private IUserRoleService userRoleService;

	@Autowired
	private ShiroDbRealm shiroRealm;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("user:read")
	public Page<User> pageList(@RequestBody UserForm f) {
		Page<User> page = new Page<User>();
		try {
			page = userService.findPageList(f);
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
	@RequiresPermissions("user:read")
	public DataVO<User> list(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setList(userService.findList(f));
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
	@RequestMapping(value = "/llist", method = RequestMethod.POST)
	@RequiresPermissions("user:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = User.class, name = { "uuid","username", "realname","oaName" })
			})
	public DataVO<User> llist(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setList(userService.findList(f));
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
	@RequiresPermissions("user:read")
	public DataVO<User> load(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setT(userService.findById(f.getUuid()));
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
	@RequiresPermissions("user:write")
	public DataVO<User> save(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				userService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_USER_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				f.setPlainPassword(null);
				userService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_USER_SUCCESS);
			} else if("audit".equalsIgnoreCase(f.getAction())){
				userService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.AUDIT_USER_SUCCESS);
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
	@RequiresPermissions("user:write")
	public DataVO<User> delete(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_USER_SUCCESS);
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
	@RequestMapping(value = "/grantRole", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> grantRole(@RequestBody UserRoleForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userRoleService.insert(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("分配角色成功");
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
	@RequestMapping(value = "/userUpdatePasswd", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> userUpdatePasswd(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.updatePassword(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("用户重置密码成功");
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
	@RequestMapping(value = "/updateSelfPasswd", method = RequestMethod.POST)
	public DataVO<User> updateSelfPasswd(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			f.setUuid(shiroRealm.getCurrentUserUuid());
			userService.updatePassword(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("用户重置密码成功");
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
	@RequestMapping(value = "/findCurrentUser", method = RequestMethod.POST)
	public DataVO<User> findCurrentUser() {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setT(userService.findById(shiroRealm.getCurrentUserUuid()));
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
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> updateInfo(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			f.setUuid(shiroRealm.getCurrentUserUuid());
			userService.updateInfo(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("修改信息成功");
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
	@RequiresPermissions("user:write")
	public DataVO<User> deletes(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_USER_SUCCESS);
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@RequiresPermissions("user:read")
	public Boolean validate(UserForm f) {
		try {
			return userService.validate(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setApproveLevel", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> setApproveLevel(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.setApproveLevel(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.APU_USER_SUCCESS);
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
	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> lock(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.lock(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.LOCK_USER_SUCCESS);
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
	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> unlock(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.unlock(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.UNLOCK_USER_SUCCESS);
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
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	@RequiresPermissions("user:write")
	public DataVO<User> recharge(@RequestBody UserForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			userService.recharge(f);
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage("用户充值成功");
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
