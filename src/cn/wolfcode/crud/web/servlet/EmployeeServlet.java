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
		// ����post��������
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

		// ����ҵ��� ��������޸�����
		employeeService.saveOrUpdate(emp);
		// ��תҳ��
		resp.sendRedirect("/employee");

	}

	private void listService_bak(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("come in");
		// ��ȡԱ����Ϣ
		List<Employee> list = employeeService.selectAll();
		// ��Ա����Ϣ����request�������
		req.setAttribute("list", list);
		// ��ת��list.jspҳ��
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
        // ��ȡ���в�����Ϣ
		List<Department> deptList = departmentService.selectAll();
		// �Ѳ�����Ϣ����rquest
		req.setAttribute("depts", deptList);
		// ������������
		req.setAttribute("qo", qo);
		// ��ȡԱ����Ϣ
		PageResult result = employeeService.selectByCondition(qo);

		// ��Ա����Ϣ����request�������
		req.setAttribute("pageInfo", result);
		// ��ת��list.jspҳ��
		req.getRequestDispatcher("/WEB-INF/employee/list.jsp").forward(req, resp);
	}

	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (hasLength(id)) {
			employeeService.deleteById(Long.valueOf(id));
		}
		// ��תlist.jsp
		// ��תҳ��
		resp.sendRedirect("/employee");
	}

	private void inputService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡ���еĲ�����Ϣ
		List<Department> list = departmentService.selectAll();
		// �Ѳ��ŵ���Ϣ���뵽request������
		req.setAttribute("depts", list);
		// ��ȡԱ����id
		String id = req.getParameter("id");
		System.out.println(id);
		if (hasLength(id)) {
			// ����ҵ����ȡԱ����Ϣ
			Employee employee = employeeService.selectById(Long.valueOf(id));
			// ��Ա����Ϣ����request��
			req.setAttribute("emp", employee);
		}
		req.getRequestDispatcher("/WEB-INF/employee/input.jsp").forward(req, resp);

	}

	private boolean hasLength(String str) {
		return str != null && !str.trim().equals("");
	}

}
