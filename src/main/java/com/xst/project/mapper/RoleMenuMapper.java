package com.xst.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xst.project.pojo.RoleMenu;

@Mapper
public interface RoleMenuMapper {
	
	/**
	 * 根据 roleId  menuId  查询 是否有内容
	 * @return
	 */
	@Select("select id,role_id roleId,menu_id menuId from role_menu where role_id = #{roleId} and menu_id = #{menuId}")
	RoleMenu findByRoleIdAndMenuId(@Param("roleId")Integer roleId,@Param("menuId")Integer menuId);
	
	@Delete("delete from role_menu where role_id = #{roleId}")
	void deleteByRoleId(int roleId);
	
	@Insert("insert into role_menu(menu_id,role_id) values (#{menuId},#{roleId})")
	void save(RoleMenu roleMenu);
	
	@Select("select id,role_id roleId,menu_id menuId from role_menu where role_id = #{roleId}")
	List<RoleMenu> findByRoleId(int roleId);
	
	@Select("select id,role_id roleId,menu_id menuId from role_menu where menu_id = #{menuId}")
	List<RoleMenu> findByMenuId(int menuId);
	
}
