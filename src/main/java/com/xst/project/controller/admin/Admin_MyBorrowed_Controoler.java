package com.xst.project.controller.admin;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BorrowedRecord;
import com.xst.project.pojo.User;
import com.xst.project.service.BookService;
import com.xst.project.service.BorrowedRecordServive;
import com.xst.project.service.MyBorrowedServive;

@Controller
@RequestMapping("/admin/MyBorrowed")
public class Admin_MyBorrowed_Controoler {

	@Autowired
	private MyBorrowedServive myBorrowedServive;

	
	/**
	 * /admin/borrowedRecord/search
	 */
	@ResponseBody
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page,Integer limit,Integer state){
		//在session中取出用户来获取用户名
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		return myBorrowedServive.search(page,limit,state,currentUser.getName());
	}
	
	
	/**
	 * /admin/borrowedRecord/giveBack
	 */
	@ResponseBody
	@RequestMapping("/giveBack")
	public Map<String, Object> giveBack(BorrowedRecord borrowedRecord){
		
		return myBorrowedServive.giveBack(borrowedRecord);
	}
}
