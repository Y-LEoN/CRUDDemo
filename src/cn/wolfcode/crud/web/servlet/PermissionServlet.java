package cn.wolfcode.crud.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.service.IPermissionService;
import cn.wolfcode.crud.service.impl.PermissionServiceImpl;
import cn.wolfcode.crud.util.StringUtil;

/**
 * Servlet implementation class PermissionServlet
 */
@WebServlet("/permission")
public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPermissionService permissionService = new PermissionServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PermissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
  	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		String cmd = req.getParameter("cmd");
  		cmd = cmd == null ? "" : cmd;
  		switch (cmd) {
  		case "delete":
  			deleteService(req, resp);
  			break;
  		default:
  			listService(req, resp);
  			break;
  		}

  	}

  	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          // ��ȡ����Ȩ����Ϣ
  		List<Permission> list = permissionService.selectAll();
  		// �Ѳ�����Ϣ����rquest
  		req.setAttribute("perm", list);
  		// ��ת��list.jspҳ��
  		req.getRequestDispatcher("/WEB-INF/permission/list.jsp").forward(req, resp);
  	}

  	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		String id = req.getParameter("id");
  		if (StringUtil.hasLength(id)) {
  			permissionService.deleteById(Long.valueOf(id));
  		}
  		// ��תҳ��
  		resp.sendRedirect(req.getContextPath()+"/permission");
  	}
}
