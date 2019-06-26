package cn.wolfcode.crud.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.mapper.DepartmentMapper;
import cn.wolfcode.crud.mapper.EmployeeMapper;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.util.MyBastisUtil;
import cn.wolfcode.crud.util.StringUtil;
import cn.wolfcode.crud.util.generateExcel;

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

	@Override
	public void generateExc(String sheetName, List<String> header, List<Department> body) {
		List<List<String>> body1 = new ArrayList<>();
		Department d;
		List<String> templist = new ArrayList<String>();
		for(int i = 0;i<body.size();i++) {
			d = body.get(i);
			templist = new ArrayList<String>();
			templist.add(String.valueOf(d.getId()));
			templist.add(d.getName());
			templist.add(d.getSn());
			body1.add(templist);
		}
	    try (
	    		OutputStream out = new FileOutputStream("d:/"+sheetName+".xls") // 输出目的地
	    ) {
	    	generateExcel.generateExcel(sheetName, header, body1, out);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public Department selectById(Long id) {
		SqlSession session = MyBastisUtil.openSession();
		departmentMapper = session.getMapper(DepartmentMapper.class);
		Department d = departmentMapper.selectById(id);
		session.close();
		return d;
	}

}
