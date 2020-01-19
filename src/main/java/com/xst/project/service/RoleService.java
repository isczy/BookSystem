package com.xst.project.service;


import java.util.Map;
import com.xst.project.pojo.Role;

/**
 * ******************************************************************
 * @brief      角色查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:39:00
 * @author     ChangZiYang
 *******************************************************************
 */
public interface RoleService {
	
	Map<String, Object> add(Role role);
	
	Map<String, Object> update(Role role);

	Map<String, Object> list(Integer page,Integer limit);
	
    
	Map<String, Object> delete(String roleIds);
	
	Map<String, Object> updateMenu(Integer roleId,String menuIds);
    

}
