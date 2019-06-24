package cn.wolfcode.crud.query;

import java.util.Collections;
import java.util.List;

import lombok.Data;
@Data
public class PageResult {
	// 需要从数据库查询的参数
	private List<?> data; // 当前页数据
	private int rows; // 总条数

	// 需要用户控制的参数
	private int pageSize; // 一页显示多少条数据
	private int currentPage; // 当前页

	// 需要程序猿计算的参数
	private int prevPage; // 上一页
	private int nextPage; // 下一页
	private int totalPage; // 总页（末页）

	public PageResult(int pageSize) {
		this(Collections.EMPTY_LIST, 0, pageSize, 1);
	}

	public PageResult(List<?> data, int rows, int pageSize, int currentPage) {
		this.data = data;
		this.rows = rows;
		this.pageSize = pageSize;
		this.currentPage = currentPage;

		// 上一页计算：
		prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
		// 总页数计算
		totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
		// 下一页计算
		nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}



}
