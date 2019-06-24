package cn.wolfcode.crud.mapper;

import java.util.List;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.QueryObject;

public interface RoleMapper {
	
	List<Role> list();
	
	void deleteById(Long id);
	
	void updateById(Role role);
	
	void insert(Role role);
	
	Role getById(Long id);

	// 根据条件查询对应的数据
//	List<Role> selectDataByCondition(QueryObject qo);
	

}
