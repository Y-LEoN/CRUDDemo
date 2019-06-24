package cn.wolfcode.crud.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.service.IDepartmentService;
import cn.wolfcode.crud.service.impl.DepartmentServiceImpl;
import cn.wolfcode.crud.util.StringUtil;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDepartmentService departmentService = new DepartmentServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String sn = req.getParameter("sn");
		Department dept = new Department();
		
		if (StringUtil.hasLength(name)) {
			dept.setName(name);
		}
		if (StringUtil.hasLength(sn)) {
			dept.setSn(sn);
		}

		if (StringUtil.hasLength(id)) {
			dept.setId(Long.parseLong(id));
		}
		// 调用业务层 保存或者修改数据
		departmentService.saveOrUpdate(dept);
		// 跳转页面
		resp.sendRedirect(req.getContextPath()+"/department");

	}
//
//	private void listService_bak(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		System.out.println("come in");
//		// 获取员工信息
//		List<Employee> list = employeeService.selectAll();
//		// 把员工信息放入request域对象中
//		req.setAttribute("list", list);
//		// 跳转到list.jsp页面
//		req.getRequestDispatcher("/WEB-INF/employee/list.jsp").forward(req, resp);
//	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取所有部门信息
		List<Department> deptList = departmentService.selectAll();
		// 把部门信息存入rquest
		req.setAttribute("depts", deptList);
		// 跳转到list.jsp页面
		req.getRequestDispatcher("/WEB-INF/department/list.jsp").forward(req, resp);
	}

	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.hasLength(id)) {
			departmentService.deleteById(Long.valueOf(id));
		}
		// 跳转list.jsp
		// 跳转页面
		resp.sendRedirect(req.getContextPath()+"/department");
	}

	private void inputService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取所有的部门信息
		List<Department> list = departmentService.selectAll();
		// 获取部门的id
		String id = req.getParameter("id");
		System.out.println(id);
		if (StringUtil.hasLength(id)) {
			for(int i = 0;i<list.size();i++) {
				if(id.equals(String.valueOf(list.get(i).getId()))) {
					req.setAttribute("dept", list.get(i));
					break;
				}
			}
			
		}
		req.getRequestDispatcher("/WEB-INF/department/input.jsp").forward(req, resp);

	}
}
