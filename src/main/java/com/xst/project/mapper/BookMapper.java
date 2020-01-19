package com.xst.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.xst.project.pojo.Book;

@Mapper
public interface BookMapper {
	
	@Select("select id,author,bianhao,create_date_time createDateTime,danjia,image_url imageUrl,name,num,order_no orderNo,press,update_date_time updateDateTime,book_type_id bookTypeId from book where id=#{id}")
    Book findById(int id);
	
	@Insert("insert into book(author,bianhao,create_date_time,danjia,image_url,name,num,order_no,press,update_date_time,book_type_id) values(#{author},#{bianhao},#{createDateTime},#{danjia},#{imageUrl},#{name},#{num},#{orderNo},#{press},#{updateDateTime},#{bookTypeId})")
	void add(Book book);
	
	@Update("update book set author=#{author},bianhao=#{bianhao},create_date_time=#{createDateTime},danjia=#{danjia},image_url=#{imageUrl},name=#{name},num=#{num},order_no=#{orderNo},press=#{press},update_date_time=#{updateDateTime},book_type_id=#{bookTypeId} where id=#{id}")
	void update(Book book);
	
	
	@Select("select * from book")//这里进行分页查询
    @Results({
    	@Result(id=true,column="id",property="id"),
    	@Result(column="create_date_time",property="createDateTime"),
    	@Result(column="name",property="name"),
    	@Result(column="order_no",property="orderNo"),
    	@Result(column="author",property="author"),
    	@Result(column="bianhao",property="bianhao"),
    	@Result(column="danjia",property="danjia"),
    	@Result(column="image_url",property="imageUrl"),
    	@Result(column="num",property="num"),
    	@Result(column="press",property="press"),
    	@Result(column="update_date_time",property="updateDateTime"),
    	@Result(column="book_type_id",property="bookType",
    		one=@One(select="com.xst.project.mapper.BookTypeMapper.findById",fetchType=FetchType.EAGER)
    			)	
    })
	List<Book> findAll();
	
	@Delete("delete from book where id = #{id}")
	void deleteById(int id);
	
	@Select("select id,author,bianhao,create_date_time createDateTime,danjia,image_url imageUrl,name,num,order_no orderNo,press,update_date_time updateDateTime,book_type_id bookTypeId from book where book_type_id=#{bookTypeId}")
	Book findByTypeId(int bookTypeId);
	
	
	@Select("select * from book where name like #{name}")
	 @Results({
	    	@Result(id=true,column="id",property="id"),
	    	@Result(column="create_date_time",property="createDateTime"),
	    	@Result(column="name",property="name"),
	    	@Result(column="order_no",property="orderNo"),
	    	@Result(column="author",property="author"),
	    	@Result(column="bianhao",property="bianhao"),
	    	@Result(column="danjia",property="danjia"),
	    	@Result(column="image_url",property="imageUrl"),
	    	@Result(column="num",property="num"),
	    	@Result(column="press",property="press"),
	    	@Result(column="update_date_time",property="updateDateTime"),
	    	@Result(column="book_type_id",property="bookType",
	    		one=@One(select="com.xst.project.mapper.BookTypeMapper.findById",fetchType=FetchType.EAGER)
	    			)
	    		
	    })
	List<Book> search(String name);
	
	
}
