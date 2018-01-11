package com.example.utils.domain;

import java.io.Serializable;

/****
* @Description: 分页基类
* @Title: BasePage.java  
 */
public class BasePage implements Serializable {
	/** 
	* @Fields serialVersionUID :
	*/ 
	private static final long serialVersionUID = -5105728849671921341L;
	
	/***总记录数*/
	private int count;
	
	/***其实位置*/
	private int page;
	
	private int pageNo;
	
	/***默认*/
	private int pageSize = 10;
	
	public int getCount() {
		return count;
	}
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}
