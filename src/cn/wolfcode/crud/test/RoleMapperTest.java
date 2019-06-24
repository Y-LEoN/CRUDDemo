package cn.wolfcode.crud.test;

import java.util.List;

import org.junit.Test;

import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.mapper.RoleMapper;
import cn.wolfcode.crud.util.MyBastisUtil;

public class RoleMapperTest {

	@Test
	public void testRoleList() {
		RoleMapper mapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		List<Role> list = mapper.list();
		if(list!=null) {
			list.parallelStream().forEach(action -> System.out.println(action));
		}
	}
	
	@Test
	public void testRoleDeleteById() {
		RoleMapper mapper = MyBastisUtil.openSession().getMapper(RoleMapper.class);
		mapper.deleteById(13L);
	}
	
}
