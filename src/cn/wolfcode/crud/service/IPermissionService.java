package cn.wolfcode.crud.service;

import java.util.List;

import cn.wolfcode.crud.domain.Permission;

public interface IPermissionService {
	
	void deleteById(Long id);
	
	List<Permission> selectAll();
	
}
