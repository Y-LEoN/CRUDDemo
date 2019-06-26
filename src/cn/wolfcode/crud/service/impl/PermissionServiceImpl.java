package cn.wolfcode.crud.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.mapper.PermissionMapper;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.crud.util.MyBastisUtil;
import cn.wolfcode.crud.util.StringUtil;
import cn.wolfcode.crud.util.generateExcel;

public class PermissionServiceImpl implements IPermissionService{
	private PermissionMapper permissionMapper;
	@Override
	public void deleteById(Long id) {
		SqlSession session = MyBastisUtil.openSession();
		permissionMapper = session.getMapper(PermissionMapper.class);
		permissionMapper.deleteById(id);
		session.close();
	}

	@Override
	public List<Permission> selectAll() {
		SqlSession session = MyBastisUtil.openSession();
		permissionMapper = session.getMapper(PermissionMapper.class);
		List<Permission> list = permissionMapper.selectAll();
		session.close();
		return list;
	}

	@Override
	public void generateExc(String name, List<String> header, List<Permission> list) {
		List<List<String>> body1 = new ArrayList<>();
		Permission per;
		List<String> templist = new ArrayList<String>();
		for(int i = 0;i<list.size();i++) {
			per = list.get(i);
			templist = new ArrayList<String>();
			templist.add(String.valueOf(per.getId()));
			templist.add(per.getName());
			templist.add(per.getExpression());
			
			body1.add(templist);
		}
	    try (
	    		OutputStream out = new FileOutputStream("d:/"+name+".xls") // 输出目的地
	    ) {
	    	generateExcel.generateExcel(name, header, body1, out);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
		
}
