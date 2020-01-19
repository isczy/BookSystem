package com.xst.project.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.BookMapper;
import com.xst.project.mapper.BorrowedRecordMapper;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BorrowedRecord;
import com.xst.project.service.MyBorrowedServive;
import com.xst.project.utils.PublicUtil;

@Service
public class MyBorrowedServiveImpl implements MyBorrowedServive {

	private static Logger log = Logger.getLogger(MyBorrowedServiveImpl.class);
	@Autowired
	private BorrowedRecordMapper borrowedRecordMapper;
	@Autowired
	private BookMapper bookMapper;

	/**
	 * ************************************************
	 * 功能实现描述：条件分页查询我的借阅
	 * @param page
	 * @param limit
	 * @param state
	 * @param username
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:56:07
	 * @author modify:
	 */
	@Override
	public Map<String, Object> search(Integer page, Integer limit, Integer state, String username) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageHelper.startPage(page, limit);
			Page<BorrowedRecord> pageBorrowedRecord = (Page<BorrowedRecord>) borrowedRecordMapper.findByUserNameAndState(username, state);
			result.put("data", pageBorrowedRecord.getResult());
			result.put("count", pageBorrowedRecord.getTotal());
			result.put("code", 0);
			result.put("msg", "");
		} catch (Exception e) {
			log.error("MyBorrowedServiveImpl >> search-条件分页查询我的借阅失败："+e.getMessage());
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：归还图书
	 * @param borrowedRecord
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:59:14
	 * @author modify:
	 */
	@Transactional
	@Override
	public Map<String, Object> giveBack(BorrowedRecord borrowedRecord) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 先判断该条记录是否已经归还
			BorrowedRecord b = borrowedRecordMapper.findById(borrowedRecord.getId());
			if (b.getState() == 1) {
				result.put("success", false);
				result.put("msg", "该书已归还，请勿再次操作");
				return result;
			}
			// 修改该条借阅记录状态为1和归还时间就是已还
			borrowedRecordMapper.updateStateEndTime(borrowedRecord.getId(), 1, PublicUtil.getNowTime());
			// 让该图书的库存+1
			// 通过bookId查出book相关的信息
			Book book = bookMapper.findById(borrowedRecord.getBookId());
			book.setNum(String.valueOf(Integer.valueOf(book.getNum()) + 1));
			bookMapper.update(book);
			result.put("success", true);
			result.put("msg", "成功归还");
		} catch (Exception e) {
			log.error("MyBorrowedServiveImpl >> giveBack-归还图书失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "归还图书失败,请重试");
		}
		return result;
	}

}
