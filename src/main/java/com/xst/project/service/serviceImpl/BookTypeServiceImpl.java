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
import com.xst.project.mapper.BookTypeMapper;
import com.xst.project.pojo.Book;
import com.xst.project.pojo.BookType;
import com.xst.project.service.BookTypeService;
import com.xst.project.utils.DateToString;

@Service("bookTypeService")
public class BookTypeServiceImpl implements BookTypeService {
	
	private static Logger log = Logger.getLogger(BookTypeServiceImpl.class);
	@Autowired
	private BookTypeMapper bookTypeMapper;
	@Autowired
	private BookMapper bookMapper;

	/**
	 * ************************************************
	 * 功能实现描述：添加图书类别
	 * @param bookType
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:38:16
	 * @author modify:
	 */
	@Override
	public Map<String, Object> add(BookType bookType)  {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> resultMap = checkBook(bookType, result);
			if (null != resultMap) {
				return resultMap;
			}
			String dateString = new DateToString().dateToSring();
			bookType.setCreateDateTime(dateString);
			bookType.setUpdateDateTime(dateString);
		
			bookTypeMapper.add(bookType);
			result.put("success", true);
			result.put("msg", "添加成功");
		} catch (Exception e) {
			log.error("BookTypeServiceImpl >> add-添加图书类型失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "添加失败,请重试");
		}
		return result;
	}

	/**
	 * ************************************************
	 * 功能实现描述：修改图书类别
	 * @param bookType
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:38:30
	 * @author modify:
	 */
	@Override
	public Map<String, Object> update(BookType bookType) {
		Map<String, Object> result = new HashMap<>();
		try {
			Map<String, Object> resultMap = checkBook(bookType, result);
			if (null != resultMap) {
				return resultMap;
			}
			// 查找旧的数据
			BookType olds = bookTypeMapper.findById(bookType.getId());
			// 把没有值的数据 换成原数据库的数据。
			bookType = repalce(bookType, olds);
			// 设置更新的时间
			bookType.setUpdateDateTime(new DateToString().dateToSring());
			bookTypeMapper.update(bookType);
			result.put("success", true);
			result.put("msg", "修改成功");
		} catch (Exception e) {
			log.error("BookTypeServiceImpl >> update-修改图书类别失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "修改失败,请重试");
		}
		return result;
	}
	
	/**
	 * ************************************************
	 * 功能实现描述：查询所有，分页
	 * @param page
	 * @param limit
	 * @return
	 * @author create: ChangZiYang 2020年1月17日 下午4:38:46
	 * @author modify:
	 */
    public Map<String, Object> list(Integer page,Integer limit){
    	Map<String, Object> result = new HashMap<String, Object>();
    	try {	
        	PageHelper.startPage(page, limit);
        	Page<BookType> pageUser = (Page<BookType>) bookTypeMapper.findAll();
        	result.put("data", pageUser.getResult());
            result.put("count", pageUser.getTotal());
            result.put("code", 0);
            result.put("msg", "");
		} catch (Exception e) {
			log.error("BookTypeServiceImpl >> list-分页查询图书类别失败："+e.getMessage());
		}
    	
    	return result;
    }
    
    /**
     * ************************************************
     * 功能实现描述：批量删除图书类别
     * @param ids
     * @return
     * @author create: ChangZiYang 2020年1月17日 下午4:40:21
     * @author modify:
     */
    @Transactional
    public Map<String, Object> deleteByIds(String ids) {
    	 Map<String, Object> result = new HashMap<>();
    	try {
			String[] idsStr = ids.split(",");
			for (int i = 0; i < idsStr.length; i++) {
				// 先查看是否有图书在使用这个图书类型
				Book book = bookMapper.findByTypeId(Integer.parseInt(idsStr[i]));
				if (book != null) {
					result.put("success", false);
					result.put("msg", "您删除的图书类型下有图书存在");
					return result;
				}
				bookTypeMapper.deleteById(Integer.parseInt(idsStr[i]));
			}
            result.put("success", true);
            result.put("msg", "删除成功");
		} catch (Exception e) {
			log.error("BookTypeServiceImpl >> deleteByIds-批量删除图书类别失败："+e.getMessage());
			result.put("success", false);
			result.put("msg", "删除失败");
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
	private Map<String, Object> checkBook(BookType bookType,Map<String, Object> result) throws Exception {
		if (bookType.getName() == null || bookType.getName() == "") {
			result.put("success", false);
			result.put("msg", "书名不能为空");
			return result;
		}
		if (bookType.getOrderNo() == null || bookType.getOrderNo() == "") {
			result.put("success", false);
			result.put("msg", "排序号不能为空");
			return result;
		}
		try {
			Integer.valueOf(bookType.getOrderNo());
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
	public BookType repalce(BookType news, BookType olds) {

		if (news.getName() == null) {
			news.setName(olds.getName());
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

		return news;
	}

}
