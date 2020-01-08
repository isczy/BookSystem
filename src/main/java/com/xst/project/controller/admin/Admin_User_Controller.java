package com.xst.project.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xst.project.pojo.User;
import com.xst.project.service.UserService;
import com.xst.project.util.CryptographyUtil;
import com.xst.project.util.StringUtil;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class Admin_User_Controller {


    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public Map<String, Object> add(User user){
    	System.out.println(user);
            return userService.saveUser(user);
        
    }

    @RequestMapping("/update")
    public Map<String, Object> update(User user){
    	
            return userService.updateUser(user);
        
    }

//设置新密码
    @RequestMapping("/set_new_pwd")
    public Map<String, Object> set_new_pwd(User user)throws Exception {
    	System.out.println(user);
        if(StringUtil.isNotEmpty(user.getPwd())){
            user.setPwd(CryptographyUtil.md5(user.getPwd(),"java"));
        }
        return userService.updatePwd(user);
    }


    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit){
        
        return userService.list(page, limit);
     
    }


    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids) throws Exception {
    
    	//System.out.println(ids);
        return userService.deleteUser(ids);
    }
}