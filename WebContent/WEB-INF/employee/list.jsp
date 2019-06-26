<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>叩丁狼教育-JavaWEB综合案例</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/header.jsp"%>

<script type="text/javascript">  
  function goPage(pageNo){
	  var currentPage = document.getElementById("currentPage");	 
	    currentPage.value = pageNo;
	   $("#searchForm").submit();
	    
  }
</script>
</head>
<body>
	<div class="container " style="margin-top: 20px">
		<%@include file="../common/top.jsp"%>
		<br /> <br />
		<div class="row">
			<div class="col-sm-3">
				<%@include file="../common/menu.jsp"%>
			</div>
			<div class="col-sm-9">
				<div class="row"></div>
				<!--高级查询--->
				<a href="${pageContext.request.contextPath}/employee?cmd=input" class="btn btn-success">添加</a>
				<form id="searchForm" action="${pageContext.request.contextPath}/employee" method="post">
					<input type="hidden" name="currentPage" id="currentPage">
					<div>
						<label for="keyword">关键字:</label> <input type="text" id="keyword"
							name="keyword" value="${qo.keyword }" placeholder="请输入姓名/邮箱">

						<label for="dept"> 部门:</label> 
						
						<select id="dept" name="deptId">
							<option value="-1">全部</option>
							<c:forEach items="${depts }" var="dept">
							   <option value="${dept.id }">${dept.name }</option>
							</c:forEach> 
						</select>
						<script type="text/javascript">
							$("#dept").val(${qo.deptId})
						</script>
						<button id="queryBtn" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
					</div>  
				</form>

				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>员工姓名</th>
							<th>员工邮箱</th>
							<th>员工年龄</th>
							<th>所属部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach items="${pageInfo.data}" var="entity" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${entity.name}</td>
							<td>${entity.email}</td>
							<td>${entity.age}</td>
							<td>${entity.dept.name}</td>
							<td><a class="btn btn-info btn-xs"
								href="${pageContext.request.contextPath}/employee?cmd=input&id=${entity.id}"> <span
									class="glyphicon glyphicon-pencil"></span>编辑
							</a> <a href="${pageContext.request.contextPath}/employee?cmd=delete&id=${entity.id}"
								class="btn btn-danger btn-xs"> <span
									class="glyphicon glyphicon-trash"></span>删除
							</a></td>
						</tr>
					</c:forEach>

					<tr align="center">
						<td colspan="6"><a href="javascript:goPage(1);">首页</a> <a
							href="javascript:goPage(${pageInfo.prevPage });"> 上一页</a> <a
							href="javascript:goPage(${pageInfo.nextPage });"> 下一页</a> <a
							href="javascript:goPage(${pageInfo.totalPage});"> 末页</a> 总共
							${pageInfo.rows } 条数据 当前 ${pageInfo.currentPage }/${pageInfo.totalPage}
							页</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
