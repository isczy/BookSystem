package com.xst.project.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xst.project.mapper.MenuMapper;
import com.xst.project.mapper.RoleMenuMapper;
import com.xst.project.pojo.Menu;
import com.xst.project.pojo.Role;
import com.xst.project.pojo.RoleMenu;
import com.xst.project.pojo.User;
import com.xst.project.service.RoleService;
import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/role")
public class Admin_Role_Controller {


    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @RequestMapping("/add")
    public Map<String, Object> add(Role role){
    	
            return roleService.add(role);
    }

    @RequestMapping("/update")
    public Map<String, Object> update(Role role){

            return roleService.update(role);
        
    }


    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit
                                   ) throws Exception { 
        return roleService.list(page, limit);
     
    }


    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids) throws Exception {
    	System.out.println(ids);
        return roleService.delete(ids);
    	
    }
    
    /**
	 * 这个接口是 layui tree组件用  用的。授权 的时候 用的，点对勾用
	 * /admin/role/getCheckedMenuData?roleId=12
	 */
	@ResponseBody
	@RequestMapping("/getCheckedMenuData")
	public List<JSONObject>  getCheckedMenuData(@RequestParam(value = "roleId", required = false) Integer roleId){
		
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		List<Menu> menuList = menuMapper.findByPId(-1);
		
		for (Menu menu : menuList) {
			JSONObject node = new JSONObject();
			node.put("id", menu.getId());
			node.put("title", menu.getName());
			node.put("spread", true);
			
			node.put("children", getChildren(menu.getId(),roleId));
			list.add(node);
		}
		return list;
	}
	
	/**
	 * 辅助方法  辅助 上面的 admin_main（getCheckedMenuData）
	 * @param pId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> getChildren(Integer pId, Integer roleId){
		List<Menu> menuList = menuMapper.findByPId(pId);
		List<JSONObject>  list =  new ArrayList<JSONObject>();
		for (Menu menu : menuList) {
				JSONObject node = new JSONObject();
				node.put("id", menu.getId());
				node.put("title", menu.getName());
				
				//判断 当前菜单     当前角色有没有拥有。
				RoleMenu roleMenu=	roleMenuMapper.findByRoleIdAndMenuId(roleId, menu.getId());
				if(roleMenu!=null){
					node.put("checked", true);
				}
				//判断 当前菜单     当前角色有没有拥有。
				
				list.add(node);
		}
		return list;
	}
	
	/**
	 * @param url    /admin/role/updateMenu
	 * @param roleId  =  12
	 * @param menuIds  1,2,5,6,8,5
	 */
	@ResponseBody
	@RequestMapping("/updateMenu")
	public Map<String, Object> updateMenu(Integer roleId, String menuIds){
		 
		return roleService.updateMenu(roleId, menuIds);
		
	}
}