package cn.wolfcode.crud.service;

import java.util.List;

import cn.wolfcode.crud.domain.Employee;

public interface IEmployeeService {
  
	  void deleteById(Long id);
	  
	  void saveOrUpdate(Employee emp);
	  
	  Employee selectById(Long id);
	  
	  List<Employee> selectAll();
}
