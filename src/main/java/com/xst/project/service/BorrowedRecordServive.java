package com.xst.project.service;

import java.util.Map;

public interface BorrowedRecordServive {

	Map<String, Object> list(Integer page,Integer limit);
	
	Map<String, Object> search(Integer page,Integer limit,Integer state,String username);
	
	Map<String, Object> add(Integer bookId);
	
	Map<String, Object> wmgp(Integer id);
}
