package com.xst.project.service;

import java.util.Map;

/**
 * ******************************************************************
 * @brief      借书记录查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:36:23
 * @author     ChangZiYang
 *******************************************************************
 */
public interface BorrowedRecordServive {

	Map<String, Object> list(Integer page,Integer limit);
	
	Map<String, Object> search(Integer page,Integer limit,Integer state,String username);
	
	Map<String, Object> add(Integer bookId);
	
	Map<String, Object> wmgp(Integer id);
}
