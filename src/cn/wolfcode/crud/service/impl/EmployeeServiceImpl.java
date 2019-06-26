package cn.wolfcode.crud.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.query.EmployeeQueryObject;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.util.MyBastisUtil;
import cn.wolfcode.crud.util.StringUtil;
import cn.wolfcode.crud.util.generateExcel;

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

	@Override
	public void generateExc(String name, List<String> header, List<Employee> list) {
		List<List<String>> body1 = new ArrayList<>();
		Employee emp;
		List<String> templist = new ArrayList<String>();
		System.out.println(list.toString());
		String deptname = null;
		for(int i = 0;i<list.size();i++) {
			emp = list.get(i);
			templist = new ArrayList<String>();
			templist.add(String.valueOf(emp.getId()));
			templist.add(emp.getName());
			templist.add(emp.getEmail());
			templist.add(String.valueOf(emp.getAge()));
			deptname = emp.getDept().getName();
			if(StringUtil.hasLength(deptname)) {
				templist.add(deptname);
			}else {
				templist.add("");
			}
			
			System.out.println((i+1)+":"+templist.toString());
			body1.add(templist);
		}
		System.out.println(2);
	    try (
	    		OutputStream out = new FileOutputStream("d:/"+name+".xls") // 输出目的地
	    ) {
	    	generateExcel.generateExcel(name, header, body1, out);
	    	System.out.println(3);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
