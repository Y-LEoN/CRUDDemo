<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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
				<div class="row col-sm-10">
					<form class="form-horizontal" action="${pageContext.request.contextPath}/role?cmd=saveOrUpdate"
						method="post" id="editForm">
						<input type="hidden" value="${role.id}" name="id">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">角色名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name"
									value="${role.name }" placeholder="请输入角色的名称">
							</div>
						</div>
						<div class="form-group">
							<label for="sn" class="col-sm-2 control-label">角色编号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="sn"
									value="${role.sn }" name="sn" placeholder="请输入角色编号">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-6">
								<button id="btn_submit" type="submit" class="btn btn-default">保存</button>
								<button type="reset" class="btn btn-default">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>