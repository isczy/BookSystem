package com.xst.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xst.project.pojo.Config;
import com.xst.project.pojo.User;

@Mapper
public interface ConfigMapper {
	@Select("select id,webName from config where id=#{id}")
    Config findId(int id);

    @Select("select * from user")
    User find();
}
