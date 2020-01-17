package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.BookType;

/**
 * ******************************************************************
 * @brief     图书类别查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:35:43
 * @author     ChangZiYang
 *******************************************************************
 */
public interface BookTypeService {
	
	 Map<String, Object> add(BookType bookType);
	
	 Map<String, Object> update(BookType bookType);
	 
	 Map<String, Object> list(Integer page,Integer limit);
	
	 Map<String, Object> deleteByIds(String ids);
}
