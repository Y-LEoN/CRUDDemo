package cn.wolfcode.crud.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Employee;
import cn.wolfcode.crud.query.EmployeeQueryObject;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.service.IEmployeeService;
import cn.wolfcode.crud.service.impl.DepartmentServiceImpl;
import cn.wolfcode.crud.service.impl.EmployeeServiceImpl;
import cn.wolfcode.crud.util.StringUtil;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IEmployeeService employeeService = new EmployeeServiceImpl();
	private IDepartmentService departmentService = new DepartmentServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 处理post请求乱码
		//req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		cmd = cmd == null ? "" : cmd;
		switch (cmd) {
		case "input":
			inputService(req, resp);
			break;
		case "delete":
			deleteService(req, resp);
			break;
		case "saveOrUpdate":
			saveOrUpdateService(req, resp);
			break;
		default:
			listService(req, resp);
			break;
		}

	}

	private void saveOrUpdateService(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nameStr = req.getParameter("name");
		String emailStr = req.getParameter("email");
		String ageStr = req.getParameter("age");
		String deptIdStr = req.getParameter("deptId");
		String id = req.getParameter("id");
		String pwd = req.getParameter("password");
		Employee emp = new Employee();
		emp.setName(nameStr);
		emp.setEmail(emailStr);
		emp.setPassword(pwd);

		if (hasLength(ageStr)) {
			emp.setAge(Integer.valueOf(ageStr));
		}
		if (hasLength(deptIdStr)) {
			Department dept = new Department();
			dept.setId(Long.valueOf(deptIdStr));
			emp.setDept(dept);
		}

		if (hasLength(id)) {
			emp.setId(Long.valueOf(id));
		}

		// 调用业务层 保存或者修改数据
		employeeService.saveOrUpdate(emp);
		// 跳转页面
		resp.sendRedirect("/employee");

	}

	private void listService_bak(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("come in");
		// 获取员工信息
		List<Employee> list = employeeService.selectAll();
		// 把员工信息放入request域对象中
		req.setAttribute("list", list);
		// 跳转到list.jsp页面
		req.getRequestDispatcher("/WEB-INF/employee/list.jsp").forward(req, resp);
	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keyword = req.getParameter("keyword");
		String deptId = req.getParameter("deptId");
		String currentPage = req.getParameter("currentPage");

		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword(keyword);
		if (StringUtil.hasLength(deptId)) {
			qo.setDeptId(Long.valueOf(deptId));
		}
		if (StringUtil.hasLength(currentPage)) {
			qo.setCurrentPage(Integer.valueOf(currentPage));
		}
        // 获取所有部门信息
		List<Department> deptList = departmentService.selectAll();
		// 把部门信息存入rquest
		req.setAttribute("depts", deptList);
		// 回显条件数据
		req.setAttribute("qo", qo);
		// 获取员工信息
		PageResult result = employeeService.selectByCondition(qo);

		// 把员工信息放入request域对象中
		req.setAttribute("pageInfo", result);
		// 跳转到list.jsp页面
		req.getRequestDispatcher("/WEB-INF/employee/list.jsp").forward(req, resp);
	}

	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (hasLength(id)) {
			employeeService.deleteById(Long.valueOf(id));
		}
		// 跳转list.jsp
		// 跳转页面
		resp.sendRedirect("/employee");
	}

	private void inputService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取所有的部门信息
		List<Department> list = departmentService.selectAll();
		// 把部门的信息存入到request对象中
		req.setAttribute("depts", list);
		// 获取员工的id
		String id = req.getParameter("id");
		System.out.println(id);
		if (hasLength(id)) {
			// 调用业务层获取员工信息
			Employee employee = employeeService.selectById(Long.valueOf(id));
			// 把员工信息存入request中
			req.setAttribute("emp", employee);
		}
		req.getRequestDispatcher("/WEB-INF/employee/input.jsp").forward(req, resp);

	}

	private boolean hasLength(String str) {
		return str != null && !str.trim().equals("");
	}

}
