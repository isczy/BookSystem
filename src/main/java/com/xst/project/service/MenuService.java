package com.xst.project.service;

import java.util.List;
import java.util.Map;

import com.xst.project.pojo.Menu;



public interface MenuService {
	
	Map<String, Object> add(Menu menu);
	
	Map<String, Object> update(Menu menu);
	
	List<Menu> findAll(Integer pId);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public Map<String,Object> list(Integer page,Integer pageSize,Integer pId);
	
	
	Map<String, Object> delete(String menuIds);
	
}
