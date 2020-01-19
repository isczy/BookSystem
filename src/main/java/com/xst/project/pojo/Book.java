package com.xst.project.pojo;

import lombok.Data;

/**
 * ******************************************************************
 * 
 * @brief 图书的实体类
 * @version 0.1
 * @date 2019年9月10日 下午3:48:22
 * @author ChangZiYang
 *******************************************************************
 */
@Data
public class Book {

	private Integer id;// 主键id

	private String author;// 作者

	private String bianhao;// 编号

	private String createDateTime;// 图书的创建时间

	private String danjia;// 图书的单价
	
	private String imageUrl;// 图书的封面
	
	private String name;// 类型名称
	
	private String num;// 图书的数量
	
	private String orderNo;// 排序号
	
	private String press;// 出版社
	
	private String updateDateTime;// 图书的更新时间
	
	private Integer bookTypeId;// 图书类型的id
	
	private BookType bookType;

}
