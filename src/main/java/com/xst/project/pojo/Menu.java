package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * 
 * @brief 菜单实体
 * @version 0.1
 * @date 2019年9月10日 下午3:36:08
 * @author ChangZiYang
 *******************************************************************
 */
@Data
public class Menu {

	private Integer id;// 主键id

	private String divId;// layui菜单id

	private String icon;// 图标

	private String name;// 菜单名称

	private String orderNo;// 排序号

	private Integer pId;// 父菜单id 根是-1 然后是自己编id

	private String permissions;// 对应的shiro权限 user：add permissions也可以是中文

	private Integer state;// 菜单节点类型 0根节点close 1叶子节点opend

	private Integer type;// 默认是0选项卡内打开 1新窗口打开 2弹出窗口打开

	private String url;// 菜单地址

}
