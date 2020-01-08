package com.xst.project.controller.houtai;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.xst.project.mapper.BookMapper;
import com.xst.project.mapper.BookTypeMapper;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BookType;
import com.xst.project.service.BookService;

@Controller
@RequestMapping("/houtai/book")
public class HouTai_Book_Controller {
	

	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private BookService bookService;
	@Autowired
	private BookTypeMapper bookTypeMapper;
	
	
	/**
	 * /houtai/book/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "图书管理");
		mav.setViewName("/admin/page/book/book_manage");
		return mav;
	}
	
	/**
	 * /houtai/book/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<BookType> bookTypeList = bookTypeMapper.findAll();
		mav.addObject("bookTypeList", bookTypeList);
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/book/add");
		mav.setViewName("/admin/page/book/add_update");
		return mav;
	}
	
	
	/**
	 * /houtai/book/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();

		List<BookType> bookTypeList = bookTypeMapper.findAll();
		mav.addObject("bookTypeList", bookTypeList);
		
		Book book = bookMapper.findById(id);
		mav.addObject("book", book);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/book/update?id=" + id);
		mav.setViewName("/admin/page/book/add_update");
		return mav;
	}
	
	
	
	
	
}
