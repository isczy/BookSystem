package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * 
 * @brief 图书的实体类
 * @version 0.1
 * @date 2019年9月10日 下午3:43:09
 * @author ChangZiYang
 *******************************************************************
 */
@Data
public class BookType {

	private Integer id;// 主键id

	private String createDateTime;// 创建时间

	private String name;// 类型名称

	private String orderNo;// 排序号

	private String updateDateTime;// 更新时间

}
