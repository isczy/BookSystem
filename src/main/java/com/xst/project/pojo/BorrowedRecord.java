package com.xst.project.pojo;

/********************************************************************
 * @brief      借阅记录表实体类
 * @version    0.1
 * @date       2019年9月19日 上午8:51:00
 * @author     ChangZiYang
 ********************************************************************/
public class BorrowedRecord {
	
	private Integer id;
	private String username;//读者号
	private String userTrueName;//真实姓名
	private String bianhao;//图书编号
	private String bookName;//书名
	private Integer state;//状态，0未还，1已还
	private String startTime;//借阅时间
	private String endTime;//归还时间
	private int bookId;
	/**  
	 * 获取id
	 */
	public Integer getId() {
		return id;
	}
	/**  
	 * 设置id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**  
	 * 获取username
	 */
	public String getUsername() {
		return username;
	}
	/**  
	 * 设置username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**  
	 * 获取userTrueName
	 */
	public String getUserTrueName() {
		return userTrueName;
	}
	/**  
	 * 设置userTrueName
	 */
	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}
	/**  
	 * 获取bianhao
	 */
	public String getBianhao() {
		return bianhao;
	}
	/**  
	 * 设置bianhao
	 */
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}
	/**  
	 * 获取bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**  
	 * 设置bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**  
	 * 获取state
	 */
	public Integer getState() {
		return state;
	}
	/**  
	 * 设置state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**  
	 * 获取startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**  
	 * 设置startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**  
	 * 获取endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**  
	 * 设置endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**  
	 * 获取bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**  
	 * 设置bookId
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**************************************************
	 * 功能实现描述：
	 * @return
	 * @author create: ChangZiYang 2019年9月19日 下午2:15:00
	 * @author modify: 
	 */
	@Override
	public String toString() {
		return "BorrowedRecord [id=" + id + ", username=" + username + ", userTrueName=" + userTrueName + ", bianhao="
				+ bianhao + ", bookName=" + bookName + ", state=" + state + ", startTime=" + startTime + ", endTime="
				+ endTime + ", bookId=" + bookId + "]";
	}

	
	
}
