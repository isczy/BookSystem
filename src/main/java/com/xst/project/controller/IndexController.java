package com.xst.project.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xst.project.mapper.MenuMapper;
import com.xst.project.mapper.RoleMenuMapper;
import com.xst.project.pojo.Menu;
import com.xst.project.pojo.RoleMenu;
import com.xst.project.pojo.User;
import com.xst.project.service.MenuService;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ******************************************************************
 * @brief      系统的入口
 * @version    0.1
 * @date       2020年1月17日 下午3:25:29
 * @author     ChangZiYang
 *******************************************************************
 */
@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     *# 请求首页
     */
    @RequestMapping("/")
    public String  index_1(HttpServletResponse res, HttpServletRequest req) throws Exception {
        return "redirect:/login";
    }

    /**
     *   #请求首页  /index
     */
    @RequestMapping("/index")
    public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
        return "redirect:/login";
    }


    /**
     *    /login
     *    #后台 用户电脑登陆
     */
    @RequestMapping("/login")
    public ModelAndView login(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/pc/login/login");
        return mv;
    }

	/**
	 * # 后台主页
	 */
	@RequestMapping("/admin/main")
	public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req,HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser"); 
		if(currentUser.getRoleId()==null){
			return mv;
		}
		//根据当前的用户   对应的角色。 展示菜单 
			//查询父节点的菜单
		List<Menu> menuList =  menuService.findAll(-1);
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		for (Menu menu : menuList) {
			//当前下当前用户的角色  有没有这个菜单 
			RoleMenu roleMenu=	roleMenuMapper.findByRoleIdAndMenuId(currentUser.getRoleId(), menu.getId());
			if(roleMenu!=null) {
				JSONObject node = new JSONObject();
				node.put("id", menu.getId());
				node.put("text", menu.getName());
				node.put("url", menu.getUrl());
				node.put("type", menu.getType());
				node.put("icon", menu.getIcon());
				node.put("divId", menu.getDivId());
				node.put("children", getChildren(menu.getId(),currentUser.getRoleId()));
				list.add(node);
			}
		}
		mv.addObject("treeList", list);
		mv.setViewName("/admin/main");
		return mv;
	}
	
	
	/**
	 * 辅助方法  辅助 上面的 admin_main（getCheckedTreeMenu）
	 * @param pId
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> getChildren(Integer pId, Integer roleId)  throws Exception {
		
		//根据pid查询对应的菜单
		List<Menu> menuList = menuMapper.findByPId(pId);
		
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		for (Menu menu : menuList) {
			RoleMenu roleMenu=	roleMenuMapper.findByRoleIdAndMenuId(roleId, menu.getId());
			if(roleMenu!=null){
				JSONObject node = new JSONObject();
				node.put("id", menu.getId());
				node.put("text", menu.getName());
				node.put("url", menu.getUrl());
				node.put("type", menu.getType());
				node.put("icon", menu.getIcon());
				node.put("divId", menu.getDivId());
				list.add(node);
			}
		}
		return list;
	}
	
	



}
