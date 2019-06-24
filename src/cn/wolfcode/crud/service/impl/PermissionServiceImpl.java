package cn.wolfcode.crud.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.mapper.PermissionMapper;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.crud.util.MyBastisUtil;

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

}
