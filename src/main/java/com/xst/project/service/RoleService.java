package com.xst.project.service;


import java.util.Map;
import com.xst.project.pojo.Role;


public interface RoleService {
	
	Map<String, Object> add(Role role);
	
	Map<String, Object> update(Role role);

	Map<String, Object> list(Integer page,Integer limit);
	
    
	Map<String, Object> delete(String roleIds);
	
	Map<String, Object> updateMenu(Integer roleId,String menuIds);
    

}
