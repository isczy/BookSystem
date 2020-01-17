package com.xst.project.pojo;

import lombok.Data;

/********************************************************************
 * @brief 借阅记录表实体类
 * @version 0.1
 * @date 2019年9月19日 上午8:51:00
 * @author ChangZiYang
 ********************************************************************/
@Data
public class BorrowedRecord {

	private Integer id;
	
	private String username;// 读者号
	
	private String userTrueName;// 真实姓名
	
	private String bianhao;// 图书编号
	
	private String bookName;// 书名
	
	private Integer state;// 状态，0未还，1已还
	
	private String startTime;// 借阅时间
	
	private String endTime;// 归还时间
	
	private int bookId;

}
