package com.xst.project.pojo;

/**
 * ******************************************************************
 * @brief      图书的实体类
 * @version    0.1
 * @date       2019年9月10日 下午3:48:22
 * @author     ChangZiYang
 *******************************************************************
 */
public class Book {

  private Integer id;//主键id
  private String author;//作者
  private String bianhao;//编号
  private String createDateTime;//图书的创建时间
  private String danjia;//图书的单价
  private String imageUrl;//图书的封面
  private String name;//类型名称
  private String num;//图书的数量
  private String orderNo;//排序号
  private String press;//出版社
  private String updateDateTime;//图书的更新时间
  private Integer bookTypeId;//图书类型的id
  private BookType bookType;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getBianhao() {
    return bianhao;
  }

  public void setBianhao(String bianhao) {
    this.bianhao = bianhao;
  }

  /**  
 * 获取danjia
 */
public String getDanjia() {
	return danjia;
}

/**  
 * 设置danjia
 */
public void setDanjia(String danjia) {
	this.danjia = danjia;
}

public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }


  public Integer getBookTypeId() {
    return bookTypeId;
  }

  public void setBookTypeId(Integer bookTypeId) {
    this.bookTypeId = bookTypeId;
  }

/**  
 * 获取createDateTime
 */
public String getCreateDateTime() {
	return createDateTime;
}

/**  
 * 设置createDateTime
 */
public void setCreateDateTime(String createDateTime) {
	this.createDateTime = createDateTime;
}

/**  
 * 获取num
 */
public String getNum() {
	return num;
}

/**  
 * 设置num
 */
public void setNum(String num) {
	this.num = num;
}

/**  
 * 获取orderNo
 */
public String getOrderNo() {
	return orderNo;
}

/**  
 * 设置orderNo
 */
public void setOrderNo(String orderNo) {
	this.orderNo = orderNo;
}

/**  
 * 获取updateDateTime
 */
public String getUpdateDateTime() {
	return updateDateTime;
}

/**  
 * 设置updateDateTime
 */
public void setUpdateDateTime(String updateDateTime) {
	this.updateDateTime = updateDateTime;
}

/**  
 * 获取bookType
 */
public BookType getBookType() {
	return bookType;
}

/**  
 * 设置bookType
 */
public void setBookType(BookType bookType) {
	this.bookType = bookType;
}

/**************************************************
 * 功能实现描述：
 * @return
 * @author create: ChangZiYang 2019年9月18日 上午9:17:40
 * @author modify: 
 */
@Override
public String toString() {
	return "Book [id=" + id + ", author=" + author + ", bianhao=" + bianhao + ", createDateTime=" + createDateTime
			+ ", danjia=" + danjia + ", imageUrl=" + imageUrl + ", name=" + name + ", num=" + num + ", orderNo="
			+ orderNo + ", press=" + press + ", updateDateTime=" + updateDateTime + ", bookTypeId=" + bookTypeId
			+ ", bookType=" + bookType + "]";
}

  
}
