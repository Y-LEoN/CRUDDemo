package cn.wolfcode.crud.service.impl;

import java.util.List;

import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.mapper.RoleMapper;
import cn.wolfcode.crud.service.RoleService;
import cn.wolfcode.crud.util.MyBastisUtil;

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

}
