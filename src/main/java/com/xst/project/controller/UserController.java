package com.xst.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xst.project.pojo.User;
import com.xst.project.service.UserService;
import com.xst.project.utils.CryptographyUtil;

/**
 * ******************************************************************
 * @brief      登录的操作
 * @version    0.1
 * @date       2020年1月17日 下午3:27:25
 * @author     ChangZiYang
 *******************************************************************
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("login")
	public Map<String,Object> login(String name,String password){
		Map<String, Object> result = new HashMap<>();
		//获取 subject
		Subject subject = SecurityUtils.getSubject();
		//封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(name,CryptographyUtil.md5(password, "java"));
		  try {
	            //执行登陆  shiro的登陆
	            subject.login(token);
	            result.put("success", true);
	            result.put("msg","登陆成功");
	            User user = userService.findByName(name);
	            Session session = SecurityUtils.getSubject().getSession();
	            //session.setTimeout(9000);//设置session失效时间，不设置默认30分钟
	            session.setAttribute("currentUser", user); //把当前用户信息存到session中
	        } catch (UnknownAccountException e) {
	            result.put("success", false);
	            result.put("msg","用户名不存在");
	        }catch (IncorrectCredentialsException e) {
	            result.put("success", false);
	            result.put("msg","密码错误");
	        }
	        return result;
	}
	/**
     * 注销
     *  /user/logout
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout()throws Exception{
        SecurityUtils.getSubject().logout(); //shiro的退出
        return "redirect:/login";
    }
}
