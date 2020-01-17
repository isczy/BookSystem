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
import com.xst.project.pojo.Book;
import com.xst.project.service.BookService;
import com.xst.project.utils.DateToString;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	private static Logger log = Logger.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookMapper bookMapper;

	/**
	 * ************************************************
	 * 功能实现描述：新增图书
	 * @param book
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:09:05
	 * @author modify:
	 */
	@Override
	public Map<String, Object> add(Book book) {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> resultMap = checkBook(book, result);
			if (null != resultMap) {
				return resultMap;
			}
			String date = new DateToString().dateToSring();
			book.setCreateDateTime(date);// 这里两个是取当前存入数据库的时间插入数据库
			book.setUpdateDateTime(date);
			// 设置book表的book_type_id字段
			book.setBookTypeId(book.getBookType().getId());
		
			bookMapper.add(book);
			result.put("success", true);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			log.error("BookServiceImpl >> add-添加图书失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：修改图书信息
	 * @param book
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:09:33
	 * @author modify:
	 */
	@Override
	public Map<String, Object> update(Book book) {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> resultMap = checkBook(book, result);
			if (null != resultMap) {
				return resultMap;
			}
			Book olds = bookMapper.findById(book.getId());
			// 把没有值的数据换成旧数据
			book = repalce(book, olds);
			// 设置book表的book_type_id字段
			book.setBookTypeId(book.getBookType().getId());
			book.setUpdateDateTime(new DateToString().dateToSring());
			bookMapper.update(book);
			result.put("success", true);
			result.put("msg", "修改成功");
		} catch (Exception e) {
			log.error("BookServiceImpl >> update-修改图书信息失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "修改失败");
		}
		return result;

	}

	/**
	 * ************************************************
	 * 功能实现描述：分页查询图书信息
	 * @param page
	 * @param limit
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:10:45
	 * @author modify:
	 */
	@Override
	public Map<String, Object> list(Integer page, Integer limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageHelper.startPage(page, limit);
			Page<Book> pageBook = (Page<Book>) bookMapper.findAll();
			result.put("data", pageBook.getResult());
			result.put("count", pageBook.getTotal());
			result.put("code", 0);
			result.put("msg", "");
		} catch (Exception e) {
			log.error("BookServiceImpl >> list-图书信息分页查询失败："+e.getMessage());
			result.put("code", 1);
			result.put("msg", "查询失败");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：根据id批量删除图书信息
	 * @param ids
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:12:07
	 * @author modify:
	 */
	@Transactional
	@Override
	public Map<String, Object> deleteByIds(String ids) {
		Map<String, Object> result = new HashMap<>();
		try {
			String[] idsStr = ids.split(",");
			for (int i = 0; i < idsStr.length; i++) {
				bookMapper.deleteById(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			log.error("BookServiceImpl >> deleteByIds-批量删除图书信息失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "删除失败");
		}
		return result;
	}


	/**
	 * ************************************************
	 * 功能实现描述：条件查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:21:41
	 * @author modify:
	 */
	@Override
	public Map<String, Object> search(Integer page,Integer limit,String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageHelper.startPage(page, limit);
			String param = "%"+name+"%";
			Page<Book> pageBook = (Page<Book>) bookMapper.search(param);
			result.put("data", pageBook.getResult());
			result.put("count", pageBook.getTotal());
			result.put("code", 0);
			result.put("msg", "");
		} catch (Exception e) {
			log.error("BookServiceImpl >> search-图书信息条件分页查询失败："+e.getMessage());
			result.put("code", 1);
			result.put("msg", "查询失败");
		}
		
		return result;
	}
	
	/**
	 * ************************************************
	 * 功能描述：辅助新增修改-校验参数
	 * @param book
	 * @param result
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2020年1月17日】
	 * @author modify:
	 */
	private Map<String, Object> checkBook(Book book,Map<String, Object> result) throws Exception {
		if (book.getName() == null || book.getName() == "") {
			result.put("success", false);
			result.put("msg", "图书名不能为空");
			return result;
		}

		if (book.getDanjia() == null || book.getDanjia() == "") {
			result.put("success", false);
			result.put("msg", "图书单价不能为空");
			return result;
		}
		// 判断图书单价是否符合规范
		try {
			Double.valueOf(book.getDanjia());
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "请输入正确的单价");
			return result;
		}
		if (book.getNum() == null || book.getNum() == "") {
			result.put("success", false);
			result.put("msg", "图书数量不能为空");
			return result;
		}
		// 判断图书数量是否符合规范
		try {
			Integer.valueOf(book.getNum());
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "请输入正确的数量");
			return result;
		}
		if (book.getOrderNo() == null || book.getOrderNo() == "") {
			result.put("success", false);
			result.put("msg", "排序号不能为空");
			return result;
		}

		// 判断排序号是否符合规范
		try {
			Integer.valueOf(book.getOrderNo());
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "请输入正确的排序号");
			return result;
		}
		return null;
	}
	
	/**
	 * @param news
	 *            当前更新的数据
	 * @param olds
	 *            源数据 以前的数据
	 * @return news
	 */
	public Book repalce(Book news, Book olds) throws Exception {

		if (news.getName() == null) {
			news.setName(olds.getName());
		}
		if (news.getDanjia() == null) {
			news.setDanjia(olds.getDanjia());
		}
		if (news.getAuthor() == null) {
			news.setAuthor(olds.getAuthor());
		}
		if (news.getPress() == null) {
			news.setPress(olds.getPress());
		}

		if (news.getBianhao() == null) {
			news.setBianhao(olds.getBianhao());
		}
		if (news.getOrderNo() == null) {
			news.setOrderNo(olds.getOrderNo());
		}
		if (news.getCreateDateTime() == null) {
			news.setCreateDateTime(olds.getCreateDateTime());
		}
		if (news.getUpdateDateTime() == null) {
			news.setUpdateDateTime(olds.getUpdateDateTime());
		}
		if (news.getImageUrl() == null) {
			news.setImageUrl(olds.getImageUrl());
		}

		if (news.getNum() == null) {
			news.setNum(olds.getNum());
		}
		if (news.getBookType() == null) {
			news.setBookType(olds.getBookType());
		}

		return news;
	}
}
