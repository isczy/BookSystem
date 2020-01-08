package com.xst.project.controller.admin;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xst.project.mapper.MenuMapper;
import com.xst.project.pojo.Menu;
import com.xst.project.service.MenuService;



@Controller
@RequestMapping("/admin/menu")
public class Admin_Menu_Controller {
	
	@Autowired
	private MenuService  menuService;
	@Autowired
	private MenuMapper menuMapper;
	
	
	/**
	 * /admin/menu/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(Menu menu){
		return menuService.add(menu);	
	}
	
	/**
	 * /admin/menu/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update( Menu menu )throws Exception {
		
		return menuService.update(menu);
	}
	
	/**
	 * /admin/menu/list
	 * @param page    默认1
	 * @param limit   数据多少
	 * @param pId   父节点  id
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			@RequestParam(value = "pId", required = false) Integer pId ) throws Exception {

		return menuService.list(page, limit,pId);	
	}
	
	
	/**
	 * /admin/menu/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids){
		
		System.out.println(ids);
        return menuService.delete(ids);
		
		
	/*	String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < idsStr.length; i++) {
			try {
				map.put("pId", Integer.parseInt(idsStr[i]));
				//看看下面有没有菜单   一同删除
				List<Menu> menuList = menuService.list(map, 0, 100);
				for(Menu menu:menuList) {
					menuDao.deleteById(menu.getId());
				}
				//看看下面有没有菜单   一同删除
				
				menuDao.deleteById(Integer.parseInt(idsStr[i]));
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "有角色正在使用些菜单");
				return result;
			}
			
		}
		
		result.put("success", true);
		return result;*/
	}
	
	
	
	
}
