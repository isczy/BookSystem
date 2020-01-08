package com.xst.project.controller.houtai;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xst.project.mapper.MenuMapper;
import com.xst.project.pojo.Menu;
import com.xst.project.service.MenuService;


@Controller
@RequestMapping("/houtai/menu")
public class HouTai_Menu_Controller {
	
	

	@Autowired
	private MenuMapper  menuMapper;
	
	/**
	 *  /houtai/menu/manage?pId=-1
	 */
	@RequiresPermissions(value= {"菜单管理"})
	@RequestMapping("/manage")
	public ModelAndView manage(@RequestParam(value = "pId", required = false) Integer pId) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "菜单管理");
		mav.addObject("title", "菜单管理");
		mav.addObject("pId", pId);
		mav.setViewName("/admin/page/menu/menu_manage");
		return mav;
	}
	
	/**
	 * /houtai/menu/add
	 */
	@RequiresPermissions(value= {"菜单管理"})
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "pId", required = false) Integer pId) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/menu/add");
		mav.addObject("pId", pId);
		mav.setViewName("/admin/page/menu/add_update");
		return mav;
	}
	
	/**
	 * /houtai/menu/edit?id=1
	 */
	@RequiresPermissions(value= {"菜单管理"})
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		Menu menu = menuMapper.findById(id);
		mav.addObject("menu", menu);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/menu/update?id=" + id);
		mav.addObject("pId", menu.getPId());
		mav.setViewName("/admin/page/menu/add_update");
		return mav;
	}
	
	
	
	
	
	
}
