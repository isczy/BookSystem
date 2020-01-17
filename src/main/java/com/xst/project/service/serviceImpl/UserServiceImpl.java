package com.xst.project.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.UserMapper;
import com.xst.project.pojo.User;
import com.xst.project.service.UserService;
import com.xst.project.utils.CryptographyUtil;
import com.xst.project.utils.DateToString;
import com.xst.project.utils.PublicUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	// 通过用户名查询用户的登录方法
	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	// 通过id查询用户
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	/**
	 * ************************************************
	 * 功能实现描述：新增用户信息
	 * @param user
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午5:25:48
	 * @author modify:
	 */
	@Transactional
	public Map<String, Object> saveUser(User user) {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> resultMap = checkUser(user, result);
			if (null != resultMap) {
				return resultMap;
			}
			user.setPwd(CryptographyUtil.md5(user.getPwd(), "java"));// 对存入数据库的密码进行加密加盐
			String date = new DateToString().dateToSring();
			user.setCreateDateTime(date);// 这里两个是取当前存入数据库的时间插入数据库
			user.setUpdateDateTime(date);
			// 设置user表的role_id字段
			user.setRoleId(user.getRole().getId());
			userMapper.savaUser(user);
			result.put("success", true);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			log.error("UserServiceImpl >> saveUser-新增用户失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：修改用户信息
	 * @param user
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午5:27:45
	 * @author modify:
	 */
	@Transactional
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> result = new HashMap<>();
		try {
			User olds = userMapper.findById(user.getId());
			// 把没有值的数据换成旧数据
			user = repalce(user, olds);
			if (!user.getPwd().equals(null)) {// 对存入数据库的密码进行加密加盐
				user.setPwd(CryptographyUtil.md5(user.getPwd(), "java"));
			}
			// 设置user表的role_id字段
			user.setRoleId(user.getRole().getId());
			user.setUpdateDateTime(PublicUtil.getNowTime());		
			userMapper.updateUser(user);
			result.put("success", true);
			result.put("msg", "修改成功");
		} catch (Exception e) {
			log.error("UserServiceImpl >> updateUser-修改用户失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "修改失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：批量删除用户信息
	 * @param ids
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午5:28:11
	 * @author modify:
	 */
	@Transactional
	public Map<String, Object> deleteUser(String ids) {
		
		Map<String, Object> result = new HashMap<>();
		try {
			String[] idsStr = ids.split(",");
			for (int i = 0; i < idsStr.length; i++) {
				userMapper.deleteUser(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			log.error("UserServiceImpl >> deleteUser-删除用户信息失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "删除失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：分页查询所有用户信息
	 * @param page
	 * @param limit
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午5:29:01
	 * @author modify:
	 */
	public Map<String, Object> list(Integer page, Integer limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageHelper.startPage(page, limit);
			Page<User> pageUser = (Page<User>) userMapper.findAll();
			result.put("data", pageUser.getResult());
			result.put("count", pageUser.getTotal());
			result.put("code", 0);
			result.put("msg", "");
		} catch (Exception e) {
			log.error("UserServiceImpl >> list-分页查询所有用户信息失败："+e.getMessage());
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：修改密码
	 * @param user
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午5:29:40
	 * @author modify:
	 */
	@Transactional
	public Map<String, Object> updatePwd(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userMapper.updatePwd(user);
			result.put("success", true);
			result.put("msg", "修改成功");

		} catch (Exception e) {
			log.error("UserServiceImpl >> updatePwd-修改密码失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "修改失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能描述：用于新增校验参数
	 * @param user
	 * @param result
	 * @return
	 * @throws Exception
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2020年1月17日】
	 * @author modify:
	 */
	private Map<String, Object> checkUser(User user, Map<String, Object> result) throws Exception {
		if (user.getName() == null || user.getName() == "") {
			result.put("success", false);
			result.put("msg", "用户名不能为空");
			return result;
		}
		// 判断用户名是否存在
		User u = userMapper.findByName(user.getName());
		if (u != null) {
			result.put("success", false);
			result.put("msg", "用户名已存在");
			return result;
		}
		if (user.getPwd() == null || user.getPwd() == "") {
			result.put("success", false);
			result.put("msg", "密码不能为空");
			return result;
		}
		if (user.getTrueName() == null || user.getTrueName() == "") {
			result.put("success", false);
			result.put("msg", "真实姓名不能为空");
			return result;
		}
		if (user.getOrderNo() == null || user.getOrderNo() == "") {
			result.put("success", false);
			result.put("msg", "排序号不能为空");
			return result;
		}
		// 判断排序号是否符合规范
		try {
			Integer.valueOf(user.getOrderNo());
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "请输入正确的排序号");
			return result;
		}
		return null;
	}

	/**
	 * ************************************************ 功能描述：校验用户-用于更新
	 * 
	 * @param news
	 * @param olds
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2020年1月17日】
	 * @author modify:
	 */
	public User repalce(User news, User olds) {
		if (news.getName() == null) {
			news.setName(olds.getName());
		}
		if (news.getPwd() == null) {
			news.setPwd(olds.getPwd());
		}
		if (news.getTrueName() == null) {
			news.setTrueName(olds.getTrueName());
		}
		if (news.getRemark() == null) {
			news.setRemark(olds.getRemark());
		}
		if (news.getOrderNo() == null) {
			news.setOrderNo(olds.getOrderNo());
		}
		if (news.getCreateDateTime() == null) {
			news.setCreateDateTime(olds.getCreateDateTime());
		}
		/*
		 * if (news.getRoleId()==null) { news.setRoleId(olds.getRoleId()); }
		 */
		return news;
	}

}
