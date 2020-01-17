package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * 
 * @brief 用户
 * @version 0.1
 * @date 2020年1月17日 下午3:24:40
 * @author ChangZiYang
 *******************************************************************
 */
@Data
public class User {

	private Integer id;// 用户id

	private String createDateTime;// 用户创建的时间

	private String name;// 用户名

	private String orderNo;// 排序号

	private String pwd;// 密码

	private String remark;// 备注

	private String trueName;// 真实姓名

	private String updateDateTime;// 用户更新的时间

	private Integer roleId; // 角色的id

	private Role role;

}
