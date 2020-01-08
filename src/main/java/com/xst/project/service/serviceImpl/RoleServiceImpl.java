package com.xst.project.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.RoleMapper;
import com.xst.project.mapper.RoleMenuMapper;
import com.xst.project.mapper.UserMapper;
import com.xst.project.pojo.Role;
import com.xst.project.pojo.RoleMenu;
import com.xst.project.pojo.User;
import com.xst.project.service.RoleService;
import com.xst.project.util.DateToString;
import com.xst.project.util.StringUtil;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

      @Autowired
      private RoleMapper roleMapper;
      @Autowired
      private UserMapper userMapper;
      @Autowired
      private RoleMenuMapper roleMenuMapper;

    @Transactional
	@Override
	public Map<String, Object> update(Role role) {
		Map<String, Object> result = new HashMap<>();
		
		  if ( role.getOrderNo()==null||role.getOrderNo()=="") {
			  result.put("success", false);
	          result.put("msg", "排序号不能为空");
	          return result;
		} 
		//判断排序号是否符合规范
		 try {
			Integer.valueOf(role.getOrderNo());
		} catch (Exception e) {
			result.put("success", false);
	        result.put("msg", "请输入正确的排序号");
	        return result;
		}
		  Role olds = roleMapper.findById(role.getId());
		  
		  
		  String dateString = new DateToString().dateToSring();
		  role.setUpdateDateTime(dateString);
		  role = repalce(role, olds);
		  try {
			  	roleMapper.update(role);
	            result.put("success", true);
	            result.put("msg", "修改成功");
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", "修改失败");
			}
	    	return result;
	}


	@Override
	public Map<String, Object> list(Integer page, Integer limit) {
			Map<String, Object> result = new HashMap<String, Object>();
    	
    	// 使用Mybatis的PageHelper插件进行分页
    	// pagehelper本质上就是一个拦截器，当mybatis操作数据库的时候拦截器生效，将查询操作转化成分页查询
    	PageHelper.startPage(page, limit);
    	Page<Role> pageRole = (Page<Role>) roleMapper.findAll();
    	result.put("data", pageRole.getResult());
        result.put("count", pageRole.getTotal());
        result.put("code", 0);
        result.put("msg", "");
    	return result;
	}

	@Transactional
	@Override
	public Map<String, Object> add(Role role) {
		  Map<String, Object> result = new HashMap<>();
		  //System.out.println(role.getName());
		  if (role.getName()==null||role.getName()=="") {
			  result.put("success", false);
	          result.put("msg", "角色名称不能为空");
	          return result;
		} 
		//判断数据库是否存在该角色名，存在return  false
			Role r = roleMapper.findByName(role.getName());
			if (r!=null) {
				result.put("success", false);
		          result.put("msg", "已存在该角色名称");
		          return result;
			}
		  if ( role.getOrderNo()==null||role.getOrderNo()=="") {
			  result.put("success", false);
	          result.put("msg", "排序号不能为空");
	          return result;
		}
		 
		 try {
			Integer.valueOf(role.getOrderNo());
		} catch (Exception e) {
			result.put("success", false);
	        result.put("msg", "请输入正确的排序号");
	        return result;
		}
		  
		  String dateString = new DateToString().dateToSring();
		  role.setCreateDateTime(dateString);
		  role.setUpdateDateTime(dateString);
		  try {
			  	roleMapper.save(role);
	            result.put("success", true);
	            result.put("msg", "添加成功");
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", "添加失败");
			}
	    	return result;
	}



	@Transactional
	@Override
	public Map<String, Object> delete(String ids) {
   	 Map<String, Object> result = new HashMap<>();
   	try {
   		String[] idsStr = ids.split(",");
   		
   		for (String id : idsStr) {
   			Integer.parseInt(id);
   			User user = userMapper.findByRoleId(Integer.parseInt(id));
			if (user!=null) {
				result.put("success", false);
		        result.put("msg", "您删除的角色有用户正在使用");
		        return result;
			}
			roleMapper.delete(Integer.parseInt(id));
		}
           result.put("success", true);
           result.put("msg", "删除成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "删除失败");
		}
   	return result;
	}

	
	@Transactional
	@Override
	public Map<String, Object> updateMenu(Integer roleId, String menuIds) {

		Map<String, Object> result = new HashMap<>();
		try {
			String[] idsStr = menuIds.split(",");
			RoleMenu roleMenu ;
			//删除之前的菜单  根据 角色id
			roleMenuMapper.deleteByRoleId(roleId);
			//添加现在新的 （角色 对应的菜单 ）
			for (int i = 0; i < idsStr.length; i++) {
				if(StringUtil.isNotEmpty(idsStr[i])) {
					roleMenu = new RoleMenu();
					roleMenu.setRoleId(roleId);
					roleMenu.setMenuId(Integer.parseInt(idsStr[i]));
					roleMenuMapper.save(roleMenu);
				}
			}
			//修改角色 的更新 时间 
			Role role = roleMapper.findById(roleId);
			String date = new DateToString().dateToSring();
			role.setUpdateDateTime(date);
			roleMapper.update(role);
			
			result.put("success", true);
	        result.put("msg", "设置成功");
			
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "设置失败");
		}
		return result;
	}


	
	public Role repalce(Role news,Role olds) {
		if (news.getName()==null) {
			news.setName(olds.getName());	
		}
		if (news.getOrderNo()==null) {
			news.setOrderNo(olds.getOrderNo());	
		}
		if (news.getName()==null) {
			news.setName(olds.getName());	
		}
		if (news.getCreateDateTime()==null) {
			news.setCreateDateTime(olds.getCreateDateTime());	
		}
		if (news.getRemark()==null) {
			news.setRemark(olds.getRemark());	
		}
		return news;
	}






}
