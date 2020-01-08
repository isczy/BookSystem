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
@RequestMapping("/admin/IWantBorrow")
public class Admin_IWantBorrow_Controoler {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowedRecordServive borrowedRecordServive;

	

	/**
	 * /admin/IWantBorrow/list
	 * @param page    默认1
	 * @param limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		
		return bookService.list(page,limit);
	}
	
	/**
	 * /admin/IWantBorrow/search
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page,Integer limit,String name){
		return bookService.search(page,limit,name);
	}
	
	/**
	 * /admin/IWantBorrow/borrow
	 * 添加一条借阅记录
	 * 前台传的为书的id
	 */
	@ResponseBody
	@RequestMapping("/borrow")
	public Map<String, Object> borrow(@RequestParam(value="id")Integer id){
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		System.out.println(id);
		return borrowedRecordServive.add(id);
	}
	
}
