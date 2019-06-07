package com.cloud.operation.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.operation.controller.vo.ModuleExt;
import com.cloud.operation.controller.vo.RoleExt;
import com.cloud.operation.core.action.BaseAction;
import com.cloud.operation.core.constants.ModuleVisibleConstant;
import com.cloud.operation.core.exception.ServiceException;
import com.cloud.operation.core.form.RoleForm;
import com.cloud.operation.core.page.Page;
import com.cloud.operation.core.utils.MessageUtil;
import com.cloud.operation.core.vo.DataVO;
import com.cloud.operation.db.entity.business.Organization;
import com.cloud.operation.db.entity.business.OrganizationUserRelation;
import com.cloud.operation.db.entity.business.Vdc;
import com.cloud.operation.db.entity.main.Module;
import com.cloud.operation.db.entity.main.Role;
import com.cloud.operation.db.entity.main.User;
import com.cloud.operation.db.entity.main.UserRole;
import com.cloud.operation.service.IModuleService;
import com.cloud.operation.service.IRoleService;
import com.cloud.operation.service.IUserRoleService;
import com.cloud.operation.service.util.AllowProperty;
import com.cloud.operation.service.util.IgnoreProperties;

/**
 * 角色控制器
 * 
 * @author syd
 *
 */
@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction<RoleForm, Role> {

	@Resource
	private IRoleService roleService;

	@Resource
	private IUserRoleService userRoleService;

	@Resource
	private IModuleService moduleService;

	@Override
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	public Page<Role> pageList(@RequestBody RoleForm f) {
		Page<Role> page = new Page<Role>();
		try {
			page = roleService.findPageList(f);
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
	public DataVO<Role> list(@RequestBody RoleForm f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	public DataVO<Role> load(@RequestBody RoleForm f) {
		DataVO<Role> vo = new DataVO<Role>();
		try {
			vo.setT(roleService.findById(f.getUuid()));
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
	@RequiresPermissions("role:write")
	public DataVO<Role> save(@RequestBody RoleForm f) {
		DataVO<Role> vo = new DataVO<Role>();
		try {
			if (RequestAction.INSERT.getValue().equalsIgnoreCase(f.getAction())) {
				roleService.insert(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.ADD_ROLE_SUCCESS);
			} else if (RequestAction.UPDATE.getValue()
					.equalsIgnoreCase(f.getAction())) {
				roleService.updateFromForm(f);
				vo.setState(ResponseState.SUCCESS.getValue());
				vo.setMessage(MessageUtil.EDIT_ROLE_SUCCESS);
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
	@RequiresPermissions("role:write")
	public DataVO<Role> delete(@RequestBody RoleForm f) {
		DataVO<Role> vo = new DataVO<Role>();
		try {
			roleService.deleteById(f.getUuid());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ROLE_SUCCESS);
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
	@RequestMapping(value = "/roleList", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	@IgnoreProperties(allow = {
			@AllowProperty(pojo = Role.class, name = { "name", "description",
					"uuid" }) })
	public DataVO<Role> roleList(@RequestBody String uuid) {
		DataVO<Role> vo = new DataVO<Role>();
		try {
			List<Role> list = roleService.findList(new RoleForm());
//			if (uuid == null || "".equalsIgnoreCase(uuid)) {
//				for (Role r : list) {
//					data.add(new RoleExt(r));
//				}
//			} else {
//				Set<String> roleSet = new HashSet<String>();
//				List<UserRole> userRoleList = userRoleService.find(uuid);
//				for (UserRole userRole : userRoleList) {
//					roleSet.add(userRole.getRole().getUuid());
//				}
//				for (Role r : list) {
//					boolean check = roleSet.contains(r.getUuid());
//					data.add(new RoleExt(r, check));
//				}
//			}
			vo.setData(list);
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
	@RequestMapping(value = "/moduleList", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	public DataVO<ModuleExt> moduleList(String uuid) {
		DataVO<ModuleExt> vo = new DataVO<ModuleExt>();
		try {
			List<ModuleExt> data = new ArrayList<ModuleExt>();
			Module f = new Module();
			f.setVisible(ModuleVisibleConstant.SHOW);
			List<Module> list = moduleService.findList(f);
			if (uuid == null || "".equalsIgnoreCase(uuid)) {
				for (Module m : list) {
					data.add(new ModuleExt(m));
				}
			} else {
				Set<String> permissionSet = new HashSet<String>(roleService.findById(
						uuid).getPermissionList());
				for (Module m : list) {
					boolean write = permissionSet.contains(m.getSn() + ":write");
					boolean read = permissionSet.contains(m.getSn() + ":read");
					data.add(new ModuleExt(m, read, write));
				}
			}
			vo.setData(data);
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
	@RequestMapping(value = "/modules", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	public DataVO<Module> modules(@RequestBody RoleForm f) {
		DataVO<Module> vo = new DataVO<Module>();
		try {
			vo.setList(moduleService.findList(new Module()));
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
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	@RequiresPermissions("role:write")
	public DataVO<Role> deletes(@RequestBody RoleForm f) {
		// TODO Auto-generated method stub
		DataVO<Role> vo = new DataVO<Role>();
		try {
			roleService.deletes(f.getUuids());
			vo.setState(ResponseState.SUCCESS.getValue());
			vo.setMessage(MessageUtil.DEL_ROLE_SUCCESS);
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
	@RequestMapping(value = "/findUsers", method = RequestMethod.POST)
	@RequiresPermissions("role:read")
	public DataVO<User> findUsers(@RequestBody RoleForm f) {
		DataVO<User> vo = new DataVO<User>();
		try {
			vo.setList(roleService.findUsers(f));
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Boolean validate(RoleForm f) {
		try {
			return roleService.countByNameAndId(f);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
