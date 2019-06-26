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

import cn.wolfcode.crud.domain.Permission;
import cn.wolfcode.crud.query.PageResult;
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
  		case "generate":
			generateService(req, resp);
			break;
  		default:
  			listService(req, resp);
  			break;
  		}

  	}

  	private void generateService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
  		List<Permission> list = permissionService.selectAll();
		List<String> header = new ArrayList<String>();
		header.add("编号");
		header.add("权限名称");
		header.add("权限表达式");

		String name = "permission";
		permissionService.generateExc(name,header,list);
		PrintWriter out = resp.getWriter();
		out.write("<script language='javascript'>alert('导出成功,数据存放于d盘根目录中');  window.location ='./permission'; </script>");
		
	}

	private void listService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageNum = 1;
		String pageNumStr = req.getParameter("pageNum");
//		pageNum = (StringUtil.hasLength(pageNumStr) && Integer.parseInt(pageNumStr)>0) ? Integer.parseInt(pageNumStr) : 1;
		if(StringUtil.hasLength(pageNumStr) && Integer.parseInt(pageNumStr)>0) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		Page<Object> startPage = PageHelper.startPage(pageNum, 5);
		
  		List<Permission> roleList = permissionService.selectAll();
  		int rows = (int)startPage.getTotal();
  		PageResult pageInfo = new PageResult(roleList, rows, 5, pageNum);
  		req.setAttribute("pageInfo", pageInfo);
  		req.getRequestDispatcher("/WEB-INF/permission/list.jsp").forward(req, resp);
  	}

  	private void deleteService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		String id = req.getParameter("id");
  		if (StringUtil.hasLength(id)) {
  			permissionService.deleteById(Long.valueOf(id));
  		}
  		resp.sendRedirect(req.getContextPath()+"/permission");
  	}
}
