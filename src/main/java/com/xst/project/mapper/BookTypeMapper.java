package com.xst.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xst.project.pojo.BookType;

@Mapper
public interface BookTypeMapper {
	
	@Select("select id,create_date_time createDateTime,name,order_no orderNo,update_date_time updateDateTime from book_type where id = #{id}")
	BookType findById(int id);
	
	@Insert("insert into book_type(create_date_time,name,order_no,update_date_time) values(#{createDateTime},#{name},#{orderNo},#{updateDateTime})")
	void add(BookType bookType);
	
	@Update("update book_type set create_date_time = #{createDateTime},name = #{name},order_no = #{orderNo},update_date_time = #{updateDateTime} where id = #{id}")
	void update(BookType bookType);
	
	@Select("select id,create_date_time createDateTime,name,order_no orderNo,update_date_time updateDateTime from book_type")
	List<BookType> findAll();
	
	@Delete("delete from book_type where id = #{id}")
	void deleteById(int id);
}
