package cn.wolfcode.crud.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.mapper.RoleMapper;
import cn.wolfcode.crud.service.RoleService;
import cn.wolfcode.crud.util.MyBastisUtil;
import cn.wolfcode.crud.util.generateExcel;

public class RoleServiceImpl implements RoleService {
	
	private RoleMapper roleMapper;
	

	@Override
	public List<Role> list() {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		return roleMapper.list();
	}

	@Override
	public void deleteById(Long id) {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		roleMapper.deleteById(id);
	}

	@Override
	public void updateById(Role role) {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		roleMapper.updateById(role);
	}

	@Override
	public void insert(Role role) {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		roleMapper.insert(role);
	}

	@Override
	public Role getById(Long id) {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		return roleMapper.getById(id);
	}

	@Override
	public void saveOrUpdate(Role role) {
		roleMapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		if(role.getId() != null) {
			roleMapper.updateById(role);
			return ;
		}
		roleMapper.insert(role);
	}

	@Override
	public void generateExc(String sheetname, List<String> header, List<Role> list) {
		List<List<String>> body1 = new ArrayList<>();
		Role role;
		List<String> templist = new ArrayList<String>();
		for(int i = 0;i<list.size();i++) {
			role = list.get(i);
			templist = new ArrayList<String>();
			templist.add(String.valueOf(role.getId()));
			templist.add(role.getName());
			templist.add(role.getSn());
			body1.add(templist);
		}
	    try (
	    		OutputStream out = new FileOutputStream("d:/"+sheetname+".xls") // 输出目的地
	    ) {
	    	generateExcel.generateExcel(sheetname, header, body1, out);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

}
