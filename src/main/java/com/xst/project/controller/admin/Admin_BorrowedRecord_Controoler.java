package com.xst.project.controller.admin;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xst.project.pojo.Book;
import com.xst.project.service.BookService;
import com.xst.project.service.BorrowedRecordServive;

@Controller
@RequestMapping("/admin/borrowedRecord")
public class Admin_BorrowedRecord_Controoler {

	@Autowired
	private BorrowedRecordServive borrowedRecordServive;

	/**
	 * /admin/borrowedRecord/list
	 * @param page    默认1
	 * @param limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		
		return borrowedRecordServive.list(page,limit);
	}
	
	
	/**
	 * /admin/borrowedRecord/search
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page,Integer limit,Integer state,String username){
		
		return borrowedRecordServive.search(page,limit,state,username);
	}
	
	/**
	 * /admin/borrowedRecord/wmgp
	 */
	@ResponseBody
	@RequestMapping("/wmgp")
	public Map<String, Object> wmgp(@RequestParam(value="id")Integer id){
		
		return borrowedRecordServive.wmgp(id);
	}
	
}
