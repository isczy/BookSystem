package com.xst.project.controller.houtai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/houtai/IWantBorrow")
public class HouTai_IWantBorrow_Controller {
	
	/**
	 * /houtai/MyBorrow/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "我要借阅");
		mav.setViewName("/admin/page/IWantBorrow/manage");
		return mav;
	}
			
}
