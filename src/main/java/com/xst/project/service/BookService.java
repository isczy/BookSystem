package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.Book;

/**
 * ******************************************************************
 * 
 * @brief 图书信息查询接口
 * @version 0.1
 * @date 2020年1月17日 下午3:35:16
 * @author ChangZiYang
 *******************************************************************
 */
public interface BookService {

	Map<String, Object> add(Book book);

	Map<String, Object> update(Book book);

	Map<String, Object> list(Integer page, Integer limit);

	Map<String, Object> deleteByIds(String ids);

	Map<String, Object> search(Integer page, Integer limit, String name);

}
