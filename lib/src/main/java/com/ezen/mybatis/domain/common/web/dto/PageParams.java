package com.ezen.mybatis.domain.common.web.dto;


/**
 * 페이지 계산에 필요한 정보들 포장
 * @author 홍재헌
 */
public class PageParams {
	
	private int elementSize;    /** 페이지에 보여지는 목록 갯수 */
	private int pageSize;       /** 페이지에 보여지는 페이지 갯수 */
	private int requestPage;    /** 사용자 요청 페이지 */
	private int searchValue; 	/** 검색된 값 목록 갯수 */
	private int rowCount;       /** 테이블 목록 갯수 */
	
	public PageParams() {
		this(10, 10, 1, 0);
	}
	
	public PageParams(int elementSize, int pageSize, int requestPage, int rowCount) {
		this.elementSize = elementSize;
		this.pageSize = pageSize;
		this.requestPage = requestPage;
		this.rowCount = rowCount;
	}

	public int getElementSize() {
		return elementSize;
	}

	public void setElementSize(int elementSize) {
		this.elementSize = elementSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(int searchValue) {
		this.searchValue = searchValue;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public String toString() {
		return "PageParams [elementSize=" + elementSize + ", pageSize=" + pageSize + ", requestPage=" + requestPage
				+ ", rowCount=" + rowCount + "]";
	}
}
