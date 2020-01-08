package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.Book;

public interface BookService {
	
	Map<String, Object> add(Book book);
	
	Map<String, Object> update(Book book);
	
	Map<String, Object> list(Integer page,Integer limit);
	
	Map<String, Object> deleteByIds(String ids);
	
	Map<String, Object> search(Integer page,Integer limit,String name);

	
	
}
