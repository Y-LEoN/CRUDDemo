package cn.wolfcode.crud.test;

import org.junit.Test;

import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.service.impl.EmployeeServiceImpl;

public class EmployeeServiceTest {

	private IEmployeeService service = new EmployeeServiceImpl();

//	@Test
//	public void testInsert() {
//		 Employee employee = new Employee();
//		 employee.setAge(12);
//		 employee.setEmail("asdfas");
//		 employee.setName("bbbb");
//		 employee.setPassword("asdfeeeee");
//		 Department dept = new Department();
//		 dept.setName("ddddd");
//		 dept.setId(333L);
//		 dept.setSn("wsss");
//		 employee.setDept(dept);
//		
//		 service.insert(employee);
//	}

	@Test
	public void testDeleteById() {
		  service.deleteById(26L); 
	}

//	@Test
//	public void testUpdateById() {
//		Employee employee = new Employee();
//		employee.setId(26L);
//		 employee.setAge(1222);
//		 employee.setEmail("222");
//		 employee.setName("2222");
//		 employee.setPassword("2222");
//		 Department dept = new Department();
//		 dept.setName("2222");
//		 dept.setId(2222L);
//		 dept.setSn("2222");
//		 employee.setDept(dept);
//		
//		 service.updateById(employee);
//	}

	@Test
	public void testSelectById() {
		Employee emp = service.selectById(1L);
		System.out.println(emp);
	}

}
