package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.BorrowedRecord;

public interface MyBorrowedServive {

	
	Map<String, Object> search(Integer page,Integer limit,Integer state,String username);
	
	Map<String, Object> giveBack(BorrowedRecord borrowedRecord);
}
