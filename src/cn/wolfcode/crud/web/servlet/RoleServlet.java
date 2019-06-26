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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.domain.Role;
import cn.wolfcode.crud.query.PageResult;
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
		case "generate":
			generateService(req, resp);
			break;
		default:
			listService(req, resp);
			break;
		}

	}
    private void generateService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	List<Role> list = roleService.list();
		List<String> header = new ArrayList<String>();
		header.add("编号");
		header.add("角色名称");
		header.add("角色编号");
		String name = "role";
		roleService.generateExc(name,header,list);
		PrintWriter out = resp.getWriter();
		out.write("<script language='javascript'>alert('导出成功,数据存放于d盘根目录中');  window.location ='./role'; </script>");
		
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
		// ����ҵ��� ��������޸�����
		roleService.saveOrUpdate(role);
		// ��תҳ��
		resp.sendRedirect(req.getContextPath()+"/role");

	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageNum = 1;
		String pageNumStr = req.getParameter("pageNum");
//		pageNum = (StringUtil.hasLength(pageNumStr) && Integer.parseInt(pageNumStr)>0) ? Integer.parseInt(pageNumStr) : 1;
		if(StringUtil.hasLength(pageNumStr) && Integer.parseInt(pageNumStr)>0) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		Page<Object> startPage = PageHelper.startPage(pageNum, 5);
		
		List<Role> roles = roleService.list();
  		int rows = (int)startPage.getTotal();
  		PageResult pageInfo = new PageResult(roles, rows, 5, pageNum);
  		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/WEB-INF/role/list.jsp").forward(req, resp);
	}

	//ɾ����ɫ
	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.hasLength(id)) {
			roleService.deleteById(Long.valueOf(id));
		}
		// ��תlist.jsp
		// ��תҳ��
		resp.sendRedirect(req.getContextPath()+"/role");
	}

	// �޸Ľ������
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
