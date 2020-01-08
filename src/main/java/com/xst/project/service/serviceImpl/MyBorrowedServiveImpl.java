package com.xst.project.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.BookMapper;
import com.xst.project.mapper.BorrowedRecordMapper;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BorrowedRecord;
import com.xst.project.service.BorrowedRecordServive;
import com.xst.project.service.MyBorrowedServive;
import com.xst.project.util.DateToString;

@Service
public class MyBorrowedServiveImpl implements MyBorrowedServive {

	@Autowired
	private BorrowedRecordMapper borrowedRecordMapper;
	@Autowired
	private BookMapper bookMapper;


	@Override
	public Map<String, Object> search(Integer page, Integer limit, Integer state, String username) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
			Page<BorrowedRecord> pageBorrowedRecord = (Page<BorrowedRecord>) borrowedRecordMapper.findByUserNameAndState(username, state);
			result.put("data", pageBorrowedRecord.getResult());
			result.put("count", pageBorrowedRecord.getTotal());
			result.put("code", 0);
			result.put("msg", "");
			return result;
	}


	@Transactional
	@Override
	public Map<String, Object> giveBack(BorrowedRecord borrowedRecord) {
		Map<String, Object> result = new HashMap<String, Object>();
		//先判断该条记录是否已经归还
		BorrowedRecord b = borrowedRecordMapper.findById(borrowedRecord.getId());
		if (b.getState()==1) {
			result.put("success", false);
			result.put("msg", "该书已归还，请勿再次操作");
			return result;
		}
		try {
			//修改该条借阅记录状态为1和归还时间就是已还
			borrowedRecordMapper.updateStateEndTime(borrowedRecord.getId(), 1,new DateToString().dateToSring());
			//让该图书的库存+1
			//通过bookId查出book相关的信息
			Book book = bookMapper.findById(borrowedRecord.getBookId());
			book.setNum(String.valueOf(Integer.valueOf(book.getNum()) + 1));
			bookMapper.update(book);
			result.put("success", true);
			result.put("msg", "成功归还");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "请重试");
		}
		return result;
	}

}
