package cn.wolfcode.crud.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import cn.wolfcode.crud.util.generateExcel;

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
		case "generate":
			generateService(req, resp);
			break;
		default:
			listService(req, resp);
			break;
		}

	}
    private void generateService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	List<Department> deptList = departmentService.selectAll();
		List<String> header = new ArrayList<String>();
		header.add("编号");
		header.add("部门名称");
		header.add("部门编号");
		String name = "department";
		departmentService.generateExc(name,header,deptList);
		PrintWriter out = resp.getWriter();
		out.write("<script language='javascript'>alert('导出成功,数据存放于d盘根目录中');  window.location ='./department'; </script>");
		//resp.sendRedirect(req.getContextPath()+"/department");
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
		departmentService.saveOrUpdate(dept);
		resp.sendRedirect(req.getContextPath()+"/department");

	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Department> deptList = departmentService.selectAll();
		req.setAttribute("depts", deptList);

		req.getRequestDispatcher("/WEB-INF/department/list.jsp").forward(req, resp);
	}

	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.hasLength(id)) {
			departmentService.deleteById(Long.valueOf(id));
		}
		resp.sendRedirect(req.getContextPath()+"/department");
	}

	private void inputService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Department dept = null;
		if (StringUtil.hasLength(id)) {
			dept = departmentService.selectById(Long.parseLong(id));
		}
		req.setAttribute("dept", dept);

		req.getRequestDispatcher("/WEB-INF/department/input.jsp").forward(req, resp);

	}
}
