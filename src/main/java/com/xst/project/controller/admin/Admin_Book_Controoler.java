package com.xst.project.controller.admin;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xst.project.pojo.Book;
import com.xst.project.service.BookService;

@Controller
@RequestMapping("/admin/book")
public class Admin_Book_Controoler {

	@Autowired
	private BookService bookService;

	/**
	 * /admin/book/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(Book book){
		
			return bookService.add(book);
		
	}
	
	/**
	 *  /admin/book/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Book book){
	
		return bookService.update(book);
		
	}
	

	/**
	 * /admin/book/list
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
	 * /admin/book/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids){
		
		return bookService.deleteByIds(ids);
	}
	
	/**
	 * /admin/book/search
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page,Integer limit,String name){
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		System.out.println(name);
		return bookService.search(page,limit,name);
	}
	
}
