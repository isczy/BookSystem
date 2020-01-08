package com.xst.project.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xst.project.mapper.BookMapper;
import com.xst.project.mapper.BorrowedRecordMapper;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BorrowedRecord;
import com.xst.project.pojo.User;
import com.xst.project.service.BookService;
import com.xst.project.service.BorrowedRecordServive;
import com.xst.project.util.DateToString;

@Service
public class BorrowedRecordServiveImpl implements BorrowedRecordServive {

	@Autowired
	private BorrowedRecordMapper borrowedRecordMapper;
	@Autowired
	private BookMapper bookMapper;
	

	@Override
	public Map<String, Object> list(Integer page, Integer limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		Page<BorrowedRecord> pageBook = (Page<BorrowedRecord>) borrowedRecordMapper.findAll();
		result.put("data", pageBook.getResult());
		result.put("count", pageBook.getTotal());
		result.put("code", 0);
		result.put("msg", "");
		return result;
	}


	@Override
	public Map<String, Object> search(Integer page, Integer limit, Integer state, String username) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		if (username!=null&&username!="") {
			Page<BorrowedRecord> pageBorrowedRecord = (Page<BorrowedRecord>) borrowedRecordMapper.findByUserNameAndState(username, state);
			result.put("data", pageBorrowedRecord.getResult());
			result.put("count", pageBorrowedRecord.getTotal());
			result.put("code", 0);
			result.put("msg", "");
			return result;
		}
		Page<BorrowedRecord> pageBorrowedRecord = (Page<BorrowedRecord>) borrowedRecordMapper.findByState(state);
		result.put("data", pageBorrowedRecord.getResult());
		result.put("count", pageBorrowedRecord.getTotal());
		result.put("code", 0);
		result.put("msg", "");
		return result;
	}


	@Transactional
	@Override
	public Map<String, Object> add(Integer bookId) {
		Map<String, Object> result = new HashMap<>();
		//从session中获取user的信息并存入borrowedRecord
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		//通过bookId查出book相关的信息,并存入borrowedRecord
		Book book = bookMapper.findById(bookId);
		//首先通过用户名也就是读者号和书的id还有未还的状态号（0，2都算未还）来查出该用户是否已经借过此书
		BorrowedRecord record1 = borrowedRecordMapper.findByNameIdState(currentUser.getName(),bookId,0);
		BorrowedRecord record2 = borrowedRecordMapper.findByNameIdState(currentUser.getName(),bookId,2);
		if (record1!=null||record2!=null) {
			result.put("success", false);
			result.put("msg", "已借阅此书，并未归还，不能再借啦");
			return result;
		}
		//添加借阅记录封装一条借阅记录存入借阅表
		BorrowedRecord borrowedRecord = new BorrowedRecord();
		
		borrowedRecord.setUsername(currentUser.getName());
		borrowedRecord.setUserTrueName(currentUser.getTrueName());
		borrowedRecord.setBookName(book.getName());
		borrowedRecord.setBianhao(book.getBianhao());
		borrowedRecord.setBookId(bookId);

		borrowedRecord.setState(0);//状态0为未还
		borrowedRecord.setStartTime(new DateToString().dateToSring());//设置借阅时间
		
		try {
			borrowedRecordMapper.add(borrowedRecord);
			//借阅成功后还要让此书的库存-1
			/*String num = book.getNum();
			Integer n = Integer.valueOf(num)-1;
			book.setNum(String.valueOf(n));
			*/
			book.setNum(String.valueOf(Integer.valueOf(book.getNum())-1));
			bookMapper.update(book);
			result.put("success", true);
			result.put("msg", "借阅成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "借阅失败，请重试");
		}
		return result;
	}


	@Transactional
	@Override
	public Map<String, Object> wmgp(Integer id) {
		Map<String, Object> result = new HashMap<>();
		//首先判断此记录是否已被催还
		BorrowedRecord br = borrowedRecordMapper.findById(id);
		if (br.getState()==1) {
			result.put("success", false);
			result.put("msg", "用户已归还此书，请勿操作");
			return result;
		}
		if (br.getState()==2) {
			result.put("success", false);
			result.put("msg", "已催还，请勿再次操作");
			return result;
		}
		
		try {
			borrowedRecordMapper.updateState(id, 2);
			result.put("success", true);
			result.put("msg", "催还成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "出错啦，请重试");
		}
		return result;
	}
	
	

}
