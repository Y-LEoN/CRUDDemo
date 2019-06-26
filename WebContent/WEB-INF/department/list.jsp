<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>叩丁狼教育-JavaWEB综合案例</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/header.jsp"%>

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
				<a href="${pageContext.request.contextPath}/department?cmd=input" class="btn btn-success">添加</a>
				<a href="${pageContext.request.contextPath}/department?cmd=generate" class="btn btn-success">导出数据</a>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>部门名称</th>
							<th>部门编号</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach items="${depts}" var="entity" varStatus="vs">
						<tr>
							<td>${entity.id}</td>
							<td>${entity.name}</td>
							<td>${entity.sn}</td>
							<td><a class="btn btn-info btn-xs"
								href="${pageContext.request.contextPath}/department?cmd=input&id=${entity.id}"> <span
									class="glyphicon glyphicon-pencil"></span>编辑
							</a> <a href="${pageContext.request.contextPath}/department?cmd=delete&id=${entity.id}"
								class="btn btn-danger btn-xs"> <span
									class="glyphicon glyphicon-trash"></span>删除
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
