package cn.wolfcode.crud.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.query.EmployeeQueryObject;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.util.MyBastisUtil;

public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeMapper employeeMapper;

	@Override
	public void deleteById(Long id) {
		SqlSession session = MyBastisUtil.openSession();
		employeeMapper = session.getMapper(EmployeeMapper.class);
		employeeMapper.deleteById(id);
		session.close();
	}

	@Override
	public void saveOrUpdate(Employee emp) {
		SqlSession session = MyBastisUtil.openSession();
		employeeMapper = session.getMapper(EmployeeMapper.class);
		if (emp.getId() == null) {
			employeeMapper.insert(emp);
		} else {
			employeeMapper.updateById(emp);
		}
		session.close();
	}

	@Override
	public Employee selectById(Long id) {
		SqlSession session = MyBastisUtil.openSession();
		employeeMapper = session.getMapper(EmployeeMapper.class);
		System.out.println(employeeMapper);
		Employee emp = employeeMapper.selectById(id);
		session.close();
		return emp;
	}

	@Override
	public List<Employee> selectAll() {
		SqlSession session = MyBastisUtil.openSession();
		employeeMapper = session.getMapper(EmployeeMapper.class);
		List<Employee> list = employeeMapper.selectAll();
		session.close();
		return list;
	}

	@Override
	public PageResult selectByCondition(QueryObject qo) {
		SqlSession session = MyBastisUtil.openSession();
		employeeMapper = session.getMapper(EmployeeMapper.class);
		int rows = employeeMapper.selectRowsByCondition(qo);
		if(rows == 0) {
		   return new PageResult(qo.getPageSize());
		} 
		List<Employee> list =  employeeMapper.selectDataByCondition(qo);
		 
		return new PageResult(list, rows, qo.getPageSize(), qo.getCurrentPage());
	}

}
