package com.xst.project.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.xst.project.pojo.Menu;



@Mapper
public interface MenuMapper {
	
	 @Insert("insert into menu(div_id,icon,name,order_no,p_id,permissions,state,type,url) values(#{divId},#{icon},#{name},#{orderNo},#{pId},#{permissions},#{state},#{type},#{url})")
	 @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
	 void add(Menu menu);
	 
	 @Update("update menu set div_id=#{divId},icon=#{icon},name=#{name},order_no=#{orderNo},p_id=#{pId},permissions=#{permissions},state=#{state},type=#{type},url=#{url} where id=#{id}")
	 void update(Menu menu);
	 
	 @Select("select id,div_id divId,icon,name,order_no orderNo,p_id pId,permissions,state,type,url from menu where id=#{id}")
	 Menu findById(Integer id);
	 
	 @Select("select id,div_id divId,icon,name,order_no orderNo,p_id pId,permissions,state,type,url from menu where p_id=#{pId}")
	 List<Menu> findAll(Integer pId);
	 
	 @Delete("delete from menu where id=#{id}")
	 void delete(Integer id);

	 
	 @Select("select id,div_id divId,icon,name,order_no orderNo,p_id pId,permissions,state,type,url from menu where p_id=#{pId}")
	 List<Menu> findByPId(Integer pId);
}
