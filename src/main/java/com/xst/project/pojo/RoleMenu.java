package com.xst.project.pojo;

/**
 * ******************************************************************
 * @brief      角色菜单关联实体中间表
 * @version    0.1
 * @date       2019年9月10日 下午3:38:32
 * @author     ChangZiYang
 *******************************************************************
 */
public class RoleMenu {

  private Integer id;//中间表主键id
  
  private Integer menuId;//菜单的id
  
  private Integer roleId;//角色的id


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getMenuId() {
    return menuId;
  }

  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

/**************************************************
 * 功能实现描述：
 * @return
 * @author create: ChangZiYang 2019年9月18日 上午8:17:09
 * @author modify: 
 */
@Override
public String toString() {
	return "RoleMenu [id=" + id + ", menuId=" + menuId + ", roleId=" + roleId + "]";
}
  

}
