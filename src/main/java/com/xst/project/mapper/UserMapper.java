package com.xst.project.mapper;

import com.xst.project.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select id,create_date_time createDateTime,NAME,order_no orderNo,pwd,remark,true_name trueName,update_date_time updateDateTime,role_id roleId from user where id=#{id}")
    User findById(Integer id);

    @Select("select id,create_date_time createDateTime,NAME,order_no orderNo,pwd,remark,true_name trueName,update_date_time updateDateTime,role_id roleId from user where name=#{name}")
    User findByName(String name);

    @Insert("insert into user(name,true_name,order_no,create_date_time,remark,pwd,update_date_time,role_id) values(#{name},#{trueName},#{orderNo},#{createDateTime},#{remark},#{pwd},#{updateDateTime},#{roleId})")
    void savaUser(User user);

    @Update("update user set name=#{name},true_name=#{trueName},order_no=#{orderNo},create_date_time=#{createDateTime},remark=#{remark},pwd=#{pwd},update_date_time=#{updateDateTime},role_id=#{roleId} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

    @Select("select * from user")//这里进行分页查询
    @Results({
    	@Result(id=true,column="id",property="id"),
    	@Result(column="create_date_time",property="createDateTime"),
    	@Result(column="name",property="name"),
    	@Result(column="order_no",property="orderNo"),
    	@Result(column="pwd",property="pwd"),
    	@Result(column="remark",property="remark"),
    	@Result(column="true_name",property="trueName"),
    	@Result(column="update_date_time",property="updateDateTime"),
    	@Result(column="role_id",property="role",
    		one=@One(select="com.xst.project.mapper.RoleMapper.findById",fetchType=FetchType.EAGER)
    			)
    		
    })
    List<User> findAll();
    
    @Update("update user set pwd=#{pwd} where id = #{id}")
    void updatePwd(User user);
    
    //供角色删除时判断是否有用户在使用角色用
    @Select("select id,create_date_time createDateTime,NAME,order_no orderNo,pwd,remark,true_name trueName,update_date_time updateDateTime,role_id roleId from user where role_id=#{roleId}")
    User findByRoleId(int roleId);
}
