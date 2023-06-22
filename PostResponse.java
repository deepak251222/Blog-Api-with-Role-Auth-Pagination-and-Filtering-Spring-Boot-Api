package com.spring;

import java.util.List;

import com.spring.Entity.User;

public class PostResponse {
	
	private List<User> context;
	private int pageNumber;
	private int pageSize;
	private Long totlaElement;
	private int totalPages;
	private Boolean lastPage;
	public PostResponse() {
		super();
	}
	public PostResponse(List<User> context, int pageNumber, int pageSize, Long totlaElement, int totalPages,
			Boolean lastPage) {
		super();
		this.context = context;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totlaElement = totlaElement;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	public List<User> getContext() {
		return context;
	}
	public void setContext(List<User> context) {
		this.context = context;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotlaElement() {
		return totlaElement;
	}
	public void setTotlaElement(Long totlaElement) {
		this.totlaElement = totlaElement;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Boolean getLastPage() {
		return lastPage;
	}
	public void setLastPage(Boolean lastPage) {
		this.lastPage = lastPage;
	}
	

}
