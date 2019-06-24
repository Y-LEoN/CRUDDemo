package cn.wolfcode.crud.service;

import java.util.List;

import cn.wolfcode.crud.domain.Department;

public interface IDepartmentService {
 
	 
	  List<Department> selectAll();

	void deleteById(Long id);

	void saveOrUpdate(Department dept);
}
