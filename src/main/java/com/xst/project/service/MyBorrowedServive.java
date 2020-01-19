package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.BorrowedRecord;

/**
 * ******************************************************************
 * @brief      我的借阅记录查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:37:54
 * @author     ChangZiYang
 *******************************************************************
 */
public interface MyBorrowedServive {

	Map<String, Object> search(Integer page,Integer limit,Integer state,String username);
	
	Map<String, Object> giveBack(BorrowedRecord borrowedRecord);
}
