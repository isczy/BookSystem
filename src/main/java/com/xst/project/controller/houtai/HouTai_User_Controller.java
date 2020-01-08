package com.xst.project.controller.houtai;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xst.project.mapper.RoleMapper;
import com.xst.project.mapper.UserMapper;
import com.xst.project.pojo.Role;
import com.xst.project.pojo.User;
import com.xst.project.service.RoleService;
import com.xst.project.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houtai/user")
public class HouTai_User_Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * /houtai/user/manage
     * 只有拥有"用户管理"的权限时才能打开这个url
     */
    @RequiresPermissions(value= {"用户管理"})
    @RequestMapping("/manage")
    public ModelAndView manage() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "用户管理");
        mav.addObject("title", "用户管理");
        mav.setViewName("/admin/page/user/user_manage");
        return mav;
    }

    /**
     * /houtai/user/add
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value= {"用户管理"})
    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = null;
        Map<String, Object> result = roleService.list( 0, 1000);
        mav.addObject("roleList", result.get("data"));

       mav.addObject("flag", true);


        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/user/add");
        mav.setViewName("/admin/page/user/add_update");
        return mav;
    }

    /**
     * /houtai/user/edit?id=1
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value= {"用户管理"})
    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = null;
         Map<String, Object> result = roleService.list( 0, 1000);
        mav.addObject("roleList", result.get("data"));

        User user = userService.findById(id);
        mav.addObject("user", user);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/user/update?id=" + id);
        mav.setViewName("/admin/page/user/add_update");
        return mav;
    }

    /**
     * /houtai/user/set_new_pwd?id=1
     * @return
    * @throws Exception
     */
    @RequiresPermissions(value= {"用户管理"})
    @RequestMapping("/set_new_pwd")
    public ModelAndView set_new_pwd(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(id);
       mav.addObject("user", user);
       mav.addObject("btn_text", "修改");
       mav.addObject("save_url", "/admin/user/set_new_pwd?id=" + id);
        mav.setViewName("/admin/page/user/set_new_pwd");
        return mav;
    }

}
