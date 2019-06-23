package cn.wolfcode.crud.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
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

}
