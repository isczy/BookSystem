package com.xst.project.controller.houtai;

import java.util.List;

import javax.annotation.Resource;

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
@RequestMapping("/houtai/MyBorrowed")
public class HouTai_MyBorrowed_Controller {
	

	@Resource
	private BookMapper bookMapper;
	@Resource
	private BookService bookService;
	@Resource
	private BookTypeMapper bookTypeMapper;
	
	
	/**
	 * /houtai/MyBorrowed/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "我的借阅");
		mav.setViewName("/admin/page/MyBorrowed/manage");
		return mav;
	}
	
	
	/**
	 * /houtai/MyBorrowed/edit?id=1
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
