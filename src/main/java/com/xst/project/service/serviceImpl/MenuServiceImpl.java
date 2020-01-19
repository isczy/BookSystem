package com.xst.project.service.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.MenuMapper;
import com.xst.project.mapper.RoleMenuMapper;
import com.xst.project.pojo.Menu;
import com.xst.project.pojo.RoleMenu;
import com.xst.project.service.MenuService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service("menuService")
public  class MenuServiceImpl implements MenuService {
	
	private static Logger log = Logger.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuMapper  menuMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Override
	public List<Menu> findAll(Integer pId){
		 return menuMapper.findAll(pId);
	}
	
	/**
	 * ************************************************
	 * 功能实现描述：添加菜单
	 * @param menu
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:51:27
	 * @author modify:
	 */
	@Transactional
	@Override
	public Map<String, Object> add(Menu menu) {
		 Map<String, Object> result = new HashMap<>();
		 try {
			  	menuMapper.add(menu);
	            //添加菜单成功后要立即给顶级管理员设置菜单
			  	RoleMenu roleMenu = new RoleMenu();
			  	roleMenu.setMenuId(menu.getId());
			  	roleMenu.setRoleId(1);
			  	roleMenuMapper.save(roleMenu);
			  	
	            result.put("success", true);
	            result.put("msg", "添加成功");
	            
			} catch (Exception e) {
				log.error("MenuServiceImpl >> add-添加菜单失败："+e.getMessage());
				result.put("success", false);
				result.put("msg", "添加失败");
			}
	    	return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：更新菜单
	 * @param menu
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:51:13
	 * @author modify:
	 */
	@Override
	public Map<String, Object> update(Menu menu) {
		Map<String, Object> result = new HashMap<>();
		try {
			Menu olds = menuMapper.findById(menu.getId());
			menu = repalce(menu, olds);
			
			menuMapper.update(menu);
            result.put("success", true);
            result.put("msg", "修改成功");
		} catch (Exception e) {
			log.error("MenuServiceImpl >> update-更新菜单失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "修改失败");
		}
    	return result;
	}
	
	/**
	 * ************************************************
	 * 功能实现描述：分页查询菜单
	 * @param page
	 * @param limit
	 * @param pId
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:52:15
	 * @author modify:
	 */
	@Override
	public Map<String,Object> list(Integer page, Integer limit,Integer pId) {
		//查找所有的父节点，pid为-1的
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageHelper.startPage(page, limit);
	    	Page<Menu> pageMenu = (Page<Menu>) menuMapper.findAll(pId);
	    	result.put("data", pageMenu.getResult());
	        result.put("count", pageMenu.getTotal());
	        result.put("code", 0);
	        result.put("msg", "");
		} catch (Exception e) {
			log.error("MenuServiceImpl >> list-分页查询菜单失败："+e.getMessage());
		}	
    	return result;	
	}
	
	/**
	 * ************************************************
	 * 功能实现描述：批量删除菜单
	 * @param ids
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:53:05
	 * @author modify:
	 */
	@Transactional
	@Override
	public Map<String, Object> delete(String ids) {
		Map<String, Object> result = new HashMap<>();
		try {
			String[] idsStr = ids.split(",");
			for (String id : idsStr) {
				int menuId = Integer.parseInt(id);
				// 判断是否有角色在使用该菜单，有return false；
				List<RoleMenu> roleMenuList = roleMenuMapper.findByMenuId(menuId);

				if (roleMenuList.size() > 0) {
					result.put("success", false);
					result.put("msg", "您删除的菜单有角色在使用");
					return result;
				}
				// 看看下面有没有菜单 一同删除
				List<Menu> menuList = menuMapper.findAll(menuId);
				for (Menu menu : menuList) {
					// 判断子菜单是否有角色在使用
					List<RoleMenu> roleMenuList2 = roleMenuMapper.findByMenuId(menu.getId());
					if (roleMenuList2.size() > 0) {
						result.put("success", false);
						result.put("msg", "您删除的菜单的子菜单有角色在使用");
						return result;
					}
					menuMapper.delete(menu.getId());
				}
				menuMapper.delete(menuId);
			}
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			log.error("MenuServiceImpl >> delete-删除菜单失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "删除失败");
		}
		return result;
	}


	/**
	 * @param news      当前更新的数据
	 * @param olds   源数据  以前的数据
	 * @return  news
	 */
	public Menu repalce(Menu news,Menu olds){
		
		if(news.getPId()==null){
			news.setPId(olds.getPId());
		}
		if(news.getName()==null){
			news.setName(olds.getName());
		}
		if(news.getUrl()==null){
			news.setUrl(olds.getUrl());
		}
		if(news.getState()==null){
			news.setState(olds.getState());
		}
		if(news.getIcon()==null){
			news.setIcon(olds.getIcon());
		}
		
		if(news.getPermissions()==null){
			news.setPermissions(olds.getPermissions());
		}
		if(news.getType()==null){
			news.setType(olds.getType());
		}
		if(news.getDivId()==null){
			news.setDivId(olds.getDivId());
		}
		return news;
	}


}
