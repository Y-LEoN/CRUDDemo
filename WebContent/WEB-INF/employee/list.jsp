<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>叩丁狼教育-JavaWEB综合案例</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../common/header.jsp" %> 
</head>
<body>

<form class="form-inline" id="searchForm" action="${pageContext.request.contextPath}/department" method="post">
    <div class="container " style="margin-top: 20px">
        <%@include file="../common/top.jsp" %>
        <br/>
        <br/>
        <div class="row">
            <div class="col-sm-3">
                <%@include file="../common/menu.jsp" %>
            </div>
            <div class="col-sm-9">
                <div class="row">

                </div>
                <!--高级查询--->
                <a href="/employee?cmd=input" class="btn btn-success">添加</a>

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
                    <c:forEach items="${list}" var="entity" varStatus="vs">
                        <tr>
                            <td>${vs.count}</td>
                            <td>${entity.name}</td>
                            <td>${entity.email}</td>
                            <td>${entity.age}</td>
                            <td>${entity.dept.name}</td>
                            <td>
                                <a class="btn btn-info btn-xs" href="${pageContext.request.contextPath}/employee?cmd=input&id=${entity.id}">
                                    <span class="glyphicon glyphicon-pencil"></span>编辑
                                </a>
                                <a href="${pageContext.request.contextPath}/employee?cmd=delete&id=${entity.id}" class="btn btn-danger btn-xs">
                                    <span class="glyphicon glyphicon-trash"></span>删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table> 
            </div>
        </div>
    </div>
</form>
</body>
</html>
