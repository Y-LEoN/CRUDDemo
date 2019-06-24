package cn.wolfcode.crud.service;

import java.util.List;

import cn.wolfcode.crud.domain.Role;

public interface RoleService {
	
	List<Role> list();
	
	void deleteById(Long id);
	
	void updateById(Role role);
	
	void insert(Role role);
	
	Role getById(Long id);
	
	void saveOrUpdate(Role role);
	

}
