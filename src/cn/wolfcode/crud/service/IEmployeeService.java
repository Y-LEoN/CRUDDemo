package cn.wolfcode.crud.service;

import java.util.List;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

public interface IEmployeeService {
  

	  void deleteById(Long id);
	  
	  void saveOrUpdate(Employee emp);
	  
	  Employee selectById(Long id);
	  
	  List<Employee> selectAll();

	PageResult selectByCondition(QueryObject qo);

	void generateExc(String name, List<String> header, List<Employee> list);
}
