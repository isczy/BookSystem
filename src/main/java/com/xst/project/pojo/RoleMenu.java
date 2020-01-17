package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * @brief      角色菜单关联实体中间表
 * @version    0.1
 * @date       2019年9月10日 下午3:38:32
 * @author     ChangZiYang
 *******************************************************************
 */
@Data
public class RoleMenu {

  private Integer id;//中间表主键id
  
  private Integer menuId;//菜单的id
  
  private Integer roleId;//角色的id

}
