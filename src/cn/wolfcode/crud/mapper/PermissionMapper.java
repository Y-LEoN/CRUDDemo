package cn.wolfcode.crud.mapper;

import java.util.List;

import cn.wolfcode.crud.domain.Permission;

public interface PermissionMapper {

	void deleteById(Long id);
	
	List<Permission> selectAll();
	
}
