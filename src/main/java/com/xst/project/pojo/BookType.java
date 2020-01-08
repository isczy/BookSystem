package com.xst.project.pojo;

/**
 * ******************************************************************
 * @brief      图书的实体类
 * @version    0.1
 * @date       2019年9月10日 下午3:43:09
 * @author     ChangZiYang
 *******************************************************************
 */
public class BookType {

  private Integer id;//主键id
  private String createDateTime;//创建时间
  private String name;//类型名称
  private String orderNo;//排序号
  private String updateDateTime;//更新时间


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

/**************************************************
 * 功能实现描述：
 * @return
 * @author create: ChangZiYang 2019年9月17日 下午4:53:23
 * @author modify: 
 */
@Override
public String toString() {
	return "BookType [id=" + id + ", createDateTime=" + createDateTime + ", name=" + name + ", orderNo=" + orderNo
			+ ", updateDateTime=" + updateDateTime + "]";
}




 

}
