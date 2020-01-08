package com.xst.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xst.project.pojo.BorrowedRecord;

@Mapper
public interface BorrowedRecordMapper {

	@Select("select id,username,user_trueName userTrueName,bianhao,book_name bookName,state,start_time startTime,end_time endTime,book_id bookId from borrowed_record")
	List<BorrowedRecord> findAll();
	
	@Select("select id,username,user_trueName userTrueName,bianhao,book_name bookName,state,start_time startTime,end_time endTime,book_id bookId from borrowed_record where username=#{username} and state=#{state}")
	List<BorrowedRecord> findByUserNameAndState(@Param("username") String username,@Param("state") Integer state);
	
	@Select("select id,username,user_trueName userTrueName,bianhao,book_name bookName,state,start_time startTime,end_time endTime,book_id bookId from borrowed_record where state=#{state}")
	List<BorrowedRecord> findByState(Integer state);
	
	@Select("select id,username,user_trueName userTrueName,bianhao,book_name bookName,state,start_time startTime,end_time endTime,book_id bookId from borrowed_record where username=#{username} and book_id=#{bookId} and state=#{state}")
	BorrowedRecord findByNameIdState(@Param("username") String username,@Param("bookId")Integer bookId,@Param("state") Integer state);
	
	@Insert("insert into borrowed_record(username,user_trueName,bianhao,book_name,state,start_time,book_id) values(#{username},#{userTrueName},#{bianhao},#{bookName},#{state},#{startTime},#{bookId})")
	void add(BorrowedRecord borrowedRecord);
	
	@Update("update borrowed_record set state=#{state},end_time=#{endTime} where id=#{id}")
	void updateStateEndTime(@Param("id")Integer id,@Param("state")Integer state,@Param("endTime")String endTime);
	
	@Select("select id,username,user_trueName userTrueName,bianhao,book_name bookName,state,start_time startTime,end_time endTime,book_id bookId from borrowed_record where id=#{id}")
	BorrowedRecord findById(Integer id);
	
	@Update("update borrowed_record set state=#{state} where id=#{id}")
	void updateState(@Param("id")Integer id,@Param("state")Integer state);
}
