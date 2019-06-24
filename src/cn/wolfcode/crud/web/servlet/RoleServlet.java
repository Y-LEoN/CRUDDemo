package cn.wolfcode.crud.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.service.RoleService;
import cn.wolfcode.crud.service.impl.RoleServiceImpl;
import cn.wolfcode.crud.util.StringUtil;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private RoleService roleService = new RoleServiceImpl();
	
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
			// 默认显示所有条数
			listService(req, resp);
			break;
		}

	}
    private void saveOrUpdateService(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String sn = req.getParameter("sn");
		Role role = new Role();
		
		if (StringUtil.hasLength(name)) {
			role.setName(name);
		}
		if (StringUtil.hasLength(sn)) {
			role.setSn(sn);
		}

		if (StringUtil.hasLength(id)) {
			role.setId(Long.parseLong(id));
		}
		// 调用业务层 保存或者修改数据
		roleService.saveOrUpdate(role);
		// 跳转页面
		resp.sendRedirect(req.getContextPath()+"/role");

	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取所有部门信息
		List<Role> roles = roleService.list();
		// 把部门信息存入rquest
		req.setAttribute("roles", roles);
		// 跳转到list.jsp页面
		req.getRequestDispatcher("/WEB-INF/role/list.jsp").forward(req, resp);
	}

	//删除角色
	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.hasLength(id)) {
			roleService.deleteById(Long.valueOf(id));
		}
		// 跳转list.jsp
		// 跳转页面
		resp.sendRedirect(req.getContextPath()+"/role");
	}

	// 修改界面回显
	private void inputService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.hasLength(id)) {
			Role role = new Role();
			role = roleService.getById(Long.parseLong(id));
			req.setAttribute("role", role);
		}
		req.getRequestDispatcher("/WEB-INF/role/input.jsp").forward(req, resp);

	}
}
