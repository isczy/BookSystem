package com.xst.project.controller.admin;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xst.project.pojo.BookType;
import com.xst.project.service.BookTypeService;

@Controller
@RequestMapping("/admin/book/type")
public class Admin_BookType_Controller {

	@Autowired
	private BookTypeService bookTypeService;

	/**
	 * /admin/book/type/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(BookType bookType) {
		return bookTypeService.add(bookType);

	}

	/**
	 * /admin/book/type/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(BookType bookType) {

		return bookTypeService.update(bookType);

	}

	/**
	 * /admin/book/type/list
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {

		return bookTypeService.list(page,limit);
	}

	/**
	 * /admin/book/type/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids) {
		
		return bookTypeService.deleteByIds(ids);
	}

}
