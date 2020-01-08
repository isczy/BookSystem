package com.xst.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/********************************************************************
 * @brief      当前日期转换工具
 * @version    0.1
 * @date       2019年9月11日 下午4:09:05
 * @author     ChangZiYang
 ********************************************************************/
public class DateToString {
	public String dateToSring() {
		Date date = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  return  formatter.format(date);
	}
}
