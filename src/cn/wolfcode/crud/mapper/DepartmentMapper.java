package cn.wolfcode.crud.mapper;

import java.util.List;

import cn.wolfcode.crud.domain.Department;

public interface DepartmentMapper { 
	  
	 List<Department> selectAll();

	void deleteById(Long id);

	void insert(Department dept);

	void updateById(Department dept);

	Department selectById(Long id);
	  
}
