package cn.wolfcode.crud.query;

import lombok.Data;

@Data
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 8; 
	
	public int getStartIndex() {
    	return (currentPage - 1) * pageSize;
    }
}
