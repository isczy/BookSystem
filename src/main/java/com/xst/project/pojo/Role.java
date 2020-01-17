package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * 
 * @brief 角色
 * @version 0.1
 * @date 2020年1月17日 下午3:22:37
 * @author ChangZiYang
 *******************************************************************
 */
@Data
public class Role {

	private Integer id;

	private String createDateTime;// 创建时间

	private String name;// 角色名称

	private String orderNo;// 排序号

	private String updateDateTime;// 更新时间

	private String remark;// 备注

}
