package cn.wolfcode.crud.query;

import java.util.Collections;
import java.util.List;

import lombok.Data;
@Data
public class PageResult {
	// ��Ҫ�����ݿ��ѯ�Ĳ���
	private List<?> data; // ��ǰҳ����
	private int rows; // ������

	// ��Ҫ�û����ƵĲ���
	private int pageSize; // һҳ��ʾ����������
	private int currentPage; // ��ǰҳ

	// ��Ҫ����Գ����Ĳ���
	private int prevPage; // ��һҳ
	private int nextPage; // ��һҳ
	private int totalPage; // ��ҳ��ĩҳ��

	public PageResult(int pageSize) {
		this(Collections.EMPTY_LIST, 0, pageSize, 1);
	}

	public PageResult(List<?> data, int rows, int pageSize, int currentPage) {
		this.data = data;
		this.rows = rows;
		this.pageSize = pageSize;
		this.currentPage = currentPage;

		// ��һҳ���㣺
		prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
		// ��ҳ������
		totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
		// ��һҳ����
		nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}



}
