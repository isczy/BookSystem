package com.xst.project.controller.houtai;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.xst.project.mapper.BookTypeMapper;
import com.xst.project.pojo.BookType;

@Controller
@RequestMapping("/houtai/book/type")
public class HouTai_BookType_Controller {
	
	@Autowired
	private BookTypeMapper bookTypeMapper;
	
	/**
	 * /houtai/book/type/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "图书类型管理");
		mav.setViewName("/admin/page/booktype/booktype_manage");
		return mav;
	}
	
	/**
	 * /houtai/book/type/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/book/type/add");
		mav.setViewName("/admin/page/booktype/add_update");
		return mav;
	}
	
	
	/**
	 * /houtai/book/type/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		BookType bookType = bookTypeMapper.findById(id);
		mav.addObject("bookType", bookType);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/book/type/update?id=" + id);
		mav.setViewName("/admin/page/booktype/add_update");
		return mav;
	}
	
	
	
	
}
