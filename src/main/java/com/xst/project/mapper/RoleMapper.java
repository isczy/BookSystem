package com.xst.project.mapper;

import org.apache.ibatis.annotations.Delete;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.xst.project.pojo.Role;
import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select id,create_date_time createDateTime,NAME,order_no orderNo,update_date_time updateDateTime,remark from role where id=#{id}")
    Role findById(Integer id);

    @Select("select id,create_date_time createDateTime,NAME,order_no orderNo,update_date_time updateDateTime,remark from role where name=#{name}")
    Role findByName(String name);

    //增加用户
    @Insert("insert into role(name,order_no,create_date_time,update_date_time,remark) values(#{name},#{orderNo},#{createDateTime},#{updateDateTime},#{remark})")
    void save(Role role);

    @Update("update role set name=#{name},order_no=#{orderNo},create_date_time=#{createDateTime},update_date_time=#{updateDateTime},remark=#{remark} where id=#{id}")
    void update(Role role);

    @Delete("delete from role where id=#{id}")
    void delete(Integer id);

    @Select("SELECT id,create_date_time createDateTime,NAME,order_no orderNo,update_date_time updateDateTime,remark FROM role")//这里进行分页查询
    List<Role> findAll();



}
