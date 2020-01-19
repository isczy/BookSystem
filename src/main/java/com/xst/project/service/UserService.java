package com.xst.project.service;

import java.util.Map;

import com.xst.project.pojo.User;

/**
 * ******************************************************************
 * @brief      用户查询接口
 * @version    0.1
 * @date       2020年1月17日 下午3:39:18
 * @author     ChangZiYang
 *******************************************************************
 */
public interface UserService {
	
    User findByName(String name);
    
    User findById(Integer id);
    
    Map<String, Object> saveUser(User user);
    
    Map<String, Object> updateUser(User user);
    
    Map<String, Object> deleteUser(String ids);
    
    Map<String, Object> list(Integer page,Integer limit);
    
    Map<String, Object> updatePwd(User user);
}
