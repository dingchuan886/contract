package com.poly.bean;

import java.util.ArrayList;
import java.util.List;
/*
 * 
 * 封装分页实体类
 * 
 * */
public class PageResult<T> {
	private int pageNum = 1;//当前页数
	private int pageSize = 10;//数据条数
	private List<T> list = new ArrayList<T>();//存放的详细数据
	private int totalPages = 0;//最大页数
	private int startIndex;//limit分页开始数
	private int totalRecords;//记录总条数
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public PageResult(int pageNum,int totalRecords) {
		
		this.pageNum = pageNum;
		this.totalRecords = totalRecords;
		this.startIndex = (this.pageNum - 1)*this.pageSize;
		this.totalPages = (this.totalRecords + this.pageSize - 1)/this.pageSize;
	}
	
}
