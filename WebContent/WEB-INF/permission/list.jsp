<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>叩丁狼教育-JavaWEB综合案例</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
	function goPage(pageNo) {
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
				<a href="${pageContext.request.contextPath}/permission"
					class="btn btn-success">∞重新加载</a>
				<form id="searchForm"
					action="${pageContext.request.contextPath}/permission"
					method="post">
					<input type="hidden" name="pageNum" id="currentPage">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>权限名称</th>
								<th>权限表达式</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:forEach items="${pageInfo.data}" var="entity" varStatus="vs">
							<tr>
								<td>${entity.id}</td>
								<td>${entity.name}</td>
								<td>${entity.expression}</td>
								<td><a class="btn btn-danger btn-xs"
									href="${pageContext.request.contextPath}/permission?cmd=delete&id=${entity.id}">
										<span class="glyphicon glyphicon-pencil"></span>删除
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
				</form>
			</div>
		</div>
	</div>
</body>
</html>
