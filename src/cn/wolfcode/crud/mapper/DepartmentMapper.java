package cn.wolfcode.crud.mapper;

import java.util.List;

import cn.wolfcode.crud.domain.Department;

public interface DepartmentMapper { 
	  
	  List<Department> selectAll();
	  
}
