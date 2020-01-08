package com.xst.project.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

public class BookMapperSql {

	public String searchBook(String name,Integer bookTypeId) {
		return new SQL(){{
			 SELECT("id,author,bianhao,create_date_time createDateTime,danjia,image_url imageUrl,name,num,order_no orderNo,press,update_date_time updateDateTime,book_type_id bookTypeId");
	            FROM("book");
	            if (name!=null&&name!="") {
	            	WHERE("name like #{name} || '%'");
				}
	            if (bookTypeId!=null) {
	            	WHERE("book_type_id = #{bookType}");
				}
		}}.toString();
	}
}
