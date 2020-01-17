package com.xst.project.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.xst.project.mapper.MenuMapper;
import com.xst.project.mapper.RoleMapper;
import com.xst.project.mapper.RoleMenuMapper;
import com.xst.project.pojo.RoleMenu;
import com.xst.project.pojo.User;
import com.xst.project.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 授权--验证url
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String name = (String) SecurityUtils.getSubject().getPrincipal();
		// 有了用户就可以拿到角色，有角色就可以拿到对应的菜单
		User user = userService.findByName(name);
		// 这个角色对应 的菜单,先用roleId查找角色和菜单的关联表，再用关联表中的menuId查找对应的menu对象，
		// 再.getPermissions找出权限名
		List<RoleMenu> roleMenuList = roleMenuMapper.findByRoleId(user.getRoleId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (RoleMenu roleMenu : roleMenuList) {
			info.addStringPermission(menuMapper.findById(roleMenu.getMenuId()).getPermissions());// 添加权限
		}
		// 设置角色
		Set<String> roles = new HashSet<String>();
		roles.add(roleMapper.findById(user.getRoleId()).getName());
		info.setRoles(roles);

		return info;
	}

	/**
	 * 权限认证--登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = (String) token.getPrincipal();// 拿到的是用户名 就是UsernamePasswordTokenr的第一个参数 name
		User user = userService.findByName(name);// 根据name找到数据库中的user实体
		if (user != null) {// 这里的一步主要是来判断密码是否正确
			// 对于我的理解第一个值应该放用户对象进去，这样我们在进行上面的授权操作的时候可以更好的获取对象，来添加用户权限
			// AuthenticationInfo authcInfo=new
			// SimpleAuthenticationInfo(user.getName(),user.getPwd(),"xxx");
			
			return new SimpleAuthenticationInfo(user.getName(), user.getPwd(), "xxx");// 第三个参数只要不为null就行
		} else {
			return null;
		}
	}

}
