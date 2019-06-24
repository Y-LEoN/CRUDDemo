package cn.wolfcode.crud.domain;

import lombok.Data;

@Data
public class Employee {
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Department dept;
	
	 
}
