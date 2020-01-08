package com.xst.project.pojo;


/**
 * 后台用户实体类
 */
public class User {

  private Integer id;//用户id
  private String createDateTime;//用户创建的时间
  private String name;//用户名
  private String orderNo;//排序号
  private String pwd;//密码
  private String remark;//备注
  private String trueName;//真实姓名
  private String updateDateTime;//用户更新的时间
  private Integer roleId; //角色的id
  private Role role;




  /**  
 * 获取roleId
 */
public Integer getRoleId() {
	return roleId;
}

/**  
 * 设置roleId
 */
public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}

/**  
 * 获取role
 */
public Role getRole() {
	return role;
}

/**  
 * 设置role
 */
public void setRole(Role role) {
	this.role = role;
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

public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getTrueName() {
    return trueName;
  }

  public void setTrueName(String trueName) {
    this.trueName = trueName;
  }


@Override
public String toString() {
	return "User [id=" + id + ", createDateTime=" + createDateTime + ", name=" + name + ", orderNo=" + orderNo
			+ ", pwd=" + pwd + ", remark=" + remark + ", trueName=" + trueName + ", updateDateTime=" + updateDateTime
			+ ", roleId=" + roleId + ", role=" + role + "]";
}









}
