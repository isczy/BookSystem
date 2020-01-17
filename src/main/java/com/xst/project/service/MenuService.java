package com.xst.project.service;

import java.util.List;
import java.util.Map;

import com.xst.project.pojo.Menu;

/**
 * ******************************************************************
 * @brief      菜单查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:37:00
 * @author     ChangZiYang
 *******************************************************************
 */
public interface MenuService {
	
	Map<String, Object> add(Menu menu);
	
	Map<String, Object> update(Menu menu);
	
	List<Menu> findAll(Integer pId);
	
	Map<String,Object> list(Integer page,Integer pageSize,Integer pId);
	
	Map<String, Object> delete(String menuIds);
	
}
