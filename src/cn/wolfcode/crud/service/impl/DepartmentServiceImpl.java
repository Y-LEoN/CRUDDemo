package cn.wolfcode.crud.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.util.MyBastisUtil;

public class DepartmentServiceImpl implements IDepartmentService {

	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> selectAll() {
		SqlSession session = MyBastisUtil.openSession();
		departmentMapper = session.getMapper(DepartmentMapper.class);
		List<Department> list = departmentMapper.selectAll();
		session.close();
		return list;
	}

	@Override
	public void deleteById(Long id) {
		SqlSession session = MyBastisUtil.openSession();
		departmentMapper = session.getMapper(DepartmentMapper.class);
		departmentMapper.deleteById(id);
		session.close();
		
	}

	@Override
	public void saveOrUpdate(Department dept) {
		SqlSession session = MyBastisUtil.openSession();
		departmentMapper = session.getMapper(DepartmentMapper.class);
		if (dept.getId() == null) {
			departmentMapper.insert(dept);
		} else {
			departmentMapper.updateById(dept);
		}
		session.close();
		
	}

}
