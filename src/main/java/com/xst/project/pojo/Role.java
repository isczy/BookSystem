package com.xst.project.pojo;

public class Role {

  private Integer id;
  private String createDateTime;
  private String name;
  private String orderNo;
  private String updateDateTime;
  private String remark;


  /**  
 * 获取remark
 */
public String getRemark() {
	return remark;
}

/**  
 * 设置remark
 */
public void setRemark(String remark) {
	this.remark = remark;
}

public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

/**************************************************
 * 功能实现描述：
 * @return
 * @author create: ChangZiYang 2019年9月12日 上午11:36:17
 * @author modify: 
 */
@Override
public String toString() {
	return "Role [id=" + id + ", createDateTime=" + createDateTime + ", name=" + name + ", orderNo=" + orderNo
			+ ", updateDateTime=" + updateDateTime + ", remark=" + remark + "]";
}




}
