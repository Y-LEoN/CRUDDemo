package cn.wolfcode.crud.query;

import java.util.Collections;
import java.util.List;

import lombok.Data;
@Data
public class PageResult {
	private List<?> data;
	private int rows; 

	private int pageSize;
	private int currentPage;

	private int prevPage; 
	private int nextPage; 
	private int totalPage; 

	public PageResult(int pageSize) {
		this(Collections.EMPTY_LIST, 0, pageSize, 1);
	}

	public PageResult(List<?> data, int rows, int pageSize, int currentPage) {
		this.data = data;
		this.rows = rows;
		this.pageSize = pageSize;
		this.currentPage = currentPage;

		prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
		totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
		nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}
}
