package cn.wolfcode.crud.mapper;

import java.util.List;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.QueryObject;

public interface EmployeeMapper {

	void insert(Employee emp);

	void deleteById(Long id);

	void updateById(Employee emp);

	Employee selectById(Long id);

	List<Employee> selectAll();

	int selectRowsByCondition(QueryObject qo);

	List<Employee> selectDataByCondition(QueryObject qo);

}
