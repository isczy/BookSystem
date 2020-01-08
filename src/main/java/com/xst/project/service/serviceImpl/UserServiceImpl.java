package com.xst.project.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.UserMapper;
import com.xst.project.pojo.User;
import com.xst.project.service.UserService;
import com.xst.project.util.CryptographyUtil;
import com.xst.project.util.DateToString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    //通过用户名查询用户的登录方法
    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
    //通过id查询用户
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    //新建用户的方法
    @Transactional
    public Map<String, Object> saveUser(User user) {
    	Map<String, Object> result = new HashMap<>();
    	if (user.getName()==null||user.getName()=="") {
			  result.put("success", false);
	          result.put("msg", "用户名不能为空");
	          return result;
		}
    	//判断用户名是否存在
    	User u = userMapper.findByName(user.getName());
    	if (u!=null) {
    		result.put("success", false);
	          result.put("msg", "用户名已存在");
	          return result;
		}
    	if (user.getPwd()==null||user.getPwd()=="") {
			  result.put("success", false);
	          result.put("msg", "密码不能为空");
	          return result;
		}
    	if (user.getTrueName()==null||user.getTrueName()=="") {
			  result.put("success", false);
	          result.put("msg", "真实姓名不能为空");
	          return result;
		}
    	if (user.getOrderNo()==null||user.getOrderNo()=="") {
			  result.put("success", false);
	          result.put("msg", "排序号不能为空");
	          return result;
		}
    	
    	
    	//判断排序号是否符合规范
    	try {
			Integer.valueOf(user.getOrderNo());
		} catch (Exception e) {
			result.put("success", false);
	        result.put("msg", "请输入正确的排序号");
	        return result;
		}
    	user.setPwd(CryptographyUtil.md5(user.getPwd(),"java"));//对存入数据库的密码进行加密加盐
    	String date = new DateToString().dateToSring();
        user.setCreateDateTime(date);//这里两个是取当前存入数据库的时间插入数据库
        user.setUpdateDateTime(date);
        //设置user表的role_id字段
        user.setRoleId(user.getRole().getId());
    	try {
            userMapper.savaUser(user);
            result.put("success", true);
            result.put("msg", "添加成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "添加失败");
		}
    	return result;
    }
    
  //修改用户的方法
    @Transactional
    public Map<String, Object> updateUser(User user) {
    	Map<String, Object> result = new HashMap<>();
    	User olds = userMapper.findById(user.getId());
    	//把没有值的数据换成旧数据
    	user= repalce(user, olds);
    	if (!user.getPwd().equals(null)){//对存入数据库的密码进行加密加盐
            user.setPwd(CryptographyUtil.md5(user.getPwd(),"java"));
            }
    	//设置user表的role_id字段
        user.setRoleId(user.getRole().getId());
    	String date = new DateToString().dateToSring();
    	user.setUpdateDateTime(date);
    	try {
            userMapper.updateUser(user);
            result.put("success", true);
            result.put("msg", "修改成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "修改失败");
		}
    	return result;
    }
    
    //删除用户的方法
    @Transactional
    public Map<String, Object> deleteUser(String ids) {
    	 String[] idsStr = ids.split(",");
    	 Map<String, Object> result = new HashMap<>();
    	try {
    		 for (int i = 0; i < idsStr.length; i++) {
                 userMapper.deleteUser(Integer.parseInt(idsStr[i]));
             }
            result.put("success", true);
            result.put("msg", "删除成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "删除失败");
		}
    	return result;
    }
    //查询所有，分页
    public Map<String, Object> list(Integer page,Integer limit){
    	Map<String, Object> result = new HashMap<String, Object>();
    	PageHelper.startPage(page, limit);
    	Page<User> pageUser = (Page<User>) userMapper.findAll();
    	result.put("data", pageUser.getResult());
        result.put("count", pageUser.getTotal());
        result.put("code", 0);
        result.put("msg", "");
    	return result;
    }
    //修改密码
    @Transactional
    public Map<String, Object> updatePwd(User user){
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {
			userMapper.updatePwd(user);
			result.put("success", true);
            result.put("msg", "修改成功");
			
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "修改失败");
		}
    	return result;
    }
    
    //定义一个方法，让update的时候不会存入null字段
    public User repalce(User news ,User olds) {
    	if (news.getName()==null) {
			news.setName(olds.getName());
		}
    	if (news.getPwd()==null) {
			news.setPwd(olds.getPwd());
		}
    	if (news.getTrueName()==null) {
			news.setTrueName(olds.getTrueName());
		}
    	if (news.getRemark()==null) {
			news.setRemark(olds.getRemark());
		}
    	if (news.getOrderNo()==null) {
			news.setOrderNo(olds.getOrderNo());
		}
    	if (news.getCreateDateTime()==null) {
			news.setCreateDateTime(olds.getCreateDateTime());
		}
    	/*if (news.getRoleId()==null) {
			news.setRoleId(olds.getRoleId());
		}*/
    	return news;
    }


    
}
