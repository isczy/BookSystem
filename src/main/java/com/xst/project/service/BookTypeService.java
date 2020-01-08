package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.BookType;

public interface BookTypeService {
	
	 Map<String, Object> add(BookType bookType);
	
	 Map<String, Object> update(BookType bookType);
	 
	 Map<String, Object> list(Integer page,Integer limit);
	
	 Map<String, Object> deleteByIds(String ids);
}
