package com.xst.project.pojo;

/**
 * ******************************************************************
 * @brief      菜单实体
 * @version    0.1
 * @date       2019年9月10日 下午3:36:08
 * @author     ChangZiYang
 *******************************************************************
 */
public class Menu {

  private Integer id;//主键id
  
  private String divId;//layui菜单id
  
  private String icon;//图标
  
  private String name;//菜单名称
  
  private String orderNo;//排序号
  
  private Integer pId;//父菜单id 根是-1  然后是自己编id
  
  private String permissions;//对应的shiro权限  user：add   permissions也可以是中文
  
  private Integer state;//菜单节点类型  0根节点close  1叶子节点opend
  
  private Integer type;//默认是0选项卡内打开     1新窗口打开   2弹出窗口打开
  
  private String url;//菜单地址


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getDivId() {
    return divId;
  }

  public void setDivId(String divId) {
    this.divId = divId;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
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
 * 获取pId
 */
public Integer getpId() {
	return pId;
}

/**  
 * 设置pId
 */
public void setpId(Integer pId) {
	this.pId = pId;
}

public Integer getPId() {
    return pId;
  }

  public void setPId(Integer pId) {
    this.pId = pId;
  }


  public String getPermissions() {
    return permissions;
  }

  public void setPermissions(String permissions) {
    this.permissions = permissions;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

/**************************************************
 * 功能实现描述：
 * @return
 * @author create: ChangZiYang 2019年9月16日 下午5:30:29
 * @author modify: 
 */
@Override
public String toString() {
	return "Menu [id=" + id + ", divId=" + divId + ", icon=" + icon + ", name=" + name + ", orderNo=" + orderNo
			+ ", pId=" + pId + ", permissions=" + permissions + ", state=" + state + ", type=" + type + ", url=" + url
			+ "]";
}

}
