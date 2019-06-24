package cn.wolfcode.crud.query;

import lombok.Data;

@Data
public class EmployeeQueryObject extends QueryObject {

	private String keyword;
	private Long deptId;

}
